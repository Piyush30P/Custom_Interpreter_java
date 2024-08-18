import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Example tokens (you would typically generate these from actual input)
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token("VAR_DECL", "He aahe"));
        tokens.add(new Token("IDENTIFIER", "x"));
        tokens.add(new Token("OPERATOR", "="));
        tokens.add(new Token("NUMBER", "5"));
        tokens.add(new Token("SEMICOLON", ";"));
        tokens.add(new Token("IF", "Jar"));
        tokens.add(new Token("LPAREN", "("));
        tokens.add(new Token("IDENTIFIER", "x"));
        tokens.add(new Token("OPERATOR", "=="));
        tokens.add(new Token("NUMBER", "5"));
        tokens.add(new Token("RPAREN", ")"));
        tokens.add(new Token("VAR_DECL", "He aahe"));
        tokens.add(new Token("IDENTIFIER", "y"));
        tokens.add(new Token("OPERATOR", "="));
        tokens.add(new Token("NUMBER", "10"));
        tokens.add(new Token("SEMICOLON", ";"));
        tokens.add(new Token("ELSE", "Nahitar"));
        tokens.add(new Token("PRINT", "Chapa"));
        tokens.add(new Token("LPAREN", "("));
        tokens.add(new Token("STRING", "x is 5"));
        tokens.add(new Token("RPAREN", ")"));
        tokens.add(new Token("SEMICOLON", ";"));

        // Parse tokens into AST
        MarathiParser parser = new MarathiParser(tokens);
        ASTNode ast = parser.parse();

        // Print tokens
        System.out.println("Tokens:");
        for (Token token : tokens) {
            System.out.println(token);
        }

        // Print AST
        System.out.println("AST:");
        if (ast != null) {
            printAST(ast);
        } else {
            System.out.println("No AST generated.");
        }

        // Interpret AST (if applicable)
        MarathiInterpreter interpreter = new MarathiInterpreter();
        interpreter.interpret(ast);
    }

    // Method to print AST
    public static void printAST(ASTNode node) {
        if (node instanceof BlockNode) {
            System.out.println("Block:");
            BlockNode block = (BlockNode) node;
            for (ASTNode statement : block.getStatements()) {
                printAST(statement);
            }
        } else if (node instanceof IfStatementNode) {
            IfStatementNode ifNode = (IfStatementNode) node;
            System.out.println("If Statement:");
            System.out.println("  Condition:");
            printAST(ifNode.getCondition());
            System.out.println("  Then Branch:");
            printAST(ifNode.getThenBranch());
            if (ifNode.getElseBranch() != null) {
                System.out.println("  Else Branch:");
                printAST(ifNode.getElseBranch());
            }
        } else if (node instanceof VariableDeclarationNode) {
            VariableDeclarationNode varNode = (VariableDeclarationNode) node;
            System.out.println("Variable Declaration:");
            System.out.println("  Variable Name: " + varNode.getVariableName());
            System.out.println("  Value: " + varNode.getValue());
        } else if (node instanceof PrintStatementNode) {
            PrintStatementNode printNode = (PrintStatementNode) node;
            System.out.println("Print Statement:");
            System.out.println("  Message: " + printNode.getMessage());
        } else if (node instanceof ConditionNode) {
            ConditionNode condNode = (ConditionNode) node;
            System.out.println("Condition:");
            System.out.println("  Variable: " + condNode.getVariableName());
            System.out.println("  Operator: " + condNode.getOperator());
            System.out.println("  Value: " + condNode.getValue());
        } else {
            System.out.println("Unknown AST Node Type");
        }
    }
}
