// PrintStatementNode.java
public class PrintStatementNode implements ASTNode {
    private String message;

    public PrintStatementNode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "PrintStatementNode{" + "message='" + message + '\'' + '}';
    }
}