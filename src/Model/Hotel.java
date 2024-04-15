package Model;

import Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String hotelName;
    private String address;
    private String email;
    private String phone;
    private int starRating;
    private String facilityFeatures;
    private String region;

    public Hotel() {
    }

    public Hotel(int id, String hotelName, String address, String email, String phone, int starRating, String facilityFeatures, String region) {
        this.id = id;
        this.hotelName = hotelName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.starRating = starRating;
        this.facilityFeatures = facilityFeatures;
        this.region = region;
    }

    public static ArrayList<Hotel> getList() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotels";

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Hotel obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setHotelName(rs.getString("hotel_name"));
                obj.setAddress(rs.getString("address"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone(rs.getString("phone"));
                obj.setStarRating(rs.getInt("star_rating"));
                obj.setFacilityFeatures(rs.getString("facility_features"));
                obj.setRegion(rs.getString("region"));
                hotelList.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    public static Hotel getHotelByID(int hotelID) {

        String query = "SELECT * FROM hotels WHERE id = ?";

        Hotel hotel = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotelID);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String hotelname = rs.getString("hotel_name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int starRating = rs.getInt("star_rating");
                String facilityFeatures = rs.getString("facility_features");
                String region = rs.getString("region");
                hotel = new Hotel(id, hotelname, address, email, phone, starRating, facilityFeatures, region);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotel;
    }

    public static boolean add(String hotelName, String address, String email, String phone, int starRating, String facilityFeatures, String region) {
        String query = "INSERT INTO hotels (hotel_name, address, email, phone, star_rating, facility_features, region) VALUES (?,?,?,?,?,?,?) ";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, hotelName);
            pr.setString(2, address);
            pr.setString(3, email);
            pr.setString(4, phone);
            pr.setInt(5, starRating);
            pr.setString(6, facilityFeatures);
            pr.setString(7, region);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean update(Hotel hotel) {
        String query = "UPDATE hotels SET hotel_name = ?, address = ?, email = ?, phone = ?, star_rating = ?, facility_features = ?, region = ? WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, hotel.getHotelName());
            pr.setString(2, hotel.getAddress());
            pr.setString(3, hotel.getEmail());
            pr.setString(4, hotel.getPhone());
            pr.setInt(5, hotel.getStarRating());
            pr.setString(6, hotel.getFacilityFeatures());
            pr.setString(7, hotel.getRegion());
            pr.setInt(8, hotel.getId());

            return pr.executeUpdate()!= -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static boolean delete(String selectRoomID) {
        String query = "DELETE FROM hotels WHERE id = ?";

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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public String getFacilityFeatures() {
        return facilityFeatures;
    }

    public void setFacilityFeatures(String facilityFeatures) {
        this.facilityFeatures = facilityFeatures;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
