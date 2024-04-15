package Model;

import Helper.DBConnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Reservation {
    private int id;
    private int userID;
    private int roomID;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int adultCount;
    private int childCount;
    private double totalPrice;
    private int clients_id;

    public Reservation() {
    }

    public Reservation(int id, int userID, int roomID, LocalDate checkInDate, LocalDate checkOutDate, int adultCount, int childCount, double totalPrice, int clients_id) {
        this.id = id;
        this.userID = userID;
        this.roomID = roomID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.totalPrice = totalPrice;
        this.clients_id = clients_id;
    }

    public Reservation(int userID, int roomID, LocalDate checkInDate, LocalDate checkOutDate, int adultCount, int childCount, double totalPrice, int clients_id) {
        this.userID = userID;
        this.roomID = roomID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.totalPrice = totalPrice;
        this.clients_id = clients_id;
    }

    public static ArrayList<Reservation> getlist() {
        String query = "SELECT * FROM reservations";
        ArrayList<Reservation> reservations = new ArrayList<>();
        Reservation obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Reservation();
                obj.setId(rs.getInt("id"));
                obj.setUserID(rs.getInt("user_id"));
                obj.setRoomID(rs.getInt("room_id"));
                obj.setCheckInDate(rs.getDate("check_in_date").toLocalDate());
                obj.setCheckOutDate(rs.getDate("check_out_date").toLocalDate());
                obj.setAdultCount(rs.getInt("adult_count"));
                obj.setChildCount(rs.getInt("child_count"));
                obj.setTotalPrice(rs.getDouble("total_price"));
                obj.setClients_id(rs.getInt("clients_id"));
                reservations.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservations;
    }
    public static boolean delete(int reservationID) {
        String query = "DELETE FROM reservations WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, reservationID);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean addReservation(Reservation reservation) {
        try {

            String query = "INSERT INTO reservations (user_id, room_id, check_in_date, check_out_date, adult_count, child_count, total_price, clients_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = DBConnector.getInstance().prepareStatement(query);
            statement.setInt(1, reservation.getUserID());
            statement.setInt(2, reservation.getRoomID());
            statement.setDate(3, Date.valueOf(reservation.getCheckInDate()));
            statement.setDate(4, Date.valueOf(reservation.getCheckOutDate()));
            statement.setInt(5, reservation.getAdultCount());
            statement.setInt(6, reservation.getChildCount());
            statement.setDouble(7, reservation.getTotalPrice());
            statement.setInt(8, reservation.getClients_id());

            statement.executeUpdate();
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getClients_id() {
        return clients_id;
    }

    public void setClients_id(int clients_id) {
        this.clients_id = clients_id;
    }
}
