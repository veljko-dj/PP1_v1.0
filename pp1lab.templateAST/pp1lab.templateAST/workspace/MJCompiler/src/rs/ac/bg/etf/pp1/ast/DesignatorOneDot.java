// generated with ast extension for cup
// version 0.8
// 27/5/2021 0:48:14


package rs.ac.bg.etf.pp1.ast;

public class DesignatorOneDot extends Designator {

    private String destName;
    private String I1;

    public DesignatorOneDot (String destName, String I1) {
        this.destName=destName;
        this.I1=I1;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName=destName;
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
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
        buffer.append("DesignatorOneDot(\n");

        buffer.append(" "+tab+destName);
        buffer.append("\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorOneDot]");
        return buffer.toString();
    }
}
