// generated with ast extension for cup
// version 0.8
// 27/5/2021 0:48:14


package rs.ac.bg.etf.pp1.ast;

public class DesignatorOneArray extends Designator {

    private String destName;
    private Expr Expr;

    public DesignatorOneArray (String destName, Expr Expr) {
        this.destName=destName;
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName=destName;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorOneArray(\n");

        buffer.append(" "+tab+destName);
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorOneArray]");
        return buffer.toString();
    }
}
