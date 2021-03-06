package simpl.typing;

import java.util.HashSet;
import java.util.Set;

final class BoolType extends Type {

    protected BoolType() {
    }

    @Override
    public boolean isEqualityType() {
        return true;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        if (t instanceof BoolType) {
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
        return Type.BOOL;
    }

    public String toString() {
        return "bool";
    }


    @Override
    public Set<TypeVar> unsolved() {
        return new HashSet<>();
    }
}
