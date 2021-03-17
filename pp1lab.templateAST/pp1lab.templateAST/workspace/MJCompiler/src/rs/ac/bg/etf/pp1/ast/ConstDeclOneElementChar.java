// generated with ast extension for cup
// version 0.8
// 17/2/2021 19:15:3


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclOneElementChar extends ConstDeclOne {

    private String cName;
    private Character val;

    public ConstDeclOneElementChar (String cName, Character val) {
        this.cName=cName;
        this.val=val;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName=cName;
    }

    public Character getVal() {
        return val;
    }

    public void setVal(Character val) {
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
        buffer.append("ConstDeclOneElementChar(\n");

        buffer.append(" "+tab+cName);
        buffer.append("\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclOneElementChar]");
        return buffer.toString();
    }
}
