// generated with ast extension for cup
// version 0.8
// 15/2/2021 0:36:40


package src.rs.ac.bg.etf.pp1.ast;

public class FactChar extends Factor {

    private Char C1;

    public FactChar (Char C1) {
        this.C1=C1;
        if(C1!=null) C1.setParent(this);
    }

    public Char getC1() {
        return C1;
    }

    public void setC1(Char C1) {
        this.C1=C1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(C1!=null) C1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(C1!=null) C1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(C1!=null) C1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactChar(\n");

        if(C1!=null)
            buffer.append(C1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactChar]");
        return buffer.toString();
    }
}
