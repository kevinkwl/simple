package simpl.parser.ast;

import simpl.interpreter.*;

public class Div extends ArithExpr {

    public Div(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " / " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        IntValue iv1 = (IntValue) l.eval(s);
        IntValue iv2 = (IntValue) r.eval(s);

        if (iv2.equals(Value.ZERO))
            throw new ZeroDivisionError();
        return new IntValue(iv1.n / iv2.n);
    }
}
