package G206DBAPP;

/**
 * GROUP MEMBER 01: JEREMIAH MAXWELL ANG
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class orderdetails {
	private int orderNumber;
	private String productCode;
	private int quantityOrdered;
	private float priceEach;
	private short orderLineNumber;

	//Constructor for creating a new orderdetail record
	public orderdetails(String productCode, int quantityOrdered, float priceEach, short orderLineNumber) {
		orderNumber = 0;
		setProductCode(productCode);
		setQuantityOrdered(quantityOrdered);
		setPriceEach(priceEach);
		setOrderLineNumber(orderLineNumber);
	}
	
	//Constructor used for searching a specific ordered product
    public orderdetails(int orderNumber, String productCode) {
		setOrderNumber(orderNumber);
		setProductCode(productCode);
		setQuantityOrdered(0);
		setPriceEach(0);
		setOrderLineNumber((short) 1);
	}
    

//	public int viewOrderDetails() {
//		int recordcount = 0;
//		try {
//			Connection conn;
//			conn = DriverManager.getConnection("jdbc:mysql://mysql-176128-0.cloudclusters.net:10107/dbsales?useTimezone=true&serverTimezone=UTC&user=CCINFOM_G206&password=DLSU1234");
//			System.out.println("Connection to DB successful");
//			
//			PreparedStatement pstmt = conn.prepareStatement("SELECT FROM orderDetails WHERE orderNumber=? AND productCode=?");
//			pstmt.setInt(1, orderNumber);
//			pstmt.setString(2, productCode);
//			
//			System.out.println("SQL Statement Prepared");
//			
//			ResultSet rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				recordcount++;
//				quantityOrdered = rs.getInt("quantityOrdered");
//				priceEach = rs.getFloat("priceEach");
//				orderLineNumber = rs.getShort("orderLineNumber");
////				System.out.println("Record was Retrieved");
//			}
//
//			
//			pstmt.close();
//			conn.close();
//			return recordcount;
//		}
//		
//		catch (Exception e) {
//			System.out.println(e.getMessage());
//			return 0;
//		}
//	}
	


	// Getter and Setter for orderNumber
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    
	 // Getter and Setter for productCode
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    // Getter and Setter for quantityOrdered
    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    // Getter and Setter for priceEach
    public float getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(float priceEach) {
        this.priceEach = priceEach;
    }

    // Getter and Setter for orderLineNumber
    public short getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setOrderLineNumber(short orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }
}
