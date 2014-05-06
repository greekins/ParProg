package aufgabe3;

public class PrimeCounter extends Thread{
	long start;
	long end;
	long count;
	
	PrimeCounter(long start, long end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void run() {
		count = countPrimes(start, end);
	}
	
  private static boolean isPrime(long number) {
    for (long factor = 2; factor * factor <= number; factor++) {
      if (number % factor == 0) { 
        return false; 
      }
    }
    return true;
  }
  
  private static long countPrimes(long start, long end){
    long count = 0;
    for (long number = start; number < end; number++) {
      if (isPrime(number)) { 
        count++; 
      }
    }
    return count;
  }
  
  private static final long START = 1_000_000L;
  private static final long END = 10_000_000L;
  private static int processors = Runtime.getRuntime().availableProcessors();
  private static final long range = (END-START)/processors;
  
  public static void main(String[] args) throws InterruptedException {
    long startTime = System.currentTimeMillis();    
    //long count = countPrimes(START, END);
    long count = 0;
    
    PrimeCounter threads[] = new PrimeCounter[processors];
    
    for(int i = 0; i < processors; i++) {
    	threads[i] = new PrimeCounter(START + (i * range), (START + (i+1) * range)-1);
    	threads[i].start();
    	
    }
   
    for(int i = 0; i < processors; i++) {
		threads[i].join();
    	count += threads[i].count;
    }
    
    long endTime = System.currentTimeMillis();
    System.out.println("#Primes: " + count + " Time: " + (endTime - startTime) + " ms");
    //586081
  }
}
