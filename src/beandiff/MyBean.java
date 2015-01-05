package beandiff;


import java.util.*;

public class MyBean implements Patch {
    public int i;
    public float f;
    public String str = "";
    public byte[] bytes = new byte[0];
    public MySubBean subBean = new MySubBean();
    public Map<Integer, MySubBean> map = new HashMap<>();
    public Set<Integer> set = new HashSet<>();

    public List<Integer> list = new ArrayList<>();

    public PatchBean diff(MyBean src) {
        PatchBean b = PatchBean.of();

        Patch a = Patch.diff(src.i, i);
        if (a != null)
            b.add((byte) 1, a);

        a = Patch.diff(src.f, f);
        if (a != null)
            b.add((byte) 2, a);

        a = Patch.diff(src.str, str);
        if (a != null)
            b.add((byte) 3, a);

        a = Patch.diff(src.bytes, bytes);
        if (a != null)
            b.add((byte) 4, a);

        PatchBean sa = subBean.diff(src.subBean);
        if (!sa.isEmpty())
            b.add((byte) 5, sa);

        {
            DiffMap<Integer, MySubBean> md = new DiffMap<>(src.map, map);
            if (!md.isEmpty()) {
                PatchMap m;
                if (md.isSuggestAsWhole()) {
                    m = PatchMap.ofWhole(this::marshalMap);
                } else {
                    m = PatchMap.of();
                    md.getRemoves().forEach(k -> m.remove(Patch.of(k)));
                    md.getAdds().forEach((k, v) -> {
                        m.put(Patch.of(k), PatchBean.ofWhole(v));
                    });
                    md.getUpdates().forEach((k, v) -> {
                        m.put(Patch.of(k), v.getTgt().diff(v.getSrc()));
                    });
                }
                b.add((byte) 6, m);
            }
        }


        {
            DiffSet<Integer> sd = new DiffSet<>(src.set, set);
            if (!sd.isEmpty()) {
                PatchSet s;
                if (sd.isSuggestAsWhole()) {
                    s = PatchSet.ofWhole(this::marshalSet);
                } else {
                    s = PatchSet.of();
                    sd.getRemoves().forEach(k -> s.remove(Patch.of(k)));
                    sd.getAdds().forEach(k -> s.add(Patch.of(k)));
                }

                b.add((byte) 7, s);
            }
        }

        {
            DiffList<Integer> dl = new DiffList<>(src.list, list);
            if (!dl.isEmpty()){
                PatchList l = new PatchList(dl.getHeadEndIndex(), dl.getTailBeginIndex());
                for (Integer c : dl.getCentres())
                    l.add(Patch.of(c));
                b.add((byte) 8, l);
            }
        }

        return b;
    }

    public void applyPatch(Buffer os) {
        //TODO
    }

    @Override
    public Buffer marshal(Buffer os) {
        return os; //TODO
    }

    public Buffer marshalMap(Buffer os) {
        return os;
    }

    public Buffer marshalSet(Buffer os) {
        return os;
    }

    public Buffer marshalList(Buffer os) {
        return os;
    }


}
