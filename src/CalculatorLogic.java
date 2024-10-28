import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorLogic {
	private StringBuilder expression;

	public CalculatorLogic() {
		expression = new StringBuilder();
	}

	public void updateExpression(String input) {
		expression.append(input);
	}

	public String getExpression() {
		return expression.toString();
	}

	public void setExpression(String input) {
		expression = new StringBuilder(input);
	}

	public void clear() {
		expression.setLength(0);
	}

	public void delete() {
		if (expression.length() > 0) {
			expression.deleteCharAt(expression.length() - 1);
		}
	}

	public void calculate() throws ArithmeticException {
		try {
			String[] tokensArray = expression.toString().replace(",", "").split("(?<=[-+*/])|(?=[-+*/])");
			List<String> tokens = new ArrayList<>(Arrays.asList(tokensArray));

			for (int i = 0; i < tokens.size() - 1; i++) {
				if ((tokens.get(i).equals("/") || tokens.get(i).equals("*")) && tokens.get(i + 1).equals("-")) {
					tokens.set(i + 1, tokens.get(i + 1) + tokens.get(i + 2));
					tokens.remove(i + 2);
				}
			}

			if (tokens.get(0).equals("-")) {
				tokens.set(1, "-" + tokens.get(1));
				tokens.remove(0);
			}

			for (int i = 0; i < tokens.size(); i++) {
				String token = tokens.get(i);
				if (token.equals("*") || token.equals("/")) {
					double left = Double.parseDouble(tokens.get(i - 1));
					double right = Double.parseDouble(tokens.get(i + 1));
					double result = 0;

					if (token.equals("*")) {
						result = left * right;
					} else {
						if (right == 0) {
							throw new ArithmeticException("Divide by zero");
						}
						result = left / right;
					}

					tokens.set(i - 1, Double.toString(result));
					tokens.remove(i);
					tokens.remove(i);
					i--;
				}
			}

			double result = Double.parseDouble(tokens.get(0));
			for (int i = 1; i < tokens.size(); i += 2) {
				String operator = tokens.get(i);
				double operand = Double.parseDouble(tokens.get(i + 1));

				if (operator.equals("+")) {
					result += operand;
				} else if (operator.equals("-")) {
					result -= operand;
				}
			}

			DecimalFormat df = new DecimalFormat();

			System.out.println(expression + "=" + df.format(result));
			expression = new StringBuilder(df.format(result));
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			throw new ArithmeticException("Invalid expression");
		}
	}
}
