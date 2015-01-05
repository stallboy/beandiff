package beandiff;

import java.util.Map;
import java.util.TreeMap;

public class PatchBean implements Patch {
    private boolean isWhole;
    private Patch whole;
    private Map<Byte, Patch> fields = new TreeMap<>();

    private PatchBean() {
    }

    public static PatchBean ofWhole(Patch whole) {
        PatchBean t = new PatchBean();
        t.isWhole = true;
        t.whole = whole;
        return t;
    }

    public static PatchBean of() {
        PatchBean t = new PatchBean();
        t.isWhole = false;
        t.fields = new TreeMap<>();
        return t;
    }

    boolean isWhole() {
        return isWhole;
    }

    Patch getWhole() {
        return whole;
    }

    Map<Byte, Patch> getFields() {
        return fields;
    }

    public boolean isEmpty(){
        return fields.isEmpty();
    }

    public void add(byte i, Patch p) {
        fields.put(i, p);
    }

    @Override
    public Buffer marshal(Buffer os) {
        os.marshal(isWhole);
        if (isWhole) {
            whole.marshal(os);
        } else {
            os.marshal(fields.size());
            fields.forEach((k, p) -> {
                os.marshal(k);
                p.marshal(os);
            });
        }
        return os;
    }
}
