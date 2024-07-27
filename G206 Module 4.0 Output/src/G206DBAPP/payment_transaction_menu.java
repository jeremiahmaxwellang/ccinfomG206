package G206DBAPP;

import java.util.Scanner;

public class payment_transaction_menu {
	public payment_transaction_menu() {
		
	}
	
	public int menu() {
		int menuselection  = 0;
		Scanner console = new Scanner(System.in);
		
		int validInput = 0;
		while(validInput == 0) {
			System.out.println("  ");
			System.out.println("  ");
			System.out.println("=======================================================");
			System.out.println("    Payment Management Menu							   ");
			System.out.println("-------------------------------------------------------");
			System.out.println("[1] Create a new Payment\r\n"
							 + "[2] Update a record of a Payment\r\n"
							 + "[3] Delete a record of a Payment\r\n"
							 + "[0] Exit Product Management\r\n");
			System.out.println("=======================================================");
			
			System.out.println("Enter Selected Function: ");
			menuselection = Integer.parseInt(console.nextLine());
			
			if (menuselection == 1) {
				payment_transaction p = new payment_transaction();
				System.out.println("Enter payment information"); 
				System.out.println("Customer Number                   : "); 
				p.customerNumber = Integer.parseInt(console.nextLine());
				System.out.println("Check Number                      : "); 
				p.checkNumber = console.nextLine();
				System.out.println("Amount                            : "); 
				p.amount = Float.parseFloat(console.nextLine());
				p.getCurrentDatetime(); // Set paymentDate to current datetime

				p.create_payment();
			} else if (menuselection == 2) {
				payment_transaction p = new payment_transaction();
				System.out.println("Enter payment information"); 
				System.out.println("Customer Number                   : "); p.customerNumber = Integer.parseInt(console.nextLine());
				System.out.println("Check Number                      : "); p.checkNumber    = console.nextLine();
				
				// Retrieve existing payment details for update
				p.get_payment();
				System.out.println("Current payment information"); 
				System.out.println("Customer Number                   : " + p.customerNumber);
				System.out.println("Check Number                      : " + p.checkNumber);
				System.out.println("Payment Date                      : " + p.paymentDate);
				System.out.println("Amount                            : " + p.amount);
				
				System.out.println("Enter new payment amount          : "); 
				p.amount = Float.parseFloat(console.nextLine());
				
				
				p.update_payment();

				
				
				
			} else if (menuselection == 3) {
				payment_transaction p = new payment_transaction();
				System.out.println("Enter payment information"); 
				System.out.println("Customer Number                   : "); p.customerNumber = Integer.parseInt(console.nextLine());
				System.out.println("Check Number                      : "); p.checkNumber = console.nextLine();
				
				p.delete_payment();
				
			} else if (menuselection == 0) {
				validInput = 1;
			}
		
		}
		return menuselection;
	}
}
