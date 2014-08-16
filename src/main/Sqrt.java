package main;

public class Sqrt extends MathFunc{

	public double function(double x) {
		return Math.sqrt(x);
	}
	
	public double twoVarFunction(double x, double y) { //Probably migrate this
		return Math.pow(Math.exp (1/y), Math.log(x));
	}

}
