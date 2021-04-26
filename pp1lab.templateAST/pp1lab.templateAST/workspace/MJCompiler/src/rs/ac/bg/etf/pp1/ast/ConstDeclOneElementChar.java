// generated with ast extension for cup
// version 0.8
// 26/3/2021 11:3:24


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclOneElementChar extends ConstDeclOne {

    private String charIdent;
    private Character val;

    public ConstDeclOneElementChar (String charIdent, Character val) {
        this.charIdent=charIdent;
        this.val=val;
    }

    public String getCharIdent() {
        return charIdent;
    }

    public void setCharIdent(String charIdent) {
        this.charIdent=charIdent;
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

        buffer.append(" "+tab+charIdent);
        buffer.append("\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclOneElementChar]");
        return buffer.toString();
    }
}
