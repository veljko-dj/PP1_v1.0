// generated with ast extension for cup
// version 0.8
// 26/3/2021 1:18:29


package rs.ac.bg.etf.pp1.ast;

public class StatBreak extends Matched {

    public StatBreak () {
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
        buffer.append("StatBreak(\n");

        buffer.append(tab);
        buffer.append(") [StatBreak]");
        return buffer.toString();
    }
}
