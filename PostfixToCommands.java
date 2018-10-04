import java.util.*;

public class PostfixToCommands {

    private String[] operations = {"+", "-", "*", "/", "sin", "cos", "tan", "asin", "acos", "atan", "abs", "^", "e"};

    //******************************************************************
	//
	// postfix produce args function
	// 		produces an argument list in the following format
	//
	//		OPERATION 		ARGUMENT_1		ARGUMENT_2		...
	//		add 			0				1
	//		subtract		1.9				prev
	//		sin 			0.3
	//
	// 'prev' keyword denotes value of previous operation
	//		consecutive 'prev' keywords denotes values previous of previous
	// Values are space-separated
	// Operation sets are newline-separated
	// 
	//******************************************************************
    public String postfixProduceArgs(String exp) {
	 	Stack<String> s = new Stack<String>();
		Scanner tokens = new Scanner(exp);
		Queue<String> commands = new Queue<String>();
		
		while (tokens.hasNext()) {
			String next = tokens.next();
			if (isOperation(next)) {
				switch(next) {
					case "+": 		commands.enqueue(createAddCommand(s.pop(), s.pop())); break;
					case "-": 		commands.enqueue(createSubtractCommand(s.pop(), s.pop())); break;
					case "*": 		commands.enqueue(createMultiplyCommand(s.pop(), s.pop())); break;
					case "/": 		commands.enqueue(createDivideCommand(s.pop(), s.pop())); break;
					case "sin":		commands.enqueue(createSinCommand(s.pop())); break;
					case "cos":		commands.enqueue(createCosCommand(s.pop())); break;
					case "tan":		commands.enqueue(createTanCommand(s.pop())); break;
					case "asin":	commands.enqueue(createAsinCommand(s.pop())); break;
					case "acos":	commands.enqueue(createAcosCommand(s.pop())); break;
					case "atan":	commands.enqueue(createAtanCommand(s.pop())); break;
					case "abs":		commands.enqueue(createAbsCommand(s.pop())); break;
					case "^":		commands.enqueue(createPowerCommand(s.pop(), s.pop())); break;
					case "e":		commands.enqueue(createECommand()); break;
					default:  		System.out.println("ERROR: INVALID OPERAND IN postfixProduceArgs(String exp) @PostfixEvaluator");
				}

				s.push("prev");
			} else {
				s.push(next);
			}
		}

		String ret = "";
		while(!commands.isEmpty())
			ret = ret + "\n" + commands.dequeue();
		System.out.println(ret);
		return ret;
    }

    private String createAddCommand(String x, String y) {
    	// FORMAT 		x + y
    	//				add x y
    	return "add " + x + " " + y; 
    }

    private String createSubtractCommand(String x, String y) {
    	// FORMAT 		y - x
    	//				subtract y x
    	return "subtract " + y + " " + x; 
    }

    private String createMultiplyCommand(String x, String y) {
    	// FORMAT 		x * y
    	//				divide x y
    	return "multiply " + x + " " + y; 
    }

    private String createDivideCommand(String x, String y) {
    	// FORMAT 		y / x
    	//				divide y x
    	return "divide " + y + " " + x; 
    }

    private String createSinCommand(String x) {
    	// FORMAT 		sin(x)
    	//				sin x
    	return "sin " + x; 
    }

    private String createCosCommand(String x) {
    	// FORMAT 		cos(x)
    	//				cos x
    	return "cos " + x; 
    }

    private String createTanCommand(String x) {
    	// FORMAT 		tan(x)
    	//				tan x
    	return "tan " + x; 
    }

    private String createAsinCommand(String x) {
    	// FORMAT 		asin(x)
    	//				asin x
    	return "asin " + x; 
    }

    private String createAcosCommand(String x) {
    	// FORMAT 		acos(x)
    	//				acos x
    	return "acos " + x; 
    }

    private String createAtanCommand(String x) {
    	// FORMAT 		atan(x)
    	//				atan x
    	return "atan " + x; 
    }

    private String createAbsCommand(String x) {
    	// FORMAT 		abs(x)
    	//				abs x
    	return "abs " + x; 
    }

    private String createPowerCommand(String x, String y) {
    	// FORMAT 		y^x
    	//				power y x
    	return "power " + y + " " + x; 
    }

    private String createECommand() {
    	// FORMAT 		e
    	//				e
    	return "e"; 
    }

    private boolean isOperation(String s) {
    	// checks if next is a variable or number
    	for(int i = 0; i < operations.length; i++)
    		if(operations[i].equals(s))
    			return true;
    	return false;
    }
}