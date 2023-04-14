package io.renren.modules.arima;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class test1 {
	public static boolean isDouble(String s) {
		Pattern pattern = Pattern.compile("[+-]?\\d+(.\\d+)?");
		return pattern.matcher(s).matches();
	}
	public static void main(String args[])
	{
		
		try {
			ArrayList<Double> arraylist=new ArrayList<Double>();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d:/data/data.txt")));
			String line=null;
			while ((line = br.readLine()) != null) {
				if(isDouble(line))
					arraylist.add(Double.parseDouble(line));
			}
			br.close();
			double[] dataArray=new double[arraylist.size()];
			/*double[] dataArray1=new double[]{17.34,22.86,22.46,17.92,16.19,19.82,18.37,17.34,22.86,22.46,17.92,16.19,19.82,18.37};
			double[] dataArray2=new double[]{17.34,22.86,22.46,17.92,16.19,19.82,18.37,17.34,22.86,22.46,17.92,16.19,19.82,18.37};*/
			for(int i=0;i<arraylist.size();i++)
				dataArray[i]=arraylist.get(i);

			//System.out.println(arraylist.size());
				
			ARIMA arima=new ARIMA(dataArray);
			
			int []model=arima.getARIMAmodel();
			//Double t = arima.getDoubleARIMAmodel();
			//System.out.println(t);
			System.out.println("Best model is [p,q]="+"["+model[0]+" "+model[1]+"]");
			System.out.println();
			System.out.println(arima.aftDeal(arima.predictValue(model[0],model[1])));
			
		
		//	String[] str = (String[])list1.toArray(new String[0]);	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			
		}
		
	/*
		ARIMA arima=new ARIMA(dataArray); 
		
		int []model=arima.getARIMAmodel();
		System.out.println("Best model is [p,q]="+"["+model[0]+" "+model[1]+"]");
		System.out.println();
		System.out.println(arima.aftDeal(arima.predictValue(model[0],model[1])));
		*/
	
		/*
		
		double corr[]=a.autocorData(arima.preDeal(), 10);
		
		for(int i=0;i<corr.length;i++)
		{
			System.out.println(corr[i]+"  ");
		}
		System.out.println();
		
		double parcorr[]=a.parautocorData(arima.preDeal(), 10);
		for(int i=0;i<parcorr.length;i++)
		{
			System.out.println(parcorr[i]+" ");
		}
		System.out.println();
		*/
	}
	
	
}
