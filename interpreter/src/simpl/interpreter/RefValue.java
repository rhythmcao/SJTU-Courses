package simpl.interpreter;

public class RefValue extends Value {

    public final int p;

    public RefValue(int p) {
        this.p = p;
    }

    public String toString() {
        return "ref@" + p;
    }

    public boolean equals(Object other) {
        if(other instanceof RefValue)
            if(((RefValue) other).p==this.p)
                return true;
        return false;
    }
}
