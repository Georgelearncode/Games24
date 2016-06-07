package point_24;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NumField extends JTextField
{
	private static final Font numfont=new Font("",Font.BOLD,40);
	public NumField()
	{
		super();
		setHorizontalAlignment(SwingConstants.CENTER);
		setSize(50, 50);
		setFont(numfont);
		setFocusable(false);
	}
}
