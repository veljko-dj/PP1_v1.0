// generated with ast extension for cup
// version 0.8
// 22/5/2021 15:44:57


package rs.ac.bg.etf.pp1.ast;

public class DStatementModif extends DesignatorStatement {

    private DesignatorOneArray DesignatorOneArray;
    private Integer val;

    public DStatementModif (DesignatorOneArray DesignatorOneArray, Integer val) {
        this.DesignatorOneArray=DesignatorOneArray;
        if(DesignatorOneArray!=null) DesignatorOneArray.setParent(this);
        this.val=val;
    }

    public DesignatorOneArray getDesignatorOneArray() {
        return DesignatorOneArray;
    }

    public void setDesignatorOneArray(DesignatorOneArray DesignatorOneArray) {
        this.DesignatorOneArray=DesignatorOneArray;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val=val;
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
        buffer.append("DStatementModif(\n");

        if(DesignatorOneArray!=null)
            buffer.append(DesignatorOneArray.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DStatementModif]");
        return buffer.toString();
    }
}
