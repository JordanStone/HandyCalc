package main;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

/*
 * Builds the About frame when required
 */
public class About extends JFrame{
	
	ThemeFactory theme;
	
	public About(){
		super("About");
		theme = new ThemeFactory();
		buildComponents();
	}
	
	private void buildComponents(){
		
		JLabel title = theme.makeLabel("HandyCalc",SwingConstants.CENTER, new Font("Century Gothic",Font.PLAIN, 40));
		
		JPanel north = theme.makePanel(new BorderLayout());
		north.setSize(100,200);
		north.add(title,"North");
		
		north.add(new JSeparator(SwingConstants.HORIZONTAL),"Center");
		north.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		
		JPanel p = theme.makePanel(new BorderLayout());
		p.setSize(200,200);
		p.add(north,"North");
		getContentPane().add(p);
		
		pack();
		
		setTitle("About");
		
		setLocationRelativeTo(null);
	}
	
}
