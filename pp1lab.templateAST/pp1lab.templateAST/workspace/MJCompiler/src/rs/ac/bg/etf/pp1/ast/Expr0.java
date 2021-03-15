// generated with ast extension for cup
// version 0.8
// 15/2/2021 12:18:5


package rs.ac.bg.etf.pp1.ast;

public class Expr0 extends Expr {

    private ExprManjiProstiji ExprManjiProstiji;
    private ExprManjiProstiji ExprManjiProstiji1;
    private ExprManjiProstiji ExprManjiProstiji2;

    public Expr0 (ExprManjiProstiji ExprManjiProstiji, ExprManjiProstiji ExprManjiProstiji1, ExprManjiProstiji ExprManjiProstiji2) {
        this.ExprManjiProstiji=ExprManjiProstiji;
        if(ExprManjiProstiji!=null) ExprManjiProstiji.setParent(this);
        this.ExprManjiProstiji1=ExprManjiProstiji1;
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.setParent(this);
        this.ExprManjiProstiji2=ExprManjiProstiji2;
        if(ExprManjiProstiji2!=null) ExprManjiProstiji2.setParent(this);
    }

    public ExprManjiProstiji getExprManjiProstiji() {
        return ExprManjiProstiji;
    }

    public void setExprManjiProstiji(ExprManjiProstiji ExprManjiProstiji) {
        this.ExprManjiProstiji=ExprManjiProstiji;
    }

    public ExprManjiProstiji getExprManjiProstiji1() {
        return ExprManjiProstiji1;
    }

    public void setExprManjiProstiji1(ExprManjiProstiji ExprManjiProstiji1) {
        this.ExprManjiProstiji1=ExprManjiProstiji1;
    }

    public ExprManjiProstiji getExprManjiProstiji2() {
        return ExprManjiProstiji2;
    }

    public void setExprManjiProstiji2(ExprManjiProstiji ExprManjiProstiji2) {
        this.ExprManjiProstiji2=ExprManjiProstiji2;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprManjiProstiji!=null) ExprManjiProstiji.accept(visitor);
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.accept(visitor);
        if(ExprManjiProstiji2!=null) ExprManjiProstiji2.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprManjiProstiji!=null) ExprManjiProstiji.traverseTopDown(visitor);
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.traverseTopDown(visitor);
        if(ExprManjiProstiji2!=null) ExprManjiProstiji2.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprManjiProstiji!=null) ExprManjiProstiji.traverseBottomUp(visitor);
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.traverseBottomUp(visitor);
        if(ExprManjiProstiji2!=null) ExprManjiProstiji2.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Expr0(\n");

        if(ExprManjiProstiji!=null)
            buffer.append(ExprManjiProstiji.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprManjiProstiji1!=null)
            buffer.append(ExprManjiProstiji1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprManjiProstiji2!=null)
            buffer.append(ExprManjiProstiji2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Expr0]");
        return buffer.toString();
    }
}
