/*
 * Jordan Stone
 * stone@drexel.edu
 * CS338:GUI, Personal Project
 * 
 * Interface is the active class for this project. It includes main and creates the GUI.
 */

package main;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainFrame extends JFrame{
	
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
                MainFrame ex = new MainFrame();
                ex.setVisible(true);
                ex.setResizable(false);
            }
        });
	}
	
	
//Component Construction
	
	public MainFrame(){
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
				StringSelection sel = new StringSelection(calcField.getText());
				Clipboard cl = Toolkit.getDefaultToolkit().getSystemClipboard();
				cl.setContents(sel,sel);
			}
        }); 
        edit.add(eMenuItem);
        
        eMenuItem = new JMenuItem("Paste");
        eMenuItem.setMnemonic(KeyEvent.VK_P);
        eMenuItem.setToolTipText("Paste Number into Result Box");
        eMenuItem.addActionListener(new ActionListener(){ //Note: May give this its own class as it will be called from key commands
			public void actionPerformed(ActionEvent e) {
				Clipboard cl = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable clip = cl.getContents(cl);
				String regExp = "[\\x00-\\x20]*[+-]?(((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*";
				if (clip != null){
					try {
						if (clip.isDataFlavorSupported(DataFlavor.stringFlavor)) {
		                  String s = (String)(clip.getTransferData(DataFlavor.stringFlavor));
		                  if (s.matches(regExp)){
		                	  calcField.setText(s);
		                  }
		                }
					}catch (UnsupportedFlavorException ufe) {
						System.err.println("Flavor unsupported: " + ufe);
		            }catch (IOException ioe) {
		            	System.err.println("Data not available: " + ioe);
		            }
				}
				
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
		
		c.gridx = 1;
		temp = new JButton("8");
		temp.addActionListener(new numPressed(8));
		nums.add(temp,c);
		
		c.gridx = 0;
		temp = new JButton("7");
		temp.addActionListener(new numPressed(7));
		nums.add(temp,c);
		
		//Middle High Row
		c.gridy = 1;
		
		c.gridx = 2;
		temp = new JButton("6");
		temp.addActionListener(new numPressed(6));
		nums.add(temp,c);
		
		c.gridx = 1;
		temp = new JButton("5");
		temp.addActionListener(new numPressed(5));
		nums.add(temp,c);
		
		c.gridx = 0;
		temp = new JButton("4");
		temp.addActionListener(new numPressed(4));
		nums.add(temp,c);
		
		//Middle Low Row
		c.gridy = 2;
		
		c.gridx = 2;
		temp = new JButton("3");
		temp.addActionListener(new numPressed(3));
		nums.add(temp,c);
		
		c.gridx = 1;
		temp = new JButton("2");
		temp.addActionListener(new numPressed(2));
		nums.add(temp,c);
		
		c.gridx = 0;
		temp = new JButton("1");
		temp.addActionListener(new numPressed(1));
		nums.add(temp,c);
		
		//Bottom Row
		c.gridy = 3;
		
		c.gridx = 2;
		nums.add(new JButton("."),c);
		
		c.gridx = 0;
		c.gridwidth = 2;
		temp = new JButton("0");
		temp.addActionListener(new numPressed(0));
		nums.add(temp,c);
		
		return nums;
	}
	
	public static Component mainGrid(){
		int MAXHEIGHT = 5; //How many rows
		int MAXWIDTH = 4; //How many columns
		
		JPanel gridSpace = new JPanel();
		gridSpace.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton temp;
		
		c.insets = new Insets(3,3,3,3); //Grid Spacing
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0;
		
	//Top Row, Filled right to left
		c.gridy = MAXHEIGHT - 5;
		
		c.gridx = MAXWIDTH;
		temp = new JButton("2-/");
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.Sqrt(),"Sqrt"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 1;
		temp = new JButton("x^2");
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.Square(),"Sqr"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 2;
		temp = new JButton("C");
		temp.addActionListener(new ActionListener(){ //Listener for C. Clears all.
			public void actionPerformed(ActionEvent e) {
				calcField.setText("0"); 
				dispLabel.setText(" "); //Clear the display label
				firstDigit = true;
			}
		});
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 3;
		temp = new JButton("CE");
		temp.addActionListener(new ActionListener(){ //Listener for CE. Clears current entry but not function.
			public void actionPerformed(ActionEvent e) {
				calcField.setText("0"); 
				firstDigit = true;
			}
		});
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 4;
		temp = new JButton("<=");
		temp.addActionListener(new ActionListener(){ //Listener for <=
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		gridSpace.add(temp,c);
		
		
	//Second Row
		c.gridy = MAXHEIGHT - 4;
		
		c.gridx = MAXWIDTH;
		temp = new JButton("x-/");
		temp.addActionListener(new twoVarFuncPressed(new MathFunc.Yroot()," Yroot "));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 1;
		temp = new JButton("x^y");
		temp.addActionListener(new twoVarFuncPressed(new MathFunc.Pow()," ^ "));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 2;
		temp = new JButton("+-");
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.Neg(),"Negate"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 3;
		temp = new JButton("n!");
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.Fact(), "Fact")); 
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 4;
		temp = new JButton("Mod");
		temp.addActionListener(new twoVarFuncPressed(new MathFunc.Mod(), "Mod"));
		gridSpace.add(temp,c);
		
	//Second Row
		c.gridy = MAXHEIGHT - 3;
		
		c.gridx = MAXWIDTH;
		temp = new JButton("%"); //Note: This one is a little bit odd. Will take more time to implement correctly.
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.Percent(),"Per"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 1;
		temp = new JButton("/");
		temp.addActionListener(new twoVarFuncPressed(new MathFunc.Div()," / "));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 4;
		c.gridwidth = 3;
		c.gridheight = 4;
		gridSpace.add(numberGrid(),c);
		
		c.gridwidth = 1;
		c.gridheight = 1;	
		
	//Third Row
		c.gridy = MAXHEIGHT - 2;
		
		c.gridx = MAXWIDTH;
		temp = new JButton("1/X");
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.Inverse(),"Reciproc"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 1;
		temp = new JButton("*");
		temp.addActionListener(new twoVarFuncPressed(new MathFunc.Mult()," * "));
		gridSpace.add(temp,c);
		
	//Fourth Row
		c.gridy = MAXHEIGHT - 1;
		
		c.gridx = MAXWIDTH;
		c.gridheight = 2;
		c.weighty = 0.5;
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
		temp.addActionListener(new twoVarFuncPressed(new MathFunc.Minus()," - "));
		gridSpace.add(temp,c);
		
	//Fifth Row
		c.gridy = MAXHEIGHT;
		
		c.gridx = MAXWIDTH - 1;
		temp = new JButton("+");
		temp.addActionListener(new twoVarFuncPressed(new MathFunc.Plus()," + "));
		gridSpace.add(temp,c);

		return gridSpace;
	}
	
	public static Component memList(){
		JPanel listFrame = new JPanel();
		listFrame.setLayout(new BorderLayout());
		listFrame.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		listFrame.setPreferredSize(new Dimension(160, 180));
		
		JLabel topLab = new JLabel("Memory",SwingConstants.CENTER);
		topLab.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		memStrings = new DefaultListModel<Double>(); //Serialized vals saved here
		memStrings.addElement(55.0); //Note: Disable rounding of shown values
		
		list = new JList<Double>(memStrings); //Put serialized values in this
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		list.addListSelectionListener(new ListSelectionListener(){ //Listener for on click of element
			public void valueChanged(ListSelectionEvent arg0) {
				int index = list.getSelectedIndex();
				if (index > -1){ //Not a non-existent index
					calcField.setText(Double.toString(memStrings.getElementAt(index)));
				}
			}
		}); 
		
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
				int index = list.getSelectedIndex();
				memStrings.remove(index);
				if (memStrings.size() != 0){
					if (index == memStrings.getSize()){
						index--;
					}
					list.setSelectedIndex(index);
			        list.ensureIndexIsVisible(index);
				}
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
		
		listFrame.add(topLab, "North");
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
			double val = Double.parseDouble(calcField.getText());
			val = func.function(val);
			if(dispLabel.getText().equals(" ")){
				dispLabel.setText(eq + "(" + calcField.getText() + ")");
			}else{
				dispLabel.setText(eq + "(" + dispLabel.getText() + ")");
			}
			
			calcField.setText(Double.toString(val));
		}
	}
	
	public static class twoVarFuncPressed implements ActionListener{ //Listener for Function Buttons
		private MathFunc func; //What function is this
		private String eq; //Used for label display
		
		public twoVarFuncPressed(MathFunc fun, String equ){
			func = fun;
			eq = equ;
		}
		
		public void actionPerformed(ActionEvent e) {
			hold = func;
			hold.setVar(Double.parseDouble(calcField.getText()));
			dispLabel.setText(calcField.getText() + dispLabel.getText() + eq);
			firstDigit = true;
		}
	}
	
}
