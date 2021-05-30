// generated with ast extension for cup
// version 0.8
// 30/4/2021 17:31:45


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarations extends VarDeclList {

    private VarDeclList VarDeclList;
    private VarDeclOne VarDeclOne;

    public VarDeclarations (VarDeclList VarDeclList, VarDeclOne VarDeclOne) {
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.VarDeclOne=VarDeclOne;
        if(VarDeclOne!=null) VarDeclOne.setParent(this);
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public VarDeclOne getVarDeclOne() {
        return VarDeclOne;
    }

    public void setVarDeclOne(VarDeclOne VarDeclOne) {
        this.VarDeclOne=VarDeclOne;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(VarDeclOne!=null) VarDeclOne.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(VarDeclOne!=null) VarDeclOne.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(VarDeclOne!=null) VarDeclOne.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarations(\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclOne!=null)
            buffer.append(VarDeclOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarations]");
        return buffer.toString();
    }
}
