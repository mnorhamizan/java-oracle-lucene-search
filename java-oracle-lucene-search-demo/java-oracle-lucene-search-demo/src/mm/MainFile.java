package mm;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class MainFile {
	
	public static void main(String[] args)
	{
		JPanel panel = new JPanel(new GridLayout(10,1));//edited
		JPanel panel2 = new JPanel();//edited
		JFrame frame = new JFrame("Oracle Lucene");
		ButtonPanel buttonPanel = new ButtonPanel(args);

		
		
		frame.add(buttonPanel);
		
		frame.setSize(1367, 725);//edited
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
