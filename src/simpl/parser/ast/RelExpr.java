package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class RelExpr extends BinaryExpr {

    public RelExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult lRes = l.typecheck(E);
        Substitution s1 = lRes.t.unify(Type.INT);
        s1 = s1.compose(lRes.s);

        TypeResult rRes = r.typecheck(s1.compose(E));
        Substitution s2 = rRes.t.unify(Type.INT);
        s2 = s2.compose(rRes.s);
        return TypeResult.of(s2.compose(s1), Type.BOOL);
    }
}
