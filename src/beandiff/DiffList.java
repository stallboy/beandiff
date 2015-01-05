package beandiff;

import java.util.List;


public class DiffList<E> {
    private List<E> src;
    private List<E> tgt;

    private int headEndIndex;
    private int tailBeginIndex;
    private List<E> centres;

    public DiffList(List<E> src, List<E> tgt) {
        this.src = src;
        this.tgt = tgt;

        int tsize = tgt.size();
        int ssize = src.size();
        int i;
        for (i = 0; i < tsize && i < ssize; i++) {
            E t = tgt.get(i);
            E s = src.get(i);

            if (!t.equals(s))
                break;
        }
        headEndIndex = i;

        for (i = ssize-1; i > headEndIndex; i--) {
            E s = src.get(i);
            int j = tsize - ssize + i;
            if (j <= headEndIndex)
                break;
            E t = tgt.get(j);

            if (!t.equals(s))
                break;
        }
        tailBeginIndex = i+1;

        centres = tgt.subList(headEndIndex, tailBeginIndex);
    }


    public int getHeadEndIndex() {
        return headEndIndex;
    }

    public int getTailBeginIndex() {
        return tailBeginIndex;
    }

    public List<E> getCentres() {
        return centres;
    }

    public boolean isEmpty() {
        return centres.isEmpty();
    }

}
