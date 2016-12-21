package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.typing.*;

/**
 * Created by kevin on 21/12/2016.
 */
public class Lazy extends UnaryExpr {

    public Lazy(Expr e) {
        super(e);
    }

    public String toString() {
        return "lazy " + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        return e.typecheck(E);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        return new LazyValue(s.E, e);
    }
}
