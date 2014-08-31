package main;

import java.awt.*;

import javax.swing.*;

public class WhiteTheme extends ThemeFactory {

	public WhiteTheme(){
		super();
	}
	
	public JFrame makeFrame(){
		JFrame temp = new JFrame();
		
		return temp;
	}
	
	public JPanel makePanel(LayoutManager lay){
		JPanel temp = new JPanel();
		temp.setBackground(new Color(210,210,210));
		if(lay instanceof BoxLayout){ //Deals with BoxLayout issue where JPanel must reference itself in call
			temp.setLayout(new BoxLayout(temp,((BoxLayout) lay).getAxis()));
		}else temp.setLayout(lay);
		
		return temp;
	}
	
	public JPanel makeHighlightPanel(LayoutManager lay){
		JPanel temp = new JPanel();
		temp.setBackground(new Color(150,150,150));
		if(lay instanceof BoxLayout){ //Deals with BoxLayout issue where JPanel must reference itself in call
			temp.setLayout(new BoxLayout(temp,((BoxLayout) lay).getAxis()));
		}else temp.setLayout(lay);
		
		return temp;
	}
	
	public JPanel makeNumPanel(LayoutManager lay){
		JPanel temp = new JPanel();
		temp.setBackground(Color.GRAY);
		if(lay instanceof BoxLayout){ //Deals with BoxLayout issue where JPanel must reference itself in call
			temp.setLayout(new BoxLayout(temp,((BoxLayout) lay).getAxis()));
		}else temp.setLayout(lay);
		
		return temp;
	}
	
	public JButton makeButton(String text, Dimension size){
		JButton temp = new JButton(text);
		
		temp.setForeground(Color.BLACK);
		
		temp.setPreferredSize(size);
		
		return temp;
	}
	
	public JButton makeHighlightButton(String text, Dimension size){ //Main Button Style
		JButton temp = new JButton(text);
		
		temp.setBackground(new Color(150,150,150));
		temp.setForeground(Color.BLACK);
		
		temp.setPreferredSize(size);
		
		return temp;
	}
	
	public JButton makeNumButton(String text, Dimension size){
		JButton temp = new JButton(text);
		temp.setBackground(Color.WHITE);
		temp.setForeground(Color.BLACK);
		
		temp.setPreferredSize(size);
		
		return temp;
	}
	
	public JLabel makeLabel(String text, int pos, Font f){
		JLabel temp = new JLabel(text, pos);
		temp.setFont(f);
		
		temp.setForeground(Color.BLACK);
		
		return temp;
	}
	
	public JList<Double> makeJListDouble(DefaultListModel<Double> vals){
		JList<Double> temp = new JList<Double>(vals);
		
		temp.setBackground(Color.WHITE);
		
		return temp;
	}
	
	public JTextField makeTextField(String text, int pos, Font f){
		JTextField temp = new JTextField(text, pos);
		temp.setFont(f);
		
		temp.setBackground(Color.WHITE);
		
		return temp;
	}
	
	public JMenuBar makeMenuBar(){
		JMenuBar temp = new JMenuBar();
		
		return temp;
	}
	
	public JMenu makeMenu(String name, int mnemonic){
		JMenu temp = new JMenu(name);
		temp.setMnemonic(mnemonic);
		
		return temp;
	}
	
	public JMenuItem makeMenuItem(String name, int mnemonic, String tooltip){
		JMenuItem temp = new JMenuItem(name);
		temp.setMnemonic(mnemonic);
		temp.setToolTipText(tooltip);
		
		return temp;
	}
}