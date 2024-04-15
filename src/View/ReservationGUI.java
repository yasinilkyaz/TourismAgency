package View;

import Helper.*;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationGUI extends JFrame {

    private JPanel wrapper;
    private JTextField fld_hotelName;
    private JButton btn_calculate;
    private JTextField fld_childPrice;
    private JTextField fld_price;
    private JTextField fld_totalPrice;
    private JButton btn_registerClient;
    private JTextField fld_roomName;
    private JTextField fld_hostelType;
    private JTextField fld_bedCount;
    private JTextField fld_clientEntry;
    private JTextField fld_clientLeave;
    private JTextField fld_stayDay;
    private JTextField fld_adultCount;
    private JTextField fld_childCount;
    private JButton btn_clientControl;
    private JTextField fld_clientaddcontrol;
    private JButton btn_reservation;
    private ArrayList<Client> clients;

    RoomManagement room;

    public ReservationGUI(int roomID, int userID, LocalDate clientEntryDate, LocalDate clientLeaveDate) {
        add(wrapper);
        setSize(700, 500);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        this.room = RoomManagement.getroom(roomID);
        HostelType hostelType = HostelType.getByID(room.getHostelTypeID());
        RoomPrice roomPrice = RoomPrice.getInstance(hostelType.getHostelType(), room.getPeriodID());
        Hotel hotel = Hotel.getHotelByID(room.getHotelID());
        int day = PeriodManagement.daysBetween(clientEntryDate, clientLeaveDate);
        fld_hotelName.setText(hotel.getHotelName());
        fld_roomName.setText(room.getRoomName());
        fld_hostelType.setText(hostelType.getHostelType());
        fld_bedCount.setText(String.valueOf(room.getBedCount()));
        fld_clientEntry.setText(String.valueOf(clientEntryDate));
        fld_clientLeave.setText(String.valueOf(clientLeaveDate));
        fld_stayDay.setText(String.valueOf(day));

        btn_calculate.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_adultCount) || Helper.isFieldEmpty(fld_childCount)) {
                Helper.showMsg("Alanlar boş bırakılamaz");
            } else {
                int adultCount = Integer.parseInt(fld_adultCount.getText());
                int childCount = Integer.parseInt(fld_childCount.getText());

                int bedCount = room.getBedCount();
                if (adultCount + childCount > bedCount) {
                    Helper.showMsg("Yatak sayısına göre fazla kişi girdiniz.");
                }if (adultCount==0){Helper.showMsg("En az bir yetişkin olmalıdır.");}else {
                    double adultPrice = ((room.getPrice() + roomPrice.getPrice()) * adultCount)*day;
                    double childPrice = ((room.getPriceChild() + roomPrice.getPriceChild()) * childCount)*day;
                    double totalPrice = adultPrice + childPrice;

                    fld_price.setText(String.valueOf(adultPrice));
                    fld_childPrice.setText(String.valueOf(childPrice));
                    fld_totalPrice.setText(String.valueOf(totalPrice));
                }
            }
        });


        btn_registerClient.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_adultCount) || Helper.isFieldEmpty(fld_childCount)) {
                Helper.showMsg("Alanlar boş bırakılamaz");
            } else {
                int adultCount = Integer.parseInt(fld_adultCount.getText());
                int childCount = Integer.parseInt(fld_childCount.getText());

                int bedCount = room.getBedCount();
                if (adultCount + childCount > bedCount) {
                    Helper.showMsg("Yatak sayısına göre fazla kişi girdiniz.");
                }if (adultCount==0){Helper.showMsg("En az bir yetişkin olmalıdır.");} else {
                    int clientCount = adultCount + childCount;
                    double adultPrice = (room.getPrice() + roomPrice.getPrice()) * adultCount;
                    double childPrice = (room.getPriceChild() + roomPrice.getPriceChild()) * childCount;
                    double totalPrice = adultPrice + childPrice;

                    fld_price.setText(String.valueOf(adultPrice));
                    fld_childPrice.setText(String.valueOf(childPrice));
                    fld_totalPrice.setText(String.valueOf(totalPrice));

                    clients = new ArrayList<>();

                    int list_id = Client.generateListId();
                    for (int i = 0; i < clientCount; i++) {
                        ClientAddGUI clientAddGUI = new ClientAddGUI(clients, list_id);
                    }
                }
            }
        });


        btn_clientControl.addActionListener(e -> {
            fld_clientaddcontrol.setText(String.valueOf(clients.size()));
            int adultCount = 0;
            int childCount = 0;
            for (Client obj : clients) {
                if (obj.getClientType().equals("adult")) {
                    adultCount++;
                }
                if (obj.getClientType().equals("child")) {
                    childCount++;
                }
            }
            int checkAdultField = Integer.parseInt(fld_adultCount.getText());
            int checkChildField = Integer.parseInt(fld_childCount.getText());

            if (adultCount == checkAdultField && childCount == checkChildField) {
                btn_reservation.setEnabled(true);
                fld_adultCount.setEditable(false);
                fld_childCount.setEditable(false);
            }
        });
        btn_reservation.addActionListener(e -> {
            int adultCount = Integer.parseInt(fld_adultCount.getText());
            int childCount = Integer.parseInt(fld_childCount.getText());
            double totalPrice = Double.parseDouble(fld_totalPrice.getText());
            int list_id = Client.generateListId();
            Reservation reservation = new Reservation(userID, roomID, clientEntryDate, clientLeaveDate, adultCount, childCount, totalPrice, list_id);

            if (Client.addClientsToDatabase(clients) && Reservation.addReservation(reservation)) {
                RoomManagement.decreaseStock(roomID);
                dispose();
                Helper.showMsg("Rezervasyon tamamlandı.");
            }
        });
    }

}
