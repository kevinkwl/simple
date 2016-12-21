package simpl.interpreter.lib;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

/**
 * Created by kevin on 21/12/2016.
 */
public class force extends FunValue{
    public force() {
        super(Env.empty, Symbol.symbol("force_val"), new Expr() {
            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                Value v = s.E.get(Symbol.symbol("force_val"));
                if (v instanceof LazyValue) {
                    return ((LazyValue) v).force(s);
                }
                throw new RuntimeError("force can only be applied on lazy.");
            }
        });
    }
}
