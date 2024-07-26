package G206DBAPP;

/**
 * GROUP MEMBER 01: JEREMIAH MAXWELL ANG
 */

import java.sql.*;
import java.util.ArrayList;

public class order_transaction {
	//orders
	private int orderNumber;
	private Timestamp orderDate;
	private Timestamp requiredDate;
	private Timestamp shippedDate;
	private String status;
	private String comments;
	private int customerNumber;
	
	private ArrayList<orderdetails> orderdetails_list;
	
	private String connectionString = "jdbc:mysql://mysql-176128-0.cloudclusters.net:10107/dbsales?useTimezone=true&serverTimezone=UTC&user=CCINFOM_G206&password=DLSU1234";
//	private String connectionString = "jdbc:mysql://localhost:3306/dbsales?useTimezone=true&serverTimezone=UTC&user=root&password=CCINFOMS12";
	
	public order_transaction(){
		orderNumber = 0;
		orderDate = null;
		requiredDate = null;
		shippedDate = null;
		status = "";
		comments = "";
		customerNumber = 0;
		orderdetails_list = new ArrayList<orderdetails>();
		
	}
	
	public void createorder() {
		try {
			Connection conn;
			conn = DriverManager.getConnection(connectionString);
			System.out.println("Connection to DB successful\n");
			
			//orders
			
			//Generate next orderNumber
			getMaxOrderNumber();
			
			
			//Get date today
			getCurrentDatetime();
			
			String insertOrder = "INSERT INTO orders (`orderNumber`, `orderDate`, `requiredDate`, `status`, `customerNumber`) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insertOrder);

			pstmt.setInt(1, orderNumber);
			pstmt.setTimestamp(2, orderDate);
			pstmt.setTimestamp(3, requiredDate);
			
			status = "In Process";
			pstmt.setString(4, status);
			
			pstmt.setInt(5, customerNumber);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void createorderdetail(orderdetails or) {
		try {
			Connection conn;
			conn = DriverManager.getConnection(connectionString);
			System.out.println("Connected; Adding product " + or.getProductCode() + " to order...\n");
			

			//orderdetails
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO orderdetails VALUES (?, ?, ?, ?, ?)");
			pstmt.setInt   (1, orderNumber);
			pstmt.setString(2, or.getProductCode());
			pstmt.setInt   (3, or.getQuantityOrdered());
			pstmt.setFloat (4, or.getPriceEach());
			pstmt.setShort (5, or.getOrderLineNumber());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int updateOrder() {
		try {
			Connection conn;
			conn = DriverManager.getConnection(connectionString);
			System.out.println("Connection to DB successful");
			
			PreparedStatement pstmt = conn.prepareStatement("UPDATE orders SET orderDate=?, requiredDate=?, shippedDate=?, status=?, comments=?, customerNumber=? WHERE orderNumber=?");
			pstmt.setInt(7, orderNumber);
			pstmt.setTimestamp(1, orderDate);
			pstmt.setTimestamp(2, requiredDate);
			pstmt.setTimestamp(3, shippedDate);
			
			pstmt.setString(4, status);
			pstmt.setString(5, comments);
			pstmt.setInt   (6, customerNumber);
			
			System.out.println("SQL Statement Prepared");
			pstmt.executeUpdate();
			System.out.println("Record was updated");
			pstmt.close();
			conn.close();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public void updateOrderedProduct(orderdetails od, String newProductCode) {
		try {
			Connection conn;
			conn = DriverManager.getConnection(connectionString);
			System.out.println("Connection to DB successful");
			PreparedStatement pstmt = conn.prepareStatement("UPDATE orderdetails SET productCode=?, quantityOrdered=? WHERE orderNumber=? AND productCode=?");

			pstmt.setInt(3, od.getOrderNumber());
			//Old product code
			pstmt.setString(4, od.getProductCode());
			
			//Updated product code
			pstmt.setString(1, newProductCode);
			pstmt.setInt(2, od.getQuantityOrdered());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteOrderedProduct(orderdetails or) {
		try {
			Connection conn;
			conn = DriverManager.getConnection(connectionString);
			System.out.println("Connection to DB successful");
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM orderdetails WHERE orderNumber=? AND productCode=?");
			pstmt.setInt(1, or.getOrderNumber());
			pstmt.setString(2, or.getProductCode());
			System.out.println("SQL Statement Prepared");
			
			pstmt.executeUpdate();
			System.out.println("Record was deleted");
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("Could not delete, product is not part of order.");
			System.out.println(e.getMessage());
		}
	}
	
	
	public int viewOrder() {
		int recordcount = 0;
		try {
			Connection conn;
			conn = DriverManager.getConnection(connectionString);
			System.out.println("Connection to DB successful");
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM orders WHERE orderNumber=?");
			pstmt.setInt(1, orderNumber);
			
			System.out.println("SQL Statement Prepared");
			
			ResultSet rs = pstmt.executeQuery();
			
			//Copy record from db
			while(rs.next()) {
				recordcount++;
				orderDate = rs.getTimestamp("orderDate");
				requiredDate = rs.getTimestamp("requiredDate");
				shippedDate = rs.getTimestamp("shippedDate");
				status = rs.getString("status");
				comments = rs.getString("comments");
				customerNumber = rs.getInt("customerNumber");
				System.out.println("Record of Order #" + orderNumber + " exists\n");
			}

			
			pstmt.close();
			conn.close();
			return recordcount;
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int getMaxOrderNumber() {
			int recordcount = 0;
			try {
				Connection conn;
				conn = DriverManager.getConnection(connectionString);
				System.out.println("Connected; Generating Order...");
				
				PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(orderNumber) FROM orders");
				System.out.println("SQL Statement Prepared");
				
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					recordcount++;
					orderNumber = rs.getInt("MAX(orderNumber)");
					orderNumber++;

					System.out.println("Your Order Number: " + orderNumber + "\n\n");
				}

				
				pstmt.close();
				conn.close();
				return recordcount;
			}
			
			catch (Exception e) {
				System.out.println(e.getMessage());
				return 0;
			}
		}
	 


    
	public int getCurrentDatetime() {
			int recordcount = 0;
			try {
				Connection conn;
				conn = DriverManager.getConnection(connectionString);
				System.out.println("Connection to DB successful");
				
				PreparedStatement pstmt = conn.prepareStatement("SELECT CURRENT_TIMESTAMP");
				System.out.println("SQL Statement Prepared (Current Timestamp)");
				
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					recordcount++;
					orderDate = rs.getTimestamp("CURRENT_TIMESTAMP");

					System.out.println("Current DATETIME: " + orderDate + "\n\n");
				}

				
				pstmt.close();
				conn.close();
				return recordcount;
			}
			
			catch (Exception e) {
				System.out.println(e.getMessage());
				return 0;
			}
		}
    

	public int viewMultipleOrderDetails() {
		int recordcount = 0;
		try {
			Connection conn;
			conn = DriverManager.getConnection(connectionString);
			System.out.println("Connection to DB successful");
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT od.orderNumber, od.productCode, od.quantityOrdered, p.productName  FROM orderdetails od JOIN products p ON p.productCode = od.productCode WHERE od.orderNumber=?");
			
			//Set the WHERE orderNumber = ?
			pstmt.setInt(1, orderNumber);
			
			System.out.println("SQL Statement Prepared, getting records...");
			
			ResultSet rs = pstmt.executeQuery();
			

			
			while(rs.next()) {
				recordcount++;
//				Displays the order detail (DOES NOT STORE IT)
				System.out.println   ("\norderNumber       : " + rs.getString(1) 
									+ "\nproductCode       : " + rs.getString(2) 
									+ "\nquantityOrdered   : " + rs.getString(3) 
									+ "\nproductName       : " + rs.getString(4) + "\n\n");
				
			}

			
			pstmt.close();
			conn.close();
			return recordcount;
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
    // Getter and Setter for orderNumber
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    // Getter and Setter for orderDate
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    // Getter and Setter for requiredDate
    public Timestamp getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Timestamp requiredDate) {
        this.requiredDate = requiredDate;
    }

    // Getter and Setter for shippedDate
    public Timestamp getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Timestamp shippedDate) {
        this.shippedDate = shippedDate;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for comments
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    // Getter and Setter for customerNumber
    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    // Getter for orderdetails_list
    public ArrayList<orderdetails> getOrderDetailsList() {
		 return orderdetails_list;
    }

    public void setOrderDetails(String productCode, int quantityOrdered, float priceEach, short orderLineNumber) {
        orderdetails od = new orderdetails(productCode, quantityOrdered, priceEach, orderLineNumber);
        
        this.orderdetails_list.add(od);
    }
    


   
}
