package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Nil extends Expr {

    public String toString() {
        return "nil";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeVar tv = new TypeVar(false);
        return TypeResult.of(Substitution.IDENTITY, new ListType(tv));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return null;
    }
}
