package G206DBAPP;

import java.sql.*;
import java.util.Scanner;

public class EmployeeManager {
	private static final String URL = "jdbc:mysql://mysql-176128-0.cloudclusters.net:10107/dbsales";
    private static final String USER = "CCINFOM_G206";
    private static final String PASSWORD = "DLSU1234";
    
	//local test connection
//	private static final String URL = "jdbc:mysql://localhost:3306/dbsales";
//    private static final String USER = "root";
//    private static final String PASSWORD = "CCINFOMS12";

//    "jdbc:mysql://localhost:3306/dbsales?useTimezone=true&serverTimezone=UTC&user=root&password=CCINFOMS12"
	public int menu() {
		int menuchoice = 0;
		Scanner scan = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);
		
		int validInput = 0;
		while(validInput == 0) {
			System.out.println("=======================================================");
			System.out.println("    Employee Management Menu							   ");
			System.out.println("-------------------------------------------------------");
            System.out.println("1. Create a new Employee");
            System.out.println("2. Update an Employee");
            System.out.println("3. Delete an Employee");
            System.out.println("4. Resign an Employee");
            System.out.println("5. View Employee details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: \n");
			
			try {
				menuchoice = Integer.parseInt(scan.nextLine());
				
				if(menuchoice < 1 || menuchoice > 6) 
					System.out.println("INVALID INPUT.");
				
				else validInput = 1;
			} 
			catch (NumberFormatException e) {
				System.out.println("INVALID INPUT.");
	        
			}
		}

		try {
			if (menuchoice == 1) {
				createEmployee(scanner, scan);
			} 
			
			else if (menuchoice == 2) {			
				updateEmployee(scanner, scan);
			} 
			
			else if (menuchoice == 3) {			
				deleteEmployee(scanner);
			} 
			
			else if (menuchoice == 4) {			
				resignEmployee(scanner);
			} 
			
			else if (menuchoice == 5) {			
				viewEmployee(scanner);
			} 
			
			else if (menuchoice == 6) {			
				System.out.println("Exiting...");
			} 
		}
		
		catch (SQLException e) {
            e.printStackTrace();
        }
		
		

		return menuchoice;
	}
	

    private static void createEmployee(Scanner scanner, Scanner scan) throws SQLException {
        System.out.print("Enter employee number: ");
        int employeeNumber = scanner.nextInt();
        System.out.print("Enter last name: ");
        String lastName = scan.next();
        System.out.print("Enter first name: ");
        String firstName = scan.next();
        System.out.print("Enter extension: ");
        String extension = scan.next();
        System.out.print("Enter email: ");
        String email = scan.next();
        System.out.print("Enter office code: ");
        String officeCode = scan.next();
        System.out.print("Enter reports to (employee number, or 0 if none): ");
        int reportsTo = scanner.nextInt();
        System.out.print("Enter job title: ");
        String jobTitle = scan.next();

        Integer supervisor = (reportsTo == 0) ? null : reportsTo;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Check if reportsTo employee exists
            if (supervisor != null) {
                try (PreparedStatement checkSupervisor = connection.prepareStatement(
                        "SELECT employeeNumber FROM employees WHERE employeeNumber = ?")) {
                    checkSupervisor.setInt(1, supervisor);
                    ResultSet resultSet = checkSupervisor.executeQuery();
                    if (!resultSet.next()) {
                        System.out.println("\nSupervisor with employee number " + reportsTo + " does not exist.\n");
                        return;
                    }
                }
            }

            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
                statement.setInt(1, employeeNumber);
                statement.setString(2, lastName);
                statement.setString(3, firstName);
                statement.setString(4, extension);
                statement.setString(5, email);
                statement.setString(6, officeCode);
                if (supervisor == null) {
                    statement.setNull(7, Types.INTEGER);
                } else {
                    statement.setInt(7, supervisor);
                }
                statement.setString(8, jobTitle);
                statement.executeUpdate();
                System.out.println("\nEmployee created.\n");
            }
        }
    }

    public void updateEmployee(Scanner scanner, Scanner scan) throws SQLException {
        System.out.print("Enter employee number to update: ");
        int employeeNumber = scanner.nextInt();
        System.out.print("Enter new last name: ");
        String lastName = scan.next();
        System.out.print("Enter new first name: ");
        String firstName = scan.next();
        System.out.print("Enter new extension: ");
        String extension = scan.next();
        System.out.print("Enter new email: ");
        String email = scan.next();
        System.out.print("Enter new office code: ");
        String officeCode = scan.next();
        System.out.print("Enter new reports to (employee number): ");
        int reportsTo = scanner.nextInt();
        System.out.print("Enter new job title: ");
        String jobTitle = scan.next();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE employees SET lastName = ?, firstName = ?, extension = ?, email = ?, officeCode = ?, reportsTo = ?, jobTitle = ? WHERE employeeNumber = ?")) {
            statement.setString(1, lastName);
            statement.setString(2, firstName);
            statement.setString(3, extension);
            statement.setString(4, email);
            statement.setString(5, officeCode);
            statement.setInt(6, reportsTo);
            statement.setString(7, jobTitle);
            statement.setInt(8, employeeNumber);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("\nEmployee updated.\n");
            } else {
                System.out.println("\nEmployee not found or resigned.\n");
            }
        }
    }

    public void deleteEmployee(Scanner scanner) throws SQLException {
        System.out.print("Enter employee number to delete: ");
        int employeeNumber = scanner.nextInt();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM employees WHERE employeeNumber = ?")) {
            statement.setInt(1, employeeNumber);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("\nEmployee deleted.\n");
            } else {
                System.out.println("\nEmployee not found or already resigned.\n");
            }
        }
    }

    public void resignEmployee(Scanner scanner) throws SQLException {
        System.out.print("Enter employee number to resign: ");
        int employeeNumber = scanner.nextInt();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement updateEmployee = connection.prepareStatement(
                     "UPDATE employees SET jobTitle = 'Resigned' WHERE employeeNumber = ?");
             PreparedStatement updateCustomers = connection.prepareStatement(
                     "UPDATE customers SET employeeNumber = NULL WHERE employeeNumber = ?")) {
            connection.setAutoCommit(false);
            updateEmployee.setInt(1, employeeNumber);
            int rowsUpdated = updateEmployee.executeUpdate();
            if (rowsUpdated > 0) {
                updateCustomers.setInt(1, employeeNumber);
                updateCustomers.executeUpdate();
                connection.commit();
                System.out.println("\nEmployee resigned.\n");
            } else {
                connection.rollback();
                System.out.println("\nEmployee not found or already resigned.\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to resign employee.");
        }
    }

    public void viewEmployee(Scanner scanner) throws SQLException {
        System.out.print("Enter employee number to view: ");
        int employeeNumber = scanner.nextInt();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE employeeNumber = ?")) {
            statement.setInt(1, employeeNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int employeeNum = resultSet.getInt("employeeNumber");
                String lastName = resultSet.getString("lastName");
                String firstName = resultSet.getString("firstName");
                String extension = resultSet.getString("extension");
                String email = resultSet.getString("email");
                String officeCode = resultSet.getString("officeCode");
                int reportsTo = resultSet.getInt("reportsTo");
                String jobTitle = resultSet.getString("jobTitle");
                Employee employee = new Employee(employeeNum, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle);
                System.out.println(employee);
            } else {
                System.out.println("\nEmployee not found.\n");
            }
        }
    }
}
