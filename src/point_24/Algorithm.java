package point_24;

import java.util.Iterator;


public class Algorithm {

	private static double[] ab=new double[6];
	private static double[]abc=new double[36];
	private static double[] ab1=new double[6];
	private static double[] ab2=new double[6];
	private static double[] abcd=new double[216];
	private static double[] ab_cd=new double[216];
	private static double[][] result=new double[12][216];
	private static double[][] result2=new double[3][216];
	private static String opop;
	public static int n=1;
	/**
	 * @param args
	 */
	//public static void main(String[] args) 
	//{
	//	helpButtonAction(a,b,c,d);
	//}

	public static double[] handle_2(double a,double b)//处理两个数的加减乘除
	{double[] ab_R=new double[6];
		
		ab_R[0]=a+b;
		ab_R[1]=a-b;
		ab_R[2]=b-a;
		ab_R[3]=a*b;
		
		try
		{
			if(b==0)
			{
				ab_R[4]=65535;//除不尽就赋值一个无穷大的数，65535就算除以最大的100，也不会等于24，安全
			}
			else
			{
				ab_R[4]=a/b;
			}
		}
		catch(Exception e)
		{
			
		}
		
		try
		{
			
			if(a==0)
			{
				ab_R[5]=65535;//除不尽就赋值一个无穷大的数，65535就算除以最大的100，也不会等于24，安全
			}
			else
			{
				ab_R[5]=b/a;
			}
		}
		catch(Exception e)
		{
			
		}
		
		return ab_R;
	}
	public static double[] handle_3(double ab[],double c)//处理三个数的加减乘除
	{
		double[] ab_R=new double[6];
		double[] abc_R=new double[36];
		for(int i=0;i<6;i++)
		{
			ab_R=handle_2(ab[i],c);
			for(int j=i*6,k=0;j<i*6+6;j++,k++)
			{
				abc_R[j]=ab_R[k];
			}
		}
		return abc_R;
	}
	public static double[] handle_4(double abc[],double d)//处理四个数的加减乘除
	{
		double[] abc_R=new double[6];
		double[] abcd_R=new double[216];
		for(int i=0;i<36;i++)
		{
			abc_R=handle_2(abc[i],d);
			for(int j=i*6,k=0;j<i*6+6;j++,k++)
			{
				abcd_R[j]=abc_R[k];
			}
		}
		return abcd_R;
	}
	public static double[] handle_22(double[] ab1,double[] ab2)
	{
		double[] ab_cd=new double[36];
		double[] ab_cd_R=new double[216];
		double[] ab_R=new double[6];
		for(int i=0;i<6;i++)
		{	
			
			for(int k=0;k<6;k++)
			{
				ab_R=handle_2(ab1[i],ab2[k]);
				for(int j=k*6,q=0;j<k*6+6;q++,j++)
				{
					ab_cd[j]=ab_R[q];//ab_cd一共36		
				}
			}
			for(int x=i*36,p=0;x<i*36+36;p++,x++)
			{
				ab_cd_R[x]=ab_cd[p];
			}
			
		}
		return ab_cd_R;
	}
	public static void helpButtonAction(double a,double b,double c,double d)//处理总的组合数方法
	{
			//组合1:((ab)c)d
			ab=handle_2(a,b);
			abc=handle_3(ab,c);
			abcd=handle_4(abc,d);
			for(int k=0;k<abcd.length;k++)
			{
				result[0][k]=abcd[k];
			}
			//组合2：((ab)d)c
			ab=handle_2(a,b);
			abc=handle_3(ab,d);
			abcd=handle_4(abc,c);
			for(int k=0;k<abcd.length;k++)
			{
				result[1][k]=abcd[k];
			}
			//组合3：((ac)b)d
			ab=handle_2(a,c);
			abc=handle_3(ab,b);
			abcd=handle_4(abc,d);
			for(int k=0;k<abcd.length;k++)
			{
				result[2][k]=abcd[k];
			}
			//组合4：((ac)d)b
			ab=handle_2(a,c);
			abc=handle_3(ab,d);
			abcd=handle_4(abc,b);
			for(int k=0;k<abcd.length;k++)
			{
				result[3][k]=abcd[k];
			}
			//组合5：((ad)b)c
			ab=handle_2(a,d);
			abc=handle_3(ab,b);
			abcd=handle_4(abc,c);
			for(int k=0;k<abcd.length;k++)
			{
				result[4][k]=abcd[k];
			}
			//组合6：((ad)c)b
			ab=handle_2(a,d);
			abc=handle_3(ab,c);
			abcd=handle_4(abc,b);
			for(int k=0;k<abcd.length;k++)
			{
				result[5][k]=abcd[k];
			}
			//组合7：((bc)a)d
			ab=handle_2(b,c);
			abc=handle_3(ab,a);
			abcd=handle_4(abc,d);
			for(int k=0;k<abcd.length;k++)
			{
				result[6][k]=abcd[k];
			}
			//组合8：((bc)d)a
			ab=handle_2(b,c);
			abc=handle_3(ab,d);
			abcd=handle_4(abc,a);
			for(int k=0;k<abcd.length;k++)
			{
				result[7][k]=abcd[k];
			}
			//组合9：((bd)a)c
			ab=handle_2(b,d);
			abc=handle_3(ab,a);
			abcd=handle_4(abc,c);
			for(int k=0;k<abcd.length;k++)
			{
				result[8][k]=abcd[k];
			}
			//组合10：((bd)c)a
			ab=handle_2(b,d);
			abc=handle_3(ab,c);
			abcd=handle_4(abc,a);
			for(int k=0;k<abcd.length;k++)
			{
				result[9][k]=abcd[k];
			}
			//组合11：((cd)a)b
			ab=handle_2(c,d);
			abc=handle_3(ab,a);
			abcd=handle_4(abc,b);
			for(int k=0;k<abcd.length;k++)
			{
				result[10][k]=abcd[k];
			}
			//组合12：((cd)b)a
			ab=handle_2(c,d);
			abc=handle_3(ab,b);
			abcd=handle_4(abc,a);
			for(int k=0;k<abcd.length;k++)
			{
				result[11][k]=abcd[k];
			}
			//组合13：(ab)(cd)
			ab1=handle_2(a,b);
			ab2=handle_2(c,d);
			ab_cd=handle_22(ab1,ab2);
			for(int k=0;k<216;k++)
			{
				result2[0][k]=ab_cd[k];
			}
			//组合14：(ac)(bd)
			ab1=handle_2(a,c);
			ab2=handle_2(b,d);
			ab_cd=handle_22(ab1,ab2);
			for(int k=0;k<216;k++)
			{
				result2[1][k]=ab_cd[k];
			}
			//组合15：(ad)(bc)
			ab1=handle_2(a,d);
			ab2=handle_2(b,c);
			ab_cd=handle_22(ab1,ab2);
			for(int k=0;k<216;k++)
			{
				result2[2][k]=ab_cd[k];
			}
		for(int j=0;j<12;j++)//输入12种不同的情况，计算出最后的表达式字符串
		{
			for(int i=0;i<result[j].length;i++)
			{			
				if(result[j][i]==24)
				{	
					Operator.chooseOperator(j, i, a, b, c, d);//表达式计算函数，括号内嵌型
				}//if语句
			}//内层for
		}//外层for
		for(int j=0;j<3;j++)
		{
			for(int i=0;i<result2[j].length;i++)
			{
				if(result2[j][i]==24)
				{
					Operator.chooseOperator2(j, i, a, b, c, d);//非内嵌括号的算式调用
				}
			
			}
		}
		Iterator<String> it=Operator.resultstr.iterator();//String容器的迭代器
		if(Operator.resultstr.isEmpty())
		{//向帮助面板发送消息
			Windows.textArea.append("本组合数无解！************************************请按开始重新选题！");
		}
		
		while(it.hasNext())
			//向帮助面板发送结果
		{	String str="结果";
			Windows.textArea.append(str+n+":"+it.next()+"\n");
			n++;
		}
		
	}//函数

}

