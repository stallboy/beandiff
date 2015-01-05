package beandiff;

public class MyTest {


    private static void eq(Object real, Object expect){
        if (!real.equals(expect))
            throw new RuntimeException("expect: " + expect + ", real: " + real);
    }


    public static void main(String[] args){
        MyBean a = new MyBean();
        MyBean b = new MyBean();
        PatchBean p = a.diff(b);
        eq(p.getFields().size(), 0);

        a.i = 123;
        p = a.diff(b);
        eq(p.getFields().size(), 1);
        eq(((PatchInt)p.getFields().get((byte)1)).getValue(), 123L);

        a = new MyBean();
        a.subBean.i = 333;
        p = a.diff(b);
        eq(p.getFields().size(), 1);
        eq(((PatchBean)p.getFields().get((byte)5)).getFields().size(), 1);
        eq(((PatchInt)((PatchBean)p.getFields().get((byte)5)).getFields().get((byte)1)).getValue(), 333L);

    }

}
