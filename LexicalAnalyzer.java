import java.util.*;

public class LexicalAnalyzer { 

    // List of Java keywords
    private static final Set<String> KEYWORDS = new HashSet<>(Arrays.asList(
            "int", "float", "double", "if", "else", "while", "for", "return"
    ));

    // List of valid operators
    private static final Set<Character> OPERATORS = new HashSet<>(Arrays.asList(
            '+', '-', '*', '/', '=', '<', '>', '!'
    ));

    // List of valid punctuation
    private static final Set<Character> PUNCTUATION = new HashSet<>(Arrays.asList(
            ';', ',', '(', ')', '{', '}'
    ));

    // Method to check if a character is a digit
    private static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    // Method to check if a character is a letter
    private static boolean isLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    // Method to tokenize the input string
    public static List<String> tokenize(String input) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while (i < input.length()) {
            char ch = input.charAt(i);

            // Skip whitespace
            if (Character.isWhitespace(ch)) {
                i++;
            }
            // Check for numbers
            else if (isDigit(ch)) {
                StringBuilder num = new StringBuilder();
                while (i < input.length() && isDigit(input.charAt(i))) {
                    num.append(input.charAt(i));
                    i++;
                }
                tokens.add("NUMBER: " + num.toString());
            }
            // Check for identifiers or keywords
            else if (isLetter(ch)) {
                StringBuilder word = new StringBuilder();
                while (i < input.length() && (isLetter(input.charAt(i)) || isDigit(input.charAt(i)))) {
                    word.append(input.charAt(i));
                    i++;
                }
                String wordStr = word.toString();
                if (KEYWORDS.contains(wordStr)) {
                    tokens.add("KEYWORD: " + wordStr);
                } else {
                    tokens.add("IDENTIFIER: " + wordStr);
                }
            }
            // Check for operators
            else if (OPERATORS.contains(ch)) {
                tokens.add("OPERATOR: " + ch);
                i++;
            }
            // Check for punctuation
            else if (PUNCTUATION.contains(ch)) {
                tokens.add("PUNCTUATION: " + ch);
                i++;
            }
            // Handle unknown characters
            else {
                System.out.println("Unknown character: " + ch);
                i++;
            }
        }
        return tokens;
    }

    public static void main(String[] args) {
        String input = "int a = 10 + 20;";
        System.out.println("Input: " + input);
        List<String> tokens = tokenize(input);
        System.out.println("Tokens:");
        for (String token : tokens) {
            System.out.println(token);
        }
    }
}
