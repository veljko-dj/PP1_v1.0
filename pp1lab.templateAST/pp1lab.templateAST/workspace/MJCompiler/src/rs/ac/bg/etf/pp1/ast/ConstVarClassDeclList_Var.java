// generated with ast extension for cup
// version 0.8
// 21/5/2021 14:13:7


package rs.ac.bg.etf.pp1.ast;

public class ConstVarClassDeclList_Var extends ConstVarClassDeclList {

    private ConstVarClassDeclList ConstVarClassDeclList;
    private VarDecl VarDecl;

    public ConstVarClassDeclList_Var (ConstVarClassDeclList ConstVarClassDeclList, VarDecl VarDecl) {
        this.ConstVarClassDeclList=ConstVarClassDeclList;
        if(ConstVarClassDeclList!=null) ConstVarClassDeclList.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public ConstVarClassDeclList getConstVarClassDeclList() {
        return ConstVarClassDeclList;
    }

    public void setConstVarClassDeclList(ConstVarClassDeclList ConstVarClassDeclList) {
        this.ConstVarClassDeclList=ConstVarClassDeclList;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstVarClassDeclList!=null) ConstVarClassDeclList.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstVarClassDeclList!=null) ConstVarClassDeclList.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstVarClassDeclList!=null) ConstVarClassDeclList.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstVarClassDeclList_Var(\n");

        if(ConstVarClassDeclList!=null)
            buffer.append(ConstVarClassDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstVarClassDeclList_Var]");
        return buffer.toString();
    }
}
