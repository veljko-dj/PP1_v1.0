// generated with ast extension for cup
// version 0.8
// 15/2/2021 0:36:40


package src.rs.ac.bg.etf.pp1.ast;

public class FactBool extends Factor {

    private Boolean B1;

    public FactBool (Boolean B1) {
        this.B1=B1;
        if(B1!=null) B1.setParent(this);
    }

    public Boolean getB1() {
        return B1;
    }

    public void setB1(Boolean B1) {
        this.B1=B1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(B1!=null) B1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(B1!=null) B1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(B1!=null) B1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactBool(\n");

        if(B1!=null)
            buffer.append(B1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactBool]");
        return buffer.toString();
    }
}
