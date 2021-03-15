// generated with ast extension for cup
// version 0.8
// 15/2/2021 13:48:59


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclOneElementBool extends ConstDeclOne {

    private String bName;
    private String val;

    public ConstDeclOneElementBool (String bName, String val) {
        this.bName=bName;
        this.val=val;
    }

    public String getBName() {
        return bName;
    }

    public void setBName(String bName) {
        this.bName=bName;
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

        buffer.append(" "+tab+bName);
        buffer.append("\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclOneElementBool]");
        return buffer.toString();
    }
}
