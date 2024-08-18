public class MarathiInterpreter {
    public void interpret(ASTNode ast) {
        if (ast instanceof IfStatementNode) {
            interpretIfStatement((IfStatementNode) ast);
        } else if (ast instanceof VariableDeclarationNode) {
            interpretVariableDeclaration((VariableDeclarationNode) ast);
        } else if (ast instanceof PrintStatementNode) {
            interpretPrintStatement((PrintStatementNode) ast);
        }
    }

    private void interpretIfStatement(IfStatementNode node) {
        // Example interpretation logic
        System.out.println("Interpreting If Statement...");
        interpret(node.getCondition());
        System.out.println("Then Branch:");
        interpret(node.getThenBranch());
        if (node.getElseBranch() != null) {
            System.out.println("Else Branch:");
            interpret(node.getElseBranch());
        }
    }

    private void interpretVariableDeclaration(VariableDeclarationNode node) {
        // Example interpretation logic
        System.out.println("Variable Declaration:");
        System.out.println("  Variable Name: " + node.getVariableName());
        System.out.println("  Value: " + node.getValue());
    }

    private void interpretPrintStatement(PrintStatementNode node) {
        // Example interpretation logic
        System.out.println("Print Statement:");
        System.out.println("  Message: " + node.getMessage());
    }
}
