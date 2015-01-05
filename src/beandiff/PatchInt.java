package beandiff;


public class PatchInt implements Patch {
    public static enum Type {
        BOOLEAN, BYTE, SHORT, INT, LONG
    }

    private long value;
    private Type type;

    PatchInt(long value, Type type) {
        this.value = value;
        this.type = type;
    }

    long getValue() {
        return value;
    }

    Type getType() {
        return type;
    }

    @Override
    public Buffer marshal(Buffer os) {
        switch (type){
            case BOOLEAN:
                os.marshal(value != 0);
                break;
            case BYTE:
                os.marshal((byte)value);
                break;
            case SHORT:
                os.marshal((short)value);
                break;
            case INT:
                os.marshal((int)value);
                break;
            case LONG:
                os.marshal(value);
                break;
        }

        return os;
    }
}
