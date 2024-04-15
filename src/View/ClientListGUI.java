package View;

import Helper.*;
import Model.Client;
import Model.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClientListGUI extends JFrame {
    private JPanel wrapper;
    private JTable tbl_client_list;
    private JScrollPane scrl_client_list;
    private DefaultTableModel mdl_client_list;
    private Object[] row_client_list;

    public ClientListGUI(int listID) {
        add(wrapper);
        setSize(700, 500);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        Object[] col_client_identifiers = {"Ad Soyad", "Telefon", "Email", "Tip"};
        mdl_client_list = new DefaultTableModel();
        mdl_client_list.setColumnIdentifiers(col_client_identifiers);
        row_client_list = new Object[col_client_identifiers.length];
        loadClientList(listID);
        tbl_client_list.setModel(mdl_client_list);
    }

    public void loadClientList(int listID) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_client_list.getModel();
        clearModel.setRowCount(0);
        for (Client obj : Client.getListByListID(listID)) {
            int i = 0;
            row_client_list[i++] = obj.getNameSurname();
            row_client_list[i++] = obj.getPhone();
            row_client_list[i++] = obj.getEmail();
            row_client_list[i++] = obj.getClientType();
            mdl_client_list.addRow(row_client_list);
        }
    }
}
