/*
 * Jordan Stone
 * stone@drexel.edu
 * CS338:GUI, Personal Project
 * 
 * MainFrame: The active class for this project. It includes main and builds the GUI.
 */

package main;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainFrame extends JFrame{
	
	static JTextField calcField;
	static JLabel dispLabel;
	
	static JList<Double> list; //Displays memory list
	static DefaultListModel<Double> memVals; //Holds formatted strings of values in memory
	
	static MathFunc hold; //Used to mark a function that uses two (or more) variables

	static Boolean firstDigit; //Used to mark 
	
	static NumberFormat formatter; //Used to format double values to look correct when displayed
	
	static ThemeFactory theme; //Factory theme defining design of the program
	
	static int parenCount; //Used to ensure that parenthesis are balanced. If positive, more left parens than right. If zero, parens balanced.

//Main
	public static void main (String[] args){
		
		formatter = new DecimalFormat("#0"); //Initialize the formatter
		formatter.setMinimumFractionDigits(0);
		formatter.setMaximumFractionDigits(8);
		
		firstDigit = true;
		theme = new ThemeFactory();
		
		parenCount = 0;
		
		MainFrame main = new MainFrame();
        main.setVisible(true);
        main.setResizable(false);
	}
	
	
//Component Construction
	
	public MainFrame(){
		buildComponents();
	}
	
	private void buildComponents() {

        JMenuBar menubar = theme.makeMenuBar();

        JMenu file = theme.makeMenu("File",KeyEvent.VK_F);

        JMenuItem eMenuItem = theme.makeMenuItem("Exit",KeyEvent.VK_E,"Exit Application");
        eMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(eMenuItem);
        
        
        JMenu edit = theme.makeMenu("Edit",KeyEvent.VK_E);

        eMenuItem = theme.makeMenuItem("Copy",KeyEvent.VK_C,"Copy Number Currently in Result Box");
        eMenuItem.addActionListener(new ActionListener(){ //Note: May give this its own class as it will be called from key commands
			public void actionPerformed(ActionEvent e) {
				StringSelection sel = new StringSelection(calcField.getText());
				Clipboard cl = Toolkit.getDefaultToolkit().getSystemClipboard();
				cl.setContents(sel,sel);
			}
        }); 
        edit.add(eMenuItem);
        
        eMenuItem = theme.makeMenuItem("Paste",KeyEvent.VK_P,"Paste Number into Result Box");
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
        
        edit.addSeparator();
        
        eMenuItem = theme.makeMenuItem("Undo",KeyEvent.VK_U,"Undoes Last Action / Calculation");
        eMenuItem.addActionListener(new ActionListener(){ //Note: May give this its own class as it will be called from key commands
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
        });
        edit.add(eMenuItem);
        
        eMenuItem = theme.makeMenuItem("Redo",KeyEvent.VK_R,"Redoes Last Action / Calculation");
        eMenuItem.addActionListener(new ActionListener(){ //Note: May give this its own class as it will be called from key commands
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
        });
        edit.add(eMenuItem);

        
        JMenu help = theme.makeMenu("Help",KeyEvent.VK_H);
        
        eMenuItem = theme.makeMenuItem("View Help",KeyEvent.VK_V,"View Help Menu for Features of HandyCalc");
        eMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
        });
        help.add(eMenuItem);
        
        help.addSeparator();
        
        eMenuItem = theme.makeMenuItem("About",KeyEvent.VK_A,"Information About This Version of HandyCalc");
        eMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { //Open the About frame
				About ab = new About();
				ab.setSize(400,300);
				ab.setVisible(true);
				ab.setResizable(false);
			}
        });
        help.add(eMenuItem);

        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        setJMenuBar(menubar);

		JPanel p = theme.makePanel(new BorderLayout());

		p.add(displayBar(), "North");
		p.add(bottomComponents());
		getContentPane().add(p);
				
		pack();
        
        setTitle("HandyCalc");

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
	public static Component displayBar(){
		JPanel tools = theme.makePanel(new BoxLayout(null,BoxLayout.Y_AXIS)); //This null is fine. Refer to ThemeFactory.
		
		tools.add(Box.createRigidArea(new Dimension(0,5))); //Space at Top

		JPanel spacing = theme.makePanel(new BoxLayout(null,BoxLayout.X_AXIS)); //This null is fine. Refer to ThemeFactory.
		spacing.add(Box.createRigidArea(new Dimension(7,0))); //Space at Left
		
		
		JPanel labelSpace = theme.makePanel(new BorderLayout()); //Components inside spacing
		
		dispLabel = theme.makeLabel(" ", SwingConstants.RIGHT, null); //Update Field
		labelSpace.add(dispLabel, "East");
		
		calcField = theme.makeTextField("0",SwingConstants.RIGHT,new Font("Consolas", Font.PLAIN, 40)); //Main Display
		calcField.setHorizontalAlignment(SwingConstants.RIGHT); //This isn't being correctly aligned in the above line
		calcField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		calcField.setEditable(false);
		labelSpace.add(calcField, "South");		
			
		spacing.add(labelSpace);
		spacing.add(Box.createRigidArea(new Dimension(7,0))); //Space at Right
		tools.add(spacing);
		tools.add(Box.createRigidArea(new Dimension(0,5))); //Space at Bottom
		
		return tools;
	}
	
	public static Component bottomComponents(){
		JPanel bot = theme.makePanel(new BorderLayout());
		
		bot.add(mainGrid());
		bot.add(memList(),"West");
		
		return bot;
	}
	
	public static Component numberGrid(){
		JPanel nums = theme.makeNumPanel(new GridBagLayout());
		nums.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		JButton temp;
		Dimension size= new Dimension(45,45);
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3,3,3,3); //Grid Spacing
		c.fill = GridBagConstraints.BOTH;
		
		c.gridheight = 1;
		c.gridwidth = 1;
		
		//Top Row
		c.gridx = 2;
		temp = theme.makeNumButton("9", size);
		temp.addActionListener(new numPressed('9'));
		nums.add(temp,c);
		
		c.gridx = 1;
		temp = theme.makeNumButton("8", size);
		temp.addActionListener(new numPressed('8'));
		nums.add(temp,c);
		
		c.gridx = 0;
		temp = theme.makeNumButton("7", size);
		temp.addActionListener(new numPressed('7'));
		nums.add(temp,c);
		
		//Middle High Row
		c.gridy = 1;
		
		c.gridx = 2;
		temp = theme.makeNumButton("6", size);
		temp.addActionListener(new numPressed('6'));
		nums.add(temp,c);
		
		c.gridx = 1;
		temp = theme.makeNumButton("5", size);
		temp.addActionListener(new numPressed('5'));
		nums.add(temp,c);
		
		c.gridx = 0;
		temp = theme.makeNumButton("4", size);
		temp.addActionListener(new numPressed('4'));
		nums.add(temp,c);
		
		//Middle Low Row
		c.gridy = 2;
		
		c.gridx = 2;
		temp = theme.makeNumButton("3", size);
		temp.addActionListener(new numPressed('3'));
		nums.add(temp,c);
		
		c.gridx = 1;
		temp = theme.makeNumButton("2", size);
		temp.addActionListener(new numPressed('2'));
		nums.add(temp,c);
		
		c.gridx = 0;
		temp = theme.makeNumButton("1", size);
		temp.addActionListener(new numPressed('1'));
		nums.add(temp,c);
		
		//Bottom Row
		c.gridy = 3;
		
		c.gridx = 2;
		temp = theme.makeNumButton(".", size);
		temp.addActionListener(new numPressed('.'));
		nums.add(temp,c);
		
		c.gridx = 0;
		c.gridwidth = 2;
		temp = theme.makeNumButton("0", size);
		temp.addActionListener(new numPressed('0'));
		nums.add(temp,c);
		
		return nums;
	}
	
	public static Component mainGrid(){
		int MAXHEIGHT = 5; //How many rows
		int MAXWIDTH = 6; //How many columns
		
		JPanel gridSpace = theme.makePanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton temp;
		Dimension avgSize = new Dimension(55,35);
		Dimension longSize = new Dimension(64,35);
		
		c.insets = new Insets(3,3,3,3); //Grid Spacing
		
	//Top Row, Filled right to left
		c.gridy = MAXHEIGHT - 5;
		
		c.gridx = MAXWIDTH;
		temp = theme.makeHighlightButton("C", avgSize);
		temp.addActionListener(new ActionListener(){ //Listener for C. Clears all.
			public void actionPerformed(ActionEvent e) {
				firstDigit = true;
				calcField.setText("0"); //Clear the calculation field
				dispLabel.setText(" "); //Clear the display label
			}
		});
		
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 1;
		temp = theme.makeHighlightButton("CE", avgSize);
		temp.addActionListener(new ActionListener(){ //Listener for CE. Clears current entry but not function.
			public void actionPerformed(ActionEvent e) {
				firstDigit = true;
				calcField.setText("0"); //Clear the calculation field
			}
		});
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 2;
		temp = theme.makeHighlightButton("<=", longSize);
		temp.addActionListener(new ActionListener(){ //Listener for <=
			public void actionPerformed(ActionEvent e) {
				if(calcField.getText().length() > 1){
					String temp = calcField.getText();
					temp = temp.substring(0,temp.length()-1);
					calcField.setText(temp);
				}else{ //Removing all digits causes a revert back to 0, reset format
					calcField.setText("0");
				}
			}
		});
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 3;
		temp = theme.makeButton("x^2", avgSize);
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.Square(),"Sqr"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 4;
		temp = theme.makeButton("2-/", avgSize);
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.Sqrt(),"Sqrt"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 5;
		temp = theme.makeButton(")", longSize);
		// TODO Implement Parenthesis
		temp.addActionListener(new ActionListener(){ //Listener for Right Paren
			public void actionPerformed(ActionEvent e) {
				if(parenCount > 0){ //Unbalanced left paren is present
					//Check if there is anything after the left paren. If not, add the current val displayed in calc field to displabel
					String temp = dispLabel.getText();
					if (temp.charAt(temp.length()-1) == '('){ //Nothing after the latest left paren
						dispLabel.setText(dispLabel.getText() + calcField.getText() + ")");
					}else if (temp.charAt(temp.length()-1) == ' '){ //If there's a space, it's a two var eq
						dispLabel.setText(dispLabel.getText() + calcField.getText() + ")");
					}
					
					parenCount -= 1; //Decrement parenCount
				}
			}
		});
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 6;
		temp = theme.makeButton("(", longSize);
		// TODO Implement Parenthesis
		temp.addActionListener(new ActionListener(){ //Listener for Left Paren
			public void actionPerformed(ActionEvent e) {
				dispLabel.setText(dispLabel.getText() + " (");
				firstDigit = true;
				
//				calcField.setText("0");
				
				parenCount += 1; //Increment parenCount
			}
		});
		gridSpace.add(temp,c);
		
	//Second Row
		c.gridy = MAXHEIGHT - 4;
		
		c.gridx = MAXWIDTH;
		temp = theme.makeButton("+-", avgSize);
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.Neg(),"Negate"));
		
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 1;
		temp = theme.makeButton("n!", avgSize);
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.Fact(), "Fact")); 
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 2;
		temp = theme.makeButton(" Mod ", longSize);
		temp.addActionListener(new twoVarFuncPressed(new MathFunc.Mod(), " Mod "));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 3;
		temp = theme.makeButton("x^y", avgSize);
		temp.addActionListener(new twoVarFuncPressed(new MathFunc.Pow()," ^ "));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 4;
		temp = theme.makeButton("x-/", avgSize);
		temp.addActionListener(new twoVarFuncPressed(new MathFunc.Yroot()," Yroot "));
		
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 5;
		temp = theme.makeButton("ASin", longSize);
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.ASin(), "ASin"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 6;
		temp = theme.makeButton("Sin", longSize);
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.Sin(), "Sin"));
		gridSpace.add(temp,c);
		
	//Third Row
		c.gridy = MAXHEIGHT - 3;
		
		c.gridx = MAXWIDTH;
		temp = theme.makeButton("%", avgSize); //Not implemented. See MathFunc
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.Percent(),"Per"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 1;
		temp = theme.makeButton("/", avgSize);
		temp.addActionListener(new twoVarFuncPressed(new MathFunc.Div()," / "));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 4;
		c.gridwidth = 3;
		c.gridheight = 4;
		gridSpace.add(numberGrid(),c);
		
		c.gridwidth = 1;
		c.gridheight = 1;	
		
		c.gridx = MAXWIDTH - 5;
		temp = theme.makeButton("ACos", longSize);
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.ACos(), "ACos"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 6;
		temp = theme.makeButton("Cos", longSize);
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.Cos(), "Cos"));
		gridSpace.add(temp,c);
		
	//Fourth Row
		c.gridy = MAXHEIGHT - 2;
		
		c.gridx = MAXWIDTH;
		temp = theme.makeButton("1/X", avgSize);
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.Inverse(),"Reciproc"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 1;
		temp = theme.makeButton("*", avgSize);
		temp.addActionListener(new twoVarFuncPressed(new MathFunc.Mult()," * "));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 5;
		temp = theme.makeButton("ATan", longSize);
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.ATan(), "ATan"));
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 6;
		temp = theme.makeButton("Tan", longSize);
		temp.addActionListener(new oneVarFuncPressed(new MathFunc.Tan(), "Tan"));
		gridSpace.add(temp,c);
		
	//Fifth Row
		c.gridy = MAXHEIGHT - 1;
		
		c.gridx = MAXWIDTH;
		c.weighty = 1;
		c.gridheight = 2;
		c.fill = GridBagConstraints.VERTICAL;
		temp = theme.makeButton("=", avgSize);
		temp.addActionListener(new ActionListener(){ //Listener for Equals
			public void actionPerformed(ActionEvent e) {
				if(hold != null){
					Double valOne = Double.parseDouble(calcField.getText()); 
					valOne = hold.function(valOne);
//					calcField.setText(formatter.format(valOne)); 
					dispLabel.setText(" "); //Clear the display label
					firstDigit = true;
				}
			}
		});
		gridSpace.add(temp,c);
		
		c.gridx = MAXWIDTH - 1;
		c.gridheight = 1;
		temp = theme.makeButton("-", avgSize);
		temp.addActionListener(new twoVarFuncPressed(new MathFunc.Minus()," - "));
		gridSpace.add(temp,c);
		
	//Sixth Row
		c.gridy = MAXHEIGHT;
		
		c.gridx = MAXWIDTH - 1;
		temp = theme.makeButton("+", avgSize);
		temp.addActionListener(new twoVarFuncPressed(new MathFunc.Plus()," + "));
		gridSpace.add(temp,c);

		return gridSpace;
	}
	
	public static Component memList(){
		JPanel listFrame = theme.makeHighlightPanel(new BorderLayout());
		listFrame.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		listFrame.setPreferredSize(new Dimension(160, 180));
		
		JLabel topLab = theme.makeLabel("Memory",SwingConstants.CENTER, null);
		topLab.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		memVals = new DefaultListModel<Double>(); //Serialized vals saved here
		memVals.addElement(3.14159265); //Test value
		
		list = theme.makeJListDouble(memVals); //Put serialized values in this
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		list.addListSelectionListener(new ListSelectionListener(){ //Listener for on click of element
			public void valueChanged(ListSelectionEvent arg0) {
				int index = list.getSelectedIndex();
				if (index > -1){ //Not a non-existent index
					calcField.setText(formatter.format(memVals.getElementAt(index)));
				}
			}
		}); 
		JScrollPane scroller = new JScrollPane(list); //Scrollbar pane containing list
		
		
		JPanel memButtons = theme.makePanel(new GridLayout());
		
		JButton temp;
		
		temp = theme.makeHighlightButton("M+", new Dimension(10,30)); //Adds current val to mem
		temp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				memVals.addElement(Double.parseDouble(calcField.getText())); //Probably add some sort of formatting here
				firstDigit = true;
			}
		});
		memButtons.add(temp);
		
		temp = theme.makeHighlightButton("M-", new Dimension(10,30)); //Removes selected val from mem
		temp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				if(index > -1){
					memVals.remove(index);
				}
				if (memVals.size() != 0){ //Automatically select next value after removing one.
					if (index == memVals.getSize()){ 
						index--;
					}
					list.setSelectedIndex(index);
			        list.ensureIndexIsVisible(index);
				}
			}
		});
		memButtons.add(temp);
		
		temp = theme.makeHighlightButton("MC", new Dimension(10,30)); //Clears entire mem
		temp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				memVals.clear();
			}
		});
		memButtons.add(temp);
		
		listFrame.add(topLab, "North");
		listFrame.add(scroller);
		listFrame.add(memButtons,"South");
		
		return listFrame;
	}
	
	
//Listeners
	
	public static class numPressed implements ActionListener{ //Listener for Number Buttons
		
		private String nVal;
		
		public numPressed(char num){
			nVal = Character.toString(num);
		}
		public void actionPerformed(ActionEvent e) {
			if(firstDigit){
				if(nVal.equals(".")){
					calcField.setText(0 + nVal);
				}else calcField.setText(nVal);
				firstDigit = false;
			}else{				// TODO Disallow creating multiple decimal points
//				rv = (Double.parseDouble(calcField.getText()) * 10) + nVal;
				if(nVal.equals(".")){
					if(calcField.getText().contains(".")){ //If there is already a decimal, don't make another one
						//Might create a noise to sound in this condition
						return;
					}
				}
				
				calcField.setText(calcField.getText() + nVal);
			}
		}
	}
	
	public static class oneVarFuncPressed implements ActionListener{ //Listener for One Var Function Buttons
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
				dispLabel.setText(eq + "(" + dispLabel.getText().trim() + ")");
			}
			
//			calcField.setText(formatter.format(val));
			firstDigit = true;
		}
	}
	
	public static class twoVarFuncPressed implements ActionListener{ //Listener for Two Var Function Buttons
		private MathFunc func; //What function is this
		private String eq; //Used for label display
		
		public twoVarFuncPressed(MathFunc fun, String equ){
			func = fun;
			eq = equ;
		}
		
		public void actionPerformed(ActionEvent e) {
			hold = func;
			hold.setVar(Double.parseDouble(calcField.getText()));
			dispLabel.setText(dispLabel.getText() + calcField.getText() + eq);
			firstDigit = true;
		}
	}
	
	
}
