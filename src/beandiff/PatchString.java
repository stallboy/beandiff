package beandiff;

public class PatchString implements Patch {
    private String value;

    PatchString(String value) {
        this.value = value;
    }

    String getValue() {
        return value;
    }

    @Override
    public Buffer marshal(Buffer os) {
        return os.marshal(value);
    }


}
