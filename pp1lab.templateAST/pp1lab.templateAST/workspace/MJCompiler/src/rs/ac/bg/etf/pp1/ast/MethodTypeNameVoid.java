// generated with ast extension for cup
// version 0.8
// 15/2/2021 0:36:40


package src.rs.ac.bg.etf.pp1.ast;

public class MethodTypeNameVoid extends MethodTypeName {

    private String methNameVoid;

    public MethodTypeNameVoid (String methNameVoid) {
        this.methNameVoid=methNameVoid;
    }

    public String getMethNameVoid() {
        return methNameVoid;
    }

    public void setMethNameVoid(String methNameVoid) {
        this.methNameVoid=methNameVoid;
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
        buffer.append("MethodTypeNameVoid(\n");

        buffer.append(" "+tab+methNameVoid);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodTypeNameVoid]");
        return buffer.toString();
    }
}
