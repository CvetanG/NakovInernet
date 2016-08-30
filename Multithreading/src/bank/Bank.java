package bank;

public class Bank {
	/*public static void deposit(Account aAcc, double aSum) {
		double oldAmmount = aAcc.getAmmount();
		double newAmmount = oldAmmount + aSum;
		aAcc.setAmmount(newAmmount);
	}
	*/
	
	/*
	synchronized public static void deposit(Account aAcc, double aSum) {
		double oldAmmount = aAcc.getAmmount();
		double newAmmount = oldAmmount + aSum;
		aAcc.setAmmount(newAmmount);
	}
	*/
	
	public static void deposit(Account aAccount, double aSum) {
		synchronized (aAccount) {
			double oldAmmount = aAccount.getAmmount();
			double newAmmount = oldAmmount + aSum;
			aAccount.setAmmount(newAmmount);
		}
	}
}
