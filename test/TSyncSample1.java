class TSyncSample1{
	public static void main(String args[]){
		Share share = new Share();
		WriteThread thread1 = new WriteThread(share, "1");
		ReadThread thread2 = new ReadThread(share, "2");
	}
}

class WriteThread extends Thread{
	Share share;
	public WriteThread(Share share, String string){
		super(string);
		this.share = share;
		start();
	}
	public void run(){
		System.out.println("The Result is " + share.ReadProcess());
	}
}

class ReadThread extends Thread{
	Share share;
	public ReadThread(Share share, String string){
		super(string);
		this.share = share;
		start();
	}
	public void run(){
		share.WriteProcess();
	}
}

class Share{
	int data = 0;

	synchronized void WriteProcess(){
		try{
			System.out.println("Write : Processing...");
			Thread.sleep(2000);
		}
		catch( Exception e ){
			System.err.println("Exception : " + e);
		}
		data = 10;
		notifyAll();
		System.out.println("Write : Process done");
	}

	public synchronized int ReadProcess(){
		System.out.println("Read : Waiting...");
		try{
			wait();
			System.out.println("data = " + data);
		}
		catch( Exception e ){
			System.err.println("Exception : " + e);
		}
		return data;
	}
}