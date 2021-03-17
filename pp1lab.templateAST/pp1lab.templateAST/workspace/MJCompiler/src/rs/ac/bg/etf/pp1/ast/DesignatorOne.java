// generated with ast extension for cup
// version 0.8
// 17/2/2021 19:15:3


package rs.ac.bg.etf.pp1.ast;

public class DesignatorOne extends Designator {

    private String destName;
    private IdentOrExpr IdentOrExpr;

    public DesignatorOne (String destName, IdentOrExpr IdentOrExpr) {
        this.destName=destName;
        this.IdentOrExpr=IdentOrExpr;
        if(IdentOrExpr!=null) IdentOrExpr.setParent(this);
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName=destName;
    }

    public IdentOrExpr getIdentOrExpr() {
        return IdentOrExpr;
    }

    public void setIdentOrExpr(IdentOrExpr IdentOrExpr) {
        this.IdentOrExpr=IdentOrExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentOrExpr!=null) IdentOrExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentOrExpr!=null) IdentOrExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentOrExpr!=null) IdentOrExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorOne(\n");

        buffer.append(" "+tab+destName);
        buffer.append("\n");

        if(IdentOrExpr!=null)
            buffer.append(IdentOrExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorOne]");
        return buffer.toString();
    }
}
