// generated with ast extension for cup
// version 0.8
// 22/5/2021 15:44:57


package rs.ac.bg.etf.pp1.ast;

public class DesignatorOneArrayProsledjivanje extends Designator {

    private DesignatorOneArray DesignatorOneArray;

    public DesignatorOneArrayProsledjivanje (DesignatorOneArray DesignatorOneArray) {
        this.DesignatorOneArray=DesignatorOneArray;
        if(DesignatorOneArray!=null) DesignatorOneArray.setParent(this);
    }

    public DesignatorOneArray getDesignatorOneArray() {
        return DesignatorOneArray;
    }

    public void setDesignatorOneArray(DesignatorOneArray DesignatorOneArray) {
        this.DesignatorOneArray=DesignatorOneArray;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorOneArray!=null) DesignatorOneArray.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorOneArray!=null) DesignatorOneArray.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorOneArray!=null) DesignatorOneArray.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorOneArrayProsledjivanje(\n");

        if(DesignatorOneArray!=null)
            buffer.append(DesignatorOneArray.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorOneArrayProsledjivanje]");
        return buffer.toString();
    }
}
