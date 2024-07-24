package G206DBAPP;

import java.util.Scanner;

public class order_transaction_menu {

	public order_transaction_menu() {}
	
	public int menu() {
		int menuchoice = 0;
		Scanner scan = new Scanner(System.in);
		
		int validInput = 0;
		while(validInput == 0) {
			System.out.println("  ");
			System.out.println("  ");
			System.out.println("=======================================================");
			System.out.println("    Order Transaction Menu							   ");
			System.out.println("-------------------------------------------------------");
			System.out.println("[1] Create a new Order\r\n"
							 + "[2] Update an In-Process Order\r\n"
							 + "[3] Update an Ordered Product\r\n"
							 + "[4] Delete an Ordered Product\r\n"
							 + "[0] Exit Order Transaction Menu\r\n");
			System.out.println("=======================================================");
			
			System.out.println("Enter Selected Function: ");
			
			try {
				menuchoice = Integer.parseInt(scan.nextLine());
				
				if(menuchoice < 0 || menuchoice > 4) 
					System.out.println("INVALID INPUT.");
				
				else validInput = 1;
			} 
			catch (NumberFormatException e) {
				System.out.println("INVALID INPUT.");
	        
			}
		}

		
		if (menuchoice == 1) {

		} 
		
		else if (menuchoice == 2) {			
	
		} 
		
		else if (menuchoice == 3) {			
		
		} 
		
		else if (menuchoice == 4) {			

		} 
		

		return menuchoice;
	}
}
