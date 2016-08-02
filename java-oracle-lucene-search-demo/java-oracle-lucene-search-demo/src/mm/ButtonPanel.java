package mm;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ButtonPanel extends JPanel implements ItemListener, ActionListener {
	JTextField textField = new JTextField(20);
	JButton button1 = new JButton("Lucene");
	JButton button2 = new JButton("Oracle");
	JLabel searchResultHL = new JLabel("Lucene Results:");
	JLabel searchResultHO = new JLabel("Oracle Results:");
	JLabel searchResultCPUO = new JLabel("");
	JLabel searchResultRAMO = new JLabel("");
	JLabel searchResultHOTO = new JLabel("");
	JLabel searchResultHOTL = new JLabel("");
	JLabel searchResultCPUL = new JLabel("");
	JLabel searchResultRAML = new JLabel("");

	JLabel searchResultL = new JLabel("");
	JLabel searchResultO = new JLabel("");

	private OracleCon textSearch;
	private SearchFiles sF;

	
	public ButtonPanel(String[] arg){
		setLayout(new GridLayout(10, 10));
		JPanel pnl1 = new JPanel(new GridLayout(1, 1));
		JPanel pnl2 = new JPanel(new GridLayout(2, 2));
		JPanel pnl3 = new JPanel(new GridLayout(3, 3));
		JPanel pnl4 = new JPanel(new GridLayout(4, 4));
		
		pnl1.add(textField);
		pnl2.add(button1);
		pnl2.add(button2);
		pnl3.add(searchResultHL);
		pnl3.add(searchResultL);
		pnl3.add(searchResultHOTL);
		pnl3.add(searchResultCPUL);
		pnl3.add(searchResultRAML);
		pnl4.add(searchResultHO);
		pnl4.add(searchResultO);
		pnl4.add(searchResultHOTO);
		pnl4.add(searchResultCPUO);
		pnl4.add(searchResultRAMO);

		add(pnl1);
		add(pnl2);
		add(pnl3);
		add(pnl4);
		
		searchResultHL.setAlignmentY(CENTER_ALIGNMENT);
		searchResultHO.setAlignmentY(CENTER_ALIGNMENT);
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		sF = new SearchFiles(arg);
	}

	@Override
	public void actionPerformed(ActionEvent e)  {
		if(e.getSource() == button2){
		String text = textField.getText();
		System.out.println(text);
		
	    Runtime runtime = Runtime.getRuntime();
	    long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
	    Date start = new Date();
	    System.out.println("Available processors (cores): " + 
	    		runtime.availableProcessors());
	    int hits = OracleCon.getDataOracle(text);
	    
	    Date end = new Date();
	    System.out.println("Available processors (cores): " + 
	    		runtime.availableProcessors());
	    long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
	    System.out.println("Memory increased:" + (usedMemoryAfter-usedMemoryBefore));
	    
		String sResults = "Oracle hit(s):";
		sResults += hits + "";
		sResults += "";
		System.out.println(sResults);
		this.searchResultO.setText(sResults);
		searchResultHOTO.setText("Time taken: " + (end.getTime() - start.getTime()) + " milliseconds");
		searchResultCPUO.setText("Memory: " + (usedMemoryAfter-usedMemoryBefore) + " bytes");
		}
		else if(e.getSource() == button1) {
			String text = textField.getText();
			System.out.println(text);
			String s = "Lucene hit(s): ";
			int hits = 0;
			try {
			    Runtime runtime = Runtime.getRuntime();
			    long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
				Date start = new Date();
			    
				hits = SearchFiles.searchQuery(text);
				
				Date end = new Date();
			    System.out.println("Available processors (cores): " + 
			    		runtime.availableProcessors());
			    long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
			    System.out.println("Memory increased:" + (usedMemoryAfter-usedMemoryBefore));
				
				s += Integer.toString(hits);
				searchResultL.setText(s);
				searchResultHOTL.setText("Time taken: " + (end.getTime() - start.getTime()) + " milliseconds");
				searchResultCPUL.setText("Memory: " + (usedMemoryAfter-usedMemoryBefore) + " bytes");

				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
		}
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
