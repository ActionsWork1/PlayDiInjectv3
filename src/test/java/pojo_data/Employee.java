package pojo_data;

public class Employee {


    private String FirstName;
    private String LastName;
    private String EmployeeID;
    private String UserName;
    private String Password;



    public Employee(String FirstName,
                    String LastName,
                    String EmployeeID,
                    String UserName,
                    String Password)
    {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.EmployeeID = EmployeeID;
        this.UserName = UserName;
        this.Password = Password;
    }

    public Employee(String FirstName,
                    String LastName,
                    String EmployeeID)
    {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.EmployeeID = EmployeeID;

    }
    public Employee(){}

    // Getters
    public String getFirstName() { return FirstName; }
    public String getLastName() { return LastName; }
    public String getEmployeeID() { return EmployeeID; }
    public String getUserName() { return UserName; }
    public String getPassword() { return Password; }

    //Setters
    public void setFirstName(String FirstName) { this.FirstName = FirstName; }
    public void setLastName(String LastName) { this.LastName = LastName; }
    public void setEmployeeID(String EmployeeID) { this.EmployeeID = EmployeeID; }
    public void setUserName(String UserName) { this.UserName = UserName; }
    public void setPassword(String Password) { this.Password = Password; }


}


