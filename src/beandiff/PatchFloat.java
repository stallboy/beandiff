package beandiff;

public class PatchFloat implements Patch {
    private double value;
    private boolean isDouble;

    PatchFloat(double value, boolean isDouble){
        this.value = value;
        this.isDouble = isDouble;
    }

    double getValue() {
        return value;
    }

    boolean isDouble() {
        return isDouble;
    }

    @Override
    public Buffer marshal(Buffer os) {
        if (isDouble)
            os.marshal(value);
        else
            os.marshal((float)value);
        return os;
    }

}
