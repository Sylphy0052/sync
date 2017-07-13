/**
 * Created by Sylphy on 7/12/17.
 */
public class MyMessage {
    public int type;
    public int from_id;
    public int to_id;
    public int tick;

    private static int REQ = 1;
    private static int ACK = 2;

    public MyMessage(int type, int from_id, int to_id, int tick) {
        this.type = type;
        this.from_id = from_id;
        this.to_id = to_id;
        this.tick = tick;
    }

    public String show() {
        if(this.type == REQ) {
            return String.format("REQ%d:   %2d", this.from_id, this.tick);
        } else if(this.type == ACK) {
            return String.format("ACK%d-%d: %2d", this.from_id, this.to_id, this.tick);
        }
        return "";
    }
}
