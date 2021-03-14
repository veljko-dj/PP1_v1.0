// generated with ast extension for cup
// version 0.8
// 15/2/2021 0:36:40


package src.rs.ac.bg.etf.pp1.ast;

public class ConstDeclOneElementChar extends ConstDeclOne {

    private String cName;
    private Char val;

    public ConstDeclOneElementChar (String cName, Char val) {
        this.cName=cName;
        this.val=val;
        if(val!=null) val.setParent(this);
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName=cName;
    }

    public Char getVal() {
        return val;
    }

    public void setVal(Char val) {
        this.val=val;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(val!=null) val.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(val!=null) val.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(val!=null) val.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclOneElementChar(\n");

        buffer.append(" "+tab+cName);
        buffer.append("\n");

        if(val!=null)
            buffer.append(val.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclOneElementChar]");
        return buffer.toString();
    }
}
