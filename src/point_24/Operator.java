package point_24;

import java.util.ArrayList;
import java.util.List;


public class Operator 
{
	private static String opop;
	private static String opop1;
	private static String opop2;
	private static int op1;
	private static int op2;
	private static int op3;
	public static List<String> resultstr=new ArrayList<String>();
	public static void chooseOperator(int j,int i,double a,double b,double c,double d)
	{
		double[] sequence=new double[4];//一个顺序数组，供以后的顺序计算使用
		switch(j)//顺序数组初始化
		{
		case 0:sequence[0]=a;sequence[1]=b;sequence[2]=c;sequence[3]=d;break;//((ab)c)d
		case 1:sequence[0]=a;sequence[1]=b;sequence[2]=d;sequence[3]=c;break;//((ab)d)c
		case 2:sequence[0]=a;sequence[1]=c;sequence[2]=b;sequence[3]=d;break;//((ac)b)d
		case 3:sequence[0]=a;sequence[1]=c;sequence[2]=d;sequence[3]=b;break;//((ac)d)b
		case 4:sequence[0]=a;sequence[1]=d;sequence[2]=b;sequence[3]=c;break;//((ad)b)c
		case 5:sequence[0]=a;sequence[1]=d;sequence[2]=c;sequence[3]=b;break;//((ad)c)b
		case 6:sequence[0]=b;sequence[1]=c;sequence[2]=a;sequence[3]=d;break;//((bc)a)d
		case 7:sequence[0]=b;sequence[1]=c;sequence[2]=d;sequence[3]=a;break;//((bc)d)a
		case 8:sequence[0]=b;sequence[1]=d;sequence[2]=a;sequence[3]=c;break;//((bd)a)c
		case 9:sequence[0]=b;sequence[1]=d;sequence[2]=c;sequence[3]=a;break;//((bd)c)a
		case 10:sequence[0]=c;sequence[1]=d;sequence[2]=a;sequence[3]=b;break;//((cd)a)b
		case 11:sequence[0]=c;sequence[1]=d;sequence[2]=b;sequence[3]=a;break;//((cd)b)a
		}
			
			op1=(i/36)%6;//第一个符号选取
			switch(op1)
			{
			case 0: opop="("+sequence[0]+"+"+sequence[1]+")";break;
			case 1: opop="("+sequence[0]+"-"+sequence[1]+")";break;
			case 2: opop="("+sequence[1]+"-"+sequence[0]+")";break;
			case 3: opop="("+sequence[0]+"*"+sequence[1]+")";break;
			case 4: opop="("+sequence[0]+"/"+sequence[1]+")";break;
			case 5: opop="("+sequence[1]+"/"+sequence[0]+")";break;
			default: break;
			}
			op2=(i/6)%6;//第二个符号选取
			switch(op2)
			{
			case 0: opop="("+opop+"+"+sequence[2]+")";break;
			case 1: opop="("+opop+"-"+sequence[2]+")";break;
			case 2: opop="("+sequence[2]+"-"+opop+")";break;
			case 3: opop="("+opop+"*"+sequence[2]+")";break;
			case 4: opop="("+opop+"/"+sequence[2]+")";break;
			case 5: opop="("+sequence[2]+"/"+opop+")";break;
			default: break;
			}
			op3=i%6;//第三个符号选取
			switch(op3)
			{
			case 0: opop=opop+"+"+sequence[3]+"=24";break;
			case 1: opop=opop+"-"+sequence[3]+"=24";break;
			case 2: opop=sequence[3]+"-"+opop+"=24";break;
			case 3: opop=opop+"*"+sequence[3]+"=24";break;
			case 4: opop=opop+"/"+sequence[3]+"=24";break;
			case 5: opop=sequence[3]+"/"+opop+"=24";break;
			default: break;
			}
			resultstr.add(opop);//向容器添加  24点的组合计算式！
		
	}
	public static void chooseOperator2(int j,int i,double a,double b,double c,double d)
	{
		double[] sequence=new double[4];//一个顺序数组，供以后的顺序计算使用
		switch(j)//顺序数组初始化
		{
			case 0:sequence[0]=a;sequence[1]=b;sequence[2]=c;sequence[3]=d;break;//(ab)(cd)
			case 1:sequence[0]=a;sequence[1]=c;sequence[2]=b;sequence[3]=d;break;//(ac)(bd)
			case 2:sequence[0]=a;sequence[1]=d;sequence[2]=b;sequence[3]=c;break;//((ad)(bc)
		}
		op1=(i/36)%6;
		switch(op1)
		{
			case 0: opop="("+sequence[0]+"+"+sequence[1]+")";break;
			case 1: opop="("+sequence[0]+"-"+sequence[1]+")";break;
			case 2: opop="("+sequence[1]+"-"+sequence[0]+")";break;
			case 3: opop="("+sequence[0]+"*"+sequence[1]+")";break;
			case 4: opop="("+sequence[0]+"/"+sequence[1]+")";break;
			case 5: opop="("+sequence[1]+"/"+sequence[0]+")";break;
			default: break;
		}
		op2=(i/6)%6;
		switch(op2)
		{
			case 0: opop1="("+sequence[2]+"+"+sequence[3]+")";break;
			case 1: opop1="("+sequence[2]+"-"+sequence[3]+")";break;
			case 2: opop1="("+sequence[3]+"-"+sequence[2]+")";break;
			case 3: opop1="("+sequence[2]+"*"+sequence[3]+")";break;
			case 4: opop1="("+sequence[2]+"/"+sequence[3]+")";break;
			case 5: opop1="("+sequence[3]+"/"+sequence[2]+")";break;
			default: break;
		}
		op3=i%6;
		switch(op3)
		{
			case 0: opop2=opop+"+"+opop1+"=24";break;
			case 1: opop2=opop+"-"+opop1+"=24";break;
			case 2: opop2=opop1+"-"+opop+"=24";break;
			case 3: opop2=opop+"*"+opop1+"=24";break;
			case 4: opop2=opop+"/"+opop1+"=24";break;
			case 5: opop2=opop1+"/"+opop+"=24";break;
			default: break;
		}
		resultstr.add(opop2);//向容器添加  24点的组合计算式！
	}
}

