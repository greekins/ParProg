package aufgabe1;
public class ConsoleTickerRunnable implements Runnable {
	
	char sign;
	int intervallMillis;
  
	public ConsoleTickerRunnable(char sign, int intervallMillis) throws InterruptedException {
		this.sign = sign;
		this.intervallMillis = intervallMillis;
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
	

  public static void main(String[] args) throws InterruptedException {
    //periodTicker('.', 10);
    // TODO: Concurrent periodTicker('*', 20);
    
    new Thread(new ConsoleTickerRunnable('.', 10)).start();
    new Thread(new ConsoleTickerRunnable('X', 20)).start();
    
  }
}
