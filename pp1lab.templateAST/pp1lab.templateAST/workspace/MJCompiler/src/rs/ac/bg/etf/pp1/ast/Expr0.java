// generated with ast extension for cup
// version 0.8
// 30/4/2021 17:31:45


package rs.ac.bg.etf.pp1.ast;

public class Expr0 extends Expr {

    private ExprCondition ExprCondition;
    private ExprConditionTrue ExprConditionTrue;
    private ExprConditionFalse ExprConditionFalse;

    public Expr0 (ExprCondition ExprCondition, ExprConditionTrue ExprConditionTrue, ExprConditionFalse ExprConditionFalse) {
        this.ExprCondition=ExprCondition;
        if(ExprCondition!=null) ExprCondition.setParent(this);
        this.ExprConditionTrue=ExprConditionTrue;
        if(ExprConditionTrue!=null) ExprConditionTrue.setParent(this);
        this.ExprConditionFalse=ExprConditionFalse;
        if(ExprConditionFalse!=null) ExprConditionFalse.setParent(this);
    }

    public ExprCondition getExprCondition() {
        return ExprCondition;
    }

    public void setExprCondition(ExprCondition ExprCondition) {
        this.ExprCondition=ExprCondition;
    }

    public ExprConditionTrue getExprConditionTrue() {
        return ExprConditionTrue;
    }

    public void setExprConditionTrue(ExprConditionTrue ExprConditionTrue) {
        this.ExprConditionTrue=ExprConditionTrue;
    }

    public ExprConditionFalse getExprConditionFalse() {
        return ExprConditionFalse;
    }

    public void setExprConditionFalse(ExprConditionFalse ExprConditionFalse) {
        this.ExprConditionFalse=ExprConditionFalse;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprCondition!=null) ExprCondition.accept(visitor);
        if(ExprConditionTrue!=null) ExprConditionTrue.accept(visitor);
        if(ExprConditionFalse!=null) ExprConditionFalse.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprCondition!=null) ExprCondition.traverseTopDown(visitor);
        if(ExprConditionTrue!=null) ExprConditionTrue.traverseTopDown(visitor);
        if(ExprConditionFalse!=null) ExprConditionFalse.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprCondition!=null) ExprCondition.traverseBottomUp(visitor);
        if(ExprConditionTrue!=null) ExprConditionTrue.traverseBottomUp(visitor);
        if(ExprConditionFalse!=null) ExprConditionFalse.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Expr0(\n");

        if(ExprCondition!=null)
            buffer.append(ExprCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprConditionTrue!=null)
            buffer.append(ExprConditionTrue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprConditionFalse!=null)
            buffer.append(ExprConditionFalse.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Expr0]");
        return buffer.toString();
    }
}
