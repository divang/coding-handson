package handson.aug142020.h;

import java.util.Stack;

/**
 * 
 * Not complete yet
 */
public class BasicCalculator {

	public static void main(String[] args) {
		String e = "(1+3)-9";
		BasicCalculator basicCalculator = new BasicCalculator();
		System.out.println("total->" + basicCalculator.calculate(e));
	}

	class OpOr {
		char op;
		int or;

		OpOr(char c) {
			if (c == '+' || c == '-' || c == '(') {
				op = c;
			} else {
				or = c - 48;
			}
		}

		public OpOr(int or) {
			this.or = or;
		}

		public char getOp() {
			return op;
		}

		public int getOr() {
			return or;
		}
	}
	Stack<OpOr> expression = new Stack<OpOr>();

	public int calculate(String s) {

		char[] exp = s.toCharArray();
		int total = 0;
		for (char c : exp) {
			if (c == ' ')
				continue;
			if (c == '(' || c == '+' || c == '-')
				expression.push(new OpOr(c));
			if (c == ')') {
				OpOr opor1;

				while (!expression.isEmpty()
						&& (opor1 = expression.pop()).getOp() != '(') {

					if (!expression.isEmpty()) {
						OpOr op = expression.pop();
						if (!expression.isEmpty()) {
							OpOr opor2 = expression.pop();
							switch (op.getOp()) {
								case '+' :
									total = opor1.getOr() + opor2.getOr();
									break;
								case '-' :
									total = opor1.getOr() - opor2.getOr();
									break;

							}
							expression.push(new OpOr(total));
						}
					}

				}
			}

		}
		return total;
	}

}
