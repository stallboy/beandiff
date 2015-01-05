package beandiff;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class DiffMap<K, V> {

    public static class Pair<V> {
        private V src;
        private V tgt;

        public Pair(V src, V tgt) {
            this.src = src;
            this.tgt = tgt;
        }

        public V getTgt() {
            return tgt;
        }

        public V getSrc() {
            return src;
        }
    }

    private Set<K> removes = new HashSet<>();
    private Map<K, V> adds;
    private Map<K, Pair<V>> updates = new HashMap<>();

    private Map<K, V> src;
    private Map<K, V> tgt;

    public DiffMap(Map<K, V> src, Map<K, V> tgt) {
        this.src = src;
        this.tgt = tgt;
        adds = new HashMap<>(tgt);
        src.forEach((k, v) -> {
            V tv = adds.remove(k);
            if (tv == null) {
                removes.add(k);
            } else if (!tv.equals(v)) {
                updates.put(k, new Pair<>(v, tv));
            }
        });
    }

    public Set<K> getRemoves() {
        return removes;
    }

    public Map<K, V> getAdds() {
        return adds;
    }

    public Map<K, Pair<V>> getUpdates() {
        return updates;
    }

    public boolean isEmpty() {
        return removes.isEmpty() && adds.isEmpty() && updates.isEmpty();
    }

    public boolean isSuggestAsWhole(){
        return src.isEmpty() || tgt.isEmpty();
    }

}
