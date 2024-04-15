package View;

import Helper.*;
import Model.HostelType;
import Model.Hotel;
import Model.PeriodManagement;
import Model.RoomManagement;

import javax.swing.*;

public class RoomUpdateGUI extends JFrame {

    private JPanel wrapper;
    private JTabbedPane tabbedPane1;
    private JComboBox cmb_upd_hotel;
    private JTextField fld_roomid;
    private JTextField fld_upd_roomName;
    private JTextField fld_upd_stock;
    private JTextField fld_upd_bedCount;
    private JComboBox cmb_upd_tv;
    private JComboBox cmb_upd_minibar;
    private JComboBox cmb_upd_hostelType;
    private JComboBox cmb_upd_period;
    private JTextField fld_upd_price;
    private JTextField fld_upd_priceChild;
    private JButton btn_upd_room;
    private JLabel lbl_old_hotel;
    private JLabel lbl_old_roomName;
    private JLabel lbl_old_stock;
    private JLabel lbl_old_bedCount;
    private JLabel lbl_old_tv;
    private JLabel lbl_old_minibar;
    private JLabel lbl_old_hostelType;
    private JLabel lbl_old_period;
    private JLabel lbl_old_price;
    private JLabel lbl_old_priceChild;

    public RoomUpdateGUI(String selectID)  {
        add(wrapper);
        setSize(700,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        fld_roomid.setText(selectID);
        loadHotelCombo(cmb_upd_hotel);
        loadPeriodManagement(cmb_upd_period);

        RoomManagement obj=RoomManagement.getroom(Integer.parseInt(selectID));
        assert obj != null;
        lbl_old_roomName.setText(obj.getRoomName());
        lbl_old_hotel.setText(Hotel.getHotelByID(obj.getHotelID()).getHotelName());
        lbl_old_stock.setText(String.valueOf(obj.getStock()));
        lbl_old_bedCount.setText(String.valueOf(obj.getBedCount()));
        lbl_old_tv.setText(obj.getTelevision());
        lbl_old_minibar.setText(obj.getMinibar());
        lbl_old_hostelType.setText(HostelType.getByID(obj.getHostelTypeID()).getHostelType());
        lbl_old_period.setText(PeriodManagement.getByID(obj.getPeriodID()).getStartDate().toString());
        lbl_old_price.setText(String.valueOf(obj.getPrice()));
        lbl_old_priceChild.setText(String.valueOf(obj.getPriceChild()));

        btn_upd_room.addActionListener(e -> {
            Item hotelItem = (Item) cmb_upd_hotel.getSelectedItem();
            Item hostelTypeItem=(Item) cmb_upd_hostelType.getSelectedItem();
            Item periodItem = (Item) cmb_upd_period.getSelectedItem();
            if (Helper.isFieldEmpty(fld_upd_roomName) || Helper.isFieldEmpty(fld_upd_stock) || Helper.isFieldEmpty(fld_upd_bedCount) || Helper.isFieldEmpty(fld_upd_price) || Helper.isFieldEmpty(fld_upd_priceChild)) {
                Helper.showMsg("Bütün Alanlar doldurulmadan Oda Güncellenemez !!!");

            }else {
                if (Helper.isFieldInt(fld_upd_stock) && Helper.isFieldInt(fld_upd_bedCount) && Helper.isFieldDouble(fld_upd_price) && Helper.isFieldDouble(fld_upd_priceChild)){
                    int ID=Integer.parseInt(selectID);
                    int hotelID = ((Item) cmb_upd_hotel.getSelectedItem()).getKey(); //Ekranda İlk önce otel seçimi yapılmalı
                    String roomName = fld_upd_roomName.getText();
                    int stock = Integer.parseInt(fld_upd_stock.getText());
                    int bedCount = Integer.parseInt(fld_upd_bedCount.getText());
                    String television = cmb_upd_tv.getSelectedItem().toString();
                    String minibar = cmb_upd_minibar.getSelectedItem().toString();
                    int hostelType_id = ((Item) cmb_upd_hostelType.getSelectedItem()).getKey();
                    int periodID = ((Item) cmb_upd_period.getSelectedItem()).getKey();
                    double price = Double.parseDouble(fld_upd_price.getText());
                    double priceChild = Double.parseDouble(fld_upd_priceChild.getText());

                    if (RoomManagement.update(ID,hotelID,roomName,stock,bedCount,television,minibar,hostelType_id,periodID,price,priceChild)){
                        Helper.showMsg("done");

                        dispose();

                    }else {
                        Helper.showMsg("error");
                    }

                }else {
                    Helper.showMsg("Uygun Değer Girilmeli !!!");
                }
            }
        });
        cmb_upd_hotel.addActionListener(e -> {
            Item hotelItem = (Item) cmb_upd_hotel.getSelectedItem();
            try {

                int selectedHotel=hotelItem.getKey();
                SwingUtilities.invokeLater(() -> loadHostelTypeByHotelID(cmb_upd_hostelType, selectedHotel));
            }catch (Exception err){
                System.out.println("Hata oluştu");
            }
        });

    }
    private void loadPeriodManagement(JComboBox jComboBox) {
        jComboBox.removeAllItems();
        for (PeriodManagement obj:PeriodManagement.getList()){
            jComboBox.addItem(new Item(obj.getId(),obj.getStartDate().toString(),obj.getEndDate().toString()));
        }
    }
    public void loadHotelCombo(JComboBox jComboBox){
        jComboBox.removeAllItems();
        for (Hotel obj:Hotel.getList()){
            jComboBox.addItem(new Item(obj.getId(),obj.getHotelName()));
        }
    }
    public void loadHostelTypeByHotelID(JComboBox jComboBox,int hotel_id){

        jComboBox.removeAllItems();
        for (HostelType obj : HostelType.getListByHotelID(hotel_id)) {
            jComboBox.addItem(new Item(obj.getId(),obj.getHotelID(), obj.getHostelType()));
        }
        jComboBox.revalidate();
        jComboBox.repaint();
    }
}
