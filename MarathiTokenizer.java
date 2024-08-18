import java.util.ArrayList;
import java.util.List;

public class MarathiTokenizer {
    private String input;
    private int position;

    public MarathiTokenizer(String input) {
        this.input = input;
        this.position = 0;
    }

    public Token nextToken() {
        skipWhitespace();

        if (position >= input.length()) {
            return null;
        }

        String currentSubstring = input.substring(position).toLowerCase();

        // Handle English keywords and symbols
        if (input.startsWith("He aahe", position)) {
            position += "He aahe".length();
            return new Token("VAR_DECL", "He aahe");
        }
        if (input.startsWith("Jar", position)) {
            position += "Jar".length();
            return new Token("IF", "Jar");
        }
        if (input.startsWith("Nahitar", position)) {
            position += "Nahitar".length();
            return new Token("ELSE", "Nahitar");
        }
        if (input.startsWith("Chapa", position)) {
            position += "Chapa".length();
            return new Token("PRINT", "Chapa");
        }
        if (input.startsWith("Then", position)) {
            position += "Then".length();
            return new Token("THEN", "Then");
        }

        // Handling string literals
        if (input.charAt(position) == '"') {
            return readString();
        }

        char currentChar = input.charAt(position);

        if (Character.isDigit(currentChar)) {
            return readNumber();
        }
        if (Character.isLetter(currentChar)) {
            return readIdentifier();
        }
        // Handling multi-character operators (==, !=)
        if (currentSubstring.startsWith("==")) {
            position += 2;
            return new Token("OPERATOR", "==");
        }
        if (currentSubstring.startsWith("!=")) {
            position += 2;
            return new Token("OPERATOR", "!=");
        }
        // Handling single-character operators
        if (currentChar == '=' || currentChar == '+' || currentChar == '-' ||
            currentChar == '*' || currentChar == '/') {
            position++;
            return new Token("OPERATOR", String.valueOf(currentChar));
        }
        // Handling parentheses and other symbols
        if (currentChar == '(') {
            position++;
            return new Token("LPAREN", "(");
        }
        if (currentChar == ')') {
            position++;
            return new Token("RPAREN", ")");
        }
        if (currentChar == ';') {
            position++;
            return new Token("SEMICOLON", ";");
        }

        throw new RuntimeException("Unexpected character: " + currentChar);
    }

    private void skipWhitespace() {
        while (position < input.length() && Character.isWhitespace(input.charAt(position))) {
            position++;
        }
    }

    private Token readNumber() {
        int start = position;
        while (position < input.length() && Character.isDigit(input.charAt(position))) {
            position++;
        }
        String number = input.substring(start, position);
        return new Token("NUMBER", number);
    }

    private Token readIdentifier() {
        int start = position;
        while (position < input.length() && Character.isLetterOrDigit(input.charAt(position))) {
            position++;
        }
        String identifier = input.substring(start, position);
        return new Token("IDENTIFIER", identifier);
    }

    private Token readString() {
        int start = position;
        position++; // Skip the opening quote
        while (position < input.length() && input.charAt(position) != '"') {
            position++;
        }
        if (position >= input.length()) {
            throw new RuntimeException("Unterminated string literal");
        }
        position++; // Skip the closing quote
        String stringLiteral = input.substring(start + 1, position - 1);
        return new Token("STRING", stringLiteral);
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        Token token;
        while ((token = nextToken()) != null) {
            tokens.add(token);
        }
        return tokens;
    }
}
