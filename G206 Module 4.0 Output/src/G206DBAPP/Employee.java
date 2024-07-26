package G206DBAPP;

/**
 * GROUP MEMBER 03: MARK RAMOS
 */
public class Employee {
	int employeeNumber;
    String lastName;
    String firstName;
    String extension;
    String email;
    String officeCode;
    int reportsTo;
    String jobTitle;

    Employee(int employeeNumber, String lastName, String firstName, String extension, String email,
             String officeCode, int reportsTo, String jobTitle) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.extension = extension;
        this.email = email;
        this.officeCode = officeCode;
        this.reportsTo = reportsTo;
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return String.format("\nEmployee Number: %d\nLast Name: %s\nFirst Name: %s\nExtension: %s\nEmail: %s\nOffice Code: %s\nReports To: %d\nJob Title: %s\n",
                employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle);
    }
}
