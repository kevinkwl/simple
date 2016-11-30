package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Rec extends Expr {

    public Symbol x;
    public Expr e;

    public Rec(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(rec " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeVar tv = new TypeVar(false);

        TypeResult Res = e.typecheck(TypeEnv.of(E, x, tv));
        Substitution s1 = Res.t.unify(Res.s.apply(tv));

        s1 = s1.compose(Res.s);
        return TypeResult.of(s1, s1.apply(tv));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        RecValue rv = new RecValue(s.E, x, e);
        Env env = new Env(s.E, x, rv);
        return e.eval(State.of(env, s.M, s.p));
    }
}
