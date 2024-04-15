package Model;

import Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class HostelType {
    private int id;
    private int hotelID;
    private String hostelType;

    private Hotel hotel;

    public HostelType() {
    }

    public HostelType(int id, int hotelID, String hostelType) {
        this.id = id;
        this.hotelID = hotelID;
        this.hostelType = hostelType;
        this.hotel = Hotel.getHotelByID(hotelID);

    }

    public static ArrayList<HostelType> getList() {
        ArrayList<HostelType> hostelTypes = new ArrayList<>();
        String query = "SELECT * FROM hostel_type";

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            HostelType obj;

            while (rs.next()) {
                obj = new HostelType();
                obj.setId(rs.getInt("id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setHostelType(rs.getString("hostel_type_name"));
                hostelTypes.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Collections.sort(hostelTypes, Comparator.comparingInt(HostelType::getHotelID));
        return hostelTypes;
    }
    public static HostelType getByID(int hostelTypeID){
        String query="SELECT * FROM hostel_type WHERE id = ?";
        HostelType obj=null;
        try {
            PreparedStatement pr=DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hostelTypeID);
            ResultSet rs=pr.executeQuery();
            while (rs.next()){
                obj=new HostelType();
                obj.setId(rs.getInt("id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setHostelType(rs.getString("hostel_type_name"));
                break;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static boolean add(int hotelID, String hostelType) {
        ArrayList<HostelType> hostelTypes=HostelType.getList();
        for (HostelType obj: hostelTypes){
            if(obj.getHotelID()==hotelID && Objects.equals(obj.getHostelType(), hostelType)){
                return false;
            }
        }

        String query="INSERT INTO hostel_type (hotel_id,hostel_type_name) VALUES (?,?)";

        try {
            PreparedStatement pr=DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelID);
            pr.setString(2,hostelType);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<HostelType> getListByHotelID(int hotelID){
        ArrayList<HostelType> hostelTypes=new ArrayList<>();
        HostelType obj=null;
        String query="SELECT * FROM hostel_type WHERE hotel_id = ?";

        try {
            PreparedStatement pr=DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelID);
            ResultSet rs=pr.executeQuery();

            while (rs.next()){
                obj=new HostelType();
                obj.setId(rs.getInt("id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setHostelType(rs.getString("hostel_type_name"));
                hostelTypes.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hostelTypes;
    }

    public static boolean delete(String selectRoomID) {
        String query = "DELETE FROM hostel_type WHERE id = ?";

        try {
            PreparedStatement pr=DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,Integer.parseInt(selectRoomID));
            return pr.executeUpdate() != -1;
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

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getHostelType() {
        return hostelType;
    }

    public void setHostelType(String hostelType) {
        this.hostelType = hostelType;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
