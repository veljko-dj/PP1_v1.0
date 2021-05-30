// generated with ast extension for cup
// version 0.8
// 30/4/2021 15:55:27


package rs.ac.bg.etf.pp1.ast;

public class VarDeclError1 extends VarDeclOne {

    public VarDeclError1 () {
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
        buffer.append("VarDeclError1(\n");

        buffer.append(tab);
        buffer.append(") [VarDeclError1]");
        return buffer.toString();
    }
}
