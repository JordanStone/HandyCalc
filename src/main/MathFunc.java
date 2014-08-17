/*
 * Jordan Stone
 * stone@drexel.edu
 * CS338:GUI, Personal Project
 * 
 * MathFunc is a command pattern interface that takes care of all math functions in HandyCalc.
 */

package main;

/*
 * MathFunc: Abstract Class defining all math functions for HandyCalc. Built like a Command Pattern but without
 * using an Interface, as I needed to keep a saved variable. Not sure if that makes it still a Command Pattern.
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
	
	
	//Functions
	
	public static class Plus extends MathFunc{
		public double function(double x) {
			return ((x) + holdVar);
		}
	}
	public static class Minus extends MathFunc{
		public double function(double x) {
			return holdVar - (x);
		}
	}
	public static class Mult extends MathFunc {
		public double function(double x) {
			return (x) * holdVar;
		}
	}
	public static class Div extends MathFunc {
		public double function(double x) {
			return holdVar / (x);
		}
	}
	public static class Square extends MathFunc {
		public double function(double x) {
			return Math.pow(x, 2);
		}
	}
	public static class Pow extends MathFunc {
		public double function(double x) {
			return Math.pow(holdVar, x);
		}
	}
	public static class Sqrt extends MathFunc{
		public double function(double x) {
			return Math.sqrt(x);
		}
	}
	public static class Yroot extends MathFunc {
		public double function(double x) {
			return Math.pow(Math.exp (1/x), Math.log(holdVar));
		}
	}
	public static class Neg extends MathFunc {
		public double function(double x) {
			return -(x);
		}
	}
	public static class Mod extends MathFunc {
		protected double function(double x) {
			return holdVar % (x);
		}
	}
	public static class Percent extends MathFunc {
		public double function(double x) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	public static class Fact extends MathFunc {
		protected double function(double x) {
			int fact = 1;
			for (int i = 1; i <= x; i++){
				fact *= i;
			}
			return fact;
		}
	}
	public static class Inverse extends MathFunc {
		public double function(double x) {
			return 1 / (x);
		}
	}

}



