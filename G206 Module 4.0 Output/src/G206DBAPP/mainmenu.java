package G206DBAPP;

import java.util.Scanner;

public class mainmenu {
	public mainmenu() {
//		menu();
	}
	
	public int menu() {
		int menuchoice = 0;
		Scanner scan = new Scanner(System.in);
		
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
		
		menuchoice = Integer.parseInt(scan.nextLine());
		
		if (menuchoice == 1) {
			product_management_menu prodmanmenu = new product_management_menu();
			while(prodmanmenu.menu() != 0) {}
		}
		
//		
//		else {
//			System.out.println("INVALID INPUT");
//		}
		
		return menuchoice;
	}
	
	public static void main(String args[]) {
		mainmenu mm = new mainmenu();
		while(mm.menu() != 0) {}
	}
	
}
