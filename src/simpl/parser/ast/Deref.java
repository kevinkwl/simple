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
import simpl.typing.TypeVar;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult Res = e.typecheck(E);

        TypeVar tv = new TypeVar(false);
        Substitution s1 = Res.t.unify(new RefType(tv));
        return TypeResult.of(s1.compose(Res.s), s1.apply(tv));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return null;
    }
}
