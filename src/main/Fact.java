package main;

public class Fact extends MathFunc {

	protected double function(double x) {
		int fact = 1;
		for (int i = 1; i <= x; i++){
			fact *= i;
		}
		return fact;
	}

}
