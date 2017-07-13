import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

/**
 * Created by Sylphy on 7/11/17.
 */

// Processという名のThread
public class MyProcess extends Thread {
    private List<MyMessage> queue;
    private int tick;
    private Integer id;
    private Random rmd;
    private Share share1, share2;
    private String space = "";

    private static int REQ = 1;
    private static int ACK = 2;
    private static int CLIENT_NUM = 2;


    public MyProcess(Share share1, Share share2, int id) {
        queue = new ArrayList<>();
        this.id = id;
        this.rmd = new Random();
        // 同じにならないように
        this.tick = rmd.nextInt(5) * 2 + 1;
        if(id == 1) {
            this.share1 = share1;
            this.share2 = share2;
        } else if(id == 2) {
            this.share1 = share2;
            this.share2 = share1;
            space = "                    ";
        }
        start();
    }

    private void print(String str) {
        System.out.println(space + str);
    }

    private void send_ack(MyMessage msg) {
//        print("Send ACK");
        tick += id;
        MyMessage ack_msg = new MyMessage(ACK, id, msg.from_id, tick);
        share1.send_process(ack_msg);
        queue.add(ack_msg);
    }

    private void send_req() {
//        print("Send REQ");
        tick += id;
        MyMessage req_msg = new MyMessage(REQ, id, 0, tick);
        queue.add(req_msg);
        share1.send_process(req_msg);
        recv();
        send_ack(req_msg);
    }

    private void recv() {
//        print("RECV");
        tick += id;
        // recv
        MyMessage msg = share2.read_process();
        queue.add(msg);
        // if req -> send
        if(msg.type == REQ) {
            send_ack(msg);
            recv();
        }
        // else -> Do nothing
    }

    private void exec() {
        tick += id;
        array_sort();
        // queue check
        StringBuilder result = new StringBuilder();
        result.append(show_queue());
        int count1 = 0;
        int count2 = 0;
        for(MyMessage msg : queue) {
            if(msg.from_id == 1) {
                count1++;
            } else if(msg.from_id == 2) {
                count2++;
            }
            if(count1 == CLIENT_NUM + 1) {
                result.append(space);
                result.append("execute Task -> 1\n");
                count1 = 0;
            }
            if(count2 == CLIENT_NUM + 1) {
                result.append(space);
                result.append("execute Task -> 2\n");
                count2 = 0;
            }
        }
        System.out.println(result.toString());
    }

    private String show_queue() {
        StringBuilder sb = new StringBuilder();
        for(MyMessage msg : queue) {
            sb.append(space);
            sb.append(msg.show());
            sb.append("\n");
        }
        return sb.toString();
    }

    // first time execute
    private void send_request() {
        tick += id;
//        print(String.format("tick : %d", tick));
        send_req();
        recv();
    }

    // sort MyMessage by tick
    private void array_sort() {
        tick++;
        Collections.sort(queue, new QueueComparator());
    }

    @Override
    public void run() {
//        print(String.format("---%d---", id));
        send_request();
        exec();
    }
}
