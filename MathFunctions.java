import java.lang.*;

class MathFunctions {

	public double add(String x, String y) {
		return Double.parseDouble(x) + Double.parseDouble(y);
	}

	public double subtract(String x, String y) {
		return Double.parseDouble(x) - Double.parseDouble(y);
	}

	public double multiply(String x, String y) {
		return Double.parseDouble(x) * Double.parseDouble(y);
	}

	public double divide(String x, String y) {
		return Double.parseDouble(x) / Double.parseDouble(y);
	}

	public double sin(String x) {
		return Math.sin(Double.parseDouble(x));
	}

	public double cos(String x) {
		return Math.cos(Double.parseDouble(x));
	}

	public double tan(String x) {
		return Math.tan(Double.parseDouble(x));
	}

	public double asin(String x) {
		return Math.asin(Double.parseDouble(x));
	}

	public double acos(String x) {
		return Math.acos(Double.parseDouble(x));
	}

	public double atan(String x) {
		return Math.atan(Double.parseDouble(x));
	}

	public double abs(String x) {
		return Math.abs(Double.parseDouble(x));
	}

	public double power(String x, String y) {
		return Math.pow(Double.parseDouble(x), Double.parseDouble(y));
	}

	public double e() {
		return Math.exp(1.0);
	}
}