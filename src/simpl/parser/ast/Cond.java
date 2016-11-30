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

public class Cond extends Expr {

    public Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult e1Res = e1.typecheck(E);
        Substitution s1 = e1Res.s;
        Substitution s2 = e1Res.t.unify(Type.BOOL);

        s2 = s2.compose(s1);

        TypeResult e2Res = e2.typecheck(s2.compose(E));
        s2 = e2Res.s.compose(s2);

        TypeResult e3Res = e3.typecheck(s2.compose(E));
        s2 = e3Res.s.compose(s2);
        Substitution s3 = e3Res.t.unify(e3Res.s.apply(e2Res.t));

        return TypeResult.of(s3.compose(s2), s3.apply(e3Res.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        BoolValue bv = (BoolValue) e1.eval(s);
        if (bv.equals(Value.TRUE)) {
            return e2.eval(s);
        } else {
            return e3.eval(s);
        }
    }
}
