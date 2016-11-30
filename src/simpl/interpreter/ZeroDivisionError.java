package simpl.interpreter;

/**
 * Created by kevin on 30/11/2016.
 */
public class ZeroDivisionError extends RuntimeError {

    private static final long serialVersionUID = 357622756003784500L;

    public ZeroDivisionError() {
        super("integer division or modulo by zero.");
    }
}
