package simpl.typing;

import java.util.Set;

public final class PairType extends Type {

    public Type t1, t2;

    public PairType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isEqualityType() {
        return true;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        if (t instanceof TypeVar) {
            return t.unify(this);
        }
        if (t instanceof PairType) {
            Substitution s1 = this.t1.unify(((PairType) t).t1);
            Substitution s2 = s1.apply(this.t2).unify(s1.apply(((PairType) t).t2));

            return s2.compose(s1);
        }
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        return t1.contains(tv) || t2.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        return new PairType(t1.replace(a, t), t2.replace(a, t));
    }

    public String toString() {
        return "(" + t1 + " * " + t2 + ")";
    }

    @Override
    public Set<TypeVar> unsolved() {
        Set<TypeVar> s1 = t1.unsolved();
        s1.addAll(t2.unsolved());
        return s1;
    }
}
