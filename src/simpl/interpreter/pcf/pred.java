package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class pred extends FunValue {

    public pred() {
        super(Env.empty, Symbol.symbol("__pred__x"), new Expr() {
            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                Value v = s.E.get(Symbol.symbol("__pred__x"));
                if (v instanceof IntValue) {
//                    if (v.equals(Value.ZERO))
//                        throw new RuntimeError("pred on zero is undefined");
                    return new IntValue(((IntValue) v).n - 1);
                }
                throw new RuntimeError("pred can only be applied on int values");
            }
        });
    }
}
