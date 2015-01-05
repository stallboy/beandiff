package beandiff;

import java.util.HashSet;
import java.util.Set;

public class PatchSet implements Patch {
    private boolean isWhole;
    private Patch whole;

    private Set<Patch> removes;
    private Set<Patch> adds;

    private PatchSet(){
    }

    public static PatchSet ofWhole(Patch whole){
        PatchSet t = new PatchSet();
        t.isWhole = true;
        t.whole = whole;
        return t;
    }

    public static PatchSet of() {
        PatchSet t = new PatchSet();
        t.isWhole = false;
        t.removes = new HashSet<>();
        t.adds = new HashSet<>();
        return t;
    }


    public void remove(Patch k) {
        removes.add(k);
    }

    public void add(Patch k){
        adds.add(k);
    }

    @Override
    public Buffer marshal(Buffer os) {
        os.marshal(isWhole);
        if (isWhole) {
            whole.marshal(os);
        } else {
            os.marshal(removes.size());
            removes.forEach(k -> k.marshal(os));
            os.marshal(adds.size());
            adds.forEach(k -> k.marshal(os));
        }
        return os;
    }
}
