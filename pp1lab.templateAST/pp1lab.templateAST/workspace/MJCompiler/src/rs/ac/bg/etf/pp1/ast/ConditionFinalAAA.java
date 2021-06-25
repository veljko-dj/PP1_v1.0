// generated with ast extension for cup
// version 0.8
// 25/5/2021 23:31:45


package rs.ac.bg.etf.pp1.ast;

public class ConditionFinalAAA extends Condition {

    private ConditionFinal ConditionFinal;

    public ConditionFinalAAA (ConditionFinal ConditionFinal) {
        this.ConditionFinal=ConditionFinal;
        if(ConditionFinal!=null) ConditionFinal.setParent(this);
    }

    public ConditionFinal getConditionFinal() {
        return ConditionFinal;
    }

    public void setConditionFinal(ConditionFinal ConditionFinal) {
        this.ConditionFinal=ConditionFinal;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionFinal!=null) ConditionFinal.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionFinal!=null) ConditionFinal.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionFinal!=null) ConditionFinal.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionFinalAAA(\n");

        if(ConditionFinal!=null)
            buffer.append(ConditionFinal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionFinalAAA]");
        return buffer.toString();
    }
}
