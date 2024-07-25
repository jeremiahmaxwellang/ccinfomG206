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
			createordermenu();
		} 
		
		else if (menuchoice == 2) {			
	
		} 
		
		else if (menuchoice == 3) {			
		
		} 
		
		else if (menuchoice == 4) {			

		} 
		

		return menuchoice;
	}
	
	public void createordermenu() {
		order_transaction o = new order_transaction();
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("Enter order information");
		System.out.println ("-------------------------------------------------------------------");
		
		inputOrderDetails(o);

		//create new order record
		o.createorder();
		
		//make new orderdetail record
		for(int i = 0; i < o.getOrderDetailsList().size(); i++) {
			
			o.createorderdetail(o.getOrderDetailsList().get(i));
			
		}
	}
	
	public void inputOrderDetails(order_transaction o) {
		Scanner scan = new Scanner(System.in);
		
		int continueOrdering = 1;
		int validInput = 0;
		String input = "";
		
		String productCode = "";
		int quantityOrdered = 0;
		float priceEach = 0;
		short orderLineNumber = 0;

		
		//Enter customer number
		inputCustomerNumber(o);
		
		//order multiple products
		while(continueOrdering == 1) {
			//increment order line number (new number per product)
			orderLineNumber++;
			
			System.out.println ("Enter desired product code       : ");  
			productCode = scan.nextLine();
			
			//price per item = MSRP
			
			
			System.out.println ("Enter quantity       : "); 
			validInput = 0;
			
			while(validInput == 0) {
		        input = scan.nextLine();

		        try {
		        	quantityOrdered = Integer.parseInt(input);

		            System.out.printf("Quantity is: %d\n\n", quantityOrdered);
		            
		            //add error checking for product quantity
		            
		            validInput = 1;
		            
		        } catch (NumberFormatException e) {
		            System.out.println("Invalid quantity. Please enter a valid integer:");
		            
		        }
			}
		
			o.setOrderDetails(productCode, quantityOrdered, priceEach, orderLineNumber);
			

//			==========Prompt user to continue order=========
			validInput = 0;
			
			
			while(validInput == 0) {
				System.out.println("Do you wish to order another product?");
				System.out.println("[ 0 - No ]\n[ 1 - Yes ]");
				
				String choice = scan.nextLine();

		        try {
		        	continueOrdering = Integer.parseInt(choice);
		        	
		        	if(continueOrdering == 1 || continueOrdering == 0)
		        		validInput = 1;
		        	
		        	else System.out.println("Invalid choice.");
		        		
		            
		        } catch (NumberFormatException e) {
		            System.out.println("Invalid choice.");
		            
		        }
			}
			
			
			
		}

		

		
//		System.out.println ("When do you require your order to be shipped?        : ");  
		o.setRequiredDate(new java.sql.Timestamp(System.currentTimeMillis())); //temp required date


		

		

		
		
	}
	
	
	public void inputCustomerNumber(order_transaction o) {
		int validInput = 0;
		String input = "";
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("Enter your Customer number    : ");
		while(validInput == 0) {
	        input = scan.nextLine();

	        try {
	        	o.setCustomerNumber(Integer.parseInt(input));

	            System.out.printf("You are customer # %d\n\n", o.getCustomerNumber());
	            
	            validInput = 1;
	            
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid customer number. Please enter a valid integer:");
	            
	        }
		}
	}
}

	

	
