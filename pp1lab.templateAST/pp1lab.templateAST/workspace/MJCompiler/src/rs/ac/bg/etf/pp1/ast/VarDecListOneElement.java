// generated with ast extension for cup
// version 0.8
// 30/4/2021 15:3:32


package rs.ac.bg.etf.pp1.ast;

public class VarDecListOneElement extends VarDeclList {

    private VarDeclOne VarDeclOne;

    public VarDecListOneElement (VarDeclOne VarDeclOne) {
        this.VarDeclOne=VarDeclOne;
        if(VarDeclOne!=null) VarDeclOne.setParent(this);
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
        if(VarDeclOne!=null) VarDeclOne.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclOne!=null) VarDeclOne.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclOne!=null) VarDeclOne.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecListOneElement(\n");

        if(VarDeclOne!=null)
            buffer.append(VarDeclOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecListOneElement]");
        return buffer.toString();
    }
}
