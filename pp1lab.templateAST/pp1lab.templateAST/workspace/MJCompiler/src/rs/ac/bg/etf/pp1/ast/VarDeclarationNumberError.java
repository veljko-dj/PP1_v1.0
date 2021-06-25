// generated with ast extension for cup
// version 0.8
// 25/5/2021 23:31:45


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarationNumberError extends VarDecl {

    public VarDeclarationNumberError () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarationNumberError(\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarationNumberError]");
        return buffer.toString();
    }
}
