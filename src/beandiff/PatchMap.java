package beandiff;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PatchMap implements Patch {
    private boolean isWhole;
    private Patch whole;

    private Set<Patch> removes;
    private java.util.Map<Patch, Patch> puts;

    private PatchMap(){
    }

    public static PatchMap ofWhole(Patch whole){
        PatchMap t = new PatchMap();
        t.isWhole = true;
        t.whole = whole;
        return t;
    }

    public static PatchMap of() {
        PatchMap t = new PatchMap();
        t.isWhole = false;
        t.removes = new HashSet<>();
        t.puts = new HashMap<>();
        return t;
    }


    boolean isWhole() {
        return isWhole;
    }

    Patch getWhole() {
        return whole;
    }

    Set<Patch> getRemoves() {
        return removes;
    }

    Map<Patch, Patch> getPuts() {
        return puts;
    }

    public void remove(Patch r) {
        removes.add(r);
    }

    public void put(Patch k, Patch v){
        puts.put(k, v);
    }

    @Override
    public Buffer marshal(Buffer os) {
        os.marshal(isWhole);
        if (isWhole) {
            whole.marshal(os);
        } else {
            os.marshal(removes.size());
            removes.forEach(k -> k.marshal(os));
            os.marshal(puts.size());
            puts.forEach((k, v) -> {
                k.marshal(os);
                v.marshal(os);
            });
        }
        return os;
    }

}
