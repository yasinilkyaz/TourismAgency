package Model;

import Helper.DBConnector;
import Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;

    public User() {
    }

    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static ArrayList<User> getList(){
        ArrayList<User> userList=new ArrayList<>();
        String query="SELECT * FROM users";

        try {
            Statement st= DBConnector.getInstance().createStatement();
            ResultSet rs= st.executeQuery(query);
            User obj;
            while (rs.next()){
                obj=new User();
                obj.setId(rs.getInt("id"));
                obj.setUsername(rs.getString("user_name"));
                obj.setPassword(rs.getString("password"));
                obj.setRole(rs.getString("role"));
                userList.add(obj);
            }
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return userList;
    }

    public static User getFetch(String username,String password){
        User obj = null;
        String query="SELECT * FROM users WHERE user_name = ? AND password = ?";


        try {
            PreparedStatement pr= DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2,password);
            ResultSet rs=pr.executeQuery();
            while(rs.next()){
                switch (rs.getString("Role")){
                    case "admin":
                        obj=new Admin();
                        break;
                    case "employee":
                        obj=new Employee();
                        break;
                }
                obj.setId(rs.getInt("id"));
                obj.setUsername(rs.getString("user_name"));
                obj.setPassword(rs.getString("password"));
                obj.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }
    public static User getFetch(String username){
        User obj = null;
        String query="SELECT * FROM users WHERE user_name = ? ";


        try {
            PreparedStatement pr= DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, username);

            ResultSet rs=pr.executeQuery();
            while(rs.next()){
                switch (rs.getString("Role")){
                    case "admin":
                        obj=new Admin();
                        break;
                    case "employee":
                        obj=new Employee();
                        break;
                }
                obj.setId(rs.getInt("id"));
                obj.setUsername(rs.getString("user_name"));
                obj.setPassword(rs.getString("password"));
                obj.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }
    public static boolean add(String username, String password, String role) {
        String query="INSERT INTO users (user_name,password,role) VALUES(?,?,?)";
        User findUser=User.getFetch(username);
        if (findUser!=null){
            Helper.showMsg("Bu kullanıcı adı kullanılmaktadır. Lütfen farklı bir kullanıcı adı giriniz.");
            return false;
        }

        try {
            PreparedStatement pr=DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);
            pr.setString(3,role);

            int response=pr.executeUpdate();
            if (response == -1)
            {
                Helper.showMsg("error");
            }
            return response != -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
