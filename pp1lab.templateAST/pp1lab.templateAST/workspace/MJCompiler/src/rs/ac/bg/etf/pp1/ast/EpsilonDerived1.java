// generated with ast extension for cup
// version 0.8
// 8/5/2021 15:8:38


package rs.ac.bg.etf.pp1.ast;

public class EpsilonDerived1 extends Epsilon {

    public EpsilonDerived1 () {
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
        buffer.append("EpsilonDerived1(\n");

        buffer.append(tab);
        buffer.append(") [EpsilonDerived1]");
        return buffer.toString();
    }
}
