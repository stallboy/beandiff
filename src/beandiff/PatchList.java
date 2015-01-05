package beandiff;

import java.util.ArrayList;
import java.util.List;

public class PatchList implements Patch {
    private int headEndIndex;
    private List<Patch> centres;
    private int tailBeginIndex;

    public PatchList(int headEndIndex, int tailBeginIndex) {
        this.headEndIndex = headEndIndex;
        this.centres = new ArrayList<>();
        this.tailBeginIndex = tailBeginIndex;
    }

    public void add(Patch p) {
        centres.add(p);
    }

    int getHeadEndIndex(){
        return headEndIndex;
    }

    List<Patch> getCentres(){
        return centres;
    }

    int getTailBeginIndex(){
        return tailBeginIndex;
    }

    @Override
    public Buffer marshal(Buffer os) {
        os.marshal(headEndIndex);
        os.marshal(centres.size());
        for (Patch p: centres)
            p.marshal(os);
        os.marshal(tailBeginIndex);
        return os;
    }

}
