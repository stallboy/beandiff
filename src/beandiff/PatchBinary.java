package beandiff;

public class PatchBinary implements Patch{
    private byte[] value;

    PatchBinary(byte[] value) {
        this.value = value;
    }

    byte[] getValue() {
        return value;
    }

    @Override
    public Buffer marshal(Buffer os) {
        return os.marshal(value);
    }

}
