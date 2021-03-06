package simpl.interpreter;

import static simpl.parser.Symbol.symbol;

import simpl.interpreter.lib.*;
import simpl.interpreter.pcf.iszero;
import simpl.interpreter.pcf.pred;
import simpl.interpreter.pcf.succ;
import simpl.parser.Symbol;

public class InitialState extends State {

    public InitialState() {
        super(initialEnv(Env.empty), new Mem(), new Int(0));
    }

    private static Env initialEnv(Env E) {
        E = new Env(E, symbol("fst"), new fst());
        E = new Env(E, symbol("snd"), new snd());
        E = new Env(E, symbol("hd"), new hd());
        E = new Env(E, symbol("tl"), new tl());
        E = new Env(E, symbol("force"), new force());

        E = new Env(E, symbol("iszero"), new iszero());
        E = new Env(E, symbol("pred"), new pred());
        E = new Env(E, symbol("succ"), new succ());

        return E;
    }
}
