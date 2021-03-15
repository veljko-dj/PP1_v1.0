// generated with ast extension for cup
// version 0.8
// 15/2/2021 21:59:50


package rs.ac.bg.etf.pp1.ast;

public class CaseListt extends CaseList {

    private CaseList CaseList;
    private Stagod Stagod;
    private StatementList StatementList;

    public CaseListt (CaseList CaseList, Stagod Stagod, StatementList StatementList) {
        this.CaseList=CaseList;
        if(CaseList!=null) CaseList.setParent(this);
        this.Stagod=Stagod;
        if(Stagod!=null) Stagod.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public CaseList getCaseList() {
        return CaseList;
    }

    public void setCaseList(CaseList CaseList) {
        this.CaseList=CaseList;
    }

    public Stagod getStagod() {
        return Stagod;
    }

    public void setStagod(Stagod Stagod) {
        this.Stagod=Stagod;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CaseList!=null) CaseList.accept(visitor);
        if(Stagod!=null) Stagod.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CaseList!=null) CaseList.traverseTopDown(visitor);
        if(Stagod!=null) Stagod.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CaseList!=null) CaseList.traverseBottomUp(visitor);
        if(Stagod!=null) Stagod.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CaseListt(\n");

        if(CaseList!=null)
            buffer.append(CaseList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Stagod!=null)
            buffer.append(Stagod.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CaseListt]");
        return buffer.toString();
    }
}
