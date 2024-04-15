package View;

import Helper.*;
import Model.RoomManagement;

import javax.swing.*;

public class RoomInfoGUI extends JFrame {

    private JPanel wrapper;
    private JTextField fld_id;
    private JTextField fld_name;
    private JTextField fld_bedCount;
    private JTextField fld_tv;
    private JTextField fld_minibar;

    public RoomInfoGUI(int roomID)  {
        add(wrapper);
        setSize(700,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        RoomManagement roomManagement=RoomManagement.getroom(roomID);
        fld_id.setText(String.valueOf(roomManagement.getId()));
        fld_name.setText(roomManagement.getRoomName());
        fld_bedCount.setText(String.valueOf(roomManagement.getBedCount()));
        fld_tv.setText(roomManagement.getTelevision());
        fld_minibar.setText(roomManagement.getMinibar());
    }
}
