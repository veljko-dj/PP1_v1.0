// generated with ast extension for cup
// version 0.8
// 26/5/2021 15:37:42


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclNoMethod extends ClassDecl {

    private String className;
    private Extends Extends;
    private ManyVarDecl ManyVarDecl;

    public ClassDeclNoMethod (String className, Extends Extends, ManyVarDecl ManyVarDecl) {
        this.className=className;
        this.Extends=Extends;
        if(Extends!=null) Extends.setParent(this);
        this.ManyVarDecl=ManyVarDecl;
        if(ManyVarDecl!=null) ManyVarDecl.setParent(this);
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Extends!=null) Extends.accept(visitor);
        if(ManyVarDecl!=null) ManyVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Extends!=null) Extends.traverseTopDown(visitor);
        if(ManyVarDecl!=null) ManyVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Extends!=null) Extends.traverseBottomUp(visitor);
        if(ManyVarDecl!=null) ManyVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclNoMethod(\n");

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

        buffer.append(tab);
        buffer.append(") [ClassDeclNoMethod]");
        return buffer.toString();
    }
}
