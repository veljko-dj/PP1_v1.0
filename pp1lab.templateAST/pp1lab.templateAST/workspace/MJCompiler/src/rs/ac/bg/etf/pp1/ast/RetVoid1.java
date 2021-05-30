// generated with ast extension for cup
// version 0.8
// 30/4/2021 17:31:45


package rs.ac.bg.etf.pp1.ast;

public class RetVoid1 extends RetType {

    public RetVoid1 () {
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
        buffer.append("RetVoid1(\n");

        buffer.append(tab);
        buffer.append(") [RetVoid1]");
        return buffer.toString();
    }
}
