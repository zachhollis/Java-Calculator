import java.util.*;

class InfixToPostfix {

	public String convert(String infixExpression) {
		return infixToPostfix(spaceSeparate(infixExpression));
	}

	private String infixToPostfix(String exp) {
		String retVal = "";
		Stack<String> stack = new Stack<String>();
		StringTokenizer st = new StringTokenizer(exp);
		while(st.hasMoreTokens()) {
			System.out.println(retVal);

			String next = st.nextToken();
			char c = next.charAt(0);
			if(isVariable(c) || isNumber(c)) {
				retVal = retVal + " " + next;
			} else if(isFunction(c)) {
				String temp = "";
				StringTokenizer st1 = new StringTokenizer(next, ",");
				while(st1.hasMoreTokens()) {
					temp = st1.nextToken() + temp;
					if(st1.hasMoreTokens())
						temp = " " + temp;
				}
				retVal = retVal + " " + temp;
				System.out.println(retVal);
			} else if(c == '(') {
				stack.push(next);
			} else if(c == ')') {
				while(stack.peek().charAt(0) != '(') {
					retVal = retVal + " " + stack.pop();
				}
				stack.pop();
			} else {
				while(!stack.isEmpty() && (precidence(stack.peek().charAt(0)) >= precidence(next.charAt(0)))) {
					retVal = retVal + " " + stack.pop();
				}
				stack.push(next);
			}
		}
		while(!stack.isEmpty())
			retVal = retVal + " " + stack.pop();
		System.out.println(retVal);
		return retVal;
	}

	private String spaceSeparate(String s) {
		String retVal = "";
		char[] c = s.toCharArray();
		for(int i = 0; i < c.length; i++) {
			if(isVariable(c[i]) || isOperator(c[i])) {
				retVal = retVal + " " + c[i] + " ";
			} else if(isNumber(c[i])) {
				retVal = retVal + c[i];
			} else if(isFunction(c[i])) {
				String temp = "";
				while(c[i] != ')') {
					if((c[i] == '(') || (c[i] == ',')) {
						temp = temp + ",";
					} else {
						temp = temp + c[i];
					}
					i++;
				}
				retVal = retVal + " " + temp;
			}
		}
		return retVal;
	}

	private boolean isVariable(char c) {
		String variables = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		if(variables.indexOf(c) == -1)
			return false;
		return true;
	}

	public boolean isNumber(char c) {
		String number = ".0123456789";
		if(number.indexOf(c) == -1)
			return false;
		return true;
	}

	private boolean isOperator(char c) {
		String operators = "+-*/^()";
		if(operators.indexOf(c) == -1)
			return false;
		return true;
	}

	private boolean isFunction(char c) {
		String functions = "abcdefghijklmnopqrstuvwxyz";
		if(functions.indexOf(c) == -1)
			return false;
		return true;
	}

	//******************************************************************
	//
	// precidence method
	//
	// returns integer corresponding to precedence of operator tokens
	// 
	//******************************************************************
	public int precidence(char c) {
		if(c == '+' || c == '-')
			return 2;
		if(c == '*' || c == '/')
			return 3;
		return 1;
	}
}