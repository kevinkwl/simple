package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.typing.RefType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult Res = e.typecheck(E);
        return TypeResult.of(Res.s, new RefType(Res.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v = e.eval(s);

        // code for garbage collector
//        int p = s.M.nextFree();
//
//        if (p == -1) {
//            s.M.gc(s.E);
//            p = s.M.nextFree();
//        }
//        if (p == -1) {
//            throw new RuntimeError("HeapOverFlowError: No enough space.");
//        }
//        s.M.put(p, v);


        // normal
        int p = s.p.get();
        s.M.put(p, v);
        s.p.set(p+1);
        return new RefValue(p);
    }
}
