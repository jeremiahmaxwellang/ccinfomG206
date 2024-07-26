package G206DBAPP;

/**
 * GROUP MEMBER 03: MARK RAMOS
 */

import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class SalesReportGenerator {
	   private static final String URL = "jdbc:mysql://mysql-176128-0.cloudclusters.net:10107/dbsales";
	    private static final String USER = "CCINFOM_G206";
	    private static final String PASSWORD = "DLSU1234";
	    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.00");
	    private static final SimpleDateFormat MONTH_YEAR_FORMAT = new SimpleDateFormat("MMMM yyyy");

	    public void generateSalesReport1() {
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
	            displayAvailableMonthsYears(connection);

	            Scanner scanner = new Scanner(System.in);
	            System.out.print("\nEnter month (1-12): ");
	            int month = scanner.nextInt();
	            System.out.print("Enter year (e.g., 2024): ");
	            int year = scanner.nextInt();

	            generateSalesReport(connection, month, year);

	            scanner.close();
				
				connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void displayAvailableMonthsYears(Connection connection) throws SQLException {
	        String sql = "SELECT DISTINCT YEAR(orderDate) AS year, MONTH(orderDate) AS month " +
	                     "FROM orders " +
	                     "ORDER BY year, month";
	        try (PreparedStatement statement = connection.prepareStatement(sql);
	             ResultSet resultSet = statement.executeQuery()) {

	            System.out.println("Available Months and Years for Sales Data:");
	            while (resultSet.next()) {
	                int year = resultSet.getInt("year");
	                int month = resultSet.getInt("month");
	                Calendar calendar = Calendar.getInstance();
	                calendar.set(Calendar.YEAR, year);
	                calendar.set(Calendar.MONTH, month - 1);
	                System.out.println("- " + MONTH_YEAR_FORMAT.format(calendar.getTime()));
	                
	                statement.close();
	            }
	        }
	    }

	    private static void generateSalesReport(Connection connection, int month, int year) throws SQLException {
	        String sql = "SELECT DAY(o.orderDate) AS day, o.status, SUM(od.quantityOrdered * od.priceEach) AS totalSales " +
	                     "FROM orders o " +
	                     "JOIN orderdetails od ON o.orderNumber = od.orderNumber " +
	                     "WHERE MONTH(o.orderDate) = ? AND YEAR(o.orderDate) = ? " +
	                     "GROUP BY DAY(o.orderDate), o.status " +
	                     "ORDER BY DAY(o.orderDate), o.status";

	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, month);
	            statement.setInt(2, year);
	            ResultSet resultSet = statement.executeQuery();

	            double totalSales = 0;
	            int currentDay = -1;
	            while (resultSet.next()) {
	                int day = resultSet.getInt("day");
	                String status = resultSet.getString("status");
	                double dailySales = resultSet.getDouble("totalSales");

	                if (day != currentDay) {
	                    if (currentDay != -1) {
	                        System.out.println();
	                    }
	                    currentDay = day;
	                    System.out.println("Day " + day + ":");
	                }

	                String formattedDailySales = DECIMAL_FORMAT.format(dailySales);
	                System.out.println("  Status: " + status + ", Sales: $" + formattedDailySales);
	                totalSales += dailySales;
	            }

	            Calendar calendar = Calendar.getInstance();
	            calendar.set(Calendar.YEAR, year);
	            calendar.set(Calendar.MONTH, month - 1);
	            String formattedMonthYear = MONTH_YEAR_FORMAT.format(calendar.getTime());

	            System.out.println("\nTotal Sales for " + formattedMonthYear + ": $" + DECIMAL_FORMAT.format(totalSales));
	            statement.close();
	        }
	    }
}
