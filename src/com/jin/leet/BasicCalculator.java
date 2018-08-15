package com.jin.leet;

import java.util.LinkedList;

/**
 * 224. Basic Calculator
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * Example 1:
 *
 * Input: "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class BasicCalculator {
    public int calculate(String s) {
        char[] exp = s.toCharArray();
        LinkedList<Character> operators = new LinkedList<>();
        LinkedList<Integer> operands = new LinkedList<>();
        for (int i=0; i<exp.length; i++) {
            char c = exp[i];
            if (c == ' ')
                continue;
            if (c == '+' || c=='-' || c=='(') {
                operators.push(c);
                continue;
            }
            if (c == ')') {
                operators.pop();
                doCaculation(operators, operands);
                continue;
            }
            if (c>='0' && c<='9') {
                StringBuilder sb = new StringBuilder();
                while(i<exp.length && exp[i]>='0' && exp[i]<='9') {
                    sb.append(exp[i]);
                    i++;
                }
                i--;
                int op = Integer.parseInt(sb.toString());
                operands.push(op);
                doCaculation(operators, operands);
            }
        }
        return operands.pop();
    }

    private void doCaculation(LinkedList<Character> operators, LinkedList<Integer> operands) {
        while (operands.size()>1) {
            if (operators.peek()=='(')
                break;
            char operator = operators.pop();
            int b = operands.pop();
            int a = operands.pop();
            switch (operator) {
                case '+':
                    operands.push(a+b);
                    break;
                case '-':
                    operands.push(a-b);
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args){
        BasicCalculator bc = new BasicCalculator();
        String exp = "1 + 1";
        System.out.println(exp +" = " + bc.calculate(exp));
    }
}
