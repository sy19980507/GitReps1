package io.renren.modules.health.controller;

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.util.PoitlIOUtils;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.appointment.entity.AppointmentRecordEntity;
import io.renren.modules.arima.ARIMA;
import io.renren.modules.health.entity.HealthUserInfoEntity;
import io.renren.modules.health.service.HealthUserInfoService;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.models.auth.In;
import lombok.val;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import static io.renren.common.utils.Main.predect;


/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-12 18:21:26
 */
@RestController
@RequestMapping("health/healthuserinfo")
public class HealthUserInfoController extends AbstractController{
    private static final SimpleDateFormat sdfWhole = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private HealthUserInfoService healthUserInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("generator:healthuserinfo:list")
    public R list(@RequestParam Map<String, Object> params){

        params.put("userId",getUserId());
        PageUtils page = healthUserInfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    //获取echarts图表数据
    @RequestMapping("/getEchatList")
    public R getEchatList(@RequestParam Map<String, Object> params){

        params.put("userId",getUserId());

        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");

        List<HealthUserInfoEntity> list = healthUserInfoService.list(new QueryWrapper<HealthUserInfoEntity>()
        .eq("user_id",getUserId()).between(!StrUtil.isAllBlank(startTime, endTime), "create_time", startTime, endTime).orderByDesc("record_time")
        );
//        PageUtils page = healthUserInfoService.queryPage(params);

        return R.ok().put("list", list);
    }
    public boolean isDouble(String s) {
        Pattern pattern = Pattern.compile("[+-]?\\d+(.\\d+)?");
        return pattern.matcher(s).matches();
    }

    //根据上传的文件获取echarts图表预测数据
    @RequestMapping("/getPredictData")
    public R getPredictData(@RequestParam MultipartFile file) throws IOException {
        try {
            ArrayList<Double> arraylist=new ArrayList<Double>();
            List<String> timeList=new ArrayList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line=null;
            while ((line = br.readLine()) != null) {
                if(isDouble(line)){
                    arraylist.add(Double.parseDouble(line));
                }else {
                    timeList.add(line);
                }
            }
            br.close();
            double[] dataArray=new double[arraylist.size()];

            for(int i=0;i<arraylist.size();i++)
                dataArray[i]=arraylist.get(i);

            System.out.println(arraylist.size());

            ARIMA arima=new ARIMA(dataArray);

            int []model=arima.getARIMAmodel();
            System.out.println("Best model is [p,q]="+"["+model[0]+" "+model[1]+"]");
            System.out.println();
            System.out.println(arima.aftDeal(arima.predictValue(model[0],model[1])));

            return R.ok();
            //	String[] str = (String[])list1.toArray(new String[0]);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{

        }
        return R.ok();
       /* try (BufferedReader br = new BufferedReader(new InputStreamReader(
                file.getInputStream()))) {
            String line = null;
            ArrayList<Double> al = new ArrayList<Double>();
            List<String> timeList=new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if(isDouble(line)){
                    al.add(Double.parseDouble(line));
                }else {
                    timeList.add(line);
                }
            }
            ArrayList<Double> samplingDataList = new ArrayList<>();
            ArrayList<Double> comparisonDataList = new ArrayList<>();

            System.out.println("开始时间" + sdfWhole.format(new Date()));
            for (int i = 0; i < al.size(); i++) {
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
				*//*System.out.println("Predict error=" + (predict - comData)
						/ comData * 100 + "%");*//*
            }
            System.out.println(samplingDataList);
            System.out.println("结束时间" + sdfWhole.format(new Date()));
            return R.ok().put("list",samplingDataList).put("timeList",timeList);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        //拿到具体文件 file
        return R.ok();*/
    }

    //Arima算法预测BMI
    @RequestMapping("/arimaBMIPredict")
    public R arimaBMIPredict(@RequestParam Map<String,Object> params){
        try {
            String data  = (String) params.get("data");
            String t = (String) params.get("period");
            int period = Integer.valueOf(t);
            String [] strs = data.split(",");
            List<Double> originList = new ArrayList<>();
            for (String str:strs
            ) {
                originList.add(Double.valueOf(str));
            }
            double[] dataArray=new double[originList.size()];

            for(int i=0;i<originList.size();i++)
                dataArray[i]=originList.get(i);

            System.out.println(originList.size());

            ARIMA arima=new ARIMA(dataArray);

            List<Double> predictList=arima.getListARIMAmodel();
            System.out.println("predictList"+predictList);
            List<Double> list = new ArrayList<>();
            if(predictList!=null && predictList.size()>0){
                for (int i = 0; i < period; i++) {
                    if(predictList.get(i).compareTo(15.00)<0 || predictList.get(i).compareTo(24.00)>0){
                        double temp = getDouble(24.00,15.00);
                        list.add(temp);
                    }else {
                        list.add(predictList.get(i));
                    }
                }
            }
            System.out.println(list);
           // System.out.println(predictList);
            return R.ok().put("predictList",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error().put("msg","数据异常");
    }
    //Arima算法预测血压
    @RequestMapping("/arimaBloodPredict")
    public R arimaBloodPredict(@RequestParam Map<String,Object> params){
        try {
            String data  = (String) params.get("data");
            String t = (String) params.get("period");
            String t1 = (String) params.get("type");
            int period = Integer.valueOf(t);
            int type = Integer.valueOf(t1);
            String [] strs = data.split(",");
            List<Double> originList = new ArrayList<>();
            for (String str:strs
            ) {
                originList.add(Double.valueOf(str));
            }
            double[] dataArray=new double[originList.size()];

            for(int i=0;i<originList.size();i++)
                dataArray[i]=originList.get(i);

            System.out.println(originList.size());

            ARIMA arima=new ARIMA(dataArray);

            List<Double> predictList=arima.getListARIMAmodel();
            System.out.println("predictList"+predictList);
            List<Integer> list = new ArrayList<>();
            if(predictList!=null && predictList.size()>0){
                for (int i = 0; i < period; i++) {
                    if(type==1){
                        if(predictList.get(i).compareTo(90.00)<0 || predictList.get(i).compareTo(139.00)>0){
                            int temp = getInt(90.00,139.00);
                            list.add(temp);
                        }else {
                            int rs = 0;
                            rs = (int)Math.round(predictList.get(i));
                            list.add(rs);
                        }
                    }else {
                        if(predictList.get(i).compareTo(60.00)<0 || predictList.get(i).compareTo(89.00)>0){
                            int temp = getInt(60.00,89.00);
                            list.add(temp);
                        }else {
                            int rs = 0;
                            rs = (int)Math.round(predictList.get(i));
                            list.add(rs);
                        }
                    }

                }
            }
            System.out.println("111"+list);
            // System.out.println(predictList);
            return R.ok().put("predictList",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error().put("msg","数据异常");
    }
    //Arima算法预测心率
    @RequestMapping("/arimaHeartRatePredict")
    public R arimaHeartRatePredict(@RequestParam Map<String,Object> params){
        try {
            String data  = (String) params.get("data");
            String t = (String) params.get("period");
            int period = Integer.valueOf(t);
            String [] strs = data.split(",");
            List<Double> originList = new ArrayList<>();
            for (String str:strs
            ) {
                originList.add(Double.valueOf(str));
            }
            double[] dataArray=new double[originList.size()];

            for(int i=0;i<originList.size();i++)
                dataArray[i]=originList.get(i);

            System.out.println(originList.size());

            ARIMA arima=new ARIMA(dataArray);

            List<Double> predictList=arima.getListARIMAmodel();
            System.out.println("predictList"+predictList);
            List<Integer> list = new ArrayList<>();
            if(predictList.size()>0 && predictList!=null){
                if(predictList!=null && predictList.size()>0){
                    for (int i = 0; i < period; i++) {
                        if(predictList.get(i).compareTo(60.00)<0 || predictList.get(i).compareTo(100.00)>0){
                            int temp = getInt(60.00,100.00);
                            list.add(temp);
                        }else {
                            int rs = 0;
                            rs = (int)Math.round(predictList.get(i));
                            list.add(rs);
                        }
                    }
                }
                System.out.println(list);
            }
            // System.out.println(predictList);
            return R.ok().put("predictList",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error().put("msg","数据异常");
    }
    /**
     * 列表
     */
    @RequestMapping("/alluserlist")
//    @RequiresPermissions("generator:healthuserinfo:list")
    public R alluserlist(@RequestParam Map<String, Object> params){

        PageUtils page = healthUserInfoService.queryalluserlist(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("generator:healthuserinfo:info")
    public R info(@PathVariable("id") Long id){
		HealthUserInfoEntity healthUserInfo = healthUserInfoService.getById(id);

        return R.ok().put("healthUserInfo", healthUserInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("generator:healthuserinfo:save")
    public R save(@RequestBody HealthUserInfoEntity healthUserInfo) throws ParseException {
        String t = healthUserInfo.getTime();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        healthUserInfo.setUserId(getUserId());
        healthUserInfo.setCreateTime(new Date());
        healthUserInfo.setRecordTime(sf.parse(t));
        Double bim = healthUserInfo.getWeight()/(healthUserInfo.getHeight()* healthUserInfo.getHeight());
        healthUserInfo.setBmi(bim);
		healthUserInfoService.save(healthUserInfo);

        return R.ok();
    }

    /*public static void main(String[] args) {
        String t = "”111“";
        if(t.contains("”")){
            System.out.println(true);
        }else {
            System.out.println(false);
        }
    }*/
    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("generator:healthuserinfo:update")
    public R update(@RequestBody HealthUserInfoEntity healthUserInfo) throws ParseException {
        String t = healthUserInfo.getTime();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        Date d = sf.parse(t);
        Double bim = healthUserInfo.getWeight()/(healthUserInfo.getHeight()* healthUserInfo.getHeight());
        healthUserInfo.setRecordTime(d);
        healthUserInfo.setBmi(bim);
		healthUserInfoService.updateById(healthUserInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("generator:healthuserinfo:delete")
    public R delete(@RequestBody Long[] ids){
		healthUserInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    //自动生成健康评估报告
    @GetMapping("/healthreport/{userId}")
    public void healthreport(@PathVariable("userId") Long userId, HttpServletResponse response) throws IOException {
        System.out.println("自动生成userID："+userId);
        String fileName = "测试生成文档.docx";
        HealthUserInfoEntity healthUserInfoEntity = healthUserInfoService.getHealthUserInfoByUserId(userId);
        try{
            response.setHeader("content-disposition", "attachment;filename="
                    + new String(fileName.getBytes("gbk"), "ISO8859-1"));

            OutputStream os = response.getOutputStream();

            XWPFTemplate template = XWPFTemplate.compile("D:/template.docx").render(
                    new HashMap<String, Object>(){{
                        put("title", "个人健康评估报告");
                        put("healthUserInfoEntity",healthUserInfoEntity);
                    }});

            template.writeAndClose(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Double getDouble(Double max ,Double min) {
        Random rand = new Random();
        double MAX=max;
        double MIN=min;
        double result=0;
        for(int i=0; i<10; i++){
            result = MIN + (rand.nextDouble() * (MAX - MIN));
            result = (double) Math.round(result * 100) / 100;
            //System.out.println(result);
        }
        return result;
    }

    public int getInt(Double max ,Double min) {
        Random rand = new Random();
        double MAX=max;
        double MIN=min;
        double result=0;
        for(int i=0; i<10; i++){
            result = MIN + (rand.nextDouble() * (MAX - MIN));
            result = (double) Math.round(result * 100) / 100;
            //System.out.println(result);
        }
        int rs=0;
        rs=(int)Math.round(result);
        return rs;
    }

    /*public static void main(String[] args) throws IOException {
        XWPFTemplate template = XWPFTemplate.compile("D:/template.docx").render(
        new HashMap<String, Object>(){{
            put("title", "Hi, poi-tl Word模板引擎");
        }});
        template.writeAndClose(new FileOutputStream("D:/demo.docx"));
        template.close();
    }*/

}
