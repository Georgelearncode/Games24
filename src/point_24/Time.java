package point_24;

public class Time implements Runnable {

	public long time=0;
	 //定义一个线程
	 static Thread thread;
	 //用于停止线程的标志
	 public  boolean  flag=true;
	 public Time(){
	  thread=new Thread(this);
	 }
	 //因为该类实现了Runnable，就必须有run方法，当线程启动时，会调用这个run方法
	 public void run(){
	  //获得当前的时间，毫秒为单位
	  long beginTime=System.currentTimeMillis();
	  //定义已过去的时间
	  time=0;
	  while(flag){
	   //这里写实现计时的代码
	   //在这里，获得已过去的时间
	   time=System.currentTimeMillis()-beginTime;
	   Windows.time.setText("计时器："+time/1000+"秒！");
	   //System.out.println("已过"+time/1000+"秒");
	   
	   //暂停线程1秒钟,不暂停的话可以把下面代码去掉
	   try{
	    Thread.sleep(1000);
	   }catch(InterruptedException e1){
	    e1.printStackTrace();
	   }
	  }
	 }
	 //这里是启动线程的方法，也就是启动线程
	 public  void start(){
	  thread.start();
	 }
	 //这里是暂停的方法，暂停线程
	 public  void Pause(){
	  try{
	   thread.wait();
	  }catch(InterruptedException e){
	   e.printStackTrace();
	  }
	 }
	 //这里是继续的方法,唤醒线程
	 public  void Resume(){
	  thread.notifyAll();
	 }
	 //停止线程
	 public  void stop(){
	  //把flag设成false，则在run中的while循环就会停止循环
	  flag=false;
	 }
	 
	/* public static void main(String []args){
	  //用于测试
	  Time t=new Time();
	  //开始线程
	  t.start();
	  try{
	   //10000毫秒以后结束线程
	  Thread.sleep(10000);
	  }catch(InterruptedException e1){
	  e1.printStackTrace();
	  }
	  //结束线程
	  t.stop();
	   
	   }*/
	 
	}
