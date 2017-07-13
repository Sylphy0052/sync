/**
 * Created by Sylphy on 7/11/17.
 */
public class Main {
    public static void main(String[] args) {
        Share share1 = new Share();
        Share share2 = new Share();
        MyProcess p1 = new MyProcess(share1, share2, 1);
        MyProcess p2 = new MyProcess(share1, share2, 2);
    }
}
