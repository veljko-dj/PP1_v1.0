// generated with ast extension for cup
// version 0.8
// 23/2/2021 20:8:2


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclList_nadovezivanje extends ConstDeclList {

    private ConstDeclList ConstDeclList;
    private ConstDeclOne ConstDeclOne;

    public ConstDeclList_nadovezivanje (ConstDeclList ConstDeclList, ConstDeclOne ConstDeclOne) {
        this.ConstDeclList=ConstDeclList;
        if(ConstDeclList!=null) ConstDeclList.setParent(this);
        this.ConstDeclOne=ConstDeclOne;
        if(ConstDeclOne!=null) ConstDeclOne.setParent(this);
    }

    public ConstDeclList getConstDeclList() {
        return ConstDeclList;
    }

    public void setConstDeclList(ConstDeclList ConstDeclList) {
        this.ConstDeclList=ConstDeclList;
    }

    public ConstDeclOne getConstDeclOne() {
        return ConstDeclOne;
    }

    public void setConstDeclOne(ConstDeclOne ConstDeclOne) {
        this.ConstDeclOne=ConstDeclOne;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclList!=null) ConstDeclList.accept(visitor);
        if(ConstDeclOne!=null) ConstDeclOne.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclList!=null) ConstDeclList.traverseTopDown(visitor);
        if(ConstDeclOne!=null) ConstDeclOne.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclList!=null) ConstDeclList.traverseBottomUp(visitor);
        if(ConstDeclOne!=null) ConstDeclOne.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclList_nadovezivanje(\n");

        if(ConstDeclList!=null)
            buffer.append(ConstDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclOne!=null)
            buffer.append(ConstDeclOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclList_nadovezivanje]");
        return buffer.toString();
    }
}
