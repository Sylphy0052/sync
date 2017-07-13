import java.util.Comparator;

/**
 * Created by Sylphy on 7/13/17.
 */
public class QueueComparator implements Comparator<MyMessage> {

    @Override
    public int compare(MyMessage m1, MyMessage m2) {
        return m1.tick < m2.tick ? -1 : 1;
    }


}
