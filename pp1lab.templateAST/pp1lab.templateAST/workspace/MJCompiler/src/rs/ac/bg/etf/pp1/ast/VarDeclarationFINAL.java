// generated with ast extension for cup
// version 0.8
// 25/5/2021 23:31:45


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarationFINAL extends VarDecl {

    private Finalno Finalno;
    private Type Type;
    private VarDeclList VarDeclList;
    private KrajFinal KrajFinal;

    public VarDeclarationFINAL (Finalno Finalno, Type Type, VarDeclList VarDeclList, KrajFinal KrajFinal) {
        this.Finalno=Finalno;
        if(Finalno!=null) Finalno.setParent(this);
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.KrajFinal=KrajFinal;
        if(KrajFinal!=null) KrajFinal.setParent(this);
    }

    public Finalno getFinalno() {
        return Finalno;
    }

    public void setFinalno(Finalno Finalno) {
        this.Finalno=Finalno;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public KrajFinal getKrajFinal() {
        return KrajFinal;
    }

    public void setKrajFinal(KrajFinal KrajFinal) {
        this.KrajFinal=KrajFinal;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Finalno!=null) Finalno.accept(visitor);
        if(Type!=null) Type.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(KrajFinal!=null) KrajFinal.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Finalno!=null) Finalno.traverseTopDown(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(KrajFinal!=null) KrajFinal.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Finalno!=null) Finalno.traverseBottomUp(visitor);
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(KrajFinal!=null) KrajFinal.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarationFINAL(\n");

        if(Finalno!=null)
            buffer.append(Finalno.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(KrajFinal!=null)
            buffer.append(KrajFinal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarationFINAL]");
        return buffer.toString();
    }
}
