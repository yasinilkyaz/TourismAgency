package Model;

import Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomPrice {
    private int id;
    private int periodID;
    private String hostelTypeName;
    private double price;
    private double priceChild;

    public RoomPrice() {
    }

    public RoomPrice(int id, int periodID, String hostelTypeName, double price, double priceChild) {
        this.id = id;
        this.periodID = periodID;
        this.hostelTypeName = hostelTypeName;
        this.price = price;
        this.priceChild = priceChild;
    }

    public static ArrayList<RoomPrice> getList(){

        ArrayList<RoomPrice> roomPrices=new ArrayList<>();
        RoomPrice obj=null;
        String query="SELECT * FROM room_price";
        try {
            PreparedStatement pr= DBConnector.getInstance().prepareStatement(query);
            ResultSet rs= pr.executeQuery();
            while (rs.next()){
                obj=new RoomPrice();
                obj.setId(rs.getInt("id"));
                obj.setPeriodID(rs.getInt("period_id"));
                obj.setHostelTypeName(rs.getString("hostel_type_name"));
                obj.setPrice(rs.getDouble("price"));
                obj.setPriceChild(rs.getDouble("price_child"));
                roomPrices.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomPrices;
    }
    public static RoomPrice getInstance(String hostel_type_name,int periodID){

        RoomPrice obj=null;
        String query="SELECT * FROM room_price WHERE hostel_type_name=? AND period_id=?";
        try {
            PreparedStatement pr= DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,hostel_type_name);
            pr.setInt(2,periodID);
            ResultSet rs= pr.executeQuery();
            while (rs.next()){
                obj=new RoomPrice();
                obj.setId(rs.getInt("id"));
                obj.setPeriodID(rs.getInt("period_id"));
                obj.setHostelTypeName(rs.getString("hostel_type_name"));
                obj.setPrice(rs.getDouble("price"));
                obj.setPriceChild(rs.getDouble("price_child"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    public static boolean update(int id, double price, double priceChild) {
        String query="UPDATE room_price SET price=? , price_child=? WHERE id=?";

        try {
            PreparedStatement pr=DBConnector.getInstance().prepareStatement(query);
            pr.setDouble(1,price);
            pr.setDouble(2,priceChild);
            pr.setInt(3,id);
            return pr.executeUpdate() !=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addNewPriceForPeriodID(int lastID) {//Kendime not:Tabloya almalıydın.
        String query="INSERT INTO room_price (period_id,hostel_type_name,price,price_child) VALUES (?,?,0,0)";
        try  {
            PreparedStatement pr1 = DBConnector.getInstance().prepareStatement(query);
            pr1.setInt(1,lastID);
            pr1.setString(2,"Ultra Herşey Dahil");

            PreparedStatement pr2=DBConnector.getInstance().prepareStatement(query);
            pr2.setInt(1,lastID);
            pr2.setString(2,"Herşey Dahil");

            PreparedStatement pr3=DBConnector.getInstance().prepareStatement(query);
            pr3.setInt(1,lastID);
            pr3.setString(2,"Oda Kahvaltı");

            PreparedStatement pr4=DBConnector.getInstance().prepareStatement(query);
            pr4.setInt(1,lastID);
            pr4.setString(2,"Tam Pansiyon");

            PreparedStatement pr5=DBConnector.getInstance().prepareStatement(query);
            pr5.setInt(1,lastID);
            pr5.setString(2,"Yarım Pansiyon");

            PreparedStatement pr6=DBConnector.getInstance().prepareStatement(query);
            pr6.setInt(1,lastID);
            pr6.setString(2,"Sadece Yatak");

            PreparedStatement pr7=DBConnector.getInstance().prepareStatement(query);
            pr7.setInt(1,lastID);
            pr7.setString(2,"Alkol Hariç Full credit");

                return pr1.executeUpdate()!=-1 && pr2.executeUpdate() !=-1 && pr3.executeUpdate()!=-1 && pr4.executeUpdate()!=-1 && pr5.executeUpdate()!=-1 && pr6.executeUpdate()!=-1 &&pr7.executeUpdate() !=-1;
        }catch (Exception err){
            return false;
        }
    }

    public static boolean deletePriceByPeriodID(int deletePeriodID) {
        String query="DELETE FROM room_price WHERE period_id = ?";

        try {
            PreparedStatement pr= DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,deletePeriodID);
            return pr.executeUpdate()!=-1;
        } catch (SQLException e) {
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeriodID() {
        return periodID;
    }

    public void setPeriodID(int periodID) {
        this.periodID = periodID;
    }

    public String getHostelTypeName() {
        return hostelTypeName;
    }

    public void setHostelTypeName(String hostelTypeName) {
        this.hostelTypeName = hostelTypeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceChild() {
        return priceChild;
    }

    public void setPriceChild(double priceChild) {
        this.priceChild = priceChild;
    }
}
