package simpl.parser.ast;

import simpl.typing.*;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult lRes = l.typecheck(E);

        TypeResult rRes = r.typecheck(lRes.s.compose(E));

        Substitution s = rRes.t.unify(rRes.s.apply(lRes.t));

        if (s.apply(rRes.t).isEqualityType())
            return TypeResult.of(s.compose(rRes.s.compose(lRes.s)), Type.BOOL);

        throw new TypeMismatchError();
    }
}
