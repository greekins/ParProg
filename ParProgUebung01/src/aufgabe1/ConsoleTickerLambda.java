package aufgabe1;
public class ConsoleTickerLambda extends Thread {
	
	/*char sign;
	int intervallMillis;
  
	public CopyOfCopyOfConsoleTickerLambda(char sign, int intervallMillis) throws InterruptedException {
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
	}*/
	
	static void startMyThread(char sign, int intervallMillis) {
    	new Thread(() -> {
    		while (true) {
  		      System.out.print(sign);
  		      try {
  				Thread.sleep(intervallMillis);
  			} catch (InterruptedException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  		} 
    	}).start(); 
	}

  public static void main(String[] args) throws InterruptedException {
    //periodTicker('.', 10);
    // TODO: Concurrent periodTicker('*', 20);
    
	  startMyThread('*', 20);
	  startMyThread('X', 20);
  }
}
