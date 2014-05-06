package aufgabe3b;

import java.util.concurrent.Semaphore;


public class UpgradeableReadWriteLock {
	
	int readers = 0;
	Semaphore readLock = new Semaphore(10);
	Semaphore readWriteLock = new Semaphore(1);
	

	public void readLock() throws InterruptedException {
		if(readers == 0) {
			readWriteLock.acquire();
		}
		readLock.acquire();
		readers++;
	}

	public void readUnlock() {
		readers--;
		if(readers == 0) {
			readWriteLock.release();
		}
		readLock.release();
	}

	public void upgradeableReadLock() throws InterruptedException {
		while(readers > 0) {
		}
		readWriteLock.acquire();
	}

	public void upgradeableReadUnlock() {
		readWriteLock.release();
	}

	public void writeLock() throws InterruptedException {
		readWriteLock.acquire();
	}

	public void writeUnlock() {
		readWriteLock.release();
	}
}
