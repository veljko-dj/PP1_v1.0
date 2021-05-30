// generated with ast extension for cup
// version 0.8
// 30/4/2021 15:3:32


package rs.ac.bg.etf.pp1.ast;

public class UnmatchedIfElse extends Unmatched {

    private Condition Condition;
    private MatchedTrue MatchedTrue;
    private Elselse Elselse;
    private StatementFalse StatementFalse;

    public UnmatchedIfElse (Condition Condition, MatchedTrue MatchedTrue, Elselse Elselse, StatementFalse StatementFalse) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.MatchedTrue=MatchedTrue;
        if(MatchedTrue!=null) MatchedTrue.setParent(this);
        this.Elselse=Elselse;
        if(Elselse!=null) Elselse.setParent(this);
        this.StatementFalse=StatementFalse;
        if(StatementFalse!=null) StatementFalse.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public MatchedTrue getMatchedTrue() {
        return MatchedTrue;
    }

    public void setMatchedTrue(MatchedTrue MatchedTrue) {
        this.MatchedTrue=MatchedTrue;
    }

    public Elselse getElselse() {
        return Elselse;
    }

    public void setElselse(Elselse Elselse) {
        this.Elselse=Elselse;
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
        if(MatchedTrue!=null) MatchedTrue.accept(visitor);
        if(Elselse!=null) Elselse.accept(visitor);
        if(StatementFalse!=null) StatementFalse.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(MatchedTrue!=null) MatchedTrue.traverseTopDown(visitor);
        if(Elselse!=null) Elselse.traverseTopDown(visitor);
        if(StatementFalse!=null) StatementFalse.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(MatchedTrue!=null) MatchedTrue.traverseBottomUp(visitor);
        if(Elselse!=null) Elselse.traverseBottomUp(visitor);
        if(StatementFalse!=null) StatementFalse.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("UnmatchedIfElse(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MatchedTrue!=null)
            buffer.append(MatchedTrue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Elselse!=null)
            buffer.append(Elselse.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementFalse!=null)
            buffer.append(StatementFalse.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [UnmatchedIfElse]");
        return buffer.toString();
    }
}
