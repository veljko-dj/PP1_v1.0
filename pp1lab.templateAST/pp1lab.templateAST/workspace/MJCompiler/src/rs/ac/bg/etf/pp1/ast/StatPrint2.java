// generated with ast extension for cup
// version 0.8
// 23/2/2021 20:8:2


package rs.ac.bg.etf.pp1.ast;

public class StatPrint2 extends Matched {

    private Expr Expr;
    private PrintValue PrintValue;

    public StatPrint2 (Expr Expr, PrintValue PrintValue) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.PrintValue=PrintValue;
        if(PrintValue!=null) PrintValue.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public PrintValue getPrintValue() {
        return PrintValue;
    }

    public void setPrintValue(PrintValue PrintValue) {
        this.PrintValue=PrintValue;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(PrintValue!=null) PrintValue.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(PrintValue!=null) PrintValue.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(PrintValue!=null) PrintValue.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatPrint2(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PrintValue!=null)
            buffer.append(PrintValue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatPrint2]");
        return buffer.toString();
    }
}
