package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Assign extends BinaryExpr {

    public Assign(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return l + " := " + r;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult rRes = r.typecheck(E);
        Substitution s1 = rRes.s;
        Type t1 = rRes.t;

        TypeResult lRes = l.typecheck(s1.compose(E));
        Substitution s2 = lRes.s;
        Type t2 = lRes.t;

        Substitution s3 = t2.unify(new RefType(s2.apply(t1)));

        return TypeResult.of(s3.compose(s2.compose(s1)), Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        RefValue rv = (RefValue) l.eval(s);
        Value v = r.eval(s);

        s.M.put(rv.p, v);
        return Value.UNIT;
    }
}
