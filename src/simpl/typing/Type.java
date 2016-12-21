package simpl.typing;

import java.util.Set;

public abstract class Type {

    public abstract boolean isEqualityType();

    public abstract Type replace(TypeVar a, Type t);

    public abstract boolean contains(TypeVar tv);

    public abstract Substitution unify(Type t) throws TypeError;

    public static final Type INT = new IntType();
    public static final Type BOOL = new BoolType();
    public static final Type UNIT = new UnitType();

    // get unsolved type var , used for let polymorphism
    public abstract Set<TypeVar> unsolved();
}
