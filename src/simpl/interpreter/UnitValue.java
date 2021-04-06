package simpl.interpreter;

class UnitValue extends Value {

    protected UnitValue() {
    }

    public String toString() {
        return "unit";
    }

    public boolean equals(Object other) {
        return other instanceof UnitValue;
    }
}
