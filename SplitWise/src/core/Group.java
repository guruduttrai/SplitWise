package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {
	private String groupName;
	private List<User> members;
	private List<Transaction> transactions;
	
	public Group(String name) {
		this.members = new ArrayList<>();	//init the member list
		this.transactions = new ArrayList<>();
		this.groupName = name;
	}
	
	public void addMember(User toBeAdded) {
		
		for(int i = 0; i < members.size(); i++) {
			toBeAdded.addOtherUserToLedger(members.get(i));
		}	//add each existing member of this group to the ledger of user to be added.
		
		for(int i = 0; i<members.size(); i++) {
			members.get(i).addOtherUserToLedger(toBeAdded);
		}	//add the user to be added to each existing member's ledger
		
		this.members.add(toBeAdded);	//actually adding new user to the group now 
		
		System.out.println("Member added to group - " + this.groupName);
	}
	
	public void addTransaction(Transaction t) {
		User payer = t.getPayer();
		
		int index = members.indexOf(payer);
		//System.out.println("payer found at index " + index);
		
		if(index == -1) {	//if payer is not present in group
			System.out.println("Can't add transaction, invalid payer..");
			return;
		}

		Double amountToBePaidByEachMember = t.getAmountPaid()/members.size();
		//System.out.println("amount to be divided : " + amountToBePaidByEachMember);
		
		for(int i = 0; i<members.size(); i++) {
			
			User temp = members.get(i);
			if(temp.equals(payer)) {
				continue;
			}
			Balance b = temp.getSpecificLedger(payer);	//each member's ledger for payer will see some changes
			b.updateBalance((-1)*amountToBePaidByEachMember);	//owe
		}
		
		List<Balance> ledger = payer.getLedger(); 
		ledger.forEach(l -> l.updateBalance(amountToBePaidByEachMember));	//update bal for each member in payer's ledger owed
	}
	
	
	public User getUserByName(String name) {
		for(User u : members) {
			if(u.getName().equalsIgnoreCase(name)) {
				return u;
			}
		}
		return null;
	}
	
	public Double settleBalance(String user1, String user2) {
		
		User user1Obj = null;
		User user2Obj = null;
		
		for(User u : this.members ) {
			if(u.getName().equalsIgnoreCase(user1)) {
				user1Obj = u;
			}
			else if(u.getName().equalsIgnoreCase(user2)) {
				user2Obj = u;
			}
			if(user1Obj != null && user2Obj != null) {	//break the loop if we get both User objects.
				break;
			}
		}
		
		if(user1Obj == null) {
			System.out.println(user1 + " is not a participant of this group");
			return null;
		}
		if(user2Obj == null) {
			System.out.println(user2 + " is not a participant of this group");
			return null;
		}	//these two conditions run if any one or both names couldn't be found in the group's member list
		
		//control comes here, it means we have both users, so we continue
		
		return User.settle(user1Obj, user2Obj);	//calling static object of User class to change corresponding user's balance in their ledgers to 0	
	}
	
	public void showGroup() {
		for(int i = 0; i<this.members.size(); i++) {
			User tbd = members.get(i);
			tbd.showDetails();
		}
	}
	
	
	

}
