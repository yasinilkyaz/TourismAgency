package View;

import Helper.*;

import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeGUI extends JFrame {

    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JPanel pnl_hotel_list;
    private JTable tbl_hotel_list;
    private JTextField fld_hotel_name;
    private JScrollPane scrl_hotel_list;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_email;
    private JTextField fld_hotel_phone;
    private JTextField fld_hotel_star;
    private JTextField fld_hotel_facilityFeatures;
    private JTextField fld_hotel_region;
    private JButton btn_hotel_add;
    private JPanel pnl_otel_add;
    private JScrollPane scrl_hotel_add;
    private JPanel pnl_hostelType_list;
    private JScrollPane scrl_hostelType_list;
    private JComboBox cmb_otel_list;
    private JTable tbl_hostelType_list;
    private JComboBox cmb_hosteltype_list;
    private JButton btn_hostelType_add;
    private JScrollPane scrl_roomManagement;
    private JTable tbl_roomManagement_list;
    private JTextField fld_roomName;
    private JTextField fld_roomStock;
    private JTextField fld_roomBedCount;
    private JComboBox cmb_roomTV;
    private JComboBox cmb_roomMinibar;
    private JComboBox cmb_roomHostelType;
    private JComboBox cmb_roomPeriod;
    private JTextField fld_roomPrice;
    private JTextField fld_roomPriceChild;
    private JButton btn_roomAdd;
    private JComboBox cmb_otel_list2;
    private JButton btn_roomDelete;
    private JTextField fld_room_ID;
    private JButton btn_room_update;
    private JButton btn_reloadTable;
    private JButton btn_hostelType_delete;
    private JTextField fld_hostelType_id;
    private JTextField fld_hotel_id;
    private JButton btn_hotel_delete;
    private JPanel pnl_room_price;
    private JTable tbl_room_price;
    private JScrollPane scrl_room_price;
    private JTextField fld_roomPrice_id;
    private JButton btn_roomPrice_upd;
    private JButton btn_roomPrice_reflesh;
    private JTable tbl_period_list;
    private JScrollPane scrl_period;
    private JPanel pnl_roomManagement;
    private JPanel pnl_PeriodManagement;
    private JButton btn_period_add;
    private JTextField fld_period_id;
    private JButton btn_period_update;
    private JButton btn_period_delete;
    private JTextField fld_periodStart;
    private JTextField fld_periodEnd;
    private JPanel pnl_roomSearch;
    private JTextField fld_roomSearch_region;
    private JTextField fld_roomSearch_hotelName;
    private JTextField fld_roomSearch_startDate;
    private JTextField fld_roomSearch_endDate;
    private JButton btn_roomSearch_getList;
    private JTextField fld_roomSearch_roomID;
    private JButton btn_roomSearch_StartReservation;
    private JPanel pnl_reservation;
    private JScrollPane scrl_roomSearch;
    private JTable tbl_roomSearch_list;
    private JButton btn_hotel_info;
    private JButton btn_room_info;
    private JPanel pnl_reservation_list;
    private JButton btn_rsr_reflesh;
    private JButton btn_show_client;
    private JButton btn_rsr_cancel;
    private JTable tbl_rsr_list;
    private JScrollPane scrl_rsr_list;
    private JTextField fld_clients_id;
    private JTextField fld_rsr_id;
    private JTextField fld_rsr_roomID;
    private JButton btn_hotel_update;
    private JLabel lbl_hostelType_id;

    private DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;
    private DefaultTableModel mdl_hostelType_list;
    private Object[] row_hostelType_list;
    private DefaultTableModel mdl_roomManagement_list;
    private Object[] row_roomManagement_list;
    private DefaultTableModel mdl_roomPrice_list;
    private Object[] row_roomPrice_list;
    private DefaultTableModel mdl_periodManagement_list;
    private Object[] row_periodManagement_list;
    private DefaultTableModel mdl_roomSearch_list;
    private Object[] row_roomSearch_list;
    private DefaultTableModel mdl_rsr_list;
    private Object[] row_rsr_list;

    public EmployeeGUI(Employee employee) {
        add(wrapper);
        setSize(1200, 700);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        //---------------------Hotel-----------------------------

        Object[] col_hotel_identifiers = {"ID", "Otel Adı", "Adres", "E-posta", "Telefon", "Yıldız", " Tesis Özellikleri", "Bölge"};
        mdl_hotel_list = new DefaultTableModel();
        mdl_hotel_list.setColumnIdentifiers(col_hotel_identifiers);

        row_hotel_list = new Object[col_hotel_identifiers.length];

        loadHotelModel();
        tbl_hotel_list.setModel(mdl_hotel_list);
        //Table width
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(50);//id
        tbl_hotel_list.getColumnModel().getColumn(1).setMaxWidth(150);//Hotel name
        tbl_hotel_list.getColumnModel().getColumn(2).setMaxWidth(1000);//Address
        tbl_hotel_list.getColumnModel().getColumn(3).setMaxWidth(1000);//Email
        tbl_hotel_list.getColumnModel().getColumn(4).setMaxWidth(120);//Phone
        tbl_hotel_list.getColumnModel().getColumn(5).setMaxWidth(50);//Star rating
        tbl_hotel_list.getColumnModel().getColumn(6).setMaxWidth(1500);//Facility features
        tbl_hotel_list.getColumnModel().getColumn(7).setMaxWidth(200);//Region

        //Hotel Add
        btn_hotel_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_name) && Helper.isFieldEmpty(fld_hotel_address) && Helper.isFieldEmpty(fld_hotel_email) && Helper.isFieldEmpty(fld_hotel_star) && Helper.isFieldEmpty(fld_hotel_facilityFeatures) && Helper.isFieldEmpty(fld_hotel_region)) {
                Helper.showMsg("Bütün Alanlar doldurulmadan Sisteme Otel Eklenemez !!!");
            } else {
                String hotelName = fld_hotel_name.getText();
                String address = fld_hotel_address.getText();
                String email = fld_hotel_email.getText();
                String phone = fld_hotel_phone.getText();
                int starRating = Integer.parseInt(fld_hotel_star.getText());
                String facilityFeatures = fld_hotel_facilityFeatures.getText();
                String region = fld_hotel_region.getText();
                if (Hotel.add(hotelName, address, email, phone, starRating, facilityFeatures, region)) {
                    Helper.showMsg("done");
                    loadHotelModel();
                    loadHotelCombo(cmb_otel_list);
                    loadHotelCombo(cmb_otel_list2);
                    fld_hotel_name.setText(null);
                    fld_hotel_address.setText(null);
                    fld_hotel_email.setText(null);
                    fld_hotel_phone.setText(null);
                    fld_hotel_star.setText(null);
                    fld_hotel_facilityFeatures.setText(null);
                    fld_hotel_region.setText(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        tbl_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectRoomID = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 0).toString();
                fld_hotel_id.setText(selectRoomID);
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }

        });
        btn_hotel_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_id)) {
                Helper.showMsg("Tablodan Seçim yapmalısınız");
            } else {
                try {
                    String selectRoomID = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 0).toString();
                    fld_hotel_id.setText(selectRoomID);
                    if (Helper.confirm("Sistemden Otel kaldırılcaktır.Onaylıyor musunuz?")) {
                        if (Hotel.delete(selectRoomID)) {
                            Helper.showMsg("done");
                            loadHotelModel();
                            loadHotelCombo(cmb_otel_list);
                            loadHotelCombo(cmb_otel_list2);
                        } else {
                            Helper.showMsg("error");
                        }
                    }


                } catch (Exception err) {
                    System.out.println(err.getMessage());
                    Helper.showMsg("Bu otele bağlı pansiyon tipi ve odalar bulunmaktadır.Odaları silin.Pansiyon tipini silin");
                }

            }
        });
        btn_hotel_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_id)) {
                Helper.showMsg("Tablodan Seçim yapmalısınız");
            }else {
                int hotelID= Integer.parseInt(fld_hotel_id.getText());
                HotelUpdateGUI hotelUpdateGUI=new HotelUpdateGUI(hotelID);
            }
        });

        //-------------------Hostel Type------------------------------------------


        Object[] col_hostelType_identifiers = {"id", "Otel id", "Otel Adı", "Pansiyon Tipi"};
        mdl_hostelType_list = new DefaultTableModel();
        mdl_hostelType_list.setColumnIdentifiers(col_hostelType_identifiers);
        row_hostelType_list = new Object[col_hostelType_identifiers.length];
        loadHostelTypeModel();
        tbl_hostelType_list.setModel(mdl_hostelType_list);
        loadHotelCombo(cmb_otel_list);

        tbl_hostelType_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectRoomID = tbl_hostelType_list.getValueAt(tbl_hostelType_list.getSelectedRow(), 0).toString();
                fld_hostelType_id.setText(selectRoomID);
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }

        });

        btn_hostelType_add.addActionListener(e -> {
            Item hotelItem = (Item) cmb_otel_list.getSelectedItem();

            if (HostelType.add(hotelItem.getKey(), cmb_hosteltype_list.getSelectedItem().toString())) {
                Helper.showMsg("done");
                loadHostelTypeModel();
            } else {
                Helper.showMsg("Mevcutta kayıt bulunmaktadır.Sisteme eklenmemiştir.");
            }
        });

        btn_hostelType_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hostelType_id)) {
                Helper.showMsg("Tablodan Seçim yapmalısınız");
            } else {
                try {
                    String selectRoomID = tbl_hostelType_list.getValueAt(tbl_hostelType_list.getSelectedRow(), 0).toString();
                    fld_hostelType_id.setText(selectRoomID);
                    if (Helper.confirm("Sistemden Otele Ait pansiyon tipi kaldırılcaktır.Onaylıyor musunuz?")) {
                        if (HostelType.delete(selectRoomID)) {
                            Helper.showMsg("done");
                            loadHostelTypeModel();
                        } else {
                            Helper.showMsg("error");
                        }
                    }


                } catch (Exception err) {
                    System.out.println(err.getMessage());
                    Helper.showMsg("Bu pansiyon tipine bağlı odalar bulunmaktadır.Odaları güncelleyin veya silin.");
                }

            }
        });

        //-------------------------Room Management----------------------------------


        Object[] col_roomManagement_identifiers = {"id", "Otel Adı", "Oda adı", "Stok", "Yatak Sayısı", "Televizyon", "Minibar", "Pansiyon Tipi", "Sezon Başlangıç", "Sezon Bitiş", "Yetişkin Ücreti", "Çocuk Ücreti"};
        mdl_roomManagement_list = new DefaultTableModel();
        mdl_roomManagement_list.setColumnIdentifiers(col_roomManagement_identifiers);
        row_roomManagement_list = new Object[col_roomManagement_identifiers.length];
        loadRoomManagementModel();
        tbl_roomManagement_list.setModel(mdl_roomManagement_list);

        tbl_roomManagement_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectRoomID = tbl_roomManagement_list.getValueAt(tbl_roomManagement_list.getSelectedRow(), 0).toString();
                fld_room_ID.setText(selectRoomID);
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }

        });

        loadHotelCombo(cmb_otel_list2);
        loadPeriodManagement(cmb_roomPeriod);

        btn_roomAdd.addActionListener(e -> {
            Item hotelItem = (Item) cmb_otel_list2.getSelectedItem();
            Item hostelTypeItem = (Item) cmb_roomHostelType.getSelectedItem();
            Item periodItem = (Item) cmb_roomPeriod.getSelectedItem();
            if (Helper.isFieldEmpty(fld_roomName) || Helper.isFieldEmpty(fld_roomStock) || Helper.isFieldEmpty(fld_roomBedCount) || Helper.isFieldEmpty(fld_roomPrice) || Helper.isFieldEmpty(fld_roomPriceChild)) {
                Helper.showMsg("Bütün Alanlar doldurulmadan Sisteme Oda Eklenemez !!!");

            } else {
                if (Helper.isFieldInt(fld_roomStock) && Helper.isFieldInt(fld_roomBedCount) && Helper.isFieldDouble(fld_roomPrice) && Helper.isFieldDouble(fld_roomPriceChild)) {
                    int hotelID = hotelItem.getKey();
                    String roomName = fld_roomName.getText();
                    int stock = Integer.parseInt(fld_roomStock.getText());
                    int bedCount = Integer.parseInt(fld_roomBedCount.getText());
                    String television = cmb_roomTV.getSelectedItem().toString();
                    String minibar = cmb_roomMinibar.getSelectedItem().toString();
                    int hostelType_id = hostelTypeItem.getKey();
                    int periodID = periodItem.getKey();
                    double price = Double.parseDouble(fld_roomPrice.getText());
                    double priceChild = Double.parseDouble(fld_roomPriceChild.getText());
                    if (RoomManagement.add(hotelID, roomName, stock, bedCount, television, minibar, hostelType_id, periodID, price, priceChild)) {
                        Helper.showMsg("done");
                        loadRoomManagementModel();
                        fld_roomName.setText(null);
                        fld_roomStock.setText(null);
                        fld_roomBedCount.setText(null);
                        fld_roomPrice.setText(null);
                        fld_roomPriceChild.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }
                } else {
                    Helper.showMsg("Uygun Değer Girilmeli !!!");
                }

            }

        });

        btn_room_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_room_ID)) {
                Helper.showMsg("Tablodan Seçim yapmalısınız");
            } else {
                try {
                    String selectRoomID = tbl_roomManagement_list.getValueAt(tbl_roomManagement_list.getSelectedRow(), 0).toString();
                    fld_room_ID.setText(selectRoomID);
                    RoomUpdateGUI roomUpdateGUI = new RoomUpdateGUI(selectRoomID);
                } catch (Exception err) {
                    System.out.println(err.getMessage());
                }

            }

        });
        btn_reloadTable.addActionListener(e -> {
            loadRoomManagementModel();
        });
        cmb_otel_list2.addActionListener(e -> {
            Item hotelItem = (Item) cmb_otel_list2.getSelectedItem();
            try {
                int selectedHotel = hotelItem.getKey();
                SwingUtilities.invokeLater(() -> loadHostelTypeByHotelID(cmb_roomHostelType, selectedHotel));
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        });
        btn_roomDelete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_room_ID)) {
                Helper.showMsg("Tablodan Seçim yapmalısınız");
            } else {
                try {
                    String selectRoomID = tbl_roomManagement_list.getValueAt(tbl_roomManagement_list.getSelectedRow(), 0).toString();
                    fld_room_ID.setText(selectRoomID);
                    if (Helper.confirm("Sistemden oda kaldırılcaktır.Onaylıyor musunuz?")) {
                        if (RoomManagement.delete(selectRoomID)) {
                            Helper.showMsg("done");
                            loadRoomManagementModel();
                        } else {
                            Helper.showMsg("error");
                        }
                    }


                } catch (Exception err) {
                    System.out.println(err.getMessage());
                }

            }
        });

        //--------------------------Room Price--------------------------------------------------
        Object[] col_roomPrice_identifiers = {"id", "Sezon Giriş", "Sezon Çıkış", "Pansiyon Tipi", "Ücret", "Çocuk Ücret"};
        mdl_roomPrice_list = new DefaultTableModel();
        mdl_roomPrice_list.setColumnIdentifiers(col_roomPrice_identifiers);

        row_roomPrice_list = new Object[col_roomPrice_identifiers.length];
        loadRoomPriceModel();
        tbl_room_price.setModel(mdl_roomPrice_list);

        tbl_room_price.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectRoomID = tbl_room_price.getValueAt(tbl_room_price.getSelectedRow(), 0).toString();
                fld_roomPrice_id.setText(selectRoomID);
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }

        });


        btn_roomPrice_upd.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_roomPrice_id)) {
                Helper.showMsg("Tablodan Seçim yapmalısınız");
            } else {
                try {
                    String selectRoomID = tbl_room_price.getValueAt(tbl_room_price.getSelectedRow(), 0).toString();
                    fld_roomPrice_id.setText(selectRoomID);
                    PriceUpdateGUI priceUpdateGUI = new PriceUpdateGUI(selectRoomID);
                } catch (Exception err) {
                    System.out.println(err.getMessage());
                }
            }
        });
        btn_roomPrice_reflesh.addActionListener(e -> {
            loadRoomPriceModel();
        });


        // -------------------------------- Period Management ------------------------------


        Object[] col_periodManagement_identifiers = {"id", "Sezon Başlangıç", "Sezon Bitiş"};
        mdl_periodManagement_list = new DefaultTableModel();
        mdl_periodManagement_list.setColumnIdentifiers(col_periodManagement_identifiers);
        row_periodManagement_list = new Object[col_periodManagement_identifiers.length];
        loadPeriodManagementModel();
        tbl_period_list.setModel(mdl_periodManagement_list);

        tbl_period_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectRoomID = tbl_period_list.getValueAt(tbl_period_list.getSelectedRow(), 0).toString();
                fld_period_id.setText(selectRoomID);
                fld_periodStart.setText(tbl_period_list.getValueAt(tbl_period_list.getSelectedRow(), 1).toString());
                fld_periodEnd.setText(tbl_period_list.getValueAt(tbl_period_list.getSelectedRow(), 2).toString());
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        });

        btn_period_add.addActionListener(e -> {
            if (Helper.isFieldLocalDate(fld_periodStart) && Helper.isFieldLocalDate(fld_periodEnd)) {
                LocalDate start = LocalDate.parse(fld_periodStart.getText());
                LocalDate end = LocalDate.parse(fld_periodEnd.getText());
                if (PeriodManagement.add(start, end)) {
                    int lastID = PeriodManagement.getLastAddID();
                    if (RoomPrice.addNewPriceForPeriodID(lastID)) {
                        Helper.showMsg("done");
                        fld_periodStart.setText(null);
                        fld_periodEnd.setText(null);
                        loadPeriodManagementModel();
                        loadRoomPriceModel();
                    } else {
                        Helper.showMsg("error");
                    }
                }
            } else {
                Helper.showMsg("Uygun değerler girilmeli");
            }
        });

        btn_period_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_period_id)) {
                Helper.showMsg("Tablodan seçim yapınız.");
            } else {
                if (Helper.isFieldLocalDate(fld_periodStart) && Helper.isFieldLocalDate(fld_periodEnd)) {
                    int id = Integer.parseInt(fld_period_id.getText());
                    LocalDate start = LocalDate.parse(fld_periodStart.getText());
                    LocalDate end = LocalDate.parse(fld_periodEnd.getText());
                    if (PeriodManagement.update(id, start, end)) {
                        Helper.showMsg("done");
                        loadPeriodManagementModel();
                        loadRoomManagementModel();
                    } else {
                        Helper.showMsg("error");
                    }
                } else {
                    Helper.showMsg("error");
                }
            }

        });

        btn_period_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_period_id)) {
                Helper.showMsg("Tablodan seçim yapınız.");
            } else {
                int deletePeriodID = Integer.parseInt(fld_period_id.getText());
                if (PeriodManagement.delete(deletePeriodID)) {
                    if (RoomPrice.deletePriceByPeriodID(deletePeriodID)) {
                        Helper.showMsg("done");
                        loadPeriodManagementModel();
                        loadRoomManagementModel();
                    } else {
                        Helper.showMsg("error");
                    }

                } else {
                    Helper.showMsg("error");
                }
            }
        });

        //-------------------------------Room Search------------------------------------------------------


        Object[] col_roomSearch_identifiers = {"Oda ID", "Bölge", "Otel Adı", "Pansiyon tipi", "Oda adı", "1 gece için tek kişilik fiyat", "Çocuk Fiyat", "Sezon Başlangıç", "Sezon Bitiş"};
        mdl_roomSearch_list = new DefaultTableModel();
        mdl_roomSearch_list.setColumnIdentifiers(col_roomSearch_identifiers);
        row_roomSearch_list = new Object[col_roomSearch_identifiers.length];
        tbl_roomSearch_list.setModel(mdl_roomSearch_list);

        tbl_roomSearch_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectRoomID = tbl_roomSearch_list.getValueAt(tbl_roomSearch_list.getSelectedRow(), 0).toString();
                fld_roomSearch_roomID.setText(selectRoomID);
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }

        });

        btn_roomSearch_getList.addActionListener(e -> {
            String hotelName = fld_roomSearch_hotelName.getText().toLowerCase();
            String region = fld_roomSearch_region.getText().toLowerCase();
            LocalDate clientEntryDate;
            LocalDate clientLeaveDate;

            try {
                String entryDateText = fld_roomSearch_startDate.getText();
                String leaveDateText = fld_roomSearch_endDate.getText();

                if (!entryDateText.isEmpty() && !leaveDateText.isEmpty()) {
                    clientEntryDate = LocalDate.parse(entryDateText);
                    clientLeaveDate = LocalDate.parse(leaveDateText);
                    btn_roomSearch_StartReservation.setEnabled(true);
                } else {
                    // Varsayılan değerler
                    btn_roomSearch_StartReservation.setEnabled(false);
                    clientEntryDate = null;
                    clientLeaveDate = null;
                }

                ArrayList<RoomManagement> result = RoomManagement.getAvailableRooms(hotelName, region, clientEntryDate, clientLeaveDate);
                System.out.println(result.size());
                if (result.isEmpty()) {
                    Helper.showMsg("Kriterlere göre sonuç bulunamadı");
                } else {
                    loadRoomSearchModel(result);
                    tbl_roomSearch_list.setModel(mdl_roomSearch_list);
                }
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        });
        btn_hotel_info.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_roomSearch_roomID)) {
                Helper.showMsg("Tablodan seçim yapınız");
            } else {
                RoomManagement roomManagement = RoomManagement.getroom(Integer.parseInt(fld_roomSearch_roomID.getText()));
                assert roomManagement != null;
                HotelInfoGUI hotelInfoGUI = new HotelInfoGUI(roomManagement.getHotelID());
            }


        });
        btn_room_info.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_roomSearch_roomID)) {
                Helper.showMsg("Tablodan seçim yapınız");

            } else {
                int roomID = Integer.parseInt(fld_roomSearch_roomID.getText());
                RoomInfoGUI roomInfoGUI = new RoomInfoGUI(roomID);
            }
        });
        btn_roomSearch_StartReservation.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_roomSearch_roomID)) {
                Helper.showMsg("Giriş tarihi ve çıkış tarihini yazdıktan sonra tablodan seçim yapın.");
            } else {
                int roomID=Integer.parseInt(fld_roomSearch_roomID.getText());
                    String entryDateText = fld_roomSearch_startDate.getText();
                    String leaveDateText = fld_roomSearch_endDate.getText();
                    int clientStayDay;
                    if (!entryDateText.isEmpty() && !leaveDateText.isEmpty()) {
                        LocalDate clientEntryDate = LocalDate.parse(entryDateText);
                        LocalDate clientLeaveDate = LocalDate.parse(leaveDateText);
                        clientStayDay=PeriodManagement.daysBetween(clientEntryDate,clientLeaveDate);
                        if (clientStayDay==0){
                            Helper.showMsg("giriş ve çıkış arasında minimum 1 gün olmalıdır.");
                        }else {
                            ReservationGUI reservationGUI=new ReservationGUI(roomID,employee.getId(),clientEntryDate,clientLeaveDate);
                        }
                    }else {Helper.showMsg("Giriş ve Çıkış tarihlerinizi doldurun.Sonrasında aratıp içinden seçiniz.");}


            }

        });

        //--------------------------------- Reservations-----------------------------------------------

        Object[] col_rsr_identifiers={"id","Kullanıcı id","Oda id","Giriş Tarihi","Çıkış Tarihi","Yetişkin Sayı","Çocuk Sayı","Toplam Ücret","Müşteri listesi id"};
        mdl_rsr_list=new DefaultTableModel();
        mdl_rsr_list.setColumnIdentifiers(col_rsr_identifiers);
        row_rsr_list=new Object[col_rsr_identifiers.length];
        loadReservationModel();
        tbl_rsr_list.setModel(mdl_rsr_list);
        tbl_rsr_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectClientList = tbl_rsr_list.getValueAt(tbl_rsr_list.getSelectedRow(), 8).toString();
                fld_clients_id.setText(selectClientList);
                String selectReservationId=tbl_rsr_list.getValueAt(tbl_rsr_list.getSelectedRow(), 0).toString();
                fld_rsr_id.setText(selectReservationId);
                String selectRoomId=tbl_rsr_list.getValueAt(tbl_rsr_list.getSelectedRow(), 2).toString();
                fld_rsr_roomID.setText(selectRoomId);
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }

        });
        btn_rsr_reflesh.addActionListener(e -> {
        loadReservationModel();
        });
        btn_show_client.addActionListener(e -> {
        int clientList= Integer.parseInt(fld_clients_id.getText());
        ClientListGUI clientListGUI=new ClientListGUI(clientList);
        });

        btn_rsr_cancel.addActionListener(e -> {
        if (Helper.confirm("Rezervasyonu İptal edilecek. Onaylıyor musunuz")){
            int deleteReservationId= Integer.parseInt(fld_rsr_id.getText());
            int increaseRoomStockId= Integer.parseInt(fld_rsr_roomID.getText());
            if (RoomManagement.increaseStock(increaseRoomStockId)&& Reservation.delete(deleteReservationId)){
                Helper.showMsg("done");
            }else {
                Helper.showMsg("error");
            }
        }
        });

    }

    private void loadReservationModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_rsr_list.getModel();
        clearModel.setRowCount(0);

        for (Reservation obj : Reservation.getlist()) {
            int i = 0;
            row_rsr_list[i++] = obj.getId();
            row_rsr_list[i++] = obj.getUserID();
            row_rsr_list[i++] = obj.getRoomID();
            row_rsr_list[i++] =obj.getCheckInDate();
            row_rsr_list[i++] =obj.getCheckOutDate();
            row_rsr_list[i++] =obj.getAdultCount();
            row_rsr_list[i++] =obj.getChildCount();
            row_rsr_list[i++] =obj.getTotalPrice();
            row_rsr_list[i++] =obj.getClients_id();
            mdl_rsr_list.addRow(row_rsr_list);
        }
    }

    private void loadRoomSearchModel(ArrayList<RoomManagement> roomManagements) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_roomSearch_list.getModel();
        clearModel.setRowCount(0);

        for (RoomManagement obj : roomManagements) {
            int i = 0;
            row_roomSearch_list[i++] = obj.getId();
            row_roomSearch_list[i++] = Hotel.getHotelByID(obj.getHotelID()).getRegion();
            row_roomSearch_list[i++] = Hotel.getHotelByID(obj.getHotelID()).getHotelName();
            row_roomSearch_list[i++] = obj.getHostelType().getHostelType();
            row_roomSearch_list[i++] = obj.getRoomName();
            row_roomSearch_list[i++] = obj.getPrice() + RoomPrice.getInstance(obj.getHostelType().getHostelType(), obj.getPeriodID()).getPrice();
            row_roomSearch_list[i++] = obj.getPriceChild() + RoomPrice.getInstance(obj.getHostelType().getHostelType(), obj.getPeriodID()).getPriceChild();
            row_roomSearch_list[i++] = PeriodManagement.getByID(obj.getPeriodID()).getStartDate().toString();
            row_roomSearch_list[i++] = PeriodManagement.getByID(obj.getPeriodID()).getEndDate().toString();
            mdl_roomSearch_list.addRow(row_roomSearch_list);
        }
    }

    private void loadPeriodManagementModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_period_list.getModel();
        clearModel.setRowCount(0);

        for (PeriodManagement obj : PeriodManagement.getList()) {
            int i = 0;
            row_periodManagement_list[i++] = obj.getId();
            row_periodManagement_list[i++] = obj.getStartDate();
            row_periodManagement_list[i++] = obj.getEndDate();

            mdl_periodManagement_list.addRow(row_periodManagement_list);
        }
    }

    private void loadRoomManagementModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_roomManagement_list.getModel();
        clearModel.setRowCount(0);

        for (RoomManagement obj : RoomManagement.getList()) {
            int i = 0;
            row_roomManagement_list[i++] = obj.getId();
            row_roomManagement_list[i++] = Hotel.getHotelByID(obj.getHotelID()).getHotelName();
            row_roomManagement_list[i++] = obj.getRoomName();
            row_roomManagement_list[i++] = obj.getStock();
            row_roomManagement_list[i++] = obj.getBedCount();
            row_roomManagement_list[i++] = obj.getTelevision();
            row_roomManagement_list[i++] = obj.getMinibar();
            row_roomManagement_list[i++] = HostelType.getByID(obj.getHostelTypeID()).getHostelType();
            row_roomManagement_list[i++] = PeriodManagement.getByID(obj.getPeriodID()).getStartDate();
            row_roomManagement_list[i++] = PeriodManagement.getByID(obj.getPeriodID()).getEndDate();
            row_roomManagement_list[i++] = obj.getPrice();
            row_roomManagement_list[i++] = obj.getPriceChild();
            mdl_roomManagement_list.addRow(row_roomManagement_list);
        }

    }

    private void loadRoomPriceModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_price.getModel();
        clearModel.setRowCount(0);

        for (RoomPrice obj : RoomPrice.getList()) {
            int i = 0;
            row_roomPrice_list[i++] = obj.getId();
            row_roomPrice_list[i++] = PeriodManagement.getByID(obj.getPeriodID()).getStartDate();
            row_roomPrice_list[i++] = PeriodManagement.getByID(obj.getPeriodID()).getEndDate();
            row_roomPrice_list[i++] = obj.getHostelTypeName();
            row_roomPrice_list[i++] = obj.getPrice();
            row_roomPrice_list[i++] = obj.getPriceChild();
            mdl_roomPrice_list.addRow(row_roomPrice_list);
        }
    }

    private void loadHostelTypeModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hostelType_list.getModel();
        clearModel.setRowCount(0);

        for (HostelType obj : HostelType.getList()) {
            int i = 0;
            row_hostelType_list[i++] = obj.getId();
            row_hostelType_list[i++] = obj.getHotelID();
            row_hostelType_list[i++] = Hotel.getHotelByID(obj.getHotelID()).getHotelName();
            row_hostelType_list[i++] = obj.getHostelType();
            mdl_hostelType_list.addRow(row_hostelType_list);
        }
    }

    private void loadHotelModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);

        for (Hotel obj : Hotel.getList()) {
            int i = 0;
            row_hotel_list[i++] = obj.getId();
            row_hotel_list[i++] = obj.getHotelName();
            row_hotel_list[i++] = obj.getAddress();
            row_hotel_list[i++] = obj.getEmail();
            row_hotel_list[i++] = obj.getPhone();
            row_hotel_list[i++] = obj.getStarRating();
            row_hotel_list[i++] = obj.getFacilityFeatures();
            row_hotel_list[i++] = obj.getRegion();
            mdl_hotel_list.addRow(row_hotel_list);
        }

    }

    private void loadPeriodManagement(JComboBox jComboBox) {
        jComboBox.removeAllItems();
        for (PeriodManagement obj : PeriodManagement.getList()) {
            jComboBox.addItem(new Item(obj.getId(), obj.getStartDate().toString(), obj.getEndDate().toString()));
        }
    }

    public void loadHotelCombo(JComboBox jComboBox) {
        jComboBox.removeAllItems();
        for (Hotel obj : Hotel.getList()) {
            jComboBox.addItem(new Item(obj.getId(), obj.getHotelName()));
        }
    }

    public void loadHostelTypeByHotelID(JComboBox jComboBox, int hotel_id) {

        jComboBox.removeAllItems();
        for (HostelType obj : HostelType.getListByHotelID(hotel_id)) {
            jComboBox.addItem(new Item(obj.getId(), obj.getHotelID(), obj.getHostelType()));
        }
        jComboBox.revalidate();
        jComboBox.repaint();
    }

}
