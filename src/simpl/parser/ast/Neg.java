package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Neg extends UnaryExpr {

    public Neg(Expr e) {
        super(e);
    }

    public String toString() {
        return "~" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult Res = e.typecheck(E);

        Substitution s1 = Res.t.unify(Type.INT);
        return TypeResult.of(s1.compose(Res.s), Type.INT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        IntValue iv = (IntValue) e.eval(s);
        return new IntValue(-iv.n);
    }
}
