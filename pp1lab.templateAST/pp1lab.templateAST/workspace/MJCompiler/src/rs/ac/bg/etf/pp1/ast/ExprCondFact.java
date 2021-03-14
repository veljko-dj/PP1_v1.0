// generated with ast extension for cup
// version 0.8
// 15/2/2021 0:36:40


package src.rs.ac.bg.etf.pp1.ast;

public class ExprCondFact extends Expr {

    private CondFact CondFact;
    private ExprManjiProstiji ExprManjiProstiji;
    private ExprManjiProstiji ExprManjiProstiji1;

    public ExprCondFact (CondFact CondFact, ExprManjiProstiji ExprManjiProstiji, ExprManjiProstiji ExprManjiProstiji1) {
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
        this.ExprManjiProstiji=ExprManjiProstiji;
        if(ExprManjiProstiji!=null) ExprManjiProstiji.setParent(this);
        this.ExprManjiProstiji1=ExprManjiProstiji1;
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.setParent(this);
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
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
        if(CondFact!=null) CondFact.accept(visitor);
        if(ExprManjiProstiji!=null) ExprManjiProstiji.accept(visitor);
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
        if(ExprManjiProstiji!=null) ExprManjiProstiji.traverseTopDown(visitor);
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        if(ExprManjiProstiji!=null) ExprManjiProstiji.traverseBottomUp(visitor);
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprCondFact(\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
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
        buffer.append(") [ExprCondFact]");
        return buffer.toString();
    }
}
