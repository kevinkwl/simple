package simpl.parser.ast;

import simpl.interpreter.ConsValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.ListType;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Cons extends BinaryExpr {

    public Cons(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " :: " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult lRes = l.typecheck(E);
        Substitution s1 = lRes.s;

        TypeResult rRes = r.typecheck(s1.compose(E));
        Substitution s2 = rRes.s;

        ListType tlist = new ListType(s2.apply(lRes.t));
        Substitution s3 = rRes.t.unify(tlist);
        return TypeResult.of(s3.compose(s2.compose(s1)), s3.apply(tlist));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value lv = l.eval(s);
        Value rv = r.eval(s);
        return new ConsValue(lv, rv);
    }
}
