package gr.alx.adventofcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        boolean valid = isValid("()[]{}");

    }

    static boolean isValid(String s) {

        char[] chars = s.toCharArray();

        Stack<Character> stack = new Stack<Character>();

        for (char aChar : chars) {
            switch (aChar) {
                case '[':
                    stack.push(aChar);
                case '{':
                    stack.push(aChar);
                case '(':
                    stack.push(aChar);
                case ']':
                    if (stack.peek().equals('[')) {
                        stack.pop();
                    } else {
                        return false;
                    }
                case '}':
                    if (stack.peek().equals('{')) {
                        stack.pop();
                    } else {
                        return false;
                    }
                case ')':
                    if (stack.peek().equals('(')) {
                        stack.pop();
                    } else {
                        return false;
                    }
            }
        }

        return stack.size() == 0;
    }
}
