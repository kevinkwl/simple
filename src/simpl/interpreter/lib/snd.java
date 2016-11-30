package simpl.interpreter.lib;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class snd extends FunValue {

    public snd() {
        super(Env.empty, Symbol.symbol("__pair__snd"), new Expr() {
            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                Value v = s.E.get(Symbol.symbol("__pair__snd"));
                if (v instanceof PairValue) {
                    return ((PairValue) v).v2;
                }
                throw new RuntimeError("snd can only be applied on pairs.");
            }
        });
    }
}
