package point_24;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Windows extends JFrame implements MouseListener,
MouseMotionListener
{

	public static NumField[] numFields;
	private RandomNum randomThread=new RandomNum();
	private JTextField[] num=new JTextField[5];//center的第一行
	private JTextField[] num1=new JTextField[5];//center的第二行
	private JTextField[] num2=new JTextField[5];//center的第三行
	private JTextField op=new JTextField(4);//等号
	private JTextField op1=new JTextField(4);//等号
	private JTextField op2=new JTextField(4);//等号
	private String number;
	private JButton count1;
	private JButton count2;
	private JButton count3;
	private static JButton summit;
	private static JTextField tf;//点击变换文本框1
	private static JTextField tf1;//点击变换文本框2
	private int flag=1;//按计算按钮后的标志，1为第一行计算，2为第二行计算，3为第三行计算
	private static final Font numfont=new Font("",Font.BOLD,25);
	private JTextField calculateField;//计分文本框
	private JButton operator1=new JButton("+");//加号
	private JButton operator2=new JButton("-");//减号
	private JButton operator3=new JButton("*");//乘号
	private JButton operator4=new JButton("/");//除号
	public static JTextArea textArea;//帮助结果集的输出文本域
	public  static JLabel time;
	public static Time t;//定义计时器对象
	private static int timeflag=0;//时间标识变量
	private static int helpflag=0;//帮助按钮标识变量
	private static int countflag=1;//选择计算框1或者2的标识变量
	private static int chooseflag=0;//抽取按钮标识变量
	private static double[] resultset=new double[7];
	public static void main(String args[])
	{
		tf1=tf;
		EventQueue.invokeLater(new Runnable(){//开启线程队列
			public void run()
			{
				try
				{
					Windows frame=new Windows();
					frame.setVisible(true);//设为可视
					frame.setResizable(false);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});		
	}
	public Windows()//构造方法
	{
		super();//调用父类构造方法
		final BorderLayout borderLayout_1=new BorderLayout();
		borderLayout_1.setVgap(10);
		getContentPane().setLayout(borderLayout_1);
		setBounds(280,50,700,650);
		setTitle("24点游戏");//标题
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final JPanel contentPanel=new JPanel();//第二层面板容器
		final BorderLayout borderLayout=new BorderLayout();
		borderLayout.setHgap(10);
		borderLayout.setVgap(10);
		contentPanel.setLayout(borderLayout);
		getContentPane().add(contentPanel);//向第一层面板容器添加第二层面板容器
		final GridLayout gridLayout=new GridLayout(1,0);//网格布局
		gridLayout.setHgap(10);
		JPanel numPanel=new JPanel();//随机数字面板容器
		contentPanel.add(numPanel,BorderLayout.NORTH);
		numPanel.setLayout(gridLayout);//网格布局，占满全格
		numFields=new NumField[4];//4位随机数的文本框数组
		for(int i=0;i<numFields.length;i++)//随机号码数字区域
		{
			numFields[i]=new NumField();
			numPanel.add(numFields[i]);
			//numFields[i].setLocation(i*5, 450);
			//numFields[i].addMouseListener(this);
			//numFields[i].addMouseMotionListener(this);
		}
		final JPanel calculatePanel=new JPanel();
		calculatePanel.setLayout(new BorderLayout());
		contentPanel.add(calculatePanel,BorderLayout.SOUTH);//计分面板添加到第二层容器
		final FlowLayout flowLayout=new FlowLayout(FlowLayout.CENTER,10,10);//流式布局
		final JPanel centerPanel=new JPanel();//中部面板
		centerPanel.setLayout(flowLayout);
		contentPanel.add(centerPanel,BorderLayout.CENTER);//向contentPanel添加中部面板

		for(int k=0;k<5;k++)//初始化
		{
		
				num[k]=new JTextField(4);
				num1[k]=new JTextField(4);
				num2[k]=new JTextField(4);
				num[k].setFont(numfont);
				num1[k].setFont(numfont);
				num2[k].setFont(numfont);
				num[k].setEditable(false);
				num1[k].setEditable(false);
				num2[k].setEditable(false);
				num[k].setBackground(Color.white);
				num1[k].setBackground(Color.white);
				num2[k].setBackground(Color.white);
		}

		op.setText("     =");//等号
		op1.setText("     =");//等号
		op2.setText("     =");//等号
		op.setSize(15, 15);
		op1.setSize(15, 15);
		op2.setSize(15, 15);
		op.setFont(numfont);
		op1.setFont(numfont);
		op2.setFont(numfont);
		op.setEditable(false);
		op1.setEditable(false);
		op2.setEditable(false);
		
			for(int i=0;i<5;i++)//布局第一行
			{
				if(i==3)
				{
					centerPanel.add(op);
				}
				else			
				{
					centerPanel.add(num[i]);
				}
			
			}
			count1=new JButton("计算");
			count1.addActionListener(new ActionListener(){//第一行的计算按钮

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub

					if(num[0].getText().equals("")||num[2].getText().equals("")||num[1].getText().equals(""))
					{
						javax.swing.JOptionPane.showMessageDialog(null,"请选择随机数字和操作符！");
						return;
					}
					
					double number1=Double.parseDouble(num[0].getText());
					double number2=Double.parseDouble(num[2].getText());

					String op=num[1].getText();
					if(op.equals("+"))
					{
						num[4].setText((number1+number2)+"");
					}
					if(op.equals("-"))
					{
						num[4].setText((number1-number2)+"");
					}
					if(op.equals("*"))
					{
						num[4].setText((number1*number2)+"");
					}
					if(op.equals("/"))
					{
						num[4].setText((number1/number2)+"");
					}
					resultGive1(e);
					flag=2;
				}
				
			});
			centerPanel.add(count1);
			
			for(int i=0;i<5;i++)//布局第二行
			{
				if(i==3)
				{
					centerPanel.add(op1);
				}
				else			
				{
					centerPanel.add(num1[i]);
				}
			
			}
			count2=new JButton("计算");
			count2.addActionListener(new ActionListener()//第二行的计算按钮
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					double number1=0;
					double number2=0;
					if(flag==1)
					{
						javax.swing.JOptionPane.showMessageDialog(null,"请先计算第一行的算式！");
						return;
					}
					if(num1[0].getText().equals("")||num1[2].getText().equals("")||num1[1].getText().equals(""))
					{
						javax.swing.JOptionPane.showMessageDialog(null,"请选择随机数字和操作符！");
						return;
					}
					
						number1=Double.parseDouble(num1[0].getText());
				
						number2=Double.parseDouble(num1[2].getText());
					

					String op=num1[1].getText();
					if(op.equals("+"))
					{
						num1[4].setText((number1+number2)+"");
					}
					if(op.equals("-"))
					{
						num1[4].setText((number1-number2)+"");
					}
					if(op.equals("*"))
					{
						num1[4].setText((number1*number2)+"");
					}
					if(op.equals("/"))
					{
						num1[4].setText((number1/number2)+"");
					}
					resultGive2(e);
					flag=3;
				}
				
			});
			centerPanel.add(count2);
			
			for(int i=0;i<5;i++)//布局第三行
			{
				if(i==3)
				{
					centerPanel.add(op2);
				}
				else			
				{
					centerPanel.add(num2[i]);
				}
			
			}
			count3=new JButton("计算");
			count3.addActionListener(new ActionListener()//第三行的计算按钮
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					if(flag==1||flag==2)
					{
						javax.swing.JOptionPane.showMessageDialog(null,"请先计算第一和第二行的算式！");
						return;
					}
					if(num2[0].getText().equals("")||num2[2].getText().equals("")||num2[1].getText().equals(""))
					{
						javax.swing.JOptionPane.showMessageDialog(null,"请选择随机数字和操作符！");
						return;
					}
					double number1=Double.parseDouble(num2[0].getText());
					double number2=Double.parseDouble(num2[2].getText());
					String op=num2[1].getText();
					if(op.equals("+"))
					{
						num2[4].setText((number1+number2)+"");
					}
					if(op.equals("-"))
					{
						num2[4].setText((number1-number2)+"");
					}
					if(op.equals("*"))
					{
						num2[4].setText((number1*number2)+"");
					}
					if(op.equals("/"))
					{
						num2[4].setText((number1/number2)+"");
					}
					flag=4;
					
				}
				
			});
			centerPanel.add(count3);
			
			//布局四则运算按钮
			operator1.setFont(numfont);
			operator2.setFont(numfont);
			operator3.setFont(numfont);
			operator4.setFont(numfont);
			centerPanel.add(operator1);
			centerPanel.add(operator2);
			centerPanel.add(operator3);
			centerPanel.add(operator4);//中部面板布局
			summit=new JButton("提交");
			summit.setFont(numfont);
			centerPanel.add(summit);
			summit.addActionListener(new ActionListener()//提交按钮
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					if(flag==1||flag==2||flag==3)
					{
						javax.swing.JOptionPane.showMessageDialog(null,"请先计算第一、第二和第三行的算式！");
						return;
					}
					if(num2[0].getText().equals("")||num2[2].getText().equals("")||num2[1].equals(""))
					{
						javax.swing.JOptionPane.showMessageDialog(null,"请选择随机数字和操作符！");
						return;
					}
					summit(e);
					t.stop();
					if(Double.parseDouble(num2[4].getText())>23.99&&Double.parseDouble(num2[4].getText())<24.01)
					{
						if((t.time/1000)<=10)
						{
							calculateField.setText("得分：100分！！你很棒喔！");
						}
						else if((t.time/1000>10&&(t.time/1000)<=30))
						{
							calculateField.setText("得分："+(100-t.time/1000*0.5)+"分！还不错，加油喔！");
						}
						else if((t.time/1000)>30&&(t.time/1000)<=60)
						{
							calculateField.setText("得分："+((100-t.time/1000*0.6))+"分！还好，不算太差！");
						}
						else if((t.time/1000)>60&&(t.time/1000)<=90)
						{
							calculateField.setText("得分："+((100-t.time/1000*0.9))+"分！有点慢了！加快速度");
						}
						else
						{
							calculateField.setText("得分：0分!你太慢了！！！");
						}
					}
					else
					{
						calculateField.setText("计算错误！得分：0分");
					}
					
				}
				
			});
			
			
			//布局结果面板
			JPanel resultPanel=new JPanel();
			resultPanel.setLayout(new BorderLayout());
			centerPanel.add(resultPanel);
			final JScrollPane scrollPane=new JScrollPane();
			textArea=new JTextArea(6,60);
			textArea.setLineWrap(true);
			textArea.setText("1：点击开始选择随机数。\n2：点击抽取获得随机数。\n3：点击随机数可以选择数字到计算框1中，" +
					"点击另一随机数可以设置在计算框2中。\n4：点击第一和第二行的结果框都可以选择数字到下一行的计算框中！\n" +
					"5:每个数字只能选择一次，位置错误请重置！\n6:必须逐行计算，每行结果用于下一行算式！");
			textArea.setEditable(false);
			final JLabel label=new JLabel("帮助按钮的结果：");
			scrollPane.setViewportView(textArea);
			resultPanel.add(scrollPane,BorderLayout.CENTER);
			resultPanel.add(label,BorderLayout.NORTH);
			//计时器布局
			time=new JLabel("计时器:");
			time.setFont(numfont);
			resultPanel.add(time,BorderLayout.SOUTH);
			
			//设置四则运算按钮
			operator1.addActionListener(new ActionListener()//加号
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					if(flag==1)
					{
						num[1].setText("+");
					}
					if(flag==2)
					{
						num1[1].setText("+");
					}
					if(flag==3)
					{
						num2[1].setText("+");
					}
				}
				
			});
			operator2.addActionListener(new ActionListener()//减号
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					if(flag==1)
					{
						num[1].setText("-");
					}
					if(flag==2)
					{
						num1[1].setText("-");
					}
					if(flag==3)
					{
						num2[1].setText("-");
					}
				}
				
			});
			operator3.addActionListener(new ActionListener()//乘号
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					if(flag==1)
					{
						num[1].setText("*");
					}
					if(flag==2)
					{
						num1[1].setText("*");
					}
					if(flag==3)
					{
						num2[1].setText("*");
					}
				}
				
			});
			operator4.addActionListener(new ActionListener()//除号
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					if(flag==1)
					{
						num[1].setText("/");
					}
					if(flag==2)
					{
						num1[1].setText("/");
					}
					if(flag==3)
					{
						num2[1].setText("/");
					}
				}
				
			});
		
		final JLabel label_1=new JLabel();
		label_1.setFont(new Font("",Font.BOLD,20));
		label_1.setText("请选择数字和符号!                           分数：");
		calculatePanel.add(label_1,BorderLayout.WEST);
		calculateField=new JTextField();//计分文本框
		calculatePanel.add(calculateField);
		calculateField.setEditable(false);
		final JLabel logoLabel=new JLabel();
		logoLabel.setFont(new Font("隶书",Font.PLAIN,60));//标题
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(logoLabel,BorderLayout.NORTH);
		logoLabel.setText("24点游戏");
		final JPanel controlPanel=new JPanel();//最下方控制面板
		final FlowLayout flowLayout1=new FlowLayout();
		flowLayout.setHgap(25);
		controlPanel.setLayout(flowLayout1);
		getContentPane().add(controlPanel,BorderLayout.SOUTH);
		final JButton startButton=new JButton("开始");//开始按钮
		startButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(final ActionEvent e) 
			{
				// TODO Auto-generated method stub
				do_startButton_actionPerformed(e);
				textArea.setText("");
				Algorithm.n=1;
				Operator.resultstr.removeAll(Operator.resultstr);
				flag=1;
				countflag=1;
				timeflag=0;
				helpflag=0;
				chooseflag=0;
				num[0].setText("");
				num[1].setText("");
				num[2].setText("");
				num[4].setText("");
				num1[0].setText("");
				num1[1].setText("");
				num1[2].setText("");
				num1[4].setText("");
				num2[0].setText("");
				num2[1].setText("");
				num2[2].setText("");
				num2[4].setText("");
				calculateField.setText("");	
				textArea.setText("1：点击开始选择随机数。\n2：点击抽取获得随机数。\n3：点击随机数可以选择数字到计算框1中，" +
						"点击另一随机数可以设置在计算框2中。\n4：点击第一和第二行的结果框都可以选择数字到下一行的计算框中！\n" +
						"5:每个数字只能选择一次，位置错误请重置！\n6:必须逐行计算，每行结果用于下一行算式！");
				//t.stop();
			}
			
		});
		controlPanel.add(startButton);
		
		final JButton chooseButton=new JButton("抽取");//抽取按钮
		chooseButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(final ActionEvent e) 
			{
				// TODO Auto-generated method stub
				if(timeflag==1)
				{
					javax.swing.JOptionPane.showMessageDialog(null,"题目已抽取！请做题！");
					return;
				}
				if(numFields[0].getText().equals(""))
				{
					javax.swing.JOptionPane.showMessageDialog(null,"请先按开始，选取随机数！");
					return;
				}
				do_chooseButton_actionPerformed(e);			
				t=new Time();
				t.flag=true;
				t.start();
				timeflag=1;
				chooseflag=1;
			}
			
		});
		controlPanel.add(chooseButton);
		
		final JButton resetButton=new JButton("重置");//抽取按钮
		resetButton.addActionListener(new ActionListener()
		{
			 
			@Override
			public void actionPerformed(final ActionEvent e) 
			{
				// TODO Auto-generated method stub
				do_resetButton_actionPerformed(e);
				flag=1;
				for(int i=0;i<5;i++)
				{
					num[i].setText("");
					num1[i].setText("");
					num2[i].setText("");
				}
				num[3].setText("     =");
				num1[3].setText("     =");
				num2[3].setText("     =");
				flag=1;
				countflag=1;
				helpflag=0;
				textArea.setText("");
				Algorithm.n=1;
				Operator.resultstr.removeAll(Operator.resultstr);
			}
			
		});
		controlPanel.add(resetButton);

		
		final JButton helpButton=new JButton("帮助");//帮助按钮
		helpButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(final ActionEvent e) 
			{
				// TODO Auto-generated method stub
				if(chooseflag==1)
				{
					if(helpflag==1)
					{
						javax.swing.JOptionPane.showMessageDialog(null,"帮助结果已给出！按开始重新做题！");
						return;
					}
					if(numFields[0].getText().equals(""))
					{
						javax.swing.JOptionPane.showMessageDialog(null,"请先按开始，选取随机数！");
						return;
					}
					summit(e);
					textArea.setText("");
				double a=Double.parseDouble(numFields[0].getText());//.parseInt(numFields[0].getText());
				double b=Double.parseDouble(numFields[1].getText());
				double c=Double.parseDouble(numFields[2].getText());
				double d=Double.parseDouble(numFields[3].getText());
				Algorithm.helpButtonAction(a,b,c,d);//调用帮助按钮函数
				if(num2[4].getText().equals("24"))
				{
					if((t.time/1000)<=10)
					{
						calculateField.setText("得分：100分！！你也太牛了吧！");
					}
					else if((t.time/1000>10&&(t.time/1000)<=30))
					{
						calculateField.setText("得分："+(100-t.time/1000*0.5)+"分！挺快，答案仅供参考！");
					}
					else if((t.time/1000)>30&&(t.time/1000)<=60)
					{
						calculateField.setText("得分："+((100-t.time/1000*0.6))+"分！还行！");
					}
					else if((t.time/1000)>60&&(t.time/1000)<=90)
					{
						calculateField.setText("得分："+((100-t.time/1000*0.9))+"分！哇，你想了很久耶！！");
					}
					else
					{
						calculateField.setText("得分：0分!敢不敢再快点？！！！");
					}
				}
				else
				{
					if((t.time/1000)<=10)
					{
						calculateField.setText("得分：0分！！你也太菜了吧？想都不想？！");
					}
					else if((t.time/1000>10&&(t.time/1000)<=30))
					{
						calculateField.setText("得分："+(100-t.time/1000*2)+"分！这题无解还是搞不定？！");
					}
					else if((t.time/1000)>30&&(t.time/1000)<=60)
					{
						calculateField.setText("得分："+((100-t.time/1000*1.5))+"分！真的不行吗？让我看看！");
					}
					else if((t.time/1000)>60&&(t.time/1000)<=90)
					{
						calculateField.setText("得分："+((100-t.time/1000*1.1))+"分！屈服了吗？给你答案！！！");
					}
					else
					{
						calculateField.setText("得分：0分!看来你真的想了很久耶！！！");
					}
				}
				t.stop();
				helpflag=1;
				for(int i=0;i<5;i++)
				{
					num[i].setEditable(false);
					num1[i].setEditable(false);
					num2[i].setEditable(false);
				}
				javax.swing.JOptionPane.showMessageDialog(null,"答案已给出！请按开始重新选题！");
			}	
				else
				{
					return;
				}
			}
		});
		controlPanel.add(helpButton);
		
		final JButton exitButton=new JButton("退出");//退出
		exitButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(final ActionEvent e) 
			{
				// TODO Auto-generated method stub
				do_exitButton_actionPerformed(e);
			}		
		});
		controlPanel.add(exitButton);
		//设置系统托盘
		if(SystemTray.isSupported())
		{
			URL url=Windows.class.getResource("exit.png");
			ImageIcon icon=new ImageIcon(url);
			Image image=icon.getImage();
			TrayIcon trayIcon=new TrayIcon(image);
			trayIcon.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					if(e.getClickCount()==2)
					{
						showFrame();
					}
				}
			});
			trayIcon.setToolTip("24点游戏");
			PopupMenu popupMenu=new PopupMenu();
			MenuItem exit=new MenuItem("退出");
			exit.addActionListener(new ActionListener()
			{
				public void actionPerformed(final ActionEvent e)
				{
					System.exit(0);
				}
			});
			popupMenu.add(exit);
			trayIcon.setPopupMenu(popupMenu);
			SystemTray systemTray=SystemTray.getSystemTray();
			try
			{
				systemTray.add(trayIcon);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}//托盘设置结束
				
	}//构造函数结束
	
	public void showFrame() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

	class RandomNum extends Thread //生成随机数字的内部线程类
	{
		private boolean stop=false;
		@Override
		public void run()
		{
			while(!stop)
			{
				for(final NumField nf:numFields)
				{
					try
					{
						Thread.sleep(1);
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
					final int num=(int)(Math.random()*10+1);
					EventQueue.invokeLater(new Runnable()
					{
						public void run()
						{
							nf.setText(num+"");
						}
					});
				}
			}
		}
		public void stopWindows()
		{
			this.stop=true;
		}
	}
	
	protected void do_startButton_actionPerformed(final ActionEvent e)//开始按钮事件
	{
		if(randomThread!=null)
			randomThread.stopWindows();
		if(t!=null)
			t.stop();
		randomThread=new RandomNum();
		randomThread.start();
		for(int i=0;i<4;i++)
		{
			numFields[i].removeMouseListener(this);
			numFields[i].setBackground(Color.white);
		}
		num[4].removeMouseListener(this);
		num1[4].removeMouseListener(this);
		num[4].setBackground(Color.white);
		num1[4].setBackground(Color.white);
		summit.setEnabled(true);
	}
	
	protected void do_chooseButton_actionPerformed(final ActionEvent e)//抽取按钮事件
	{
		if(randomThread!=null)
			randomThread.stopWindows();
		try
		{
			randomThread.join();
		}
		catch(InterruptedException e1)
		{
			e1.printStackTrace();
		}
		for(int i=0;i<4;i++)
		{
			numFields[i].removeMouseListener(this);
			numFields[i].addMouseListener(this);
		}
		summit.setEnabled(true);
		count1.setEnabled(true);
		count2.setEnabled(true);
		count3.setEnabled(true);
		operator1.setEnabled(true);
		operator2.setEnabled(true);
		operator3.setEnabled(true);
		operator4.setEnabled(true);

	}
	protected void do_resetButton_actionPerformed(final ActionEvent e)//重置
	{
		for(int i=0;i<4;i++)
		{
			numFields[i].removeMouseListener(this);
			numFields[i].addMouseListener(this);
			numFields[i].setBackground(Color.white);
		}
		num[4].removeMouseListener(this);
		num1[4].removeMouseListener(this);
		num[4].addMouseListener(this);
		num1[4].addMouseListener(this);
		num[4].setBackground(Color.white);
		num1[4].setBackground(Color.white);
	}
	protected void do_exitButton_actionPerformed(final ActionEvent e) //退出按钮事件
	{
		System.exit(0);							// 退出程序
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(flag==1)
		{
			if(countflag==1)
			{
				tf=(JTextField) e.getSource();
				number=tf.getText();
				num[0].setText(number);
				countflag=2;
				tf.removeMouseListener(this);
				tf.setBackground(Color.red);
				return;
			}
			if(countflag==2)
			{
				tf=(JTextField) e.getSource();
				number=tf.getText();
				num[2].setText(number);
				countflag=1;
				tf.removeMouseListener(this);
				tf.setBackground(Color.red);
				return;
			}

		}
		if(flag==2)
		{
			if(countflag==1)
			{
				tf=(JTextField) e.getSource();
				number=tf.getText();
				num1[0].setText(number);
				countflag=2;
				tf.removeMouseListener(this);
				tf.setBackground(Color.red);
				return;
			}
			if(countflag==2)
			{
				tf=(JTextField) e.getSource();
				number=tf.getText();
				num1[2].setText(number);
				countflag=1;	
				tf.removeMouseListener(this);
				tf.setBackground(Color.red);
				return;
			}
		}
		if(flag==3)
		{
			if(countflag==1)
			{
				tf=(JTextField) e.getSource();
				number=tf.getText();
				num2[0].setText(number);
				countflag=2;
				tf.removeMouseListener(this);
				tf.setBackground(Color.red);
				return;
			}
			if(countflag==2)
			{
				tf=(JTextField) e.getSource();
				number=tf.getText();
				num2[2].setText(number);
				countflag=1;
				tf.removeMouseListener(this);
				tf.setBackground(Color.red);
				return;
				
			}
		}
		

	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	protected void summit(final ActionEvent e)//帮助按钮；按下后所有键不可按
	{
		summit.setEnabled(false);
		count1.setEnabled(false);
		count2.setEnabled(false);
		count3.setEnabled(false);
		operator1.setEnabled(false);
		operator2.setEnabled(false);
		operator3.setEnabled(false);
		operator4.setEnabled(false);
	}
	protected void resultGive1(final ActionEvent e)//未按计算按钮1，计算结果框不能按
	{
		num[4].removeMouseListener(this);
		num[4].addMouseListener(this);

	}
	protected void resultGive2(final ActionEvent e)//未按计算按钮2，计算结果框不能按
	{
		num1[4].removeMouseListener(this);
		num1[4].addMouseListener(this);
	}

}
