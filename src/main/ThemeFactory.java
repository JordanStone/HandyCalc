/*
 * Jordan Stone
 * stone@drexel.edu
 * CS338:GUI, Personal Project
 * 
 * ThemeFactory: Factory class to allow for easy modification of GUI objects.
 */

package main;

import java.awt.*;

import javax.swing.*;

public class ThemeFactory {

	public ThemeFactory(){
		
	}
	
	public JFrame makeFrame(){
		JFrame temp = new JFrame();
		
		return temp;
	}
	
	public JPanel makePanel(LayoutManager lay){ //Main Panel Style
		JPanel temp = new JPanel();
		if(lay instanceof BoxLayout){ //Deals with BoxLayout issue where JPanel must reference itself in call
			temp.setLayout(new BoxLayout(temp,((BoxLayout) lay).getAxis()));
		}else temp.setLayout(lay);
		
		return temp;
	}
	
	public JPanel makeHighlightPanel(LayoutManager lay){ //Alternate Panel Style
		JPanel temp = new JPanel();
		if(lay instanceof BoxLayout){ //Deals with BoxLayout issue where JPanel must reference itself in call
			temp.setLayout(new BoxLayout(temp,((BoxLayout) lay).getAxis()));
		}else temp.setLayout(lay);
			
		return temp;
	}
	
	public JPanel makeNumPanel(LayoutManager lay){ //Panel Style Used on NumPad
		JPanel temp = new JPanel();
		temp.setLayout(lay);
		if(lay instanceof BoxLayout){ //Deals with BoxLayout issue where JPanel must reference itself in call
			temp.setLayout(new BoxLayout(temp,((BoxLayout) lay).getAxis()));
		}else temp.setLayout(lay);
		
		return temp;
	}
	
	public JButton makeButton(String text, Dimension size){ //Main Button Style
		JButton temp = new JButton(text);
		
		temp.setPreferredSize(size);
		
		return temp;
	}
	
	public JButton makeHighlightButton(String text, Dimension size){ //Alternate Button Style
		JButton temp = new JButton(text);
		
		temp.setPreferredSize(size);
		
		return temp;
	}
	
	public JButton makeNumButton(String text, Dimension size){ //Button Style Used on NumPad
		JButton temp = new JButton(text);
		
		temp.setPreferredSize(size);
		
		return temp;
	}
	
	public JLabel makeLabel(String text, int pos){
		JLabel temp = new JLabel(text,pos);
		
		return temp;
	}
	
	public JTextField makeTextField(String text){
		JTextField temp = new JTextField(text);
		
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
