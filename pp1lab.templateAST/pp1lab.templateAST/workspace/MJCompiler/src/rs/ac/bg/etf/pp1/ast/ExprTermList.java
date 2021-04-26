// generated with ast extension for cup
// version 0.8
// 26/3/2021 11:18:18


package rs.ac.bg.etf.pp1.ast;

public class ExprTermList extends ExprManjiProstiji {

    private ExprManjiProstiji ExprManjiProstiji;
    private Addop Addop;
    private Term Term;

    public ExprTermList (ExprManjiProstiji ExprManjiProstiji, Addop Addop, Term Term) {
        this.ExprManjiProstiji=ExprManjiProstiji;
        if(ExprManjiProstiji!=null) ExprManjiProstiji.setParent(this);
        this.Addop=Addop;
        if(Addop!=null) Addop.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
    }

    public ExprManjiProstiji getExprManjiProstiji() {
        return ExprManjiProstiji;
    }

    public void setExprManjiProstiji(ExprManjiProstiji ExprManjiProstiji) {
        this.ExprManjiProstiji=ExprManjiProstiji;
    }

    public Addop getAddop() {
        return Addop;
    }

    public void setAddop(Addop Addop) {
        this.Addop=Addop;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprManjiProstiji!=null) ExprManjiProstiji.accept(visitor);
        if(Addop!=null) Addop.accept(visitor);
        if(Term!=null) Term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprManjiProstiji!=null) ExprManjiProstiji.traverseTopDown(visitor);
        if(Addop!=null) Addop.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprManjiProstiji!=null) ExprManjiProstiji.traverseBottomUp(visitor);
        if(Addop!=null) Addop.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprTermList(\n");

        if(ExprManjiProstiji!=null)
            buffer.append(ExprManjiProstiji.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Addop!=null)
            buffer.append(Addop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprTermList]");
        return buffer.toString();
    }
}
