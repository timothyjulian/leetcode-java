/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 150. Evaluate Reverse Polish Notation (Medium)
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 * @author Timothy Julian
 * @version $Id: EvaluateReversePolishNotation.java, v 0.1 2026‐10‐14 11.37 timothy.julian Exp $$
 */
public class EvaluateReversePolishNotation {

    public EvaluateReversePolishNotation() {
        Judge j = new Judge();
        j.check("9", evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        j.check("6", evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        j.check("22", evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
        j.done();
    }

    // ==== leetcode:begin ====
    public int evalRPN(String[] tokens) {
        List<Integer> stack = new ArrayList<>();
        for (String token: tokens) {

            if (token.equals("+")) {
                int num2 = stack.removeLast();
                int num1 = stack.removeLast();

                stack.add(num1+ num2);
            } else if (token.equals("-")) {
                int num2 = stack.removeLast();
                int num1 = stack.removeLast();

                stack.add(num1 - num2);
            } else if (token.equals("*")) {
                int num2 = stack.removeLast();
                int num1 = stack.removeLast();

                stack.add(num1 * num2);
            } else if (token.equals("/")) {
                int num2 = stack.removeLast();
                int num1 = stack.removeLast();

                stack.add(num1 / num2);
            } else {
                stack.add(Integer.valueOf(token));
            }
        }


        return stack.removeFirst();
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new EvaluateReversePolishNotation();
    }
}
