package Model;

import Helper.DBConnector;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PeriodManagement {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;

    public PeriodManagement() {
    }

    public PeriodManagement(int id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static ArrayList<PeriodManagement> getList(){
        ArrayList<PeriodManagement> periodManagements=new ArrayList<>();
        String query="SELECT * FROM period_management";
        PeriodManagement periodManagement=null;
        try {
            Statement st=DBConnector.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);

            while (rs.next()){
                periodManagement=new PeriodManagement();
                periodManagement.setId(rs.getInt("id"));
                periodManagement.setStartDate(rs.getDate("start_date").toLocalDate());
                periodManagement.setEndDate(rs.getDate("end_date").toLocalDate());
                periodManagements.add(periodManagement);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return periodManagements;
    }
    public static PeriodManagement getByID(int periodID) {
        String query="SELECT * FROM period_management WHERE id=?";
        PeriodManagement periodManagement=null;

        try {
            PreparedStatement pr= DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,periodID);
            ResultSet rs=pr.executeQuery();

            while (rs.next()){
                periodManagement=new PeriodManagement();
                periodManagement.setId(periodID);
                periodManagement.setStartDate(rs.getDate("start_date").toLocalDate());
                periodManagement.setEndDate(rs.getDate("end_date").toLocalDate());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return periodManagement;
    }
    public static int getPeriodForClientDates(LocalDate clientEntryDate, LocalDate clientLeaveDate) {
        for (PeriodManagement period : getList()) {
            if (!clientEntryDate.isAfter(period.getEndDate()) && !clientLeaveDate.isBefore(period.getStartDate())) {
                return period.id;
            }
        }
        return -1;
    }
    public static int daysBetween(LocalDate startDate, LocalDate endDate) {
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }
    public static boolean add(LocalDate startDate,LocalDate endDate){
        String query="INSERT INTO period_management (start_date,end_date) VALUES (?,?)";

        try {
            PreparedStatement pr=DBConnector.getInstance().prepareStatement(query);
            pr.setDate(1, Date.valueOf(startDate));
            pr.setDate(2, Date.valueOf(endDate));

            return pr.executeUpdate()!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean update(int id,LocalDate startDate,LocalDate endDate){
        String query="UPDATE period_management SET start_date =?,end_date=?  WHERE id=?";

        try {
            PreparedStatement pr=DBConnector.getInstance().prepareStatement(query);
            pr.setDate(1, Date.valueOf(startDate));
            pr.setDate(2, Date.valueOf(endDate));
            pr.setInt(3,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getLastAddID() {
        String query="SELECT * FROM period_management";

        try {
            Statement st=DBConnector.getInstance().createStatement();
            int lastID = 0;
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                lastID=rs.getInt("id");
            }
            return lastID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean delete(int deletePeriodID) {
        String qoery="DELETE FROM period_management WHERE id = ?";

        try {
            PreparedStatement pr=DBConnector.getInstance().prepareStatement(qoery);
            pr.setInt(1,deletePeriodID);
            return pr.executeUpdate()!=-1;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
