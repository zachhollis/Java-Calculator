import java.util.*;

class ComputeCommandExecutor {

	private Stack<Double> computedValues = new Stack<Double>();
	private Queue<String> parsedCommands = new Queue<String>();

	public String execute(String commands) {
		StringTokenizer st = new StringTokenizer(commands, "\n");
		while(st.hasMoreTokens()) {
			parsedCommands.enqueue(st.nextToken());
		}
		while(!parsedCommands.isEmpty()) {
			computedValues.push(executeCommand(parsedCommands.dequeue()));
		}
		return Double.toString(computedValues.pop());
	}

	private Double executeCommand(String command) {
		MathFunctions m = new MathFunctions();
		StringTokenizer st = new StringTokenizer(command);
		Queue<String> args = new Queue<String>();
		while(st.hasMoreTokens()) {
			String s = st.nextToken();
			if(s.equals("prev"))
				args.enqueue(Double.toString(computedValues.pop()));
			else if("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(s))
				args.enqueue(Double.toString(CalculatorGlobals.sharedInstance.getUserVariable(s.charAt(0))));
			else
				args.enqueue(s);
		}
		String operation = args.dequeue();
		switch(operation) {
			case "add":	 			return m.add(args.dequeue(), args.dequeue());
			case "subtract": 		return m.subtract(args.dequeue(), args.dequeue());
			case "multiply": 		return m.multiply(args.dequeue(), args.dequeue());
			case "divide": 			return m.divide(args.dequeue(), args.dequeue());
			case "sin":				return m.sin(args.dequeue());
			case "cos":				return m.cos(args.dequeue());
			case "tan":				return m.tan(args.dequeue());
			case "asin":			return m.asin(args.dequeue());
			case "acos":			return m.acos(args.dequeue());
			case "atan":			return m.atan(args.dequeue());
			case "abs":				return m.abs(args.dequeue());
			case "power":			return m.power(args.dequeue(), args.dequeue());
			case "e":				return m.e();
			default:  System.out.println("ERROR: INVALID OPERAND" + "(" + operation + ") IN executeCommand(String command) @ComputeCommandExecutor");
		}
		return 0.0;
	}
}