package simpl.typing;

import java.util.HashSet;
import java.util.Set;

final class IntType extends Type {

    protected IntType() {
    }

    @Override
    public boolean isEqualityType() {
        return true;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        if (t instanceof IntType) {
            return Substitution.IDENTITY;
        }
        if (t instanceof TypeVar) {
            return t.unify(this);
        }
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        return Type.INT;
    }

    public String toString() {
        return "int";
    }

    @Override
    public Set<TypeVar> unsolved() {
        return new HashSet<>();
    }
}
