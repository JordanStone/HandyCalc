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

public class Interface extends JFrame{
	
	static JTextField calcField;
	static JLabel dispLabel;
	
	static JList<Double> list; //Displays memory list
//	static ArrayList<Double> memVals; //Holds actual values of data in memory. Note: Trying to use this is breaking everything.
	static DefaultListModel<Double> memStrings; //Holds formatted strings of values in memory
	
	static MathFunc hold; //Used to mark a function that uses two (or more) variables

	static Boolean firstDigit;

	public static void main (String[] args){
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Interface ex = new Interface();
                ex.setVisible(true);
                ex.setResizable(false);
            }
        });
	}
	
	
//Component Construction
	
	public Interface(){
		buildComponents();
	}
	
	private void buildComponents() {

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem("Exit");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(eMenuItem);
        
        
        JMenu edit = new JMenu("Edit");
        edit.setMnemonic(KeyEvent.VK_E);
        
        eMenuItem = new JMenuItem("Copy");
        eMenuItem.setMnemonic(KeyEvent.VK_C);
        eMenuItem.setToolTipText("Copy Number Currently in Result Box");
        eMenuItem.addActionListener(new ActionListener(){ //Note: May give this its own class as it will be called from key commands
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
        }); 
        edit.add(eMenuItem);
        
        eMenuItem = new JMenuItem("Paste");
        eMenuItem.setMnemonic(KeyEvent.VK_P);
        eMenuItem.setToolTipText("Paste Number into Result Box");
        eMenuItem.addActionListener(new ActionListener(){ //Note: May give this its own class as it will be called from key commands
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
        });
        edit.add(eMenuItem);
        
        eMenuItem = new JMenuItem("Undo");
        eMenuItem.setMnemonic(KeyEvent.VK_U);
        eMenuItem.setToolTipText("Undoes Last Action / Calculation");
        eMenuItem.addActionListener(new ActionListener(){ //Note: May give this its own class as it will be called from key commands
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
        });
        edit.add(eMenuItem);
        
        eMenuItem = new JMenuItem("Redo");
        eMenuItem.setMnemonic(KeyEvent.VK_R);
        eMenuItem.setToolTipText("Redoes Last Action / Calculation");
        eMenuItem.addActionListener(new ActionListener(){ //Note: May give this its own class as it will be called from key commands
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
        });
        edit.add(eMenuItem);
        
        
        JMenu help = new JMenu("Help");
        edit.setMnemonic(KeyEvent.VK_H);
        
        eMenuItem = new JMenuItem("About");
        eMenuItem.setMnemonic(KeyEvent.VK_A);
        eMenuItem.setToolTipText("Information About This Version of HandyCalc");
        eMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
        });
        help.add(eMenuItem);

        menubar.add(file);
        menubar.add(edit);
        menubar.add(Box.createHorizontalGlue());
        menubar.add(help);

        setJMenuBar(menubar);

		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());

		p.add(displayBar(), "North");
		p.add(bottomComponents());
		getContentPane().add(p);
				
		pack();
        
        setTitle("HandyCalc");

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
	public static Component displayBar(){
		JPanel tools = new JPanel();
		tools.setLayout(new BoxLayout(tools,BoxLayout.Y_AXIS));
		
		tools.add(Box.createRigidArea(new Dimension(0,5))); //Space at Top

		JPanel spacing = new JPanel();
		spacing.setLayout(new BoxLayout(spacing,BoxLayout.X_AXIS));
		spacing.add(Box.createRigidArea(new Dimension(7,0))); //Space at Left
		
		
		JPanel labelSpace = new JPanel(); //Components inside spacing
		labelSpace.setLayout(new BorderLayout());
		
		dispLabel = new JLabel(" ", SwingConstants.RIGHT); //Update Field
		labelSpace.add(dispLabel, "East");
		
		calcField = new JTextField("0"); //Main Display
		calcField.setHorizontalAlignment(JTextField.RIGHT);	
		calcField.setEditable(false);
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
		JButton temp;
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3,3,3,3); //Grid Spacing
		c.fill = GridBagConstraints.BOTH;
		
		c.gridheight = 1;
		c.gridwidth = 1;
		
		//Top Row
		c.gridx = 2;
		temp = new JButton("9");
		temp.addActionListener(new numPressed(9));
		nums.add(temp,c);
		
		c.gridx = 0;
		temp = new JButton("8");
		temp.addActionListener(new numPressed(8));
		nums.add(temp,c);
		
		c.gridx = 1;
		temp = new JButton("7");
		temp.addActionListener(new numPressed(7));
		nums.add(temp,c);
		
		//Middle High Row
		c.gridx = 2;
		c.gridy = 1;
		temp = new JButton("6");
		temp.addActionListener(new numPressed(6));
		nums.add(temp,c);
		
		c.gridx = 0;
		temp = new JButton("5");
		temp.addActionListener(new numPressed(5));
		nums.add(temp,c);
		
		c.gridx = 1;
		temp = new JButton("4");
		temp.addActionListener(new numPressed(4));
		nums.add(temp,c);
		
		//Middle Low Row
		c.gridx = 2;
		c.gridy = 2;
		temp = new JButton("3");
		temp.addActionListener(new numPressed(3));
		nums.add(temp,c);
		
		c.gridx = 0;
		temp = new JButton("2");
		temp.addActionListener(new numPressed(2));
		nums.add(temp,c);
		
		c.gridx = 1;
		temp = new JButton("1");
		temp.addActionListener(new numPressed(1));
		nums.add(temp,c);
		
		//Bottom Row
		c.gridx = 2;
		c.gridy = 3;
		nums.add(new JButton("."),c);
		
		c.gridx = 0;
		c.gridwidth = 2;
		temp = new JButton("0");
		temp.addActionListener(new numPressed(0));
		nums.add(temp,c);
		
		return nums;
	}
	
	public static Component mainGrid(){
		int MAXHEIGHT = 4; //How many rows
		int MAXWIDTH = 4; //How many columns
		
		JPanel gridSpace = new JPanel();
		gridSpace.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton temp;
		
		c.insets = new Insets(3,3,3,3); //Grid Spacing
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 1;
		c.gridwidth = 1; 
		
	//Top Row, Filled right to left
		c.gridy = MAXHEIGHT - 4;
		
		c.gridx = MAXWIDTH;
		temp = new JButton("-/");
		temp.addActionListener(new oneVarFuncPressed(new Sqrt(),"Sqrt"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 1;
		temp = new JButton("+-");
		temp.addActionListener(new oneVarFuncPressed(new Neg(),"Negate"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 2;
		temp = new JButton("C");
		temp.addActionListener(new ActionListener(){ //Listener for C
			public void actionPerformed(ActionEvent e) {
				calcField.setText("0"); 
				dispLabel.setText(" "); //Clear the display label
				firstDigit = true;
			}
		});
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 3;
		temp = new JButton("CE");
		temp.addActionListener(new ActionListener(){ //Listener for CE
			public void actionPerformed(ActionEvent e) {
				calcField.setText("0"); 
				dispLabel.setText(" "); //Clear the display label
				firstDigit = true;
			}
		});
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 4;
		temp = new JButton("<=");
		temp.addActionListener(new ActionListener(){ //Listener for <=
			public void actionPerformed(ActionEvent e) {
				

			}
		});
		gridSpace.add(temp,c);
		
	//Second Row
		c.gridy = MAXHEIGHT - 3;
		
		c.gridx = MAXWIDTH;
		temp = new JButton("%"); //Note: This one is a little bit odd. Will take more time to implement correctly.
		temp.addActionListener(new oneVarFuncPressed(new Percent(),"Per"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 1;
		temp = new JButton("/");
		temp.addActionListener(new twoVarFuncPressed(new Div()," / "));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 4;
		c.gridy = MAXWIDTH - 3;
		c.gridwidth = 3;
		c.gridheight = 4;
		gridSpace.add(numberGrid(),c);
		
		c.gridwidth = 1;
		c.gridheight = 1;	
		
	//Third Row
		c.gridy = MAXHEIGHT - 2;
		
		c.gridx = MAXWIDTH;
		temp = new JButton("1/X");
		temp.addActionListener(new oneVarFuncPressed(new Inverse(),"Reciproc"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 1;
		temp = new JButton("*");
		temp.addActionListener(new twoVarFuncPressed(new Mult()," * "));
		gridSpace.add(temp,c);
		
	//Fourth Row
		c.gridy = MAXHEIGHT - 1;
		
		c.gridx = MAXWIDTH;
		c.gridheight = 2;
		temp = new JButton("=");
		temp.addActionListener(new ActionListener(){ //Listener for Equals
			public void actionPerformed(ActionEvent e) {
				if(hold != null){
					Double valOne = Double.parseDouble(calcField.getText()); 
					valOne = hold.function(valOne);
					calcField.setText(Double.toString(valOne)); 
					dispLabel.setText(" "); //Clear the display label
					firstDigit = true;
				}
			}
		});
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 1;
		c.gridheight = 1;
		temp = new JButton("-");
		temp.addActionListener(new twoVarFuncPressed(new Minus()," - "));
		gridSpace.add(temp,c);
		
	//Fifth Row
		c.gridy = MAXHEIGHT;
		
		c.gridx = MAXWIDTH - 1;
		temp = new JButton("+");
		temp.addActionListener(new twoVarFuncPressed(new Plus()," + "));
		gridSpace.add(temp,c);

		return gridSpace;
	}
	
	public static Component memList(){
		JPanel listFrame = new JPanel();
		listFrame.setLayout(new BorderLayout());
		listFrame.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		listFrame.setPreferredSize(new Dimension(160, 180));
		
		memStrings = new DefaultListModel<Double>(); //Serialized vals saved here
		memStrings.addElement(55.0); //Note: Disable rounding of shown values
		
		list = new JList<Double>(memStrings); //Put serialized values in this
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
//		list.addListSelectionListener(new ElementPressed()); //Add a listener for on click of element
		
		JScrollPane scroller = new JScrollPane(list);
		scroller.setPreferredSize(new Dimension(100,280));
		
		JPanel memButtons = new JPanel();
		memButtons.setLayout(new BoxLayout(memButtons,BoxLayout.X_AXIS));
		
		JButton temp;
		
		temp = new JButton("M+"); //Adds current val to mem
		temp.setPreferredSize(new Dimension(10,30));
		temp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
//				memVals.add(Double.parseDouble(calcField.getText()));
				memStrings.addElement(Double.parseDouble(calcField.getText())); //Probably add some sort of formatting here
			}
		});
		memButtons.add(temp);
		
		
		temp = new JButton("M-"); //Removes selected val from mem
		temp.setPreferredSize(new Dimension(10,30));
		temp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		memButtons.add(temp);
		
		
		temp = new JButton("MC"); //Clears entire mem
		temp.setPreferredSize(new Dimension(10,30));
		temp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
//				memVals.clear();
				memStrings.clear();
			}
		});
		memButtons.add(temp);
		
		
		listFrame.add(list);
		listFrame.add(memButtons,"South");
		
		return listFrame;
	}
	
//Listeners
	
	public static class numPressed implements ActionListener{ //Listener for Number Buttons
		private int nVal;
		
		
		public numPressed(int num){
			nVal = num;
			firstDigit = true;
		}
		public void actionPerformed(ActionEvent e) {
			double rv = nVal;
			if(firstDigit){
				calcField.setText(Double.toString(rv));
				firstDigit = false;
			}else{
				rv = (Double.parseDouble(calcField.getText()) * 10) + nVal;
				calcField.setText(Double.toString(rv));
			}
		}
	}
	
	public static class oneVarFuncPressed implements ActionListener{ //Listener for Function Buttons
		private MathFunc func;
		private String eq;
		
		public oneVarFuncPressed(MathFunc fun, String equ){
			func = fun;
			eq = equ;
		}
		
		public void actionPerformed(ActionEvent e) {
			Double val = Double.parseDouble(calcField.getText());
			val = func.function(val);
			dispLabel.setText(eq + "(" + dispLabel.getText() + ")");
			calcField.setText(Double.toString(val));
		}
	}
	
	public static class twoVarFuncPressed implements ActionListener{ //Listener for Function Buttons
		private MathFunc func; //What function is this
		private String eq; //Used for label display
//		Double one, two;
		
		public twoVarFuncPressed(MathFunc fun, String equ){
			func = fun;
			eq = equ;
		}
		
		public void actionPerformed(ActionEvent e) {
			hold = func;
			hold.setVar(Double.parseDouble(calcField.getText()));
			dispLabel.setText(calcField.getText() + dispLabel.getText() + eq);
			firstDigit = true;
//			Double valOne = Double.parseDouble(calcField.getText());
//			Double valTwo = Double.parseDouble(calcField.getText()); //Change this to actually make sense
//			valOne = func.twoVarFunction(valOne,valTwo);
//			calcField.setText(Double.toString(valOne));
		}
	}
	
	public static class backPressed implements ActionListener{ //Listener for <=
		
		public void actionPerformed(ActionEvent e) {


		}
	}
	
}
