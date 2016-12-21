package simpl.typing;

import simpl.parser.Symbol;

import java.util.Set;

public class DefaultTypeEnv extends TypeEnv {

    private TypeEnv E;

    public DefaultTypeEnv() {
        Symbol fst = Symbol.symbol("fst");
        Symbol snd = Symbol.symbol("snd");
        Symbol hd = Symbol.symbol("hd");
        Symbol tl = Symbol.symbol("tl");
        Symbol iszero = Symbol.symbol("iszero");
        Symbol pred = Symbol.symbol("pred");
        Symbol succ = Symbol.symbol("succ");
        Symbol force = Symbol.symbol("force");



        TypeVar t = new TypeVar(false);
        ArrowType fst_t = new ArrowType(new PairType(t, new TypeVar(false)), t);
        E = TypeEnv.of(TypeEnv.empty, fst, fst_t);

        t = new TypeVar(false);
        ArrowType snd_t = new ArrowType(new PairType(new TypeVar(false), t), t);
        E = TypeEnv.of(E, snd, snd_t);

        t = new TypeVar(false);
        ArrowType hd_t = new ArrowType(new ListType(t), t);
        E = TypeEnv.of(E, hd, hd_t);

        t = new TypeVar(false);
        ListType tlist = new ListType(t);
        ArrowType tl_t = new ArrowType(tlist, tlist);
        E = TypeEnv.of(E, tl, tl_t);

        ArrowType iszero_t = new ArrowType(Type.INT, Type.BOOL);
        ArrowType pred_t = new ArrowType(Type.INT, Type.INT);
        ArrowType succ_t = new ArrowType(Type.INT, Type.INT);
        E = TypeEnv.of(E, iszero, iszero_t);
        E = TypeEnv.of(E, pred, pred_t);
        E = TypeEnv.of(E, succ, succ_t);

        t = new TypeVar(false);
        ArrowType force_t = new ArrowType(t, t);
        E = TypeEnv.of(E, force, force_t);

    }

    @Override
    public Type get(Symbol x) {
        return E.get(x);
    }

    public Set<TypeVar> getEnvUnsolved() {
        Set<TypeVar> uns = E.getEnvUnsolved();
        return uns;
    }
}
