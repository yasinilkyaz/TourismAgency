package Model;

import Helper.DBConnector;
import Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoomManagement {
    private int id;
    private int hotelID;
    private String roomName;
    private int stock;
    private int bedCount;
    private String television;
    private String minibar;
    private int hostelTypeID;
    private int periodID;
    private double price;
    private double priceChild;

    private Hotel hotel;
    private HostelType hostelType;
    private PeriodManagement periodManagement;
    private RoomPrice roomPrice;

    public RoomManagement() {
    }

    public RoomManagement(int id, int hotelID, String roomName, int stock, int bedCount, String television, String minibar, int hostelTypeID, int periodID, double price, double priceChild) {
        this.id = id;
        this.hotelID = hotelID;
        this.roomName = roomName;
        this.stock = stock;
        this.bedCount = bedCount;
        this.television = television;
        this.minibar = minibar;
        this.hostelTypeID = hostelTypeID;
        this.periodID = periodID;
        this.price = price;
        this.priceChild = priceChild;
        this.hotel = Hotel.getHotelByID(this.hotelID);
        this.hostelType = HostelType.getByID(this.hostelTypeID);
        this.periodManagement = PeriodManagement.getByID(this.periodID);
        this.roomPrice = RoomPrice.getInstance(hostelType.getHostelType(), periodID);
    }

    public static ArrayList<RoomManagement> getList() {
        String query = "SELECT * FROM room_management";
        ArrayList<RoomManagement> roomManagements = new ArrayList<>();
        RoomManagement obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                obj = new RoomManagement();
                obj.setId(rs.getInt("id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setRoomName(rs.getString("room_name"));
                obj.setStock(rs.getInt("stock_quantity"));
                obj.setBedCount(rs.getInt("bed_count"));
                obj.setTelevision(rs.getString("television"));
                obj.setMinibar(rs.getString("minibar"));
                obj.setHostelTypeID(rs.getInt("hostel_type_id"));
                obj.setPeriodID(rs.getInt("period_management_id"));
                obj.setPrice(rs.getDouble("price"));
                obj.setPriceChild(rs.getDouble("price_child"));
                roomManagements.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomManagements;
    }

    public static ArrayList<RoomManagement> getAvailableRooms(String hotelName, String region, LocalDate clientEntryDate, LocalDate clientLeaveDate) {
        int period;
        if (clientEntryDate != null && clientLeaveDate != null) {
            period = PeriodManagement.getPeriodForClientDates(clientEntryDate, clientLeaveDate);
        } else {
            period = -1;
        }
        System.out.println(period+" period no");
        List<RoomManagement> allRooms = getList();

        if (Objects.equals(hotelName, "") && Objects.equals(region, "")) {
            return (ArrayList<RoomManagement>) allRooms.stream()
                    .filter(room -> ( room.getPeriodID() == period) && room.getStock() > 0)
                    .collect(Collectors.toList());
        } else {
            List<RoomManagement> filteredRooms = allRooms.stream()
                    .filter(room -> ( room.getPeriodID() == period) &&
                            (room.getHotel() != null &&
                                    (room.getHotel().getHotelName() == null || room.getHotel().getHotelName().toLowerCase().contains(hotelName)) ||
                                    (room.getHotel().getRegion() == null || room.getHotel().getRegion().toLowerCase().contains(region))) &&
                            room.getStock() > 0
                    )
                    .collect(Collectors.toList());

            // Kontrol: Filtrelenmiş odaların içinde otel bulunmuyorsa boş liste döndür
            if (filteredRooms.isEmpty()) {
                return new ArrayList<>();
            } else {
                return (ArrayList<RoomManagement>) filteredRooms;
            }
        }
    }



    public static RoomManagement getroom(int roomID) {
        String query = "SELECT * FROM room_management WHERE id=?";

        RoomManagement obj;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, roomID);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                obj = new RoomManagement();
                obj.setId(rs.getInt("id"));
                obj.setHotelID(rs.getInt("hotel_id"));
                obj.setRoomName(rs.getString("room_name"));
                obj.setStock(rs.getInt("stock_quantity"));
                obj.setBedCount(rs.getInt("bed_count"));
                obj.setTelevision(rs.getString("television"));
                obj.setMinibar(rs.getString("minibar"));
                obj.setHostelTypeID(rs.getInt("hostel_type_id"));
                obj.setPeriodID(rs.getInt("period_management_id"));
                obj.setPrice(rs.getDouble("price"));
                obj.setPriceChild(rs.getDouble("price_child"));
                return obj;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static boolean add(int hotelID, String roomName, int stock, int bedCount, String television, String minibar, int hostelTypeID, int periodID, double price, double priceChild) {

        for (RoomManagement obj : getList()) {
            if (obj.getHotelID() == hotelID && roomName.equals(obj.roomName) && bedCount == obj.getBedCount() && television.equals(obj.getTelevision()) && minibar.equals(obj.getMinibar()) && hostelTypeID == obj.getHostelTypeID() && periodID == obj.getPeriodID()) {
                Helper.showMsg("Sistemde seçilen özelliklere sahip oda bulunmaktadır. Stoğu veya Ücretleri güncelleyiniz");
                return false;
            }
        }
        String query = "INSERT INTO room_management (hotel_id,room_name,stock_quantity,bed_count,television,minibar,hostel_type_id,period_management_id,price,price_child) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotelID);
            pr.setString(2, roomName);
            pr.setInt(3, stock);
            pr.setInt(4, bedCount);
            pr.setString(5, television);
            pr.setString(6, minibar);
            pr.setInt(7, hostelTypeID);
            pr.setInt(8, periodID);
            pr.setDouble(9, price);
            pr.setDouble(10, priceChild);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean update(int id, int hotelID, String roomName, int stock, int bedCount, String television, String minibar, int hostelTypeId, int periodID, double price, double priceChild) {
        String query = "UPDATE room_management SET hotel_id=?, room_name=?, stock_quantity=?, bed_count=?, television=?, minibar=?, hostel_type_id=?, period_management_id=?, price=?, price_child=? WHERE id=?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotelID);
            pr.setString(2, roomName);
            pr.setInt(3, stock);
            pr.setInt(4, bedCount);
            pr.setString(5, television);
            pr.setString(6, minibar);
            pr.setInt(7, hostelTypeId);
            pr.setInt(8, periodID);
            pr.setDouble(9, price);
            pr.setDouble(10, priceChild);
            pr.setInt(11, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean delete(String selectRoomID) {
        String query = "DELETE FROM room_management WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, Integer.parseInt(selectRoomID));
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean decreaseStock(int roomID) {
        String query = "UPDATE room_management SET stock_quantity = stock_quantity - 1 WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, roomID);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean increaseStock(int roomID) {
        String query = "UPDATE room_management SET stock_quantity = stock_quantity + 1 WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, roomID);
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    public String getTelevision() {
        return television;
    }

    public void setTelevision(String television) {
        this.television = television;
    }

    public String getMinibar() {
        return minibar;
    }

    public void setMinibar(String minibar) {
        this.minibar = minibar;
    }

    public int getHostelTypeID() {
        return hostelTypeID;
    }

    public void setHostelTypeID(int hostelTypeID) {
        this.hostelTypeID = hostelTypeID;
    }

    public int getPeriodID() {
        return periodID;
    }

    public void setPeriodID(int periodID) {
        this.periodID = periodID;
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

    public Hotel getHotel() {
        return Hotel.getHotelByID(hotelID);
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public HostelType getHostelType() {
        return HostelType.getByID(hostelTypeID);
    }

    public void setHostelType(HostelType hostelType) {
        this.hostelType = hostelType;
    }

    public PeriodManagement getPeriodManagement() {
        return periodManagement;
    }

    public void setPeriodManagement(PeriodManagement periodManagement) {
        this.periodManagement = periodManagement;
    }
}
