package simpl.interpreter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Mem extends HashMap<Integer, Value> {

    private static final long serialVersionUID = -1155291135560730618L;
    private LinkedList<Integer> freeList;
    private Stack<Env> envStack;
    private static final int heapSize = 2;
    private HashMap<Integer, Boolean> markBit;


    public Mem() {
        super();
        freeList = new LinkedList<>();
        envStack = new Stack<>();

        for (int i = 0; i < heapSize; ++i) {
            freeList.add(i);
        }
        markBit = new HashMap<>();
    }

    public int nextFree() {
        if (freeList.isEmpty())
            return -1;
        else
            return freeList.removeFirst();
    }

    public void gc(Env env) {
        System.out.println("Start GC");
        markBit.clear();
        markEnv(env);
        for (Env e : envStack) {
            markEnv(e);
        }
        sweep();
    }

    private void markEnv(Env env) {
        for (; env != Env.empty && env != null; env = env.getEnv()) {
            Value v = env.getValue();
            markVal(v);
        }
    }

    private void markVal(Value val) {
        if (val instanceof RefValue) {
            markBit.put(((RefValue) val).p, Boolean.TRUE);
            markVal(this.get(((RefValue) val).p));
        }
        else if (val instanceof ConsValue) {
            markVal(((ConsValue) val).getV1());
            markVal(((ConsValue) val).getV2());
        }
        else if (val instanceof PairValue) {
            markVal(((PairValue) val).getV1());
            markVal(((PairValue) val).getV2());
        }
        else if (val instanceof FunValue) {
            markEnv(((FunValue) val).getE());
        }
        else if (val instanceof RecValue) {
            // for rec value, skip over itself to prevent infinite loop
            markEnv(((RecValue) val).getE().getEnv());
        }
    }

    private void sweep() {
        freeList.clear();
        for (int i = 0; i < heapSize; ++i) {
            if (markBit.getOrDefault(i, Boolean.FALSE) == Boolean.FALSE) {
                System.out.println("collect " + String.valueOf(i));
                this.put(i, null);
                freeList.addLast(i);
            }
        }
    }

    public void pushEnv(Env env) {
        envStack.push(env);
    }

    public Env popEnv() {
        return envStack.pop();
    }
}
