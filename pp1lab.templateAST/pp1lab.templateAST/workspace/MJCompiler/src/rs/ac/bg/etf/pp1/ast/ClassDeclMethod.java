// generated with ast extension for cup
// version 0.8
// 27/5/2021 0:48:14


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclMethod extends ClassDecl {

    private String className;
    private Extends Extends;
    private ManyVarDecl ManyVarDecl;
    private MethodDeclList MethodDeclList;

    public ClassDeclMethod (String className, Extends Extends, ManyVarDecl ManyVarDecl, MethodDeclList MethodDeclList) {
        this.className=className;
        this.Extends=Extends;
        if(Extends!=null) Extends.setParent(this);
        this.ManyVarDecl=ManyVarDecl;
        if(ManyVarDecl!=null) ManyVarDecl.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className=className;
    }

    public Extends getExtends() {
        return Extends;
    }

    public void setExtends(Extends Extends) {
        this.Extends=Extends;
    }

    public ManyVarDecl getManyVarDecl() {
        return ManyVarDecl;
    }

    public void setManyVarDecl(ManyVarDecl ManyVarDecl) {
        this.ManyVarDecl=ManyVarDecl;
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Extends!=null) Extends.accept(visitor);
        if(ManyVarDecl!=null) ManyVarDecl.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Extends!=null) Extends.traverseTopDown(visitor);
        if(ManyVarDecl!=null) ManyVarDecl.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Extends!=null) Extends.traverseBottomUp(visitor);
        if(ManyVarDecl!=null) ManyVarDecl.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclMethod(\n");

        buffer.append(" "+tab+className);
        buffer.append("\n");

        if(Extends!=null)
            buffer.append(Extends.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ManyVarDecl!=null)
            buffer.append(ManyVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclMethod]");
        return buffer.toString();
    }
}
