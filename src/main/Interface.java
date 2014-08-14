/*
 * Jordan Stone
 * stone@drexel.edu
 * CS338:GUI, Personal Project
 * 
 * Interface is the active class for this project. It includes main and creates the GUI.
 */

package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class Interface {
	
	static JList<String> list;

	public static void main(String[] args){
		JFrame frame = new JFrame("HandyCalc");
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());

		p.add(displayBar(), "North");
		p.add(bottomComponents());
//		p.add(mainGrid(), BorderLayout.CENTER);
//		p.add(memList(), BorderLayout.WEST);
		frame.getContentPane().add(p, BorderLayout.CENTER);
				
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ensure process is ended
	}
	
	
//Component Construction
	
	public static Component displayBar(){
		JPanel tools = new JPanel();
		tools.setLayout(new BoxLayout(tools,BoxLayout.Y_AXIS));
		
		tools.add(Box.createRigidArea(new Dimension(0,5))); //Space at Top

		JPanel spacing = new JPanel();
		spacing.setLayout(new BoxLayout(spacing,BoxLayout.X_AXIS));
		spacing.add(Box.createRigidArea(new Dimension(7,0))); //Space at Left
		
		
		JPanel labelSpace = new JPanel(); //Components inside spacing
		labelSpace.setLayout(new BorderLayout());
		
		JLabel dispLabel = new JLabel(" ", SwingConstants.RIGHT); //Update Field
		labelSpace.add(dispLabel, "East");
		
		JTextField calcField = new JTextField("0"); //Main Display
		calcField.setHorizontalAlignment(JTextField.RIGHT);	
		labelSpace.add(calcField, "South");		
		
		
		spacing.add(labelSpace);
		
		spacing.add(Box.createRigidArea(new Dimension(7,0))); //Space at Right
		
		tools.add(spacing);
		
		tools.add(Box.createRigidArea(new Dimension(0,5))); //Space at Bottom
		
		return tools;
	}
	
	public static Component bottomComponents(){
		JPanel bot = new JPanel();
		bot.setLayout(new BorderLayout());
		
		bot.add(mainGrid());
		bot.add(memList(),"West");
		
		return bot;
	}
	
	public static Component numberGrid(){
		JPanel nums = new JPanel();
		nums.setLayout(new GridBagLayout());
		nums.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3,3,3,3); //Grid Spacing
		c.fill = GridBagConstraints.BOTH;
		
		c.gridheight = 1;
		c.gridwidth = 1;
		
		//Top Row
		c.gridx = 2;
		nums.add(new JButton("9"),c);
		
		c.gridx = 0;
		nums.add(new JButton("8"),c);
		
		c.gridx = 1;
		nums.add(new JButton("7"),c);
		
		//Middle High Row
		c.gridx = 2;
		c.gridy = 1;
		nums.add(new JButton("6"),c);
		
		c.gridx = 0;
		nums.add(new JButton("5"),c);
		
		c.gridx = 1;
		nums.add(new JButton("4"),c);
		
		//Middle Low Row
		c.gridx = 2;
		c.gridy = 2;
		nums.add(new JButton("3"),c);
		
		c.gridx = 0;
		nums.add(new JButton("2"),c);
		
		c.gridx = 1;
		nums.add(new JButton("1"),c);
		
		//Bottom Row
		c.gridx = 2;
		c.gridy = 3;
		nums.add(new JButton("."),c);
		
		c.gridx = 0;
		c.gridwidth = 2;
		nums.add(new JButton("0"),c);
		
		return nums;
	}
	
	public static Component mainGrid(){
		int MAXHEIGHT = 4; //How many rows
		int MAXWIDTH = 4; //How many columns
		
		JPanel gridSpace = new JPanel();
		gridSpace.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(3,3,3,3); //Grid Spacing
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 1;
		c.gridwidth = 1; 
		
		//Top Row. Filled right to left.
		c.gridy = MAXHEIGHT - 4;
		
		c.gridx = MAXWIDTH;
		gridSpace.add(new JButton("-/"),c);
		
		c.gridx = MAXWIDTH - 1;
		gridSpace.add(new JButton("+-"),c);
		
		c.gridx = MAXWIDTH - 2;
		gridSpace.add(new JButton("C"),c);
		
		c.gridx = MAXWIDTH - 3;
		gridSpace.add(new JButton("CE"),c);
		
		c.gridx = MAXWIDTH - 4;
		gridSpace.add(new JButton("<="),c);
		
		
		//Second Row.
		c.gridy = MAXHEIGHT - 3;
		
		c.gridx = MAXWIDTH;
		gridSpace.add(new JButton("%"),c);
		
		c.gridx = MAXWIDTH - 1;
		gridSpace.add(new JButton("/"),c);
		
		
		c.gridx = MAXWIDTH - 4;
		c.gridy = MAXWIDTH - 3;
		c.gridwidth = 3;
		c.gridheight = 4;
		gridSpace.add(numberGrid(),c);
		
		c.gridwidth = 1;
		c.gridheight = 1;
		
/*		
		c.gridx = MAXWIDTH - 2;
		gridSpace.add(new JButton("9"),c);
		
		c.gridx = MAXWIDTH - 3;
		gridSpace.add(new JButton("8"),c);
		
		c.gridx = MAXWIDTH - 4;
		gridSpace.add(new JButton("7"),c);
*/
		
		//Third Row.
		c.gridy = MAXHEIGHT - 2;
		
		c.gridx = MAXWIDTH;
		gridSpace.add(new JButton("1/x"),c);
		
		c.gridx = MAXWIDTH - 1;
		gridSpace.add(new JButton("*"),c);

/*
		c.gridx = MAXWIDTH - 2;
		gridSpace.add(new JButton("6"),c);
		
		c.gridx = MAXWIDTH - 3;
		gridSpace.add(new JButton("5"),c);
		
		c.gridx = MAXWIDTH - 4;
		gridSpace.add(new JButton("4"),c);
*/
		
		//Fourth Row.
		c.gridy = MAXHEIGHT - 1;
		
		c.gridx = MAXWIDTH;
		c.gridheight = 2;
		gridSpace.add(new JButton("="),c);
		
		c.gridx = MAXWIDTH - 1;
		c.gridheight = 1;
		gridSpace.add(new JButton("-"),c);

/*
		c.gridx = MAXWIDTH - 2;
		gridSpace.add(new JButton("3"),c);
		
		c.gridx = MAXWIDTH - 3;
		gridSpace.add(new JButton("2"),c);
		
		c.gridx = MAXWIDTH - 4;
		gridSpace.add(new JButton("1"),c);
*/
		
		//Fifth Row.
		c.gridy = MAXHEIGHT;
		
		c.gridx = MAXWIDTH - 1;
		gridSpace.add(new JButton("+"),c);

/*
		c.gridx = MAXWIDTH - 2;
		gridSpace.add(new JButton("."),c);
		
		c.gridx = MAXWIDTH - 4;
		c.gridwidth = 2;
		gridSpace.add(new JButton("0"),c);
*/
		return gridSpace;
	}
	
	public static Component memList(){
		JPanel listFrame = new JPanel();
		listFrame.setLayout(new BorderLayout());
		listFrame.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		DefaultListModel<String> memVals = new DefaultListModel<String>(); //Serialized vals saved here
		memVals.addElement("derp");
		list = new JList<String>(memVals); //Put serialized values in this
		listFrame.setPreferredSize(new Dimension(100, 180));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
//		list.addListSelectionListener(new ElementPressed()); //Add a listener
		
		JScrollPane scroller = new JScrollPane(list);
		scroller.setPreferredSize(new Dimension(100,280));
		
		listFrame.add(list);
		
		return listFrame;
	}
	
	
}
