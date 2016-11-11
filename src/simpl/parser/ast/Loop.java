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

public class Loop extends Expr {

    public Expr e1, e2;

    public Loop(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(while " + e1 + " do " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult e1Res = e1.typecheck(E);
        Substitution s0 = e1Res.s;
        Substitution s1 = e1Res.t.unify(Type.BOOL);

        s1 = s1.compose(s0);

        TypeResult e2Res = e2.typecheck(s1.compose(E));
        Substitution s2 = e2Res.s;

        Substitution s3 = e2Res.t.unify(Type.UNIT);
        return TypeResult.of(s3.compose(s2.compose(s1)), Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return null;
    }
}
