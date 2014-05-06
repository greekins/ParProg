package aufgabe1;

public class WarehouseWithMonitor implements Warehouse {
  int capacity;
  int actualAmount;
  
  public WarehouseWithMonitor(int capacity) {
    this.capacity = capacity;
  }
  
  @Override
  public synchronized void put(int amount) throws InterruptedException {
    while(actualAmount + amount > capacity) {
    	wait();
    }
    actualAmount += amount;
    notifyAll();
  }

  @Override
  public synchronized void get(int amount) throws InterruptedException {
	  while(actualAmount < amount) {
		  wait();
	  }
	  actualAmount -= amount;
	  notifyAll();
  }
}
