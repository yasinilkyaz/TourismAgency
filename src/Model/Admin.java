package Model;

public class Admin extends User {
    public Admin() {
    }

    public Admin(int id, String username, String password, String role) {
        super(id, username, password, role);
    }
}
