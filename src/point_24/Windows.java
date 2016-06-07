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
	private JTextField[] num=new JTextField[5];//center�ĵ�һ��
	private JTextField[] num1=new JTextField[5];//center�ĵڶ���
	private JTextField[] num2=new JTextField[5];//center�ĵ�����
	private JTextField op=new JTextField(4);//�Ⱥ�
	private JTextField op1=new JTextField(4);//�Ⱥ�
	private JTextField op2=new JTextField(4);//�Ⱥ�
	private String number;
	private JButton count1;
	private JButton count2;
	private JButton count3;
	private static JButton summit;
	private static JTextField tf;//����任�ı���1
	private static JTextField tf1;//����任�ı���2
	private int flag=1;//�����㰴ť��ı�־��1Ϊ��һ�м��㣬2Ϊ�ڶ��м��㣬3Ϊ�����м���
	private static final Font numfont=new Font("",Font.BOLD,25);
	private JTextField calculateField;//�Ʒ��ı���
	private JButton operator1=new JButton("+");//�Ӻ�
	private JButton operator2=new JButton("-");//����
	private JButton operator3=new JButton("*");//�˺�
	private JButton operator4=new JButton("/");//����
	public static JTextArea textArea;//���������������ı���
	public  static JLabel time;
	public static Time t;//�����ʱ������
	private static int timeflag=0;//ʱ���ʶ����
	private static int helpflag=0;//������ť��ʶ����
	private static int countflag=1;//ѡ������1����2�ı�ʶ����
	private static int chooseflag=0;//��ȡ��ť��ʶ����
	private static double[] resultset=new double[7];
	public static void main(String args[])
	{
		tf1=tf;
		EventQueue.invokeLater(new Runnable(){//�����̶߳���
			public void run()
			{
				try
				{
					Windows frame=new Windows();
					frame.setVisible(true);//��Ϊ����
					frame.setResizable(false);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});		
	}
	public Windows()//���췽��
	{
		super();//���ø��๹�췽��
		final BorderLayout borderLayout_1=new BorderLayout();
		borderLayout_1.setVgap(10);
		getContentPane().setLayout(borderLayout_1);
		setBounds(280,50,700,650);
		setTitle("24����Ϸ");//����
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final JPanel contentPanel=new JPanel();//�ڶ����������
		final BorderLayout borderLayout=new BorderLayout();
		borderLayout.setHgap(10);
		borderLayout.setVgap(10);
		contentPanel.setLayout(borderLayout);
		getContentPane().add(contentPanel);//���һ�����������ӵڶ����������
		final GridLayout gridLayout=new GridLayout(1,0);//���񲼾�
		gridLayout.setHgap(10);
		JPanel numPanel=new JPanel();//��������������
		contentPanel.add(numPanel,BorderLayout.NORTH);
		numPanel.setLayout(gridLayout);//���񲼾֣�ռ��ȫ��
		numFields=new NumField[4];//4λ��������ı�������
		for(int i=0;i<numFields.length;i++)//���������������
		{
			numFields[i]=new NumField();
			numPanel.add(numFields[i]);
			//numFields[i].setLocation(i*5, 450);
			//numFields[i].addMouseListener(this);
			//numFields[i].addMouseMotionListener(this);
		}
		final JPanel calculatePanel=new JPanel();
		calculatePanel.setLayout(new BorderLayout());
		contentPanel.add(calculatePanel,BorderLayout.SOUTH);//�Ʒ������ӵ��ڶ�������
		final FlowLayout flowLayout=new FlowLayout(FlowLayout.CENTER,10,10);//��ʽ����
		final JPanel centerPanel=new JPanel();//�в����
		centerPanel.setLayout(flowLayout);
		contentPanel.add(centerPanel,BorderLayout.CENTER);//��contentPanel����в����

		for(int k=0;k<5;k++)//��ʼ��
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

		op.setText("     =");//�Ⱥ�
		op1.setText("     =");//�Ⱥ�
		op2.setText("     =");//�Ⱥ�
		op.setSize(15, 15);
		op1.setSize(15, 15);
		op2.setSize(15, 15);
		op.setFont(numfont);
		op1.setFont(numfont);
		op2.setFont(numfont);
		op.setEditable(false);
		op1.setEditable(false);
		op2.setEditable(false);
		
			for(int i=0;i<5;i++)//���ֵ�һ��
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
			count1=new JButton("����");
			count1.addActionListener(new ActionListener(){//��һ�еļ��㰴ť

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub

					if(num[0].getText().equals("")||num[2].getText().equals("")||num[1].getText().equals(""))
					{
						javax.swing.JOptionPane.showMessageDialog(null,"��ѡ��������ֺͲ�������");
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
			
			for(int i=0;i<5;i++)//���ֵڶ���
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
			count2=new JButton("����");
			count2.addActionListener(new ActionListener()//�ڶ��еļ��㰴ť
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					double number1=0;
					double number2=0;
					if(flag==1)
					{
						javax.swing.JOptionPane.showMessageDialog(null,"���ȼ����һ�е���ʽ��");
						return;
					}
					if(num1[0].getText().equals("")||num1[2].getText().equals("")||num1[1].getText().equals(""))
					{
						javax.swing.JOptionPane.showMessageDialog(null,"��ѡ��������ֺͲ�������");
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
			
			for(int i=0;i<5;i++)//���ֵ�����
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
			count3=new JButton("����");
			count3.addActionListener(new ActionListener()//�����еļ��㰴ť
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					if(flag==1||flag==2)
					{
						javax.swing.JOptionPane.showMessageDialog(null,"���ȼ����һ�͵ڶ��е���ʽ��");
						return;
					}
					if(num2[0].getText().equals("")||num2[2].getText().equals("")||num2[1].getText().equals(""))
					{
						javax.swing.JOptionPane.showMessageDialog(null,"��ѡ��������ֺͲ�������");
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
			
			//�����������㰴ť
			operator1.setFont(numfont);
			operator2.setFont(numfont);
			operator3.setFont(numfont);
			operator4.setFont(numfont);
			centerPanel.add(operator1);
			centerPanel.add(operator2);
			centerPanel.add(operator3);
			centerPanel.add(operator4);//�в���岼��
			summit=new JButton("�ύ");
			summit.setFont(numfont);
			centerPanel.add(summit);
			summit.addActionListener(new ActionListener()//�ύ��ť
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					if(flag==1||flag==2||flag==3)
					{
						javax.swing.JOptionPane.showMessageDialog(null,"���ȼ����һ���ڶ��͵����е���ʽ��");
						return;
					}
					if(num2[0].getText().equals("")||num2[2].getText().equals("")||num2[1].equals(""))
					{
						javax.swing.JOptionPane.showMessageDialog(null,"��ѡ��������ֺͲ�������");
						return;
					}
					summit(e);
					t.stop();
					if(Double.parseDouble(num2[4].getText())>23.99&&Double.parseDouble(num2[4].getText())<24.01)
					{
						if((t.time/1000)<=10)
						{
							calculateField.setText("�÷֣�100�֣�����ܰ�ร�");
						}
						else if((t.time/1000>10&&(t.time/1000)<=30))
						{
							calculateField.setText("�÷֣�"+(100-t.time/1000*0.5)+"�֣�����������ร�");
						}
						else if((t.time/1000)>30&&(t.time/1000)<=60)
						{
							calculateField.setText("�÷֣�"+((100-t.time/1000*0.6))+"�֣����ã�����̫�");
						}
						else if((t.time/1000)>60&&(t.time/1000)<=90)
						{
							calculateField.setText("�÷֣�"+((100-t.time/1000*0.9))+"�֣��е����ˣ��ӿ��ٶ�");
						}
						else
						{
							calculateField.setText("�÷֣�0��!��̫���ˣ�����");
						}
					}
					else
					{
						calculateField.setText("������󣡵÷֣�0��");
					}
					
				}
				
			});
			
			
			//���ֽ�����
			JPanel resultPanel=new JPanel();
			resultPanel.setLayout(new BorderLayout());
			centerPanel.add(resultPanel);
			final JScrollPane scrollPane=new JScrollPane();
			textArea=new JTextArea(6,60);
			textArea.setLineWrap(true);
			textArea.setText("1�������ʼѡ���������\n2�������ȡ����������\n3��������������ѡ�����ֵ������1�У�" +
					"�����һ��������������ڼ����2�С�\n4�������һ�͵ڶ��еĽ���򶼿���ѡ�����ֵ���һ�еļ�����У�\n" +
					"5:ÿ������ֻ��ѡ��һ�Σ�λ�ô��������ã�\n6:�������м��㣬ÿ�н��������һ����ʽ��");
			textArea.setEditable(false);
			final JLabel label=new JLabel("������ť�Ľ����");
			scrollPane.setViewportView(textArea);
			resultPanel.add(scrollPane,BorderLayout.CENTER);
			resultPanel.add(label,BorderLayout.NORTH);
			//��ʱ������
			time=new JLabel("��ʱ��:");
			time.setFont(numfont);
			resultPanel.add(time,BorderLayout.SOUTH);
			
			//�����������㰴ť
			operator1.addActionListener(new ActionListener()//�Ӻ�
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
			operator2.addActionListener(new ActionListener()//����
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
			operator3.addActionListener(new ActionListener()//�˺�
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
			operator4.addActionListener(new ActionListener()//����
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
		label_1.setText("��ѡ�����ֺͷ���!                           ������");
		calculatePanel.add(label_1,BorderLayout.WEST);
		calculateField=new JTextField();//�Ʒ��ı���
		calculatePanel.add(calculateField);
		calculateField.setEditable(false);
		final JLabel logoLabel=new JLabel();
		logoLabel.setFont(new Font("����",Font.PLAIN,60));//����
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(logoLabel,BorderLayout.NORTH);
		logoLabel.setText("24����Ϸ");
		final JPanel controlPanel=new JPanel();//���·��������
		final FlowLayout flowLayout1=new FlowLayout();
		flowLayout.setHgap(25);
		controlPanel.setLayout(flowLayout1);
		getContentPane().add(controlPanel,BorderLayout.SOUTH);
		final JButton startButton=new JButton("��ʼ");//��ʼ��ť
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
				textArea.setText("1�������ʼѡ���������\n2�������ȡ����������\n3��������������ѡ�����ֵ������1�У�" +
						"�����һ��������������ڼ����2�С�\n4�������һ�͵ڶ��еĽ���򶼿���ѡ�����ֵ���һ�еļ�����У�\n" +
						"5:ÿ������ֻ��ѡ��һ�Σ�λ�ô��������ã�\n6:�������м��㣬ÿ�н��������һ����ʽ��");
				//t.stop();
			}
			
		});
		controlPanel.add(startButton);
		
		final JButton chooseButton=new JButton("��ȡ");//��ȡ��ť
		chooseButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(final ActionEvent e) 
			{
				// TODO Auto-generated method stub
				if(timeflag==1)
				{
					javax.swing.JOptionPane.showMessageDialog(null,"��Ŀ�ѳ�ȡ�������⣡");
					return;
				}
				if(numFields[0].getText().equals(""))
				{
					javax.swing.JOptionPane.showMessageDialog(null,"���Ȱ���ʼ��ѡȡ�������");
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
		
		final JButton resetButton=new JButton("����");//��ȡ��ť
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

		
		final JButton helpButton=new JButton("����");//������ť
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
						javax.swing.JOptionPane.showMessageDialog(null,"��������Ѹ���������ʼ�������⣡");
						return;
					}
					if(numFields[0].getText().equals(""))
					{
						javax.swing.JOptionPane.showMessageDialog(null,"���Ȱ���ʼ��ѡȡ�������");
						return;
					}
					summit(e);
					textArea.setText("");
				double a=Double.parseDouble(numFields[0].getText());//.parseInt(numFields[0].getText());
				double b=Double.parseDouble(numFields[1].getText());
				double c=Double.parseDouble(numFields[2].getText());
				double d=Double.parseDouble(numFields[3].getText());
				Algorithm.helpButtonAction(a,b,c,d);//���ð�����ť����
				if(num2[4].getText().equals("24"))
				{
					if((t.time/1000)<=10)
					{
						calculateField.setText("�÷֣�100�֣�����Ҳ̫ţ�˰ɣ�");
					}
					else if((t.time/1000>10&&(t.time/1000)<=30))
					{
						calculateField.setText("�÷֣�"+(100-t.time/1000*0.5)+"�֣�ͦ�죬�𰸽����ο���");
					}
					else if((t.time/1000)>30&&(t.time/1000)<=60)
					{
						calculateField.setText("�÷֣�"+((100-t.time/1000*0.6))+"�֣����У�");
					}
					else if((t.time/1000)>60&&(t.time/1000)<=90)
					{
						calculateField.setText("�÷֣�"+((100-t.time/1000*0.9))+"�֣��ۣ������˺ܾ�Ү����");
					}
					else
					{
						calculateField.setText("�÷֣�0��!�Ҳ����ٿ�㣿������");
					}
				}
				else
				{
					if((t.time/1000)<=10)
					{
						calculateField.setText("�÷֣�0�֣�����Ҳ̫���˰ɣ��붼���룿��");
					}
					else if((t.time/1000>10&&(t.time/1000)<=30))
					{
						calculateField.setText("�÷֣�"+(100-t.time/1000*2)+"�֣������޽⻹�Ǹ㲻������");
					}
					else if((t.time/1000)>30&&(t.time/1000)<=60)
					{
						calculateField.setText("�÷֣�"+((100-t.time/1000*1.5))+"�֣���Ĳ��������ҿ�����");
					}
					else if((t.time/1000)>60&&(t.time/1000)<=90)
					{
						calculateField.setText("�÷֣�"+((100-t.time/1000*1.1))+"�֣��������𣿸���𰸣�����");
					}
					else
					{
						calculateField.setText("�÷֣�0��!������������˺ܾ�Ү������");
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
				javax.swing.JOptionPane.showMessageDialog(null,"���Ѹ������밴��ʼ����ѡ�⣡");
			}	
				else
				{
					return;
				}
			}
		});
		controlPanel.add(helpButton);
		
		final JButton exitButton=new JButton("�˳�");//�˳�
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
		//����ϵͳ����
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
			trayIcon.setToolTip("24����Ϸ");
			PopupMenu popupMenu=new PopupMenu();
			MenuItem exit=new MenuItem("�˳�");
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
		}//�������ý���
				
	}//���캯������
	
	public void showFrame() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

	class RandomNum extends Thread //����������ֵ��ڲ��߳���
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
	
	protected void do_startButton_actionPerformed(final ActionEvent e)//��ʼ��ť�¼�
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
	
	protected void do_chooseButton_actionPerformed(final ActionEvent e)//��ȡ��ť�¼�
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
	protected void do_resetButton_actionPerformed(final ActionEvent e)//����
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
	protected void do_exitButton_actionPerformed(final ActionEvent e) //�˳���ť�¼�
	{
		System.exit(0);							// �˳�����
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
	protected void summit(final ActionEvent e)//������ť�����º����м����ɰ�
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
	protected void resultGive1(final ActionEvent e)//δ�����㰴ť1�����������ܰ�
	{
		num[4].removeMouseListener(this);
		num[4].addMouseListener(this);

	}
	protected void resultGive2(final ActionEvent e)//δ�����㰴ť2�����������ܰ�
	{
		num1[4].removeMouseListener(this);
		num1[4].addMouseListener(this);
	}

}
