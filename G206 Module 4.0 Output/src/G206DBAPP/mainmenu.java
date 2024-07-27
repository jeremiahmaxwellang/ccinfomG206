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
							 + "[4] Office Management (Unavailable)\r\n\n"
							 
							 + "[5] Order Processing\r\n"
							 + "[6] Payment Processing\r\n"
							 + "[7] Report Generation - Sales Report 1\r\n"
							 + "[8] Report Generation - Sales Report 2 (Unavailable)\r\n"
							 + "[0] Exit App\r\n");
			System.out.println("=======================================================");
			
			System.out.println("Enter Selected Function: ");
			
			try {
				menuchoice = Integer.parseInt(scan.nextLine());
				
				if(menuchoice < 0 || menuchoice > 8) 
					System.out.println("INVALID INPUT.");
				
				else validInput = 1;
			} 
			catch (Exception e) {
				System.out.println("Exception occurred: INVALID INPUT.");
	        
			}
		}
		
//		Product Management
		if (menuchoice == 1) {
			product_management_menu prodmanmenu = new product_management_menu(p);
			while(prodmanmenu.menu() != 0) {}
		}
		
//		Customer Management
		else if (menuchoice == 2) {
			customer_management_menu c = new customer_management_menu();
			c.menu();
//			while(c.menu() != 0) {}
		}
		
//		Employee Management
		else if (menuchoice == 3) {
			EmployeeManager e = new EmployeeManager();
			while(e.menu() != 6) {}
		}
		
//		Office Management - MEMBER 04 NOT CONTRIBUTING
		else if (menuchoice == 4) {
			System.out.println("Eiden Bryce Tieng did NOT contribute to the DB App");
		}
		
//		Order Processing
		else if (menuchoice == 5) {
			order_transaction_menu ordermenu = new order_transaction_menu();
			while(ordermenu.menu() != 0) {}
		}
		
//		Payt Processing
		else if (menuchoice == 6) {
			payment_transaction_menu ptm = new payment_transaction_menu();
			ptm.menu();
		}
		
//		Report 1
		else if (menuchoice == 7) {
			SalesReportGenerator report1 = new SalesReportGenerator();
			report1.generateSalesReport1();
		}
		
//		Report 2
		else if (menuchoice == 8) {
			System.out.println("Eiden Bryce Tieng did NOT contribute to the DB App");
		}

//		scan.close();
		return menuchoice;
	}
	
	public static void main(String args[]) {
		mainmenu mm = new mainmenu();
		while(mm.menu() != 0) {}
		
		System.out.println("Closing application...");
	}
	
}
