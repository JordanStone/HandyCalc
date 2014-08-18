/*
 * Jordan Stone
 * stone@drexel.edu
 * CS338:GUI, Personal Project
 * 
 * BlackTheme: SubClass of ThemeFactory. Implements a dark theme variant.
 */

package main;

import java.awt.*;

import javax.swing.*;

public class BlackTheme extends ThemeFactory {

	public BlackTheme(){
		super();
	}
	
	public JFrame makeFrame(){
		JFrame temp = new JFrame();
		
		return temp;
	}
	
	public JPanel makePanel(LayoutManager lay){
		JPanel temp = new JPanel();
		temp.setBackground(Color.DARK_GRAY);
		if(lay instanceof BoxLayout){ //Deals with BoxLayout issue where JPanel must reference itself in call
			temp.setLayout(new BoxLayout(temp,((BoxLayout) lay).getAxis()));
		}else temp.setLayout(lay);
		
		return temp;
	}
	
	public JPanel makeHighlightPanel(LayoutManager lay){
		JPanel temp = new JPanel();
		temp.setBackground(Color.BLACK);
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
		
		temp.setBackground(Color.BLACK);
		temp.setForeground(Color.WHITE);
		
		temp.setPreferredSize(size);
		
		return temp;
	}
	
	public JButton makeHighlightButton(String text, Dimension size){ //Main Button Style
		JButton temp = new JButton(text);
		
		temp.setBackground(Color.DARK_GRAY);
		temp.setForeground(Color.WHITE);
		
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
	
	public JLabel makeLabel(String text, int pos){
		JLabel temp = new JLabel(text, pos);
		temp.setForeground(Color.WHITE);
		
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
