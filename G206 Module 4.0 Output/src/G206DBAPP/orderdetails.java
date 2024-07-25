package G206DBAPP;

public class orderdetails {
	private String productCode;
	private int quantityOrdered;
	private float priceEach;
	private short orderLineNumber;

	public orderdetails(String productCode, int quantityOrdered, float priceEach, short orderLineNumber) {
		setProductCode(productCode);
		setQuantityOrdered(quantityOrdered);
		setPriceEach(priceEach);
		setOrderLineNumber(orderLineNumber);
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
