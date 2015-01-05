package beandiff;

public class MySubBean implements Patch{
    public int i;

    public PatchBean diff(MySubBean src){
        PatchBean b = PatchBean.of();
        Patch a = Patch.diff(src.i, i);
        if (a != null)
            b.add((byte) 1, a);
        return b;
    }

    public void applyPatch(Buffer os){

    }

    @Override
    public Buffer marshal(Buffer os) {
        return os;
    }

}
