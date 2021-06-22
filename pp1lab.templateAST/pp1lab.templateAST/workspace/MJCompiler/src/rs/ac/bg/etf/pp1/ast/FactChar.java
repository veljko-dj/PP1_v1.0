// generated with ast extension for cup
// version 0.8
// 21/5/2021 16:40:7


package rs.ac.bg.etf.pp1.ast;

public class FactChar extends Factor {

    private Character C1;

    public FactChar (Character C1) {
        this.C1=C1;
    }

    public Character getC1() {
        return C1;
    }

    public void setC1(Character C1) {
        this.C1=C1;
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
        buffer.append("FactChar(\n");

        buffer.append(" "+tab+C1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactChar]");
        return buffer.toString();
    }
}
