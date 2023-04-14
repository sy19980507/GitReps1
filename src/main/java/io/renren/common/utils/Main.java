package io.renren.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class Main
{
    private static final SimpleDateFormat sdfWhole = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static boolean isDouble(String s) {
        Pattern pattern = Pattern.compile("[+-]?\\d+(.\\d+)?");
        return pattern.matcher(s).matches();
    }
    public static void main(String args[]) {
        /*Path path = Paths.get("./data/", "data_20220621.txt");
        File file = path.toFile();*/
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("d:/data/data.txt")))) {
            String line = null;
            ArrayList<Double> al = new ArrayList<Double>();
           /* while ((line = br.readLine()) != null) {
                if(isDouble(line))
                al.add(Double.parseDouble(line));
            }*/
            Double [] do1=new Double[]{62.0,77.0,79.0,99.0,98.0,100.0,75.0,20.0,120.0,134.0,159.0,99.0,100.0};
            Double [] do2=new Double[]{18.91,17.34,24.65,20.28,23.63,22.04,18.5,22.86,22.46,17.92,16.19,19.82,18.37};
            Double [] do3=new Double[]{110.0,117.0,78.0,86.0,95.0,100.0,120.0,20.0,89.0,68.0,95.0,78.0,76.0};
            for (double d1:do3
                 ) {
                al.add(d1);
            }
            ArrayList<Double> samplingDataList = new ArrayList<>();
            ArrayList<Double> comparisonDataList = new ArrayList<>();
            System.out.println("开始时间" + sdfWhole.format(new Date()));
            double predict1=predect(al);
            System.out.println(predict1);
            for (int i = 0; i < al.size(); i++) {
                //samplingDataList.add(al.get(i));
                if (i < al.size() / 3 * 2) {
                    samplingDataList.add(al.get(i));
                } else {
                    comparisonDataList.add(al.get(i));
                }
            }
            System.out.println(samplingDataList);
            for (double comData : comparisonDataList) {
                double predict = predect(samplingDataList);
                System.out.println("Predict value=" + predict);
                samplingDataList.add(predict);
				/*System.out.println("Predict error=" + (predict - comData)
						/ comData * 100 + "%");*/
            }
            System.out.println(samplingDataList);
            System.out.println("结束时间" + sdfWhole.format(new Date()));
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 获取预测结果的值
     *
     * @param al
     * @return
     */
    public static double predect(ArrayList<Double> al) {
        double[] data = null;
        data = al.stream().mapToDouble(i -> i).toArray();
        ARIMAModel arima = new ARIMAModel(data);

        ArrayList<int[]> list = new ArrayList<>();
        int period = 7;
        int modelCnt = 3, cnt = 0;// 通过多次预测的平均值作为预测值
        int[] tmpPredict = new int[modelCnt];
        for (int k = 0; k < modelCnt; ++k) {// 控制通过多少组参数进行计算最终的结果
            int[] bestModel = arima.getARIMAModel(period, list,
                    (k == 0) ? false : true);
            if (bestModel.length == 0) {
                tmpPredict[k] = (int) data[data.length - period];
                cnt++;
                break;
            } else {
                int predictDiff = arima.predictValue(bestModel[0],
                        bestModel[1], period);
                tmpPredict[k] = arima.aftDeal(predictDiff, period);
                cnt++;
            }
            list.add(bestModel);
        }
        double sumPredict = 0.0;
        for (int k = 0; k < cnt; ++k) {
            sumPredict += (double) tmpPredict[k] / (double) cnt;
        }
        double predict = (double) Math.round(sumPredict);
        return predict;
    }
}
