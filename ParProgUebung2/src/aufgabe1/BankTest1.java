package aufgabe1;

import java.util.ArrayList;
import java.util.List;

class BankAccount {
	private int balance = 0;

	public synchronized void deposit(int amount) {
		balance += amount;
		notifyAll();
	}

	public synchronized boolean withdraw(int amount) throws InterruptedException {
		while(amount > balance) {
			wait();
		}
			balance -= amount;	
			notifyAll();
			return true;
	}
	public synchronized int getBalance() {
		return balance;
	}
}

class BankCustomer extends Thread {
	private final static int NOF_TRANSACTIONS = 10000000;
	private final BankAccount account;
	
	public BankCustomer(BankAccount account) {
		this.account = account;
	}
	
	@Override
	public void run() {
		for (int k = 0; k < NOF_TRANSACTIONS; k++) {
			account.deposit(100);
			try {
				account.withdraw(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class BankTest1 {
	private final static int NOF_CUSTOMERS = 10;

	public static void main(String[] args) throws InterruptedException {
		
		List<BankCustomer> customerList = new ArrayList<>();
		
		
		BankAccount account = new BankAccount();
		for (int i = 0; i < NOF_CUSTOMERS; i++) {
			BankCustomer customer = new BankCustomer(account);
			customerList.add(customer);
			customer.start();
		}
		for(BankCustomer b : customerList) {
			b.join();
		}
		System.out.println(account.getBalance());
	}
}
