package aufgabe1;

import java.io.IOException;

public class ConsoleTickerThread extends Thread {
	
	char sign;
	int intervallMillis;
  
	public ConsoleTickerThread(char sign, int intervallMillis) throws InterruptedException {
		this.sign = sign;
		this.intervallMillis = intervallMillis;
		setDaemon(true);
  }
	
	@Override
	public void run() {
		while (true) {
		      System.out.print(sign);
		      try {
				Thread.sleep(intervallMillis);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
	}

  public static void main(String[] args) throws InterruptedException, IOException {
    //periodTicker('.', 10);
    // TODO: Concurrent periodTicker('*', 20);
    
    new ConsoleTickerThread('.', 10).start();
    new ConsoleTickerThread('X', 20).start();
    
    System.in.read();
    
  }
}
