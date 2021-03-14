// generated with ast extension for cup
// version 0.8
// 15/2/2021 0:36:40


package src.rs.ac.bg.etf.pp1.ast;

public class ConstVarClassDeclList_No extends ConstVarClassDeclList {

    public ConstVarClassDeclList_No () {
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
        buffer.append("ConstVarClassDeclList_No(\n");

        buffer.append(tab);
        buffer.append(") [ConstVarClassDeclList_No]");
        return buffer.toString();
    }
}
