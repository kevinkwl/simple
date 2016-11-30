package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class AndAlso extends BinaryExpr {

    public AndAlso(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " andalso " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult lRes = l.typecheck(E);
        Substitution s1 = lRes.t.unify(Type.BOOL);
        s1 = s1.compose(lRes.s);

        TypeResult rRes = r.typecheck(s1.compose(E));
        Substitution s2 = rRes.t.unify(Type.BOOL);

        return TypeResult.of(s2.compose(s1), Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        BoolValue lval = (BoolValue) l.eval(s);
        if (lval.equals(Value.FALSE))
            return Value.FALSE;
        else
            return r.eval(s);
    }
}
