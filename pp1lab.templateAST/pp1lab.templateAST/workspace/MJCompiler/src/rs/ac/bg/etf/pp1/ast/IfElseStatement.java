// generated with ast extension for cup
// version 0.8
// 25/5/2021 23:31:45


package rs.ac.bg.etf.pp1.ast;

public class IfElseStatement extends Unmatched {

    private Condition Condition;
    private StatementTrue StatementTrue;
    private StatementFalse StatementFalse;

    public IfElseStatement (Condition Condition, StatementTrue StatementTrue, StatementFalse StatementFalse) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.StatementTrue=StatementTrue;
        if(StatementTrue!=null) StatementTrue.setParent(this);
        this.StatementFalse=StatementFalse;
        if(StatementFalse!=null) StatementFalse.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public StatementTrue getStatementTrue() {
        return StatementTrue;
    }

    public void setStatementTrue(StatementTrue StatementTrue) {
        this.StatementTrue=StatementTrue;
    }

    public StatementFalse getStatementFalse() {
        return StatementFalse;
    }

    public void setStatementFalse(StatementFalse StatementFalse) {
        this.StatementFalse=StatementFalse;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(StatementTrue!=null) StatementTrue.accept(visitor);
        if(StatementFalse!=null) StatementFalse.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(StatementTrue!=null) StatementTrue.traverseTopDown(visitor);
        if(StatementFalse!=null) StatementFalse.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(StatementTrue!=null) StatementTrue.traverseBottomUp(visitor);
        if(StatementFalse!=null) StatementFalse.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfElseStatement(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementTrue!=null)
            buffer.append(StatementTrue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementFalse!=null)
            buffer.append(StatementFalse.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfElseStatement]");
        return buffer.toString();
    }
}
