package View;

import Helper.*;
import Model.RoomPrice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PriceUpdateGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_roomPrice_id;
    private JTextField fld_upd_price;
    private JTextField fld_upd_priceChild;
    private JButton btn_upd_roomPrice;

    public PriceUpdateGUI(String selectID)  {
        add(wrapper);
        setSize(700,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        fld_roomPrice_id.setText(selectID);


        btn_upd_roomPrice.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_upd_price)&&Helper.isFieldEmpty(fld_upd_priceChild)){
                Helper.showMsg("Bütün Alanlar doldurulmadan fiyat güncellenemez !!!");
            }else {
                if (Helper.isFieldDouble(fld_upd_price)&&Helper.isFieldDouble(fld_upd_priceChild)){
                    int ID=Integer.parseInt(selectID);
                    double price=Double.parseDouble(fld_upd_price.getText());
                    double priceChild=Double.parseDouble(fld_upd_priceChild.getText());
                    if (RoomPrice.update(ID,price,priceChild)){
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
    }
}
