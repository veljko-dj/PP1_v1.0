// generated with ast extension for cup
// version 0.8
// 21/5/2021 16:40:7


package rs.ac.bg.etf.pp1.ast;

public class ConditionMulti extends ConditionFinal {

    private ConditionFinal ConditionFinal;
    private CondTerm CondTerm;

    public ConditionMulti (ConditionFinal ConditionFinal, CondTerm CondTerm) {
        this.ConditionFinal=ConditionFinal;
        if(ConditionFinal!=null) ConditionFinal.setParent(this);
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
    }

    public ConditionFinal getConditionFinal() {
        return ConditionFinal;
    }

    public void setConditionFinal(ConditionFinal ConditionFinal) {
        this.ConditionFinal=ConditionFinal;
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionFinal!=null) ConditionFinal.accept(visitor);
        if(CondTerm!=null) CondTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionFinal!=null) ConditionFinal.traverseTopDown(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionFinal!=null) ConditionFinal.traverseBottomUp(visitor);
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionMulti(\n");

        if(ConditionFinal!=null)
            buffer.append(ConditionFinal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionMulti]");
        return buffer.toString();
    }
}
