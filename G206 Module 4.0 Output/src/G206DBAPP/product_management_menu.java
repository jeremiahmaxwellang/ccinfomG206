package G206DBAPP;

import java.util.Scanner;

public class product_management_menu {
	public product_management_menu() {
		
	}
	
	public int menu() {
		int menuchoice = 0;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("  ");
		System.out.println("  ");
		System.out.println("=======================================================");
		System.out.println("    Product Management Menu							   ");
		System.out.println("-------------------------------------------------------");
		System.out.println("[1] Create a new Product\r\n"
						 + "[2] Update a record of a Product\r\n"
						 + "[3] Delete a record of a Product\r\n"
						 + "[4] Discontinue a Product\r\n"
						 + "[5] View a Product Record\r\n"
						 + "[0] Exit Product Management\r\n");
		System.out.println("=======================================================");
		
		System.out.println("Enter Selected Function: ");
		
		menuchoice = Integer.parseInt(scan.nextLine());
		
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
			//discontinue product
		} 
		
		else if (menuchoice == 5) {
			viewproductmenu();
		}
		

		return menuchoice;
	}
	
	public void createproductmenu() {
		product_management p = new product_management();
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("Enter product information");
		inputProductDetails(p);

		p.addproduct();
	}
	

	
	public void updateproductmenu() {
		product_management p = new product_management();
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("Enter product information");
		System.out.println ("Product Code        : ");  p.setProductCode(scan.nextLine());

		if (p.viewproduct() == 0) {
			System.out.println("That product does not exist on the records");
		} else {
			showcurrentproductinfo(p);

			System.out.println ("Enter updated product information");
			
			inputProductDetails(p);
			
			p.updateproduct();
		}
	}
	
	public void deleteproductmenu() {
		product_management p = new product_management();
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("Enter product information");
		System.out.println ("Product Code        : ");  p.setProductCode(scan.nextLine());		
		
		p.deleteproduct();
	}
	
	public void viewproductmenu() {
		product_management p = new product_management();
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("Enter product information");
		System.out.println ("Product Code        : ");  
		p.setProductCode(scan.nextLine());
		
		if(p.viewproduct() != 0) 
			showcurrentproductinfo(p);
		
		else 
			System.out.println("That product does not exist on the records");
		
	}
	
	public void showcurrentproductinfo(product_management p) {
		System.out.println ("Current Product information");
		System.out.println ("-------------------------------------------------------------------");
		System.out.println ("Product Code        : " + p.getProductCode());
		System.out.println ("Product Name        : " + p.getProductName());
		System.out.println ("Product Line        : " + p.getProductLine());
		System.out.println ("Product Scale       : " + p.getProductScale());
		System.out.println ("Product Description : " + p.getProductDescription());
		System.out.println ("Product Vendor      : " + p.getProductVendor());
		System.out.println ("Initial quantity    : " + p.getQuantityInStock());
		System.out.println ("Buy Price           : " + p.getBuyPrice());
		System.out.println ("MSRP                : " + p.getMSRP());
	}
	
	public void inputProductDetails(product_management p) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("-------------------------------------------------------------------");
		System.out.println ("Product Code        : ");  p.setProductCode(scan.nextLine());
		System.out.println ("Product Name        : ");  p.setProductName(scan.nextLine());
		System.out.println ("Product Line        : ");  p.setProductLine(scan.nextLine());
		System.out.println ("Product Scale       : ");  p.setProductScale(scan.nextLine());
		System.out.println ("Product Description : ");  p.setProductDescription(scan.nextLine());
		System.out.println ("Product Vendor      : ");  p.setProductVendor(scan.nextLine());
		System.out.println ("Initial quantity    : ");  
		
		int validInput = 0;
		
		while(validInput == 0) {
	        String input = scan.nextLine();

	        try {
	        	p.setQuantityInStock(Integer.parseInt(input));

	            System.out.println("Quantity is: " + p.getQuantityInStock());
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

	            System.out.println("Buy Price is: " + p.getBuyPrice());
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

	            System.out.println("MSRP is: " + p.getBuyPrice());
	            validInput = 1;
	            
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid MSRP. Please enter a valid float:");
	            
	        }
		}
		
	}
}
