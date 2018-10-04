class TerminalInterface {
	public static void main(String[] args) {
		CalculatorGlobals.sharedInstance.storeUserVariable('A', 4.0);
		String testInput = "B+(1.0+2)*sin(2.0)";
		System.out.println("\n" + testInput);
		InfixToPostfix i2p = new InfixToPostfix();
		PostfixToCommands p2c = new PostfixToCommands();
		ComputeCommandExecutor cce = new ComputeCommandExecutor();
		System.out.println("\n" + cce.execute(p2c.postfixProduceArgs(i2p.convert(testInput))));
	}
}