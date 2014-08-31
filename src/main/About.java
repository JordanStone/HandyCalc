package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/*
 * Builds the About frame when required
 */
public class About extends JFrame{
	
	ThemeFactory theme;
	
	String versNum = "0.5 (Beta)";
	String copy = "(Not Really) 2014 Jordan Stone";
	
	public About(){
		super("About");
		theme = new ThemeFactory();
		buildComponents();
	}
	
	private void buildComponents(){

//Top
		Font titleFont = new Font("Century Gothic",Font.PLAIN, 40);
		Font baseFont = new Font("Century Gothic",Font.PLAIN, 12);
		
		JLabel title = theme.makeLabel("HandyCalc",SwingConstants.CENTER, titleFont);
		
//Center		
		JPanel center = theme.makePanel(new BorderLayout());
		
		center.add(new JSeparator(SwingConstants.HORIZONTAL), "North");
		
		JLabel comp = theme.makeLabel("Jordan Stone",SwingConstants.CENTER,baseFont);
		JLabel vers = theme.makeLabel("Version " + versNum,SwingConstants.CENTER,baseFont);
		JLabel cop =  theme.makeLabel("Copyright " + copy,SwingConstants.CENTER,baseFont);
		
		JPanel text = theme.makePanel(new BoxLayout(null,BoxLayout.Y_AXIS));
		text.add(comp);
		text.add(vers);
		text.add(cop);
		center.add(text);
		
//Bottom		
		JPanel bot = theme.makePanel(new BorderLayout());
		
		JButton ok = theme.makeButton("OK", null);
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		bot.add(ok, "East");
		
		
//Put It All Together	
		JPanel p = theme.makePanel(new BorderLayout());
		p.add(title,"North");
		p.add(center,"Center");
		p.add(bot,"South");
		
		p.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
		
		getContentPane().add(p);
		
		pack();
		
		setTitle("About");
		
		setLocationRelativeTo(null);
	}
	
}
