package core;

public class Transaction {
	private User payer;
	private Double amountPaid;
	private String description;
	private Group group;	//check if this even reqd. or not
	
	public Transaction(User payer, Double amountPaid, String description, Group group) {
		this.payer = payer;
		this.amountPaid = amountPaid;
		this.description = description;
		this.group = group;
	}

	public User getPayer() {
		return payer;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public String getDescription() {
		return description;
	}

	public Group getGroup() {
		return group;
	}
	
}
