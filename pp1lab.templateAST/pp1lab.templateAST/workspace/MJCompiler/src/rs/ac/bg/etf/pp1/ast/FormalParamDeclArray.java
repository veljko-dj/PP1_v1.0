// generated with ast extension for cup
// version 0.8
// 17/2/2021 19:15:3


package rs.ac.bg.etf.pp1.ast;

public class FormalParamDeclArray extends FormalParamDecl {

    private Type Type;
    private String paramNameArry;
    private Array Array;

    public FormalParamDeclArray (Type Type, String paramNameArry, Array Array) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.paramNameArry=paramNameArry;
        this.Array=Array;
        if(Array!=null) Array.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getParamNameArry() {
        return paramNameArry;
    }

    public void setParamNameArry(String paramNameArry) {
        this.paramNameArry=paramNameArry;
    }

    public Array getArray() {
        return Array;
    }

    public void setArray(Array Array) {
        this.Array=Array;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(Array!=null) Array.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(Array!=null) Array.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(Array!=null) Array.traverseBottomUp(visitor);
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

        buffer.append(" "+tab+paramNameArry);
        buffer.append("\n");

        if(Array!=null)
            buffer.append(Array.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParamDeclArray]");
        return buffer.toString();
    }
}
