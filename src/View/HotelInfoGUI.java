package View;

import Helper.*;
import Model.Hotel;

import javax.swing.*;

public class HotelInfoGUI extends JFrame {

    private JPanel wrapper;
    private JTextField fld_hotel_id;
    private JTextField fld_name;
    private JTextField fld_address;
    private JTextField fld_email;
    private JTextField fld_phone;
    private JTextField fld_star;
    private JTextField fld_facilityFeatures;
    private JTextField fld_region;

    public HotelInfoGUI(int hotelID) {
        add(wrapper);
        setSize(700,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        fld_hotel_id.setText(String.valueOf(hotelID));

        Hotel hotel=Hotel.getHotelByID(hotelID);

        fld_name.setText(hotel.getHotelName());
        fld_address.setText(hotel.getAddress());
        fld_email.setText(hotel.getEmail());
        fld_phone.setText(hotel.getPhone());
        fld_star.setText(String.valueOf(hotel.getStarRating()));
        fld_facilityFeatures.setText(hotel.getFacilityFeatures());
        fld_region.setText(hotel.getRegion());
    }
}
