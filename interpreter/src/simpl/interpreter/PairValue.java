package simpl.interpreter;

public class PairValue extends Value {

    public final Value v1, v2;

    public PairValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        return "pair@" + v1 + "@" + v2;
    }

    public boolean equals(Object other) {
        if(other instanceof PairValue){
            PairValue tmp=(PairValue)other;
            return (v1.equals(tmp.v1))&&(v2.equals(tmp.v2));
        }
        return false;
    }
}
