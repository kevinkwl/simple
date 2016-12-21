package simpl.typing;

import simpl.parser.Symbol;

import java.util.HashSet;
import java.util.Set;

public class TypeVar extends Type {

    private static int tvcnt = 0;

    private boolean equalityType;
    private Symbol name;

    public TypeVar(boolean equalityType) {
        this.equalityType = equalityType;
        name = Symbol.symbol("tv" + ++tvcnt);
    }

    @Override
    public boolean isEqualityType() {
        return equalityType;
    }

    @Override
    public Substitution unify(Type t) throws TypeCircularityError {
        if (t instanceof TypeVar) {
            return Substitution.of(this, t);
        }
        if (t.contains(this)) {
            throw new TypeCircularityError();
        } else {
            return Substitution.of(this, t);
        }
    }

    public String toString() {
        return "" + name;
    }

    @Override
    public boolean contains(TypeVar tv) {
        if (this.name.equals(tv.name)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        if (a.name.equals(this.name)) {
            return t;
        } else {
            return this;
        }
    }

    @Override
    public Set<TypeVar> unsolved() {
        Set<TypeVar> s1 = new HashSet<>();
        s1.add(this);
        return s1;
    }
}
