// Import the Stack class
import java.util.Stack;

// Main class for the C++ syntax checker
public class CPPSyntaxChecker {

    // Entry point of the program
    public static void main(String[] args) {
        // Array of test strings to be checked
        String[] testStrings = {
            "if(a == b) {a++;}", // Valid
            "if(a < (b*c)) {t = 5;}", // Valid
            "int []b = new int[5];", // Valid
            "[](){}", // Valid
            "int a = 5; // init a to 5", // Valid
            "for(int i=0;i<10;i++] {a+= b;}", // Not valid
            "{abc)" // Not valid
        };

        // Iterate over each test string and check its syntax
        for (String s : testStrings) {
            System.out.println("Testing: " + s);
            checkCPPSyntax(s);
            System.out.println(); // Just for readability of output
        }
    }

     // Method to check the syntax of a given C++ code snippet
    public static void checkCPPSyntax(String code) {
        // Stack to keep track of opening parentheses, brackets, and braces
        Stack<Character> stack = new Stack<>();
        boolean inSingleLineComment = false;

        // Iterate over each character in the code
        for (int i = 0; i < code.length(); i++) {
            char ch = code.charAt(i);
            // Get the next character or '\0' if at the end
            char nextChar = (i < code.length() - 1) ? code.charAt(i + 1) : '\0';

            // Handle single-line comments
            if (ch == '/' && nextChar == '/') {
                inSingleLineComment = true;
                i++; // Skip the next '/'
            }
            if (ch == '\n') {
                inSingleLineComment = false;
            }
            if (inSingleLineComment) {
                continue;
            }

            // Handle parentheses, brackets, and braces
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty() || !matches(stack.peek(), ch)) {
                    reportError(i);
                    return;
                } else {
                    stack.pop();
                }
            }
        }

        if (!stack.isEmpty()) {
            reportError(code.length());
        } else {
            System.out.println("C++ syntax is correct for: " + code);
        }
    }

    // Helper method to check if a pair of symbols matches
    private static boolean matches(char open, char close) {
        // Returns true if the symbols are a matching pair
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }

    // Helper method to print an error message
    private static void reportError(int position) {
        // Prints an error message with the position of the error
        System.out.println("Error: Unmatched symbol at position " + position);
    }
}
