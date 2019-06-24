package core;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private String email;
	private List<Balance> ledger;
	
	public User(String name, String email) {
		super();
		this.name = name;
		this.email = email;
		this.ledger = new ArrayList<>();
	}
	
	public void addOtherUserToLedger(User otherUser) {
		Balance b = new Balance(otherUser, 0.0);
		//add other user to this user's trusted group, initially no body owes anyone  anything
		this.ledger.add(b);
	}
	
	public Balance getSpecificLedger(User u) {
		for(int i = 0; i<ledger.size(); i++) {
			Balance temp = ledger.get(i);
			if((temp.getOtherUser()).equals(u) == true) {	//get balance.user and check equality with u
				return temp;
			}
		}
		System.out.println("No ledger found...");
		return null;
	}
	
	@Override
	public boolean equals(Object o) {	//overridden to provide equality functionality
		User u = (User)o;
		return(this.name.equals(u.getName()));	//try appending && balance equality too.
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public List<Balance> getLedger() {
		return ledger;
	}
	
	public void showLedger() {	//check if this is even needed
		this.ledger.forEach(e -> System.out.println(e));		
	}

	public void showDetails() {
		System.out.println("\n--------\n" + name);
		for(Balance b : ledger) {
			System.out.println(b);
		}
		System.out.println("\n--------\n");
	}
	
	public static Double settle(User user1, User user2) {
		Double bal = null;
		
		for(Balance b : user1.ledger) {
			if(b.getOtherUser().equals(user2)) {
				bal = b.getBalance();
				b.updateBalance((-1) * b.getBalance());	//subtract current balance with current balance to make it 0.
			}
		}
		
		for(Balance b : user2.ledger) {
			if(b.getOtherUser().equals(user1)) {
				b.updateBalance((-1) * b.getBalance());	//subtract current balance with current balance to make it 0.
			}
		}
		
		return bal;	//bal is same for both users, so we can get value into bal variable from any of the for loops above
		//two for loops, one for each user object
	}
	
}
