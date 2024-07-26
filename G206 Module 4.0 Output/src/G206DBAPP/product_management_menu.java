package G206DBAPP;

/**
 * GROUP MEMBER 01: JEREMIAH MAXWELL ANG
 */

import java.util.Scanner;

public class product_management_menu {
	public product_management p;
	
	public product_management_menu(product_management p) {
		this.p = p;
	}
	
	public int menu() {
		int menuchoice = 0;
		Scanner scan = new Scanner(System.in);
		
		int validInput = 0;
		while(validInput == 0) {
			System.out.println("  ");
			System.out.println("  ");
			System.out.println("=======================================================");
			System.out.println("    Product Management Menu							   ");
			System.out.println("-------------------------------------------------------");
			System.out.println("[1] Create a new Product\r\n"
							 + "[2] Update a record of a Product\r\n"
							 + "[3] Delete a record of a Product\r\n"
							 
							 + "[4] Discontinue a Product\r\n"
							 + "[5] View discontinued products list\r\n"
							 
							 + "[6] View a Product Record\r\n"
							 + "[0] Exit Product Management\r\n");
			System.out.println("=======================================================");
			
			System.out.println("Enter Selected Function: ");
			
			try {
				menuchoice = Integer.parseInt(scan.nextLine());
				
				if(menuchoice < 0 || menuchoice > 6) 
					System.out.println("INVALID INPUT.");
				
				else validInput = 1;
			} 
			catch (NumberFormatException e) {
				System.out.println("INVALID INPUT.");
	        
			}
		}

		
		if (menuchoice == 1) {
			createproductmenu();
		} 
		
		else if (menuchoice == 2) {			
			updateproductmenu();
		} 
		
		else if (menuchoice == 3) {			
			deleteproductmenu();
		} 
		
		else if (menuchoice == 4) {			
			discontinueproductmenu();
		} 
		
		else if (menuchoice == 5) {
			showDiscontinuedProducts();
		}
		
		else if (menuchoice == 6) {
			viewproductmenu();
		}

		return menuchoice;
	}
	
	public void createproductmenu() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("Enter product information");
		System.out.println ("-------------------------------------------------------------------");
		System.out.println ("Product Code        : ");  p.setProductCode(scan.nextLine());
		inputProductDetails();

		p.addproduct();
		
		if(p.viewproduct() != 0)
			showcurrentproductinfo();
	}
	

	
	public void updateproductmenu() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("Enter product information");
		System.out.println ("Product Code        : ");  p.setProductCode(scan.nextLine());

		if (p.viewproduct() == 0) {
			System.out.println("That product does not exist on the records");
		} 
		
		//If product exists
		else if(p.getDiscontinuedProducts().contains(p.getProductCode())) 
			System.out.println ("ERROR: Product is discontinued and cannot be updated."); 

		
		else {
			showcurrentproductinfo();

			System.out.println ("\nEnter updated product information");
			System.out.println ("-------------------------------------------------------------------");
			
			inputProductDetails();
			
			p.updateproduct();
			
			//Show updated product info
			if(p.viewproduct() != 0)
				showcurrentproductinfo();
		}
	}
	
	public void deleteproductmenu() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("Enter product information");
		System.out.println ("Product Code        : ");  
		p.setProductCode(scan.nextLine());		
		
		if(p.getDiscontinuedProducts().contains(p.getProductCode())) 
			System.out.println ("ERROR: Product is discontinued and cannot be deleted."); 
		
		
		else
		p.deleteproduct();
	}
	
	public void discontinueproductmenu() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("Enter product to be discontinued");
		System.out.println ("Product Code        : ");  
		p.setProductCode(scan.nextLine());


		if(p.viewproduct() != 0) {
			
			if(p.getDiscontinuedProducts().contains(p.getProductCode())) {
				System.out.println(p.getProductCode() + " is ALREADY DISCONTINUED\n");
			}
			else {
				p.discontinueproduct();
				System.out.println("Product has been discontinued.");	
			}
	
			
			showDiscontinuedProducts();
		}

		
		else 
			System.out.println("That product does not exist on the records");
	}
	
	public void viewproductmenu() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("Enter product information");
		System.out.println ("Product Code        : ");  
		p.setProductCode(scan.nextLine());
		
		if(p.viewproduct() != 0) {
			showcurrentproductinfo();
			showListOfOrders();
		}
			
		
		else 
			System.out.println("That product does not exist on the records");
		
	}
	
	public void showcurrentproductinfo() {
		System.out.println ("-------------------------------------------------------------------");
		System.out.println ("Current Product information");
		System.out.println ("-------------------------------------------------------------------");
		System.out.println ("Product Code        : " + p.getProductCode());
		System.out.println ("Product Name        : " + p.getProductName());
		System.out.println ("Product Line        : " + p.getProductLine());
		System.out.println ("Product Scale       : " + p.getProductScale());
		
		System.out.println ("Product Vendor      : " + p.getProductVendor());
		System.out.println ("Product Description : " + p.getProductDescription());
		System.out.println ("Initial quantity    : " + p.getQuantityInStock());
		System.out.println ("Buy Price           : " + p.getBuyPrice());
		System.out.println ("MSRP                : " + p.getMSRP());
	}
	
	public void inputProductDetails() {
		Scanner scan = new Scanner(System.in);
		

		System.out.println ("Product Name        : ");  p.setProductName(scan.nextLine());
		System.out.println ("Product Line        : ");  p.setProductLine(scan.nextLine());
		System.out.println ("Product Scale       : ");  p.setProductScale(scan.nextLine());
		
		System.out.println ("Product Vendor      : ");  p.setProductVendor(scan.nextLine());
		System.out.println ("Product Description : ");  p.setProductDescription(scan.nextLine());
		System.out.println ("Initial quantity    : ");  
		
		int validInput = 0;
		
		while(validInput == 0) {
	        String input = scan.nextLine();

	        try {
	        	p.setQuantityInStock(Integer.parseInt(input));

	            System.out.printf("Quantity is: %d\n\n", p.getQuantityInStock());
	            validInput = 1;
	            
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid quantity. Please enter a valid integer:");
	            
	        }
		}

        
		  
		
		System.out.println ("Buy Price           : ");
		validInput = 0;
		
		while(validInput == 0) {
			
	        String input = scan.nextLine();

	        try {
	        	p.setBuyPrice(Float.parseFloat(input));

	        	System.out.printf("Buy Price is: %.2f\n\n", p.getBuyPrice());
	            validInput = 1;
	            
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid Buy Price. Please enter a valid float:");
	            
	        }
		}

		
		System.out.println ("MSRP                : "); 
		
		validInput = 0;
		
		while(validInput == 0) {
			
	        String input = scan.nextLine();

	        try {
	        	p.setMSRP(Float.parseFloat(input));

	        	System.out.printf("MSRP is: %.2f\n\n", p.getMSRP());
	            validInput = 1;
	            
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid MSRP. Please enter a valid float:");
	            
	        }
		}
		
	}
	
	public void showDiscontinuedProducts() {
		System.out.println ("-------------------------------------------------------------------");
		System.out.println ("Discontinued products");
		System.out.println ("-------------------------------------------------------------------");

		for(int i = 0; i < p.getDiscontinuedProducts().size(); i++) {
			System.out.println ("Product Code        : " + p.getDiscontinuedProducts().get(i));
		}
		

	}
	
	public void showListOfOrders() {
		int year = 0;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("[View a list of orders made involving the product given a specific year]");
		System.out.println ("Enter year    : ");  
		year = Integer.parseInt(scan.nextLine());
		
		p.viewOrdersWithProduct(year);
	}
}
