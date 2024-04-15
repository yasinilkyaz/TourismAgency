package View;

import Helper.Helper;
import Model.Admin;
import Helper.Config;
import Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminGUI extends JFrame {
    private Object[] row_user_list;
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JTextField fld_user_username;
    private JTextField fld_user_password;
    private JComboBox cmb_user_role;
    private JButton btn_user_add;
    private DefaultTableModel mdl_user_list;

    public AdminGUI(Admin admin) {
        add(wrapper);
        setSize(1000,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        Object[] col_user_list={ "id","Kullanıcı Adı","Şifre", "Rol"};
        mdl_user_list=new DefaultTableModel();
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list=new Object[col_user_list.length];

        tbl_user_list.setModel(mdl_user_list);
        loadUserModel();
        tbl_user_list.setModel(mdl_user_list);


        btn_user_add.addActionListener(e -> {

            if (Helper.isFieldEmpty(fld_user_username)||Helper.isFieldEmpty(fld_user_password)){
                Helper.showMsg("fill");
            }else {
                String username=fld_user_username.getText();
                String password=fld_user_password.getText();
                String role= cmb_user_role.getSelectedItem().toString();
                if(User.add(username,password,role)){
                    Helper.showMsg("done");
                    loadUserModel();
                    fld_user_username.setText(null);
                    fld_user_password.setText(null);
                }
            }
        });
    }
    public void loadUserModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        for (User obj : User.getList()) {
            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getUsername();
            row_user_list[i++] = obj.getPassword();
            row_user_list[i++] = obj.getRole();
            mdl_user_list.addRow(row_user_list);
        }
    }
}
