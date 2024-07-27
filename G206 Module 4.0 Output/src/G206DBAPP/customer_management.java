package G206DBAPP;

/**
 * MEMBER 02: EULYSIS DIMAILIG
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class customer_management {
	public int	  customerNumber;
	public String customerName;
	public String contactLastName;
	public String contactFirstName;
	public String phone;
	public String addressLine1;
	public String addressLine2;
	public String city;
	public String state;
	public String postalCode;
	public String country;
	public int    salesRepEmployeeNumber;
	public double creditLimit;
	
	public customer_management () {
		customerNumber = 0;
		customerName = "";
		contactLastName = "";
		contactFirstName = "";
		phone = "";
		addressLine1 = "";
		addressLine2 = "";
		city = "";
		state = "";
		postalCode = "";
		country = "";
		salesRepEmployeeNumber = 0;
		creditLimit = 0;
	}






	public int add_customer() { 
		try {
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://mysql-176128-0.cloudclusters.net:10107/dbsales?useTimezone=true&serverTimezone=UTC&user=CCINFOM_G206&password=DLSU1234");
			System.out.println("Connection to DB successful");
			
			
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO customers VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt   (1, customerNumber);
			pstmt.setString(2, customerName);
			pstmt.setString(3, contactLastName);
			pstmt.setString(4, contactFirstName);
			pstmt.setString(5, phone);
			pstmt.setString(6, addressLine1);
			pstmt.setString(7, addressLine2);
			pstmt.setString(8, city);
			pstmt.setString(9, state);
			pstmt.setString(10, postalCode);
			pstmt.setString(11, country);
			pstmt.setInt   (12, salesRepEmployeeNumber);
			pstmt.setDouble(13,  creditLimit);	
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	
	
	
	
	public int update_customer() {
		try {
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://mysql-176128-0.cloudclusters.net:10107/dbsales?useTimezone=true&serverTimezone=UTC&user=CCINFOM_G206&password=DLSU1234");
			System.out.println("Connection to DB successful");
			PreparedStatement pstmt = conn.prepareStatement("UPDATE customers SET customerName=?, contactLastName=?, contactFirstName=?, phone=?, addressLine1=?, addressLine2=?, city=?, state=?, postalCode=?, country=?, salesRepEmployeeNumber=?, creditLimit=? WHERE customerNumber=?");
			pstmt.setInt   (13, customerNumber);
			pstmt.setString(1, customerName);
			pstmt.setString(2, contactLastName);
			pstmt.setString(3, contactFirstName);
			pstmt.setString(4, phone);
			pstmt.setString(5, addressLine1);
			pstmt.setString(6, addressLine2);
			pstmt.setString(7, city);
			pstmt.setString(8, state);
			pstmt.setString(9, postalCode);
			pstmt.setString(10, country);
			pstmt.setInt   (11, salesRepEmployeeNumber);
			pstmt.setDouble(12,  creditLimit);	
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	
	
	
	
	public int delete_customer() {
		try {
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://mysql-176128-0.cloudclusters.net:10107/dbsales?useTimezone=true&serverTimezone=UTC&user=CCINFOM_G206&password=DLSU1234");
			System.out.println("Connection to DB successful");
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM customers WHERE customerNumber=?");
			pstmt.setInt   (1, customerNumber);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	

	
	public int view_customer(int year) { 
		int recordcount = 0;
		try {
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://mysql-176128-0.cloudclusters.net:10107/dbsales?useTimezone=true&serverTimezone=UTC&user=CCINFOM_G206&password=DLSU1234");
			System.out.println("Connection to DB successful");
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT c.customerNumber, c.customerName, o.orderNumber, o.orderDate, o.status FROM customers c JOIN orders o ON c.customerNumber = o.customerNumber WHERE c.customerNumber = ? AND YEAR(o.orderDate) = ?");
			pstmt.setInt(1, customerNumber);
			pstmt.setInt(2, year);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
	            recordcount++;  //Displays the order detail (DOES NOT STORE IT)
	            System.out.println   ("\ncustomerNumber       : " + rs.getInt(1) 
	                                + "\ncustomerName      : " + rs.getString(2) 
	                                + "\norderNumber   : " + rs.getInt(3) 
	                                + "\norderDate   : " + rs.getDate(3) 
	                                + "\nstatus      : " + rs.getString(4) + "\n\n");
	        }
			pstmt.close();
			conn.close();
			return recordcount;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	 
	}
	
	
	public int ban_customers() {
		try {
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://mysql-176128-0.cloudclusters.net:10107/dbsales?useTimezone=true&serverTimezone=UTC&user=CCINFOM_G206&password=DLSU1234");
			System.out.println("Connection to DB successful");
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO banned_customers VALUES (?)");
			pstmt.setInt(1, customerNumber);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	

	public int get_customer() {
		try {
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://mysql-176128-0.cloudclusters.net:10107/dbsales?useTimezone=true&serverTimezone=UTC&user=CCINFOM_G206&password=DLSU1234");
			System.out.println("Connection to DB successful");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM customers WHERE customerNumber = ?");
			pstmt.setInt   (1, customerNumber);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				customerName = rs.getString("customerName");
				contactFirstName = rs.getString("contactFirstname");
				contactLastName = rs.getString("contactLastName");
				phone = rs.getString("phone");
				addressLine1 = rs.getString("addressLine1");
				addressLine2 = rs.getString("addressLine2");
				city = rs.getString("city");
				state = rs.getString("state");
				postalCode = rs.getString("postalCode");
				country = rs.getString("country");
				salesRepEmployeeNumber = rs.getInt("salesRepEmployeeNumber");
				creditLimit = rs.getDouble("creditLimit");
			}
			pstmt.close();
			conn.close();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
}