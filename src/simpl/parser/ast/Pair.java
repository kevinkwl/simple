package simpl.parser.ast;

import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.PairType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Pair extends BinaryExpr {

    public Pair(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(pair " + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult lRes = l.typecheck(E);

        TypeResult rRes = r.typecheck(lRes.s.compose(E));
        return TypeResult.of(rRes.s.compose(lRes.s), new PairType(lRes.t, rRes.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value lv = l.eval(s);
        Value rv = r.eval(s);
        return new PairValue(lv, rv);
    }
}
