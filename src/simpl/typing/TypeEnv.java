package simpl.typing;

import simpl.parser.Symbol;

import java.util.HashSet;
import java.util.Set;

public abstract class TypeEnv {

    public abstract Type get(Symbol x);
    public abstract Set<TypeVar> getEnvUnsolved();

    public static TypeEnv of(final TypeEnv E, final Symbol x, final Type t) {
        return new TypeEnv() {
            public Type get(Symbol x1) {
                if (x == x1) {
                    if (t instanceof TypeScheme)
                        return ((TypeScheme) t).instantiate();
                    else
                        return t;
                }
                return E.get(x1);
            }

            public String toString() {
                return x + ":" + t + ";" + E;
            }

            public Set<TypeVar> getEnvUnsolved() {
                Set<TypeVar> uns = E.getEnvUnsolved();
                if (t instanceof TypeVar)
                    uns.add((TypeVar)t);
                return uns;
            }
        };
    }

    public static final TypeEnv empty = new TypeEnv() {
        @Override
        public Type get(Symbol x) {
            return null;
        }
        public Set<TypeVar> getEnvUnsolved() {
            return new HashSet<>();
        }
    };
}
