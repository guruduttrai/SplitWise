package core;

public class Balance {
	private User otherUser;
	private Double balance;
	
	public Balance(User u, Double bal) {
		this.otherUser = u;
		this.balance = bal;
	}
	
	public User getOtherUser() {
		return this.otherUser;
	}
	
	public Double getBalance() {
		return this.balance;
	}
	
	public void updateBalance(Double d) {
		this.balance += d;
	}

	@Override
	public String toString() {
		return "To/From " + this.otherUser.getName() + " : " + balance;
	}
	
	
}
