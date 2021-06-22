// generated with ast extension for cup
// version 0.8
// 21/5/2021 16:40:7


package rs.ac.bg.etf.pp1.ast;

public class VarDeclOneNoSquare extends VarDeclOne {

    private String nameVarOne;

    public VarDeclOneNoSquare (String nameVarOne) {
        this.nameVarOne=nameVarOne;
    }

    public String getNameVarOne() {
        return nameVarOne;
    }

    public void setNameVarOne(String nameVarOne) {
        this.nameVarOne=nameVarOne;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclOneNoSquare(\n");

        buffer.append(" "+tab+nameVarOne);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclOneNoSquare]");
        return buffer.toString();
    }
}
