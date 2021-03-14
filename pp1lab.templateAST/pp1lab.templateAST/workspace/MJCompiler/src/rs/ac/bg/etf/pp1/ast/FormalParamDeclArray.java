// generated with ast extension for cup
// version 0.8
// 15/2/2021 0:36:40


package src.rs.ac.bg.etf.pp1.ast;

public class FormalParamDeclArray extends FormalParamDecl {

    private Type Type;
    private String paramNameArray;

    public FormalParamDeclArray (Type Type, String paramNameArray) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.paramNameArray=paramNameArray;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getParamNameArray() {
        return paramNameArray;
    }

    public void setParamNameArray(String paramNameArray) {
        this.paramNameArray=paramNameArray;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParamDeclArray(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+paramNameArray);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParamDeclArray]");
        return buffer.toString();
    }
}
