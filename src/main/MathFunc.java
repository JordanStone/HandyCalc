/*
 * Jordan Stone
 * stone@drexel.edu
 * CS338:GUI, Personal Project
 * 
 * MathFunc: Abstract Class defining all math functions for HandyCalc. Built like a Command Pattern but without
 * using an Interface, as I needed to keep a saved variable. Not sure if that makes it still a Command Pattern.
 */

package main;

import java.awt.Toolkit;

public abstract class MathFunc {
	
	protected double holdVar = 0;
	
	protected abstract double function(double x);
	
	public double getVar(){
		return holdVar;
	}
	
	public void setVar(double v){
		holdVar = v;
	}
	
	
//Implementations
	
	//Basic Calculations
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
			if (x == 0){ //Don't let user divide by 0.
				java.awt.Toolkit.getDefaultToolkit().beep();
				return 0;
			}
			
			return holdVar / (x);
		}
	}
	
	//Pow and Root
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
			if(x < 0){ //Don't let user root negative numbers
				Toolkit.getDefaultToolkit().beep();
				return(x);
			}
			return Math.sqrt(x);
		}
	}
	public static class Yroot extends MathFunc {
		public double function(double x) {
			if(holdVar < 0){ //Don't let user root negative numbers
				Toolkit.getDefaultToolkit().beep();
				return(x);
			}
			return Math.pow(Math.exp (1/x), Math.log(holdVar));
		}
	}
	
	//One Var Funcs (Excluding Above)
	public static class Neg extends MathFunc {
		public double function(double x) {
			return -(x);
		}
	}
	public static class Fact extends MathFunc {
		public double function(double x) {
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
	
	//Two Var Funcs (Excluding Above)
	public static class Mod extends MathFunc {
		public double function(double x) {
			return holdVar % (x);
		}
	}
	public static class Percent extends MathFunc {
		public double function(double x) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	
	
	//Trig Funcs (Use Radians By Default. Need to Create a Degree Conversion Function)
	public static class Sin extends MathFunc {
		public double function(double x) {
			return Math.sin(x);
		}
	}
	public static class Cos extends MathFunc {
		public double function(double x) {
			return Math.cos(x);
		}
	}
	public static class Tan extends MathFunc {
		public double function(double x) {
			return Math.tan(x);
		}
	}
	
	public static class ASin extends MathFunc {
		public double function(double x) {
			return Math.asin(x);
		}
	}
	public static class ACos extends MathFunc {
		public double function(double x) {
			return Math.acos(x);
		}
	}
	public static class ATan extends MathFunc {
		public double function(double x) {
			return Math.atan(x);
		}
	}

}



