package simpl.typing;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kevin on 21/12/2016.
 */
public class TypeScheme extends Type {
    private Type t;
    private Set<TypeVar> unsolved;

    public TypeScheme(Type t, Set<TypeVar> un) {
        this.t = t;
        this.unsolved = un;
    }

    public static TypeScheme generateScheme(Type t, TypeEnv env) {
        Set<TypeVar> envUnsolved = env.getEnvUnsolved();
        Set<TypeVar> unsolved = t.unsolved();

        unsolved.removeAll(envUnsolved);
        return new TypeScheme(t, unsolved);
    }

    @Override
    public boolean isEqualityType() {
        return t.isEqualityType();
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        return this.t.unify(t);
    }

    @Override
    public boolean contains(TypeVar tv) {
        return t.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        return this.replace(a, t);
    }

    public String toString() {
        return t + " schema";
    }

    public Type instantiate() {
        Type newType = this.t;
        for(TypeVar tv : unsolved) {
            TypeVar nTv = new TypeVar(tv.isEqualityType());
            newType = newType.replace(tv, nTv);
        }
        return newType;
    }

    @Override
    public Set<TypeVar> unsolved() {
        return new HashSet<>();
    }
}
