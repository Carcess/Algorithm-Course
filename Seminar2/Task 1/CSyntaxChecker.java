// Import the Stack class
import java.util.Stack;

// Main class for the C syntax checker
public class CSyntaxChecker {

    // Entry point of the program
    public static void main(String[] args) {
        // Array of test strings to be checked
        String[] testStrings = {
            "int a;", // Valid
            "int a; /* random comment */", // Valid
            "int a; /* for storing width * height */", // Valid
            "int a = b*c;", // Valid
            "int a = b / c;", // Valid
            "int a = 55; // This is a comment", // Valid
            "public void doIt(int x) {System.out.println(x*100);}", // Valid
            "int[] arr = new int[10];", // Valid
            "/* */ {}", // Valid
            "if(a == b) {a++;}", // Valid
            "if(a < (b*c)) {t = 5;}", // Valid
            "int []b = new int[5];", // Valid
            "{}{}", // Valid
            "int a = 5; // init a to 5", // Valid
            "int b = 5; /* this is a comment */", // Valid
            "int c = 5; // half a comment \n }", // Valid
            "/* Unmatched comment", // Not valid, unclosed comment
            "int d = 5; // another half a comment" // Not valid, missing newline or closing
        };

        // Iterate over each test string and check its syntax
        for (String s : testStrings) {
            System.out.println("Testing: " + s);
            checkCSyntax(s);
            System.out.println(); // Just for readability of output
        }
    }

    // Method to check the syntax of a given C code snippet
    public static void checkCSyntax(String code) {
        // Stack to keep track of opening brackets and braces
        Stack<Character> stack = new Stack<>();
        boolean inSingleLineComment = false;
        boolean inMultiLineComment = false;
    
        // Iterate over each character in the code
        for (int i = 0; i < code.length(); i++) {
            char ch = code.charAt(i);
            // Get the next character or '\0' if at the end
            char nextChar = (i < code.length() - 1) ? code.charAt(i + 1) : '\0';
    
            // Handle single-line comments
            if (ch == '/' && nextChar == '/' && !inMultiLineComment) {
                inSingleLineComment = true;
                i++; // Skip the next '/'
            }
            if (inSingleLineComment && (ch == '\n')) {
                inSingleLineComment = false;
            }
            if (inSingleLineComment) {
                continue;
            }
    
            // Handle multi-line comments
            if (ch == '/' && nextChar == '*' && !inMultiLineComment) {
                inMultiLineComment = true;
                i++; // Skip the next '*'
                continue;
            }
            if (ch == '*' && nextChar == '/' && inMultiLineComment) {
                inMultiLineComment = false;
                i++; // Skip the next '/'
                continue;
            }
            if (inMultiLineComment) {
                continue;
            }
    
            // Handle open and close brackets and braces
            if (ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ']' || ch == '}') {
                if (stack.isEmpty() || !isMatching(stack.peek(), ch)) {
                    error(i, "Unmatched closing bracket or brace");
                    return;
                } else {
                    stack.pop();
                }
            }
        }
    
        // Check for unclosed comments or unmatched symbols
        if (inMultiLineComment) {
            error(code.length(), "Unclosed comment");
        } else if (!stack.isEmpty()) {
            error(code.length(), "Unmatched opening bracket or brace");
        } else {
            System.out.println("C syntax is correct for: " + code);
        }
    }

    // Helper method to check if a pair of symbols matches
    private static boolean isMatching(char opening, char closing) {
        // Returns true if the symbols are a matching pair of brackets or braces
        return (opening == '[' && closing == ']') || (opening == '{' && closing == '}');
    }

    // Helper method to print an error message
    private static void error(int position, String message) {
        // Prints an error message with the position of the error
        System.out.println("Error at position " + position + ": " + message);
    }
}
