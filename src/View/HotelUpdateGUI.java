package View;

import Helper.*;
import Model.Hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelUpdateGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_hotel_id;
    private JTextField fld_name;
    private JTextField fld_address;
    private JTextField fld_email;
    private JTextField fld_phone;
    private JTextField fld_star;
    private JTextField fld_facilityFeatures;
    private JTextField fld_region;
    private JButton btn_hotelUpdate;
    private JLabel lbl_hotelName;
    private JLabel lbl_hotelAddress;
    private JLabel lbl_hotelEmail;
    private JLabel lbl_hotelPhone;
    private JLabel lbl_hotelStar;
    private JLabel lbl_hotelFF;
    private JLabel lbl_hotelRegion;

    public HotelUpdateGUI(int hotelID)  {
        add(wrapper);
        setSize(700,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        Hotel obj = Hotel.getHotelByID(hotelID);
        lbl_hotelName.setText(obj.getHotelName());
        lbl_hotelAddress.setText(obj.getAddress());
        lbl_hotelEmail.setText(obj.getEmail());
        lbl_hotelPhone.setText(obj.getPhone());
        lbl_hotelStar.setText(String.valueOf(obj.getStarRating()));
        lbl_hotelFF.setText(obj.getFacilityFeatures());
        lbl_hotelRegion.setText(obj.getRegion());


        btn_hotelUpdate.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_name)){fld_name.setText(obj.getHotelName());}else {obj.setHotelName(fld_name.getText());}
            if (Helper.isFieldEmpty(fld_address)){fld_address.setText(obj.getAddress());} else {obj.setAddress(fld_address.getText());}
            if (Helper.isFieldEmpty(fld_email)){fld_email.setText(obj.getEmail());}else {obj.setEmail(fld_email.getText());}
            if (Helper.isFieldEmpty(fld_phone)){fld_phone.setText(obj.getPhone());}else {obj.setPhone(fld_phone.getText());}
            if (Helper.isFieldEmpty(fld_star)&&!Helper.isFieldInt(fld_star)){fld_star.setText(String.valueOf(obj.getStarRating()));}else {obj.setStarRating(Integer.parseInt(fld_star.getText()));}
            if (Helper.isFieldEmpty(fld_facilityFeatures)){fld_facilityFeatures.setText(obj.getFacilityFeatures());}else {obj.setFacilityFeatures(fld_facilityFeatures.getText());}
            if (Helper.isFieldEmpty(fld_region)){fld_region.setText(obj.getRegion());}else{obj.setRegion(fld_region.getText());}

            if (Helper.confirm("Değişiklikleri onaylıyor musunuz?")){
                if (Hotel.update(obj)){
                    Helper.showMsg("done");
                }else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}
