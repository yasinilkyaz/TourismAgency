package View;

import Helper.*;
import Model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientAddGUI extends JFrame {
    private JPanel wrapper;
    private JButton btn_client_add;
    private JTextField fld_listId;
    private JTextField fld_name;
    private JTextField fld_phone;
    private JTextField fld_email;
    private JComboBox cmb_clientType;


    public ClientAddGUI(ArrayList<Client> clients ,int listID){
        add(wrapper);
        setSize(700,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        fld_listId.setText(String.valueOf(listID));

        btn_client_add.addActionListener(e -> {
            Client client=new Client();


            String name= fld_name.getText();
            String phone= fld_phone.getText();
            String email= fld_email.getText();
            String type= cmb_clientType.getSelectedItem().toString();

            if (Helper.isFieldEmpty(fld_name)){
                Helper.showMsg("İsim alanı boş bırakılamaz");
            }else {client.setList_id(listID);
                client.setNameSurname(name);
                client.setPhone(phone);
                client.setEmail(email);
                client.setClientType(type);
                clients.add(client);
                Helper.showMsg("done");
                dispose();
            }


        });
        cmb_clientType.addActionListener(e -> {
            String type=cmb_clientType.getSelectedItem().toString();
            if (type.equals("child")){
                fld_phone.setText(null);
                fld_email.setText(null);
                fld_phone.setEnabled(false);
                fld_email.setEnabled(false);
            }else {
                fld_phone.setEnabled(true);
                fld_email.setEnabled(true);}
        });
    }
}
