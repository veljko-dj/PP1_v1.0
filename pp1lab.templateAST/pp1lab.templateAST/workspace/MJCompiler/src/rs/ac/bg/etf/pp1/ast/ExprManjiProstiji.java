// generated with ast extension for cup
// version 0.8
// 15/2/2021 0:36:40


package src.rs.ac.bg.etf.pp1.ast;

public class ExprManjiProstiji implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private OneMinus OneMinus;
    private Term Term;
    private TermsInExpr TermsInExpr;

    public ExprManjiProstiji (OneMinus OneMinus, Term Term, TermsInExpr TermsInExpr) {
        this.OneMinus=OneMinus;
        if(OneMinus!=null) OneMinus.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.TermsInExpr=TermsInExpr;
        if(TermsInExpr!=null) TermsInExpr.setParent(this);
    }

    public OneMinus getOneMinus() {
        return OneMinus;
    }

    public void setOneMinus(OneMinus OneMinus) {
        this.OneMinus=OneMinus;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public TermsInExpr getTermsInExpr() {
        return TermsInExpr;
    }

    public void setTermsInExpr(TermsInExpr TermsInExpr) {
        this.TermsInExpr=TermsInExpr;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OneMinus!=null) OneMinus.accept(visitor);
        if(Term!=null) Term.accept(visitor);
        if(TermsInExpr!=null) TermsInExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OneMinus!=null) OneMinus.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(TermsInExpr!=null) TermsInExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OneMinus!=null) OneMinus.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(TermsInExpr!=null) TermsInExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprManjiProstiji(\n");

        if(OneMinus!=null)
            buffer.append(OneMinus.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TermsInExpr!=null)
            buffer.append(TermsInExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprManjiProstiji]");
        return buffer.toString();
    }
}
