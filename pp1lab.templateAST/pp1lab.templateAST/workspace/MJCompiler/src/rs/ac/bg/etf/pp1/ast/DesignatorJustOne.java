// generated with ast extension for cup
// version 0.8
// 15/2/2021 13:48:59


package rs.ac.bg.etf.pp1.ast;

public class DesignatorJustOne extends Designator {

    private String destNameOne;

    public DesignatorJustOne (String destNameOne) {
        this.destNameOne=destNameOne;
    }

    public String getDestNameOne() {
        return destNameOne;
    }

    public void setDestNameOne(String destNameOne) {
        this.destNameOne=destNameOne;
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
        buffer.append("DesignatorJustOne(\n");

        buffer.append(" "+tab+destNameOne);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorJustOne]");
        return buffer.toString();
    }
}
