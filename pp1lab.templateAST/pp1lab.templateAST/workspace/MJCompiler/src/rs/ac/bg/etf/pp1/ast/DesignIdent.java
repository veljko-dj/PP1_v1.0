// generated with ast extension for cup
// version 0.8
// 17/2/2021 19:15:3


package rs.ac.bg.etf.pp1.ast;

public class DesignIdent extends IdentOrExpr {

    private IdentOrExpr IdentOrExpr;
    private String I2;

    public DesignIdent (IdentOrExpr IdentOrExpr, String I2) {
        this.IdentOrExpr=IdentOrExpr;
        if(IdentOrExpr!=null) IdentOrExpr.setParent(this);
        this.I2=I2;
    }

    public IdentOrExpr getIdentOrExpr() {
        return IdentOrExpr;
    }

    public void setIdentOrExpr(IdentOrExpr IdentOrExpr) {
        this.IdentOrExpr=IdentOrExpr;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
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
        buffer.append("DesignIdent(\n");

        if(IdentOrExpr!=null)
            buffer.append(IdentOrExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignIdent]");
        return buffer.toString();
    }
}
