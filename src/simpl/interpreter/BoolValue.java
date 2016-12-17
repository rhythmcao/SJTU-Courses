package simpl.interpreter;

public class BoolValue extends Value {

    public final boolean b;

    public BoolValue(boolean b) {
        this.b = b;
    }

    public String toString() {
        return "" + b;
    }

    public boolean equals(Object other) {
        if(other instanceof BoolValue)
            if(((BoolValue)other).b==this.b)
                return true;
        return false;
    }
}
