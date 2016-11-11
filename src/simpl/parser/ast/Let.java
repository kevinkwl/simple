package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Let extends Expr {

    public Symbol x;
    public Expr e1, e2;

    public Let(Symbol x, Expr e1, Expr e2) {
        this.x = x;
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(let " + x + " = " + e1 + " in " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult e1Res = e1.typecheck(E);

        TypeResult e2Res = e2.typecheck(TypeEnv.of(e1Res.s.compose(E), x, e1Res.t));

        return TypeResult.of(e2Res.s.compose(e1Res.s), e2Res.t);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return null;
    }
}
