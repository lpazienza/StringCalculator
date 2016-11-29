package it.uniba.tdd;

public class StringCalculator {

	public int add(String numbersStr) throws StringCalculatorException {
		// Returns the sum of the numbers given in numbersStr
		int size = numbersStr.length();
		if (size == 0) {
			return 0;
		} else {
			if (numbersStr.startsWith("-")) {
				throw new StringCalculatorException();
			} else {
				String newString = "";
				if (numbersStr.contains("//")) {
					String delimetersString = numbersStr.substring(2).split("\n")[0];
					newString = numbersStr.split("\n")[1];
					String delimeters[] = computeDelimeters(delimetersString);
					for (int i = 0; i < delimeters.length; i++) {
						newString = newString.replace(delimeters[i], ",");
					}
				} else {
					newString = numbersStr.replaceAll("\n", ",");
				}
				return getSum(newString);
			}
		}

	}

	public int getSum(String expression) throws StringCalculatorException {
		if (expression.contains(",,")) {
			throw new StringCalculatorException();
		} else {
			int sum = 0;
			String[] numbers = expression.split(",");
			int operand = 0;
			for (int i = 0; i < numbers.length; i++) {
				operand = Integer.parseInt(numbers[i]);
				if (operand > 1000) {
					operand = 0;
				}
				sum += operand;
			}
			return sum;
		}
	}

	public String[] computeDelimeters(String stringWithDelimeters) {
		String[] holder = stringWithDelimeters.split("\\[");
		String holderToghether = "";
		for (int i = 0; i < holder.length; i++) {
			holderToghether += holder[i];
		}
		String[] delimeters = holderToghether.split("\\]");
		return delimeters;
	}
}