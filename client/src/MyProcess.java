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

    private static int REQ = 1;
    private static int ACK = 2;
    private static int NUM = 2;


    public MyProcess(int id) {
        queue = new ArrayList<>();
        this.id = id;
        this.rmd = new Random();
        this.tick = rmd.nextInt(10);
    }

    // send ack
    private void multi_send() {
        tick++;

    }

    private void recv() {
        tick++;
        // analyze

        MyMessage msg = new MyMessage();
        queue.add(msg);

        // if id != my_id -> multi_send

        // else -> Do nothing

    }

    private void exec() {
        tick++;
        // 6 message in queue

    }

    // first time execute
    private void send_request() {
        tick++;
        System.out.println(String.format("tick : %d", tick));
        queue.add(new MyMessage(REQ, id, tick));
    }

    // sort MyMessage by tick
    private void array_sort() {
        tick++;
        Collections.sort(queue, new QueueComparator());
    }

    @Override
    public void run() {
        System.out.println(String.format("---%d---", id));
        send_request();
        for(int i = 0; i < NUM * NUM + NUM - 1; i++) {
            recv();
        }
        exec();
    }

}
