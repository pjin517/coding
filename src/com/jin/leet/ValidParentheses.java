package com.jin.leet;

import java.util.HashMap;
import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: true
 * Example 2:
 *
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: "(]"
 * Output: false
 * Example 4:
 *
 * Input: "([)]"
 * Output: false
 * Example 5:
 *
 * Input: "{[]}"
 * Output: true
 */
public class ValidParentheses {
    Stack<Character> stack;
    HashMap<Character, Character> lefts;
    HashMap<Character, Character> rights;

    public ValidParentheses() {
        this.stack = new Stack<>();
        lefts = new HashMap<Character, Character>();
        lefts.put('(', ')');
        lefts.put('[', ']');
        lefts.put('{', '}');
        rights = new HashMap<Character, Character>();
        rights.put('}', '{');
        rights.put(']', '[');
        rights.put(')', '(');
    }

    public boolean isValid(String s) {


        char[] chars = s.toCharArray();

        for (char c: chars) {
            if (c==' ')
                continue;
            if (lefts.containsKey(c)) {
                stack.push(c);
                continue;
            }
            if (rights.containsKey(c)) {
                if (stack.isEmpty())
                    return false;
                char left = stack.pop();
                if (rights.get(c).charValue() == left)
                    continue;
                else
                    return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses v = new ValidParentheses();
        System.out.println("(): " + v.isValid("()"));
        System.out.println("()[]{}: " + v.isValid("()[]{}"));
        System.out.println("(]: " + v.isValid("(]"));
        System.out.println("([)]: " + v.isValid("([)]"));
        System.out.println("{[]}: " + v.isValid("{[]}"));
    }
}
