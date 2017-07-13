import java.util.Comparator;

/**
 * Created by Sylphy on 7/13/17.
 */
public class QueueComparator implements Comparator<MyMessage> {

    @Override
    public int compare(MyMessage m1, MyMessage m2) {
        if(m1.tick < m2.tick) {
            return -1;
        } else if(m1.tick > m2.tick) {
            return 1;
        } else {
            if(m1.from_id < m2.from_id) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
