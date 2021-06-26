// generated with ast extension for cup
// version 0.8
// 27/5/2021 0:0:32


package rs.ac.bg.etf.pp1.ast;

public class ConstVarClassDeclList_Const extends ConstVarClassDeclList {

    private ConstVarClassDeclList ConstVarClassDeclList;
    private ConstDecl ConstDecl;

    public ConstVarClassDeclList_Const (ConstVarClassDeclList ConstVarClassDeclList, ConstDecl ConstDecl) {
        this.ConstVarClassDeclList=ConstVarClassDeclList;
        if(ConstVarClassDeclList!=null) ConstVarClassDeclList.setParent(this);
        this.ConstDecl=ConstDecl;
        if(ConstDecl!=null) ConstDecl.setParent(this);
    }

    public ConstVarClassDeclList getConstVarClassDeclList() {
        return ConstVarClassDeclList;
    }

    public void setConstVarClassDeclList(ConstVarClassDeclList ConstVarClassDeclList) {
        this.ConstVarClassDeclList=ConstVarClassDeclList;
    }

    public ConstDecl getConstDecl() {
        return ConstDecl;
    }

    public void setConstDecl(ConstDecl ConstDecl) {
        this.ConstDecl=ConstDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstVarClassDeclList!=null) ConstVarClassDeclList.accept(visitor);
        if(ConstDecl!=null) ConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstVarClassDeclList!=null) ConstVarClassDeclList.traverseTopDown(visitor);
        if(ConstDecl!=null) ConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstVarClassDeclList!=null) ConstVarClassDeclList.traverseBottomUp(visitor);
        if(ConstDecl!=null) ConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstVarClassDeclList_Const(\n");

        if(ConstVarClassDeclList!=null)
            buffer.append(ConstVarClassDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDecl!=null)
            buffer.append(ConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstVarClassDeclList_Const]");
        return buffer.toString();
    }
}
