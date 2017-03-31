import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;


public class myframe extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    public static int [][]newpixels;
    public static int [][]levels;
    public static int [][]original;
    private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myframe frame = new myframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public myframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(190, 34, 229, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterLevels = new JLabel("Enter Levels ");
		lblEnterLevels.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnterLevels.setBounds(25, 26, 97, 46);
		contentPane.add(lblEnterLevels);
		
		JButton btnCompress = new JButton("Compress");
		btnCompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int level=new Integer(0);
				
				System.out.println("Enter levels:-");
				level=Integer.parseInt(textField.getText());
				int img[][]=Main.readImage("lena.jpg");
				myframe.original=img;
				int [][] cpy=Main.compress( img , level );
				myframe.levels=cpy;
				System.out.println("end compress");
				int [][]igcpy=Main.decompress( cpy , level );
				myframe.newpixels=igcpy;
				
				
				
			}
		});
		btnCompress.setBounds(204, 132, 89, 23);
		contentPane.add(btnCompress);
		
		JButton btnDecompress = new JButton("Decompress");
		btnDecompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("end decompress");
				Main.writeImage(myframe.newpixels, "copy.jpg");
				int mean_square=Main.calc_mean_square_error(myframe.newpixels,myframe.original);
				textField_1.setText(Integer.toString(mean_square));
			}
		});
		btnDecompress.setBounds(190, 171, 112, 23);
		contentPane.add(btnDecompress);
		
		textField_1 = new JTextField();
		textField_1.setBounds(190, 80, 229, 29);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblMeanSquareError = new JLabel("Mean Square Error");
		lblMeanSquareError.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMeanSquareError.setBounds(10, 87, 150, 14);
		contentPane.add(lblMeanSquareError);
	}
}
