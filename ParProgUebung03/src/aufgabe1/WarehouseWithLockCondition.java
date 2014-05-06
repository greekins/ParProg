package aufgabe1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WarehouseWithLockCondition implements Warehouse {
	int actualAmount;
	private Lock monitor;
	private Condition nonFull;
	private Condition nonEmpty;
	private int capacity;
	
  public WarehouseWithLockCondition(int capacity, boolean fair) {
	  monitor = new ReentrantLock(fair);
	  nonFull = monitor.newCondition();
	  nonEmpty = monitor.newCondition();
	  this.capacity = capacity;
  }
  
  @Override
  public void put(int amount) throws InterruptedException {
	  	monitor.lock();
	    try {
	    	while (actualAmount + amount > capacity) { nonFull.await(); }
	    	actualAmount += amount;
	    	nonEmpty.signal();
	    } finally { monitor.unlock(); }
  }

  @Override
  public void get(int amount) throws InterruptedException {
	  monitor.lock();
	  try {
		  while (amount > actualAmount) { nonEmpty.await(); }
		  actualAmount -= amount;
		  nonFull.signal();
	  } finally { monitor.unlock(); }
  }
}
