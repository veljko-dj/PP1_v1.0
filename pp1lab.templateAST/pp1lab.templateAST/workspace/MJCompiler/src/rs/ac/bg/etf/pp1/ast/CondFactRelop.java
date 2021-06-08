// generated with ast extension for cup
// version 0.8
// 8/5/2021 20:12:36


package rs.ac.bg.etf.pp1.ast;

public class CondFactRelop extends CondFact {

    private ExprManjiProstiji ExprManjiProstiji;
    private Relop Relop;
    private ExprManjiProstiji ExprManjiProstiji1;

    public CondFactRelop (ExprManjiProstiji ExprManjiProstiji, Relop Relop, ExprManjiProstiji ExprManjiProstiji1) {
        this.ExprManjiProstiji=ExprManjiProstiji;
        if(ExprManjiProstiji!=null) ExprManjiProstiji.setParent(this);
        this.Relop=Relop;
        if(Relop!=null) Relop.setParent(this);
        this.ExprManjiProstiji1=ExprManjiProstiji1;
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.setParent(this);
    }

    public ExprManjiProstiji getExprManjiProstiji() {
        return ExprManjiProstiji;
    }

    public void setExprManjiProstiji(ExprManjiProstiji ExprManjiProstiji) {
        this.ExprManjiProstiji=ExprManjiProstiji;
    }

    public Relop getRelop() {
        return Relop;
    }

    public void setRelop(Relop Relop) {
        this.Relop=Relop;
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
        if(ExprManjiProstiji!=null) ExprManjiProstiji.accept(visitor);
        if(Relop!=null) Relop.accept(visitor);
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprManjiProstiji!=null) ExprManjiProstiji.traverseTopDown(visitor);
        if(Relop!=null) Relop.traverseTopDown(visitor);
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprManjiProstiji!=null) ExprManjiProstiji.traverseBottomUp(visitor);
        if(Relop!=null) Relop.traverseBottomUp(visitor);
        if(ExprManjiProstiji1!=null) ExprManjiProstiji1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFactRelop(\n");

        if(ExprManjiProstiji!=null)
            buffer.append(ExprManjiProstiji.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Relop!=null)
            buffer.append(Relop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprManjiProstiji1!=null)
            buffer.append(ExprManjiProstiji1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFactRelop]");
        return buffer.toString();
    }
}
