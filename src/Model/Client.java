package Model;

import Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Client {
    private int id;
    private int list_id;
    private String nameSurname;
    private String phone;
    private String email;
    private String clientType;

    public Client() {
    }

    public Client(int id, int list_id, String nameSurname, String phone, String email,String clientType) {
        this.id = id;
        this.list_id = list_id;
        this.nameSurname = nameSurname;
        this.phone = phone;
        this.email = email;
        this.clientType=clientType;
    }
    public static int generateListId() {


        int listId = 1; // Varsayılan değer: 1
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {


            // Mevcut en yüksek list_id'yi sorgulayın
            String query = "SELECT MAX(list_id) FROM clients";
            statement = DBConnector.getInstance().prepareStatement(query);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                int maxListId = resultSet.getInt(1);
                listId = maxListId + 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Veritabanı kaynaklarını kapatın
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println(listId+"liste id");
        return listId;
    }
    public static ArrayList<Client> getListByListID(int list_id){
        ArrayList<Client> clients=new ArrayList<>();
        Client obj;
        String query="SELECT * FROM clients WHERE list_id=?";

        try {
            PreparedStatement pr= DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,list_id);
            ResultSet rs=pr.executeQuery();
            while (rs.next()){
                obj=new Client();
                obj.setId(rs.getInt("id"));
                obj.setList_id(rs.getInt("list_id"));
                obj.setNameSurname(rs.getString("name_surname"));
                obj.setPhone(rs.getString("phone"));
                obj.setEmail(rs.getString("email"));
                obj.setClientType(rs.getString("client_type"));
                clients.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }
    public static boolean addClientsToDatabase(ArrayList<Client> clients) {

          try {
                for (Client client : clients) {
                String query = "INSERT INTO clients (list_id, name_surname, phone, email, client_type) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement=DBConnector.getInstance().prepareStatement(query);
                statement.setInt(1, client.getList_id());
                statement.setString(2, client.getNameSurname());
                statement.setString(3, client.getPhone());
                statement.setString(4, client.getEmail());
                statement.setString(5, client.getClientType());

                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
          return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getList_id() {
        return list_id;
    }

    public void setList_id(int list_id) {
        this.list_id = list_id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
}
