package G206DBAPP;

/**
 * GROUP MEMBER 01: JEREMIAH MAXWELL ANG
 */

import java.util.Scanner;

public class mainmenu {
	public product_management p = new product_management();
	public mainmenu() {}
	
	public int menu() {
		int menuchoice = 0;
		Scanner scan = new Scanner(System.in);

		int validInput = 0;
		
		while(validInput == 0) {
			System.out.println("  ");
			System.out.println("  ");
			System.out.println("=======================================================");
			System.out.println("    CCINFOM G206 DB Application Menu							   ");
			System.out.println("-------------------------------------------------------");
			System.out.println("[1] Product Management\r\n"
							 + "[2] Customer Management\r\n"
							 + "[3] Employee Management\r\n"
							 + "[4] Office Management\r\n\n"
							 
							 + "[5] Order Processing\r\n"
							 + "[6] Payment Processing\r\n"
							 + "[7] Report Generation - Sales Report 1\r\n"
							 + "[8] Report Generation - Sales Report 2\r\n"
							 + "[0] Exit App\r\n");
			System.out.println("=======================================================");
			
			System.out.println("Enter Selected Function: ");
			
			try {
				menuchoice = Integer.parseInt(scan.nextLine());
				
				if(menuchoice < 0 || menuchoice > 8) 
					System.out.println("INVALID INPUT.");
				
				else validInput = 1;
			} 
			catch (NumberFormatException e) {
				System.out.println("INVALID INPUT.");
	        
			}
		}
		
		
		if (menuchoice == 1) {
			product_management_menu prodmanmenu = new product_management_menu(p);
			while(prodmanmenu.menu() != 0) {}
		}
		
		//Order Processing
		else if (menuchoice == 5) {
			order_transaction_menu ordermenu = new order_transaction_menu();
			while(ordermenu.menu() != 0) {}
		}
		

		return menuchoice;
	}
	
	public static void main(String args[]) {
		mainmenu mm = new mainmenu();
		while(mm.menu() != 0) {}
		
		System.out.println("Closing application...");
	}
	
}
