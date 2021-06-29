// generated with ast extension for cup
// version 0.8
// 29/5/2021 14:5:7


package rs.ac.bg.etf.pp1.ast;

public class DesignatorModif extends Designator {

    private String destName;
    private Integer N1;
    private Integer N2;

    public DesignatorModif (String destName, Integer N1, Integer N2) {
        this.destName=destName;
        this.N1=N1;
        this.N2=N2;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName=destName;
    }

    public Integer getN1() {
        return N1;
    }

    public void setN1(Integer N1) {
        this.N1=N1;
    }

    public Integer getN2() {
        return N2;
    }

    public void setN2(Integer N2) {
        this.N2=N2;
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
        buffer.append("DesignatorModif(\n");

        buffer.append(" "+tab+destName);
        buffer.append("\n");

        buffer.append(" "+tab+N1);
        buffer.append("\n");

        buffer.append(" "+tab+N2);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorModif]");
        return buffer.toString();
    }
}
