package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Name extends Expr {

    public Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        Type t = E.get(x);
        if (t == null)
            throw new TypeError("Undefined Identifier");
        else
            return TypeResult.of(Substitution.IDENTITY, t);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v = s.E.get(x);
        if (v == null)
            throw new NameError(x);
        if (v instanceof RecValue) {
            return ((RecValue) v).e.eval(s);
        }
        return v;
    }
}
