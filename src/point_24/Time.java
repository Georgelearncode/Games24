package point_24;

public class Time implements Runnable {

	public long time=0;
	 //����һ���߳�
	 static Thread thread;
	 //����ֹͣ�̵߳ı�־
	 public  boolean  flag=true;
	 public Time(){
	  thread=new Thread(this);
	 }
	 //��Ϊ����ʵ����Runnable���ͱ�����run���������߳�����ʱ����������run����
	 public void run(){
	  //��õ�ǰ��ʱ�䣬����Ϊ��λ
	  long beginTime=System.currentTimeMillis();
	  //�����ѹ�ȥ��ʱ��
	  time=0;
	  while(flag){
	   //����дʵ�ּ�ʱ�Ĵ���
	   //���������ѹ�ȥ��ʱ��
	   time=System.currentTimeMillis()-beginTime;
	   Windows.time.setText("��ʱ����"+time/1000+"�룡");
	   //System.out.println("�ѹ�"+time/1000+"��");
	   
	   //��ͣ�߳�1����,����ͣ�Ļ����԰��������ȥ��
	   try{
	    Thread.sleep(1000);
	   }catch(InterruptedException e1){
	    e1.printStackTrace();
	   }
	  }
	 }
	 //�����������̵߳ķ�����Ҳ���������߳�
	 public  void start(){
	  thread.start();
	 }
	 //��������ͣ�ķ�������ͣ�߳�
	 public  void Pause(){
	  try{
	   thread.wait();
	  }catch(InterruptedException e){
	   e.printStackTrace();
	  }
	 }
	 //�����Ǽ����ķ���,�����߳�
	 public  void Resume(){
	  thread.notifyAll();
	 }
	 //ֹͣ�߳�
	 public  void stop(){
	  //��flag���false������run�е�whileѭ���ͻ�ֹͣѭ��
	  flag=false;
	 }
	 
	/* public static void main(String []args){
	  //���ڲ���
	  Time t=new Time();
	  //��ʼ�߳�
	  t.start();
	  try{
	   //10000�����Ժ�����߳�
	  Thread.sleep(10000);
	  }catch(InterruptedException e1){
	  e1.printStackTrace();
	  }
	  //�����߳�
	  t.stop();
	   
	   }*/
	 
	}
