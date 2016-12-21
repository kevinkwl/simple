package simpl.interpreter;

import simpl.parser.ast.Expr;

/**
 * Created by kevin on 21/12/2016.
 */
public class LazyValue extends Value {

    private final Env E;
    private final Expr e;

    private boolean isVal;
    private Value val;

    public LazyValue(Env E, Expr e) {
        this.E = E;
        this.e = e;
    }

    public boolean isVal() {
        return this.isVal;
    }

    public Value force(State s) throws RuntimeError {
        if (this.isVal)
            return this.val;
        Value v = e.eval(State.of(this.E, s.M, s.p));
        this.isVal = true;
        if (v instanceof LazyValue)
            this.val = ((LazyValue) v).force(s);
        else
            this.val = v;
        return this.val;
    }



    public String toString() {
        return "lazy" + e;
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }
}
