package aufgabe1;

import java.util.concurrent.Semaphore;

public class WarehouseWithSemaphore implements Warehouse {
	int actualAmount;
	private Semaphore upperLimit;
	private Semaphore lowerLimit;
	private Semaphore mutex;
	
  public WarehouseWithSemaphore(int capacity, boolean fair) {
	  upperLimit = new Semaphore(capacity, fair);
	  lowerLimit = new Semaphore(0, fair);
	  mutex = new Semaphore(1, fair);
  }
  
  @Override
  public void put(int amount) throws InterruptedException {
	  upperLimit.acquire(amount);
	  mutex.acquire();
	  actualAmount += amount;
	  mutex.release();
	  lowerLimit.release(amount);
  }

  @Override
  public void get(int amount) throws InterruptedException {
	  lowerLimit.acquire(amount);
	  mutex.acquire();
	  actualAmount -= amount;
	  mutex.release();
	  upperLimit.release(amount);
  }
}
