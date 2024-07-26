package G206DBAPP;

/**
 * GROUP MEMBER 01: JEREMIAH MAXWELL ANG
 */

import java.sql.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class product_management {
	private String  productCode;
	private String 	productName;
	private String  productLine;
	private String	productScale;
	private String	productDescription;
	private String	productVendor;
	private int		quantityInStock;
	private float	buyPrice;
	private float	MSRP;
	private ArrayList<String> discontinuedProducts = new ArrayList<String>();
	
	private String connectionString = "jdbc:mysql://mysql-176128-0.cloudclusters.net:10107/dbsales?useTimezone=true&serverTimezone=UTC&user=CCINFOM_G206&password=DLSU1234";
	
	
	public product_management() {
		productCode 		= "";
		productName			= "";
		productLine 		= "";
		productScale 		= "";
		productDescription  = "";
		productVendor		= "";
		quantityInStock		= 0;
		buyPrice			= 0;
		MSRP				= 0;
	}
	
	public void addproduct() {
		try {
			Connection conn;
			conn = DriverManager.getConnection(connectionString);
			System.out.println("Connection to DB successful");
			
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO products VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, productCode);
			pstmt.setString(2, productName);
			pstmt.setString(3, productLine);
			pstmt.setString(4, productScale);
			
			pstmt.setString(5, productVendor);
			pstmt.setString(6, productDescription);
			pstmt.setInt   (7, quantityInStock);
			
			BigDecimal roundedBuyPrice = new BigDecimal(buyPrice).setScale(2, RoundingMode.HALF_UP);
			pstmt.setBigDecimal(8, roundedBuyPrice);
			
			BigDecimal roundedMSRP = new BigDecimal(MSRP).setScale(2, RoundingMode.HALF_UP);
			pstmt.setBigDecimal(9, roundedMSRP);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int updateproduct() {
		try {
			Connection conn;
			conn = DriverManager.getConnection(connectionString);
			System.out.println("Connection to DB successful");
			
			PreparedStatement pstmt = conn.prepareStatement("UPDATE products SET productName=?, productLine=?, productScale=?, productVendor=?, productDescription=?, quantityInStock=?, buyPrice=?, MSRP=? WHERE productCode=?");
			pstmt.setString(9, productCode);
			pstmt.setString(1, productName);
			pstmt.setString(2, productLine);
			pstmt.setString(3, productScale);
			
			pstmt.setString(4, productVendor);
			pstmt.setString(5, productDescription);
			pstmt.setInt   (6, quantityInStock);
			
			BigDecimal roundedBuyPrice = new BigDecimal(buyPrice).setScale(2, RoundingMode.HALF_UP);
			pstmt.setBigDecimal(7, roundedBuyPrice);

			BigDecimal roundedMSRP = new BigDecimal(MSRP).setScale(2, RoundingMode.HALF_UP);
			pstmt.setBigDecimal(8, roundedMSRP);
			
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
	
	public void deleteproduct() {
		try {
			Connection conn;
			conn = DriverManager.getConnection(connectionString);
			System.out.println("Connection to DB successful");
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM products WHERE productCode=?");
			pstmt.setString(1, productCode);
			System.out.println("SQL Statement Prepared");
			
			pstmt.executeUpdate();
			System.out.println("Record was deleted");
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void discontinueproduct() {
		discontinuedProducts.add(productCode);
	}
	
	public int viewproduct() {
		int recordcount = 0;
		try {
			Connection conn;
			conn = DriverManager.getConnection(connectionString);
			System.out.println("Connection to DB successful");
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM products WHERE productCode=?");
			pstmt.setString(1, productCode);
			
			System.out.println("SQL Statement Prepared");
			
			ResultSet rs = pstmt.executeQuery();
			
			//Copy record from db
			while(rs.next()) {
				recordcount++;
				productName = rs.getString("productName");
				productLine = rs.getString("productLine");
				productScale = rs.getString("productScale");
				productDescription = rs.getString("productDescription");
				productVendor = rs.getString("productVendor");
				quantityInStock = rs.getInt("quantityInStock");
				buyPrice = rs.getFloat("buyPrice");
				MSRP = rs.getFloat("MSRP");
				System.out.println("Record of the product " + productCode + " exists\n");
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
	
	//====GETTERS====
	
	public String getProductCode() {
        return productCode;
    }
	
	public String getProductName() {
        return productName;
    }

    public String getProductLine() {
        return productLine;
    }

    public String getProductScale() {
        return productScale;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductVendor() {
        return productVendor;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public float getMSRP() {
        return MSRP;
    }
    
    public ArrayList<String> getDiscontinuedProducts() {
        return discontinuedProducts;
    }

    //====SETTERS====
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public void setProductScale(String productScale) {
        this.productScale = productScale;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductVendor(String productVendor) {
        this.productVendor = productVendor;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setMSRP(float MSRP) {
        this.MSRP = MSRP;
    }
    
    
}
