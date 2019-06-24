package tester;

import java.util.Scanner;

import core.Group;
import core.Transaction;
import core.User;

public class Test {

	public static void main(String[] args) {
		
		Group g = new Group("Flat 101");	//initializing a object of group type
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to SplitWise!");
		boolean flag = false;

		while(!flag) {
			System.out.flush();
			try {
				System.out.println("1. Add Member to Group\n2. Add Transaction\n3. Show Group Particulars\n4. Settle up balance between two users\n5. Exit");
				int choice = sc.nextInt();
				
				switch(choice) {
				
				case 1: {
							System.out.print("Enter the name of user : ");
							String name = sc.next();
							System.out.print("Enter email : ");
							String email = sc.next();
							User temp = new User(name, email);
							g.addMember(temp);
							break;
						}
				case 2: {
							System.out.print("Enter the name of payer : ");
							String name = sc.next();
							User payer = g.getUserByName(name);
							if(payer== null) {
								System.out.println("Can't add transcation. No user by the name " + name + " in the group...");
							}
							else {
								System.out.print("Enter amount paid by " + name + " : ");
								Double amount = sc.nextDouble();
								System.out.print("Enter a description about the expense : ");
								String description = sc.next();
								Transaction t = new Transaction(payer, amount, description, g);
								g.addTransaction(t);
							}
							break;
						}
							
				case 3:	{
							g.showGroup();
							break;
						}
				
				case 4:	{
							System.out.println("Enter the names of both users you want to settle between : ");
							String user1 = sc.next();
							String user2 = sc.next();
							Double amountSettled = g.settleBalance(user1, user2);
							
							if(amountSettled != null) {
								System.out.println("Rs. " + amountSettled +" settled between " + user1 + " and " + user2);
							}
							else {
								System.out.println("Settlement Failed!");
							}
							
							break;
						}
				case 5: {
							flag = true;
							break;
						}
				default: {
							System.out.println("Invalid input...");
							break;
						}
				}
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			finally {
				sc.nextLine();	//eats up any buffer
			}
		}
			
	}

}
