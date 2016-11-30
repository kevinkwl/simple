package simpl.interpreter;

import simpl.parser.Symbol;

/**
 * Created by kevin on 25/11/2016.
 */
public class NameError extends RuntimeError {

    private static final long serialVersionUID = -8887425643799183594L;

    public NameError(Symbol symbol) {
        super("name \"" + symbol + "\" is not defined");
    }
}
