// generated with ast extension for cup
// version 0.8
// 27/5/2021 0:48:14


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclOneElementNumber extends ConstDeclOne {

    private String numIdent;
    private Integer val;

    public ConstDeclOneElementNumber (String numIdent, Integer val) {
        this.numIdent=numIdent;
        this.val=val;
    }

    public String getNumIdent() {
        return numIdent;
    }

    public void setNumIdent(String numIdent) {
        this.numIdent=numIdent;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
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
        buffer.append("ConstDeclOneElementNumber(\n");

        buffer.append(" "+tab+numIdent);
        buffer.append("\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclOneElementNumber]");
        return buffer.toString();
    }
}
