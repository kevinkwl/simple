package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult rRes = r.typecheck(E);
        Substitution s1 = rRes.s;
        Type t1 = rRes.t;
        TypeVar t2 = new TypeVar(false);    //TODO: default false ???

        TypeResult lRes = l.typecheck(rRes.s.compose(E));
        Substitution s2 = lRes.s;
        Type t = lRes.t;

        Substitution s3 = t.unify(new ArrowType(t1, t2));
        return TypeResult.of(s3.compose(s2.compose(s1)), s3.apply(t2));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        FunValue lval = (FunValue) l.eval(s);

        return null;
    }
}
