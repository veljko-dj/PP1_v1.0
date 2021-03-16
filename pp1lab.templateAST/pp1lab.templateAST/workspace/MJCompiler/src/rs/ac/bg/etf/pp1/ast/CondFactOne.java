// generated with ast extension for cup
// version 0.8
// 15/2/2021 21:59:50


package rs.ac.bg.etf.pp1.ast;

public class CondFactOne extends CondFact {

    private ExprManjiProstiji ExprManjiProstiji;

    public CondFactOne (ExprManjiProstiji ExprManjiProstiji) {
        this.ExprManjiProstiji=ExprManjiProstiji;
        if(ExprManjiProstiji!=null) ExprManjiProstiji.setParent(this);
    }

    public ExprManjiProstiji getExprManjiProstiji() {
        return ExprManjiProstiji;
    }

    public void setExprManjiProstiji(ExprManjiProstiji ExprManjiProstiji) {
        this.ExprManjiProstiji=ExprManjiProstiji;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprManjiProstiji!=null) ExprManjiProstiji.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprManjiProstiji!=null) ExprManjiProstiji.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprManjiProstiji!=null) ExprManjiProstiji.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFactOne(\n");

        if(ExprManjiProstiji!=null)
            buffer.append(ExprManjiProstiji.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFactOne]");
        return buffer.toString();
    }
}
