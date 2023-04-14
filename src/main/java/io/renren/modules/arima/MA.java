package io.renren.modules.arima;

import java.util.Vector;

public class MA {

	double[] stdoriginalData={};
	int q;
	ARMAMath armamath=new ARMAMath();
	
	/** MAģ��
	 * @param stdoriginalData //Ԥ������������
	 * @param q //qΪMAģ�ͽ���
	 */
	public MA(double [] stdoriginalData,int q)
	{
		this.stdoriginalData=stdoriginalData;
		this.q=q;
	}
	
	public Vector<double[]> MAmodel()
	{
		Vector<double[]> v=new Vector<double[]>();
		v.add(armamath.getMApara(armamath.autocorData(stdoriginalData,q), q));
		return v;//�õ�MAģ������Ĳ���ֵ
	}
		
	
}
