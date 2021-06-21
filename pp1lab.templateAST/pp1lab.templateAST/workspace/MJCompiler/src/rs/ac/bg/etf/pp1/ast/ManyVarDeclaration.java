// generated with ast extension for cup
// version 0.8
// 21/5/2021 14:13:7


package rs.ac.bg.etf.pp1.ast;

public class ManyVarDeclaration extends ManyVarDecl {

    private ManyVarDecl ManyVarDecl;
    private VarDecl VarDecl;

    public ManyVarDeclaration (ManyVarDecl ManyVarDecl, VarDecl VarDecl) {
        this.ManyVarDecl=ManyVarDecl;
        if(ManyVarDecl!=null) ManyVarDecl.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public ManyVarDecl getManyVarDecl() {
        return ManyVarDecl;
    }

    public void setManyVarDecl(ManyVarDecl ManyVarDecl) {
        this.ManyVarDecl=ManyVarDecl;
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
        if(ManyVarDecl!=null) ManyVarDecl.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ManyVarDecl!=null) ManyVarDecl.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ManyVarDecl!=null) ManyVarDecl.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ManyVarDeclaration(\n");

        if(ManyVarDecl!=null)
            buffer.append(ManyVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ManyVarDeclaration]");
        return buffer.toString();
    }
}
