package simpl.interpreter;

public class ConsValue extends Value {

    public final Value v1, v2;

    public ConsValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        return "list@" + length();
    }

    public int length() {
        if (v2 instanceof NilValue)
            return 1;
        else
            return 1 + ((ConsValue)v2).length();
    }
    
    public boolean equals(Object other) {
        if (other instanceof ConsValue) {
            ConsValue tmp = (ConsValue) other;
            return (v1.equals(tmp.v1)) && (v2.equals(tmp.v2));
        }
        return false;
    }
}
