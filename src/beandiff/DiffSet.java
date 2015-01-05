package beandiff;

import java.util.HashSet;
import java.util.Set;


public class DiffSet<E> {

    private Set<E> removes = new HashSet<>();
    private Set<E> adds;

    private Set<E> src;
    private Set<E> tgt;

    public DiffSet(Set<E> src, Set<E> tgt) {
        this.src = src;
        this.tgt = tgt;
        adds = new HashSet<>(tgt);
        src.forEach(e -> {
            if (!adds.remove(e))
                removes.add(e);
        });
    }

    public Set<E> getAdds() {
        return adds;
    }

    public Set<E> getRemoves() {
        return removes;
    }


    public boolean isEmpty() {
        return removes.isEmpty() && adds.isEmpty();
    }

    public boolean isSuggestAsWhole() {
        return src.isEmpty() || tgt.isEmpty();
    }

}
