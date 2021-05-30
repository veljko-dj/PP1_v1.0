// generated with ast extension for cup
// version 0.8
// 30/4/2021 15:55:27


package rs.ac.bg.etf.pp1.ast;

public class VarDeclError2 extends VarDeclOne {

    private Integer err;

    public VarDeclError2 (Integer err) {
        this.err=err;
    }

    public Integer getErr() {
        return err;
    }

    public void setErr(Integer err) {
        this.err=err;
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
        buffer.append("VarDeclError2(\n");

        buffer.append(" "+tab+err);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclError2]");
        return buffer.toString();
    }
}
