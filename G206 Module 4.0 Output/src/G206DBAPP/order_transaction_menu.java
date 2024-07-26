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
			createOrderMenu();
		} 
		
		else if (menuchoice == 2) {			
			updateOrderMenu();
		} 
		
		else if (menuchoice == 3) {			
		
		} 
		
		else if (menuchoice == 4) {			
			deleteOrderedProductMenu();
		} 
		

		return menuchoice;
	}
	
	public void createOrderMenu() {
		order_transaction o = new order_transaction();
		
		System.out.println ("Enter order information");
		System.out.println ("-------------------------------------------------------------------");
		
		inputOrderDetails(o);

		//create new order record
		o.createorder();
		
		//make new orderdetail records (multiple products can be bought)
		for(int i = 0; i < o.getOrderDetailsList().size(); i++) {
			
			o.createorderdetail(o.getOrderDetailsList().get(i));
			
		}
	}
	
	public void updateOrderMenu() {
//		1. Enter Order Number
//		2. Check if Order exists and if status = 'In Process'
//		3. Display Order (orderDate, requiredDate, etc)
//		4. Enter new Order data 
		
		order_transaction o = new order_transaction();
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("Enter order information");
		System.out.println ("-------------------------------------------------------------------");
		System.out.println ("Order Number           :");
		
		try {
			o.setOrderNumber(Integer.parseInt(scan.nextLine()));
			
			//if order exists
			if(o.viewOrder() != 0) {
				showCurrentOrder(o);
				
				if(o.getStatus().equals("In Process")) {
					//ask for new Order fields
					System.out.println ("Enter updated order information");
					System.out.println ("-------------------------------------------------------------------");
					
				}
				
				else System.out.println("\nERROR: Cannot update. Order is not In Process.\n\n");
				
				
			}
		}
		catch (Exception e) {
			System.out.println("Invalid order number.");
	
		}

	}
	
	public void deleteOrderedProductMenu() {
//		1. Enter Order Number
//		2. Display Ordered Products
//		3. Enter Product Code
//		4. Check if Product Code is in Order
//		5. Delete
		
		order_transaction o = new order_transaction();
		int orderNumber = 0;
		String productCode = "";
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("Enter order information");
		System.out.println ("-------------------------------------------------------------------");
		System.out.println ("Order Number              : ");
		
		try {
        	orderNumber = Integer.parseInt(scan.nextLine());
        	
        	System.out.println ("Product Code to Delete    : ");
        	productCode = scan.nextLine();
        	
        	//add product checking
    		orderdetails od = new orderdetails(orderNumber, productCode);
    		
    		o.deleteOrderedProduct(od);
        		
        		
    		System.out.println ("-------------------------------------------------------------------");
    		System.out.println ("Current Order Details");
    		System.out.println ("-------------------------------------------------------------------");
            
        } catch (NumberFormatException e) {
            System.out.println("Order Number must be AN INTEGER\n\n");
            
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
			System.out.println ("Enter desired product code       : ");  
			productCode = scan.nextLine();
			
        	product_management pm = new product_management();
        	pm.setProductCode(productCode);
        	
        	//If product exists, proceed with adding next details
        	if(pm.viewproduct() != 0) {
        		
    			//increment order line number (new number per product)
    			orderLineNumber++;
    			
        		//price per item = MSRP
    			priceEach = pm.getMSRP();
    			System.out.println ("Price each         : " + priceEach); 
    			System.out.println ("Quantity in Stock  : " + pm.getQuantityInStock() + "\n");
    			
    			validInput = 0;
    			
    			while(validInput == 0) {
    				System.out.println ("Enter quantity       : "); 
    		        input = scan.nextLine();

    		        try {
    		        	
    		        	quantityOrdered = Integer.parseInt(input);

    		            
    		            //ensure product is in stock
    		            if(pm.getQuantityInStock() >= quantityOrdered) {
    		            	System.out.printf("Quantity is: %d\n\n", quantityOrdered);
    		            	validInput = 1;
    		            }
    		            
    		            else System.out.printf("Insufficient stock. Current: %d\n\n", pm.getQuantityInStock());
    		            
    		            
    		            
    		        } catch (NumberFormatException e) {
    		            System.out.println("Invalid quantity. Please enter a valid integer:");
    		            
    		        }
    			}
    		
    			o.setOrderDetails(productCode, quantityOrdered, priceEach, orderLineNumber);
        	}
        	
        	else System.out.println("Product does NOT exist in the records\n\n");
        	
//        	==========Prompt user to continue order=========
        	int choice = -1;
        	while(choice == -1) {
        		choice = continueOrderingPrompt();
        	}

        	continueOrdering = choice;
		}
			
			
//		System.out.println ("When do you require your order to be shipped?        : ");  
		o.setRequiredDate(new java.sql.Timestamp(System.currentTimeMillis())); //temp required date
}


//	==========Prompt user to continue order=========
	public int continueOrderingPrompt() {
	
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Do you wish to order another product?");
			System.out.println("[ 0 - No ]\n[ 1 - Yes ]");
			
			int choice = 0;
	
	        try {
	        	choice = Integer.parseInt(scan.nextLine());
	        	
	        	if(choice == 1 || choice == 0)
	        		return choice;
	        	
	        	else {
	        		System.out.println("Invalid choice.");
	        		return -1;
	        	}
	        		
	            
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid choice.");
	            return -1;
	            
	        }
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
	
	public void showCurrentOrder(order_transaction o) {

		System.out.println ("Order Number        : " + o.getOrderNumber());
		System.out.println ("Order Date          : " + o.getOrderDate());
		
		System.out.println ("Required Date       : " + o.getRequiredDate());
		System.out.println ("Shipped Date        : " + o.getShippedDate());
		System.out.println ("Status              : " + o.getStatus());
		System.out.println ("Comments            : " + o.getComments());
		System.out.println ("Customer Number     : " + o.getCustomerNumber());

	}
	
	public void showCurrentOrderDetails(orderdetails od) {

		System.out.println ("Order Number        : " + od.getOrderNumber());
		System.out.println ("Product Code        : " + od.getProductCode());
		
		System.out.println ("Quantity ordered    : " + od.getQuantityOrdered());
		System.out.println ("Price each          : " + od.getPriceEach());
		System.out.println ("Order Line Number   : " + od.getOrderLineNumber());

	}
}



	
