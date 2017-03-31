package Implementation;

import java.awt.Dimension;

import javax.swing.*;

public class GUI extends JFrame {

	public JButton compress;
	public JPanel Panel_1;

    public JTextField Text;
    public JTextField Text2;
public GUI() {
		   
	      super("Compress");    
	   // ActionListener n=new Show_Number();
	   // Operator O=new Operator();       
	   
	   Panel_1=new JPanel();
	   
	   Text=new JTextField(null,21);    
	   Text2=new JTextField(null,21);
	   
	   
	   compress=new JButton("Compress");
	
	   Dimension Button_Dimension=new Dimension(100,50);
	   Dimension Text_Dimension=new Dimension(124,130);
	   Text.setPreferredSize(Text_Dimension);
	   Text2.setPreferredSize(Text_Dimension);
	 //Button Dimension
	    
	     compress.setPreferredSize(Button_Dimension);
	   Panel_1.add(Text);
	   Panel_1.add(compress);
	   Panel_1.add(Text2);
	   this.setContentPane(Panel_1);
}
}