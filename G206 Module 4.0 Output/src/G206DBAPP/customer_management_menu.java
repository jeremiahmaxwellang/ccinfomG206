package G206DBAPP;

/**
 * MEMBER 02: EULYSIS DIMAILIG
 */

import java.util.Scanner;

public class customer_management_menu {
	
	public customer_management_menu() {
		
	}

	public int menu() {
		int menuselection  = 0;
		Scanner console = new Scanner(System.in);
		
		int validInput = 0;
		while(validInput == 0) {
			System.out.println("  ");
			System.out.println("  ");
			System.out.println("=======================================================");
			System.out.println("    Customer Management Menu							   ");
			System.out.println("-------------------------------------------------------");
			System.out.println("[1] Create a new Customer\r\n"
							 + "[2] Update a record of a Customer\r\n"
							 + "[3] Delete a record of a Customer\r\n"
							 + "[4] Ban a Customer\r\n"
							 + "[5] View a Customer & their Orders\r\n"
							 + "[0] Exit Customer Management\r\n");
			System.out.println("=======================================================");
			
			System.out.println("Enter Selected Function: ");
			menuselection = Integer.parseInt(console.nextLine());
			
			if (menuselection == 1) {
				customer_management c = new customer_management();
				
				System.out.println("Enter customer information"); 
				System.out.println("Customer Number                   : "); c.customerNumber           = Integer.parseInt(console.nextLine());
				System.out.println("Customer Name                     : "); c.customerName           = console.nextLine();
				System.out.println("Contact Last Name                 : "); c.contactLastName          = console.nextLine();
				System.out.println("Contact First Name                : "); c.contactFirstName         = console.nextLine();
				System.out.println("Phone                             : "); c.phone                    = console.nextLine();
				System.out.println("Address Line 1                    : "); c.addressLine1             = console.nextLine();
				System.out.println("Address Line 2                    : "); c.addressLine2             = console.nextLine();
				System.out.println("City                              : "); c.city                     = console.nextLine();
				System.out.println("State                             : "); c.state                    = console.nextLine();
				System.out.println("Postal Code                       : "); c.postalCode               = console.nextLine();
				System.out.println("Country                           : "); c.country                  = console.nextLine();
				System.out.println("Sales Representative Employee No. : "); c.salesRepEmployeeNumber   = Integer.parseInt(console.nextLine());
				System.out.println("Credit Limit                      : ");	c.creditLimit              = Double.parseDouble(console.nextLine());
				
				c.add_customer();
			} else if (menuselection == 2) {
				customer_management c = new customer_management();
				System.out.println("Enter customer information"); 
				System.out.println("Customer Number                   : "); c.customerNumber           = Integer.parseInt(console.nextLine());
				
				c.get_customer();
				System.out.println("Current customer information"); 
				System.out.println("Customer Number                   : " + c.customerNumber);         
				System.out.println("Customer Name                     : " + c.customerName);          
				System.out.println("Contact Last Name                 : " + c.contactLastName);          
				System.out.println("Contact First Name                : " + c.contactFirstName);        
				System.out.println("Phone                             : " + c.phone);                   
				System.out.println("Address Line 1                    : " + c.addressLine1);            
				System.out.println("Address Line 2                    : " + c.addressLine2);             
				System.out.println("City                              : " + c.city);                     
				System.out.println("State                             : " + c.state);                    
				System.out.println("Postal Code                       : " + c.postalCode);               
				System.out.println("Country                           : " + c.country);                  
				System.out.println("Sales Representative Employee No. : " + c.salesRepEmployeeNumber);   
				System.out.println("Credit Limit                      : " +	c.creditLimit);             
				
				
				System.out.println("Enter customer information"); 
				System.out.println("Customer Number                   : "); c.customerNumber           = Integer.parseInt(console.nextLine());
				System.out.println("Customer Name                     : "); c.customerName           = console.nextLine();
				System.out.println("Contact Last Name                 : "); c.contactLastName          = console.nextLine();
				System.out.println("Contact First Name                : "); c.contactFirstName         = console.nextLine();
				System.out.println("Phone                             : "); c.phone                    = console.nextLine();
				System.out.println("Address Line 1                    : "); c.addressLine1             = console.nextLine();
				System.out.println("Address Line 2                    : "); c.addressLine2             = console.nextLine();
				System.out.println("City                              : "); c.city                     = console.nextLine();
				System.out.println("State                             : "); c.state                    = console.nextLine();
				System.out.println("Postal Code                       : "); c.postalCode               = console.nextLine();
				System.out.println("Country                           : "); c.country                  = console.nextLine();
				System.out.println("Sales Representative Employee No. : "); c.salesRepEmployeeNumber   = Integer.parseInt(console.nextLine());
				System.out.println("Credit Limit                      : ");	c.creditLimit              = Double.parseDouble(console.nextLine());
				
				c.add_customer();
			} else if (menuselection == 3) {
				customer_management c = new customer_management();
				System.out.println("Enter customer information"); 
				System.out.println("Customer Number                   : "); c.customerNumber           = Integer.parseInt(console.nextLine());
				
				c.delete_customer();
			} else if (menuselection == 4) {
				customer_management c = new customer_management();
				System.out.println("Enter customer information"); 
				System.out.println("Customer Number                   : "); c.customerNumber           = Integer.parseInt(console.nextLine());
				
				c.ban_customers();
			} else if (menuselection == 5) {
				customer_management c = new customer_management();
				System.out.println("Enter customer information"); 
				System.out.println("Customer Number                   : "); c.customerNumber           = Integer.parseInt(console.nextLine());
				System.out.println("Enter the year                    : "); int year = Integer.parseInt(console.nextLine());
				
				c.view_customer(year);
				
				
			} else if (menuselection == 0) {
				validInput = 1;
			}
		
		}
		return menuselection;
	}
}
