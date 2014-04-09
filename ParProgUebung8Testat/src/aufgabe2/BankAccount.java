package aufgabe2;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {
	private AtomicInteger balance = new AtomicInteger(0);

	public void deposit(int amount) {
		balance.addAndGet(amount);
	}

	public boolean withdraw(int amount) {
		if (amount <= this.balance.get()) {
			balance.addAndGet(-amount);
			return true;
		} else {
			return false;
		}
	}

	public int getBalance() {
		return balance.get();
	}
}
