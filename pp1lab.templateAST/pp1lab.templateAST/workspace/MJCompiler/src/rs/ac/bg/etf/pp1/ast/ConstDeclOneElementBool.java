// generated with ast extension for cup
// version 0.8
// 21/5/2021 16:40:7


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclOneElementBool extends ConstDeclOne {

    private String boolIdent;
    private String val;

    public ConstDeclOneElementBool (String boolIdent, String val) {
        this.boolIdent=boolIdent;
        this.val=val;
    }

    public String getBoolIdent() {
        return boolIdent;
    }

    public void setBoolIdent(String boolIdent) {
        this.boolIdent=boolIdent;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val=val;
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
        buffer.append("ConstDeclOneElementBool(\n");

        buffer.append(" "+tab+boolIdent);
        buffer.append("\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclOneElementBool]");
        return buffer.toString();
    }
}
