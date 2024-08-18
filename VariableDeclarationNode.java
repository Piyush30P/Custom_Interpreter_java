// VariableDeclarationNode.java
public class VariableDeclarationNode implements ASTNode {
    private String variableName;
    private String value;

    public VariableDeclarationNode(String variableName, String value) {
        this.variableName = variableName;
        this.value = value;
    }

    public String getVariableName() {
        return variableName;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "VariableDeclarationNode{" + "variableName='" + variableName + '\'' + ", value='" + value + '\'' + '}';
    }
}