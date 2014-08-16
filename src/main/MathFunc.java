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
/*
	
	
	public double mult(double x, double y){
		double rv = 0;
		
		rv = x * y;
		
		return rv;
	}
	
	public double div(double x, double y){
		double rv = 0;
		
		rv = x / y;
		
		return rv;
	}
	
	
	public double recip(double x){
		double rv = 0;
		
		
		return rv;
	}

*/
}


