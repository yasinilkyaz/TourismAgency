package Model;

public class Employee extends User{
    public Employee() {
    }

    public Employee(int id, String username, String password, String role) {
        super(id, username, password, role);
    }
}
