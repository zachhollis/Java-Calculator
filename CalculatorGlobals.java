class CalculatorGlobals {

	public static CalculatorGlobals sharedInstance = new CalculatorGlobals();

	// Array of user variables, each associated with a (capital) letter of the alphabet
	private double[] userVariables = new double[26];

	// 2nd, SHIFT, and ALPHA keypress tracking
	private boolean alpha = false;
	private boolean shift = false;
	private boolean second = false;

	public CalculatorGlobals() {
		for(int i = 0; i < userVariables.length; i++) {
			userVariables[i] = 0.0;
		}
	}

	public double getUserVariable(char var) {
		String compare = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return userVariables[compare.indexOf(var)];
	}

	public void storeUserVariable(char var, double value) {
		String compare = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		userVariables[compare.indexOf(var)] = value;
	}

	public boolean isAlpha() {
		return alpha;
	}

	public boolean isShift() {
		return shift;
	}

	public boolean isSecond() {
		return second;
	}

	public void setAlpha(boolean b) {
		alpha = b;
	}

	public void setShift(boolean b) {
		shift = b;
	}

	public void setSecond(boolean b) {
		second = b;
	}
}