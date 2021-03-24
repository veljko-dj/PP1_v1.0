// generated with ast extension for cup
// version 0.8
// 23/2/2021 20:8:2


package rs.ac.bg.etf.pp1.ast;

public class Expr0 extends Expr {

    private Condition Condition;
    private ExprManjiProstiji ExprManjiProstiji;
    private ExprManjiProstiji ExprManjiProstiji1;

    public Expr0 (Condition Condition, ExprManjiProstiji ExprManjiProstiji, ExprManjiProstiji ExprManjiProstiji1) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.ExprManjiProstiji=ExprManjiProstiji;
        if(ExprManjiProstiji!=null) ExprManjiProstiji.setParent(this);
        this.ExprManjiProstiji1=ExprManjiProstiji1;
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(ExprManjiProstiji!=null) ExprManjiProstiji.accept(visitor);
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(ExprManjiProstiji!=null) ExprManjiProstiji.traverseTopDown(visitor);
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(ExprManjiProstiji!=null) ExprManjiProstiji.traverseBottomUp(visitor);
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Expr0(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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

        buffer.append(tab);
        buffer.append(") [Expr0]");
        return buffer.toString();
    }
}
