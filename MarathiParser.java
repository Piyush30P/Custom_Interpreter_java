// MarathiParser.java
import java.util.ArrayList;
import java.util.List;

public class MarathiParser {
    private List<Token> tokens;
    private int current = 0;

    public MarathiParser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public ASTNode parse() {
        List<ASTNode> statements = new ArrayList<>();
        while (!isAtEnd()) {
            statements.add(parseStatement());
        }
        return statements.size() == 1 ? statements.get(0) : new BlockNode(statements);
    }

    private ASTNode parseStatement() {
        Token token = consume();
        switch (token.getType()) {
            case "VAR_DECL":
                return parseVariableDeclaration();
            case "IF":
                return parseIfStatement();
            case "PRINT":
                return parsePrintStatement();
            default:
                throw new RuntimeException("Unexpected token: " + token.getType());
        }
    }

    private ASTNode parseVariableDeclaration() {
        String variableName = consume().getValue(); // Identifier
        consume(); // Skip "="
        String value = consume().getValue(); // Number
        consume(); // Skip ";"
        return new VariableDeclarationNode(variableName, value);
    }

    private ASTNode parseIfStatement() {
        consume(); // Skip "("
        ASTNode condition = parseCondition();
        consume(); // Skip ")"
        ASTNode thenBranch = parseStatement();
        ASTNode elseBranch = null;
        if (match("ELSE")) {
            elseBranch = parseStatement();
        }
        return new IfStatementNode(condition, thenBranch, elseBranch);
    }

    private ASTNode parseCondition() {
        String variableName = consume().getValue(); // Identifier
        String operator = consume().getValue(); // Operator
        String value = consume().getValue(); // Number
        return new ConditionNode(variableName, operator, value);
    }

    private ASTNode parsePrintStatement() {
        consume(); // Skip "("
        String message = consume().getValue(); // String
        consume(); // Skip ")"
        consume(); // Skip ";"
        return new PrintStatementNode(message);
    }

    private boolean isAtEnd() {
        return current >= tokens.size();
    }

    private Token consume() {
        if (isAtEnd()) throw new RuntimeException("Unexpected end of input");
        return tokens.get(current++);
    }

    private boolean match(String type) {
        if (isAtEnd()) return false;
        if (tokens.get(current).getType().equals(type)) {
            current++;
            return true;
        }
        return false;
    }
}
