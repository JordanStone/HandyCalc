/*
 * Jordan Stone
 * stone@drexel.edu
 * CS338:GUI, Personal Project
 * 
 * MathFunc is a command pattern interface that takes care of all math functions in HandyCalc.
 */

package main;

/*
 * MathFunc: 
 */
public abstract class MathFunc {
	
	protected double holdVar = 0;
	
	protected abstract double function(double x);
	
	public double getVar(){
		return holdVar;
	}
	
	public void setVar(double v){
		holdVar = v;
	}
}

