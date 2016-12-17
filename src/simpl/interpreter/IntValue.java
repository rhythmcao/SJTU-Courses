package simpl.interpreter;

public class IntValue extends Value {

    public final int n;

    public IntValue(int n) {
        this.n = n;
    }

    public String toString() {
        return "" + n;
    }

    public boolean equals(Object other) {
        if(other instanceof IntValue)
            if(((IntValue)other).n==this.n)
                return true;
            else return false;
        return false;
    }
}
