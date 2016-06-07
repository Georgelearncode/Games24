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

	public static double[] handle_2(double a,double b)//�����������ļӼ��˳�
	{double[] ab_R=new double[6];
		
		ab_R[0]=a+b;
		ab_R[1]=a-b;
		ab_R[2]=b-a;
		ab_R[3]=a*b;
		
		try
		{
			if(b==0)
			{
				ab_R[4]=65535;//�������͸�ֵһ������������65535�����������100��Ҳ�������24����ȫ
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
				ab_R[5]=65535;//�������͸�ֵһ������������65535�����������100��Ҳ�������24����ȫ
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
	public static double[] handle_3(double ab[],double c)//�����������ļӼ��˳�
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
	public static double[] handle_4(double abc[],double d)//�����ĸ����ļӼ��˳�
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
					ab_cd[j]=ab_R[q];//ab_cdһ��36		
				}
			}
			for(int x=i*36,p=0;x<i*36+36;p++,x++)
			{
				ab_cd_R[x]=ab_cd[p];
			}
			
		}
		return ab_cd_R;
	}
	public static void helpButtonAction(double a,double b,double c,double d)//�����ܵ����������
	{
			//���1:((ab)c)d
			ab=handle_2(a,b);
			abc=handle_3(ab,c);
			abcd=handle_4(abc,d);
			for(int k=0;k<abcd.length;k++)
			{
				result[0][k]=abcd[k];
			}
			//���2��((ab)d)c
			ab=handle_2(a,b);
			abc=handle_3(ab,d);
			abcd=handle_4(abc,c);
			for(int k=0;k<abcd.length;k++)
			{
				result[1][k]=abcd[k];
			}
			//���3��((ac)b)d
			ab=handle_2(a,c);
			abc=handle_3(ab,b);
			abcd=handle_4(abc,d);
			for(int k=0;k<abcd.length;k++)
			{
				result[2][k]=abcd[k];
			}
			//���4��((ac)d)b
			ab=handle_2(a,c);
			abc=handle_3(ab,d);
			abcd=handle_4(abc,b);
			for(int k=0;k<abcd.length;k++)
			{
				result[3][k]=abcd[k];
			}
			//���5��((ad)b)c
			ab=handle_2(a,d);
			abc=handle_3(ab,b);
			abcd=handle_4(abc,c);
			for(int k=0;k<abcd.length;k++)
			{
				result[4][k]=abcd[k];
			}
			//���6��((ad)c)b
			ab=handle_2(a,d);
			abc=handle_3(ab,c);
			abcd=handle_4(abc,b);
			for(int k=0;k<abcd.length;k++)
			{
				result[5][k]=abcd[k];
			}
			//���7��((bc)a)d
			ab=handle_2(b,c);
			abc=handle_3(ab,a);
			abcd=handle_4(abc,d);
			for(int k=0;k<abcd.length;k++)
			{
				result[6][k]=abcd[k];
			}
			//���8��((bc)d)a
			ab=handle_2(b,c);
			abc=handle_3(ab,d);
			abcd=handle_4(abc,a);
			for(int k=0;k<abcd.length;k++)
			{
				result[7][k]=abcd[k];
			}
			//���9��((bd)a)c
			ab=handle_2(b,d);
			abc=handle_3(ab,a);
			abcd=handle_4(abc,c);
			for(int k=0;k<abcd.length;k++)
			{
				result[8][k]=abcd[k];
			}
			//���10��((bd)c)a
			ab=handle_2(b,d);
			abc=handle_3(ab,c);
			abcd=handle_4(abc,a);
			for(int k=0;k<abcd.length;k++)
			{
				result[9][k]=abcd[k];
			}
			//���11��((cd)a)b
			ab=handle_2(c,d);
			abc=handle_3(ab,a);
			abcd=handle_4(abc,b);
			for(int k=0;k<abcd.length;k++)
			{
				result[10][k]=abcd[k];
			}
			//���12��((cd)b)a
			ab=handle_2(c,d);
			abc=handle_3(ab,b);
			abcd=handle_4(abc,a);
			for(int k=0;k<abcd.length;k++)
			{
				result[11][k]=abcd[k];
			}
			//���13��(ab)(cd)
			ab1=handle_2(a,b);
			ab2=handle_2(c,d);
			ab_cd=handle_22(ab1,ab2);
			for(int k=0;k<216;k++)
			{
				result2[0][k]=ab_cd[k];
			}
			//���14��(ac)(bd)
			ab1=handle_2(a,c);
			ab2=handle_2(b,d);
			ab_cd=handle_22(ab1,ab2);
			for(int k=0;k<216;k++)
			{
				result2[1][k]=ab_cd[k];
			}
			//���15��(ad)(bc)
			ab1=handle_2(a,d);
			ab2=handle_2(b,c);
			ab_cd=handle_22(ab1,ab2);
			for(int k=0;k<216;k++)
			{
				result2[2][k]=ab_cd[k];
			}
		for(int j=0;j<12;j++)//����12�ֲ�ͬ���������������ı��ʽ�ַ���
		{
			for(int i=0;i<result[j].length;i++)
			{			
				if(result[j][i]==24)
				{	
					Operator.chooseOperator(j, i, a, b, c, d);//���ʽ���㺯����������Ƕ��
				}//if���
			}//�ڲ�for
		}//���for
		for(int j=0;j<3;j++)
		{
			for(int i=0;i<result2[j].length;i++)
			{
				if(result2[j][i]==24)
				{
					Operator.chooseOperator2(j, i, a, b, c, d);//����Ƕ���ŵ���ʽ����
				}
			
			}
		}
		Iterator<String> it=Operator.resultstr.iterator();//String�����ĵ�����
		if(Operator.resultstr.isEmpty())
		{//�������巢����Ϣ
			Windows.textArea.append("��������޽⣡************************************�밴��ʼ����ѡ�⣡");
		}
		
		while(it.hasNext())
			//�������巢�ͽ��
		{	String str="���";
			Windows.textArea.append(str+n+":"+it.next()+"\n");
			n++;
		}
		
	}//����

}

