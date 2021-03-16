// generated with ast extension for cup
// version 0.8
// 16/2/2021 15:41:39


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclOneElementNumber extends ConstDeclOne {

    private String nName;
    private Integer val;

    public ConstDeclOneElementNumber (String nName, Integer val) {
        this.nName=nName;
        this.val=val;
    }

    public String getNName() {
        return nName;
    }

    public void setNName(String nName) {
        this.nName=nName;
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

        buffer.append(" "+tab+nName);
        buffer.append("\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclOneElementNumber]");
        return buffer.toString();
    }
}
