package G206DBAPP;

/**
 * MEMBER 02: EULYSIS DIMAILIG
 */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;

public class payment_transaction {
	int customerNumber;
	String checkNumber;
	Timestamp paymentDate;
	float amount;
	
//	Cloud connection
	private String connectionString = "jdbc:mysql://mysql-176128-0.cloudclusters.net:10107/dbsales?useTimezone=true&serverTimezone=UTC&user=CCINFOM_G206&password=DLSU1234";
		
//	Local connection test
		// private String connectionString = "jdbc:mysql://localhost:3306/dbsales?useTimezone=true&serverTimezone=UTC&user=root&password=CCINFOMS12";
	

	payment_transaction(){
		customerNumber = 0;
		checkNumber = "";
		getCurrentDatetime(); //set paymentDate to today
		amount = 0;
	}
	
	public void create_payment() {
		try {
			Connection conn;
			conn = DriverManager.getConnection(connectionString);
			System.out.println("Connection to DB successful");
			
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO payments VALUES (?, ?, ?, ?)");
			pstmt.setInt(1, customerNumber);
			pstmt.setString(2, checkNumber);
			pstmt.setTimestamp(3, paymentDate);
			
			BigDecimal roundedAmount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
			pstmt.setBigDecimal(4, roundedAmount);
			

			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int update_payment() {
			try {
				Connection conn;
				conn = DriverManager.getConnection(connectionString);
				System.out.println("Connection to DB successful");
				
				PreparedStatement pstmt = conn.prepareStatement("UPDATE payments SET paymentDate=?, amount=? WHERE customerNumber=? AND checkNumber=?");
				pstmt.setTimestamp(1, paymentDate);
				pstmt.setFloat(2, amount);
				pstmt.setInt(3, customerNumber);
				pstmt.setString(4, checkNumber);
				
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
	
	public void delete_payment() {
		try {
			Connection conn;
			conn = DriverManager.getConnection(connectionString);
			System.out.println("Connection to DB successful");
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM payments WHERE customerNumber=? AND checkNumber=?");
			pstmt.setInt(1, customerNumber);
			pstmt.setString(2, checkNumber);
			System.out.println("SQL Statement Prepared");
			
			pstmt.executeUpdate();
			System.out.println("Record was deleted");
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
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
				paymentDate = rs.getTimestamp("CURRENT_TIMESTAMP");

				System.out.println("Current DATETIME: " + paymentDate + "\n\n");
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
	
	public int get_payment() {
		try {
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://mysql-176128-0.cloudclusters.net:10107/dbsales?useTimezone=true&serverTimezone=UTC&user=CCINFOM_G206&password=DLSU1234");
			System.out.println("Connection to DB successful");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM payments WHERE customerNumber = ? AND checkNumber = ?");
			pstmt.setInt   (1, customerNumber);
			pstmt.setString  (2, checkNumber);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				paymentDate = rs.getTimestamp("paymentDate");
				amount = rs.getFloat("amount");

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