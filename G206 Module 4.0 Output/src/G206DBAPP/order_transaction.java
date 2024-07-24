package G206DBAPP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class order_transaction {
	//orders
	private int orderNumber;
	private Timestamp orderDate;
	private Timestamp requiredDate;
	private Timestamp shippedDate;
	private String status;
	private String comments;
	private int customerNumber;
	
	//orderdetails
	private String productCode;
	private int quantityOrdered;
	private float priceEach;
	private short orderLineNumber;
	
	public order_transaction(){}
	
	public void createorder() {
		try {
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://mysql-176128-0.cloudclusters.net:10107/dbsales?useTimezone=true&serverTimezone=UTC&user=CCINFOM_G206&password=DLSU1234");
			System.out.println("Connection to DB successful");
			
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO orders VALUES (?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, orderNumber);
			pstmt.setTimestamp(2, requiredDate);
			pstmt.setTimestamp(3, shippedDate);
			pstmt.setString(4, status);
			pstmt.setString(5, comments);
			pstmt.setInt(6, customerNumber);
			
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("INSERT INTO orderdetails VALUES (?, ?, ?, ?)");
			pstmt.setString(1, productCode);
			pstmt.setInt(2, quantityOrdered);
			pstmt.setFloat(3, priceEach);
			pstmt.setShort(4, orderLineNumber);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
