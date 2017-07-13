/**
 * Created by Sylphy on 7/13/17.
 */
public class Share {
    MyMessage msg = null;

    public synchronized void send_process(MyMessage msg){
        try{
            while(this.msg != null){
                wait();
            }
            this.msg = msg;
            notifyAll();
        }
        catch( Exception e ){
            System.err.println("Exception : " + e);
        }
    }

    public synchronized MyMessage read_process(){
        MyMessage result = null;
        try{
            while(this.msg == null) {
                wait();
            }
            result = this.msg;
            this.msg = null;
        }
        catch( Exception e ){
            System.err.println("Exception : " + e);
        }
        return result;
    }
}
