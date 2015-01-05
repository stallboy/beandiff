package beandiff;


import java.util.Arrays;

public interface Patch {
    Buffer marshal(Buffer os);

    public static PatchInt of(long t) {
        return new PatchInt(t, PatchInt.Type.LONG);
    }

    public static PatchInt of(int t) {
        return new PatchInt(t, PatchInt.Type.INT);
    }

    public static PatchInt of(short t) {
        return new PatchInt(t, PatchInt.Type.SHORT);
    }

    public static PatchInt of(byte t) {
        return new PatchInt(t, PatchInt.Type.BYTE);
    }

    public static PatchInt of(boolean t) {
        return new PatchInt(t ? 1 : 0, PatchInt.Type.BOOLEAN);
    }

    public static PatchFloat of(float t) {
        return new PatchFloat(t, false);
    }

    public static PatchFloat of(double t) {
        return new PatchFloat(t, true);
    }

    public static PatchString of(String t) {
        return new PatchString(t);
    }

    public static PatchBinary of(byte[] t) {
        return new PatchBinary(t);
    }


    public static PatchInt diff(long s, long t) {
        return t == s ? null : of(t);
    }

    public static PatchInt diff(int s, int t) {
        return t == s ? null : of(t);
    }

    public static PatchInt diff(short s, short t) {
        return t == s ? null : of(t);
    }

    public static PatchInt diff(byte s, byte t) {
        return t == s ? null : of(t);
    }

    public static PatchInt diff(boolean s, boolean t) {
        return t == s ? null : of(t);
    }


    public static PatchFloat diff(float s, float t) {
        return t == s ? null : of(t);
    }

    public static PatchFloat diff(double s, double t) {
        return t == s ? null : of(t);
    }

    public static PatchString diff(String s, String t) {
        return t.equals(s) ? null : of(t);
    }

    public static PatchBinary diff(byte[] s, byte[] t) {
        return Arrays.equals(t, s) ? null : of(t);
    }
}
