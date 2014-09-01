package main;

import java.awt.*;

import javax.swing.*;

public class WhiteTheme extends ThemeFactory {

	public WhiteTheme(){
		super();
	}
	
	public JFrame makeFrame(){
		JFrame temp = super.makeFrame();
		
		return temp;
	}
	
	public JPanel makePanel(LayoutManager lay){
		JPanel temp = super.makePanel(lay);
		
		temp.setBackground(new Color(210,210,210));
		
		return temp;
	}
	
	public JPanel makeHighlightPanel(LayoutManager lay){
		JPanel temp = super.makeHighlightPanel(lay);
		
		temp.setBackground(new Color(150,150,150));
		
		return temp;
	}
	
	public JPanel makeNumPanel(LayoutManager lay){
		JPanel temp = super.makeNumPanel(lay);
		
		temp.setBackground(Color.GRAY);
		
		return temp;
	}
	
	public JButton makeButton(String text, Dimension size){
		JButton temp = super.makeButton(text,size);
		
		temp.setBackground(new Color(150,150,150));
		temp.setForeground(Color.BLACK);
		
		return temp;
	}
	
	public JButton makeHighlightButton(String text, Dimension size){ //Main Button Style
		JButton temp = super.makeHighlightButton(text,size);
		
		temp.setBackground(new Color(200,200,200));
		temp.setForeground(Color.BLACK);
		
		return temp;
	}
	
	public JButton makeNumButton(String text, Dimension size){
		JButton temp = super.makeNumButton(text,size);
		
		temp.setBackground(Color.WHITE);
		temp.setForeground(Color.BLACK);
		
		return temp;
	}
	
	public JLabel makeLabel(String text, int pos, Font f){
		JLabel temp = super.makeLabel(text,pos,f);
		
		temp.setForeground(Color.BLACK);
		
		return temp;
	}
	
	public JList<Double> makeJListDouble(DefaultListModel<Double> vals){
		JList<Double> temp = super.makeJListDouble(vals);
		
		temp.setBackground(Color.WHITE);
		
		return temp;
	}
	
	public JTextField makeTextField(String text, int pos, Font f){
		JTextField temp = super.makeTextField(text,pos,f);
		
		temp.setBackground(Color.WHITE);
		
		return temp;
	}
	
	public JMenuBar makeMenuBar(){
		JMenuBar temp = super.makeMenuBar();
		
		return temp;
	}
	
	public JMenu makeMenu(String name, int mnemonic){
		JMenu temp = super.makeMenu(name,mnemonic);
		
		return temp;
	}
	
	public JMenuItem makeMenuItem(String name, int mnemonic, String tooltip){
		JMenuItem temp = super.makeMenuItem(name,mnemonic,tooltip);
		
		return temp;
	}
}