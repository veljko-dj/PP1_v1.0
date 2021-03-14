// generated with ast extension for cup
// version 0.8
// 15/2/2021 0:36:40


package src.rs.ac.bg.etf.pp1.ast;

public class ConstDeclOneElementBool extends ConstDeclOne {

    private String bName;
    private Boolean val;

    public ConstDeclOneElementBool (String bName, Boolean val) {
        this.bName=bName;
        this.val=val;
        if(val!=null) val.setParent(this);
    }

    public String getBName() {
        return bName;
    }

    public void setBName(String bName) {
        this.bName=bName;
    }

    public Boolean getVal() {
        return val;
    }

    public void setVal(Boolean val) {
        this.val=val;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(val!=null) val.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(val!=null) val.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(val!=null) val.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclOneElementBool(\n");

        buffer.append(" "+tab+bName);
        buffer.append("\n");

        if(val!=null)
            buffer.append(val.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclOneElementBool]");
        return buffer.toString();
    }
}
