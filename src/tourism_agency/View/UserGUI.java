package tourism_agency.View;

import tourism_agency.Helper.Config;
import tourism_agency.Helper.Helper;
import tourism_agency.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UserGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane panel_user;
    private JButton button_user_exit;
    private JPanel panel_top;
    private JTable table_hotel_list;
    private JButton button_add_hotel;
    private JLabel label_welcome;
    private JScrollPane panel_scroll;
    private JButton button_add_hotel_feature;
    private JTextField field_hotel_settings;
    private JPanel panel_room;
    private JPanel panel_hotel;
    private JScrollPane panel_scroll_room;
    private JTable table_room_list;
    private JButton button_add_room;
    private JTextField field_select_room;
    private JButton button_add_room_feature;
    private JButton button_add_season;
    private JTextField field_search;
    private JTextField field_search_city;
    private JTextField field_search_hotel;
    private JButton search_room;
    private JTextField field_check_in;
    private JTextField field_check_out;
    private JButton refreshButton;
    private JButton button_reservation;
    private JPanel panel_reservation;
    private JTable table_reservation_list;
    private JTextField field_selected_res;
    private JButton button_detail;
    private JButton refreshButton1;
    private JButton deleteReservationButton;
    private JButton deleteHotelButton;
    private JButton refreshListButton;
    private JButton button_new_room;
    private DefaultTableModel model_hotel_list;
    private Object[] row_hotel_list;
    public static int selected_hotel_id;
    public static String selected_hotel_name;

    private DefaultTableModel model_room_list;
    private Object[] row_room_list;
    private DefaultTableModel model_reservation_list;
    private Object[] row_reservation_list;
    public static int selected_room_id;
    public static String selected_room_name;
    public static int selected_room_hotel_id;
    public static int selected_room_pension_id;
    public static String checkInText;
    public static String checkOutText;
    public static int selected_res_hotel_id;
    public static int selected_res_room_id;
    public static int reserveID;

    public UserGUI() {
        add(wrapper);
        setSize(1000, 600);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);


        table_room_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                selected_room_id = (int) table_room_list.getValueAt(table_room_list.getSelectedRow(), 0);
                selected_room_name = table_room_list.getValueAt(table_room_list.getSelectedRow(), 1).toString();
                selected_room_hotel_id = Hotel.getFetchHotelID((int) table_room_list.getValueAt(table_room_list.getSelectedRow(), 0));
                selected_room_pension_id = Pension.getFetcPensionID(UserGUI.selected_room_id);
                field_select_room.setText(selected_room_name);
            } catch (Exception exp) {

            }
        });

        table_reservation_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                reserveID = Integer.parseInt(table_reservation_list.getValueAt(table_reservation_list.getSelectedRow(), 0).toString());
                selected_res_hotel_id = Integer.parseInt(table_reservation_list.getValueAt(table_reservation_list.getSelectedRow(), 1).toString());
                selected_res_room_id = Integer.parseInt(table_reservation_list.getValueAt(table_reservation_list.getSelectedRow(), 2).toString());
                field_selected_res.setText(String.valueOf(reserveID));
            } catch (Exception exp) {

            }
        });


        table_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                selected_hotel_id = (int) table_hotel_list.getValueAt(table_hotel_list.getSelectedRow(), 0);
                selected_hotel_name = table_hotel_list.getValueAt(table_hotel_list.getSelectedRow(), 1).toString();
                field_hotel_settings.setText(selected_hotel_name);
            } catch (Exception exp) {

            }
        });

        button_add_hotel.addActionListener(e -> {
            new HotelRegistrationGUI();
        });

        model_hotel_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_hotel_list = {"ID", "Hotel", "City", "Region", "Adress", "Email", "Phone", "Star"};
        model_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list = new Object[col_hotel_list.length];
        loadHotelModel();
        table_hotel_list.setModel(model_hotel_list);
        table_hotel_list.getTableHeader().setReorderingAllowed(false);

        model_room_list = new DefaultTableModel();
        Object[] col_room_list = {"ID", "Hotel", "City", "Region", "Room", "Season", "Adult Price", "Child Price"};
        model_room_list.setColumnIdentifiers(col_room_list);
        row_room_list = new Object[col_room_list.length];
        table_room_list.setModel(model_room_list);
        table_room_list.getTableHeader().setReorderingAllowed(false);
        table_room_list.getColumnModel().getColumn(0).setMaxWidth(60);

        model_reservation_list = new DefaultTableModel();
        Object[] col_reservation_list = {"ID", "Hotel_ID", "Room_ID", "Name-Surname", "E-mail", "Phone", "Adult Res", "Child Res", "Total Price"};
        model_reservation_list.setColumnIdentifiers(col_reservation_list);
        row_reservation_list = new Object[col_reservation_list.length];
        loadReservationModel();
        table_reservation_list.setModel(model_reservation_list);
        table_reservation_list.getTableHeader().setReorderingAllowed(false);
        table_reservation_list.getColumnModel().getColumn(0).setMaxWidth(60);


        button_add_hotel_feature.addActionListener(e -> {

            if (field_hotel_settings.getText().isEmpty()) {
                Helper.showMsg("Please select a hotel from the list to continue !");
            } else {
                new HotelFeatureAddGUI();
            }
        });
        button_user_exit.addActionListener(e -> {
            dispose();
        });
        button_add_room.addActionListener(e -> {
            if (field_hotel_settings.getText().isEmpty()) {
                Helper.showMsg("Please select a hotel from the list to continue !");
            } else {
                new RoomAddGUI();
            }
        });
        button_add_room_feature.addActionListener(e -> {
            if (field_select_room.getText().isEmpty()) {
                Helper.showMsg("Please select a hotel from the list to continue !");
            } else {
                new RoomFeatureAddGUI();
            }

        });
        button_add_season.addActionListener(e -> {
            new HotelSeasonAddGUI();
        });


        search_room.addActionListener(e -> {
            String searchText = field_search.getText();
            checkInText = field_check_in.getText();
            if (!checkInText.isEmpty()) {
                checkInText = LocalDate.parse(checkInText, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

            }
            checkOutText = field_check_out.getText();
            if (!checkOutText.isEmpty()) {
                checkOutText = LocalDate.parse(checkOutText, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
            }
            String query = Room.searchQuery(searchText, checkInText, checkOutText);
            ArrayList<RoomTableDto> rooms = Room.searchRoomList(query);
            loadRoomModel(rooms);

        });


        refreshButton.addActionListener(e -> loadRoomModel());


        button_reservation.addActionListener(e -> {
            if (field_check_in.getText().isEmpty() || field_check_in.getText().equals("dd/MM/yyyy") || field_check_out.getText().isEmpty() ||field_check_out.getText().equals("dd/MM/yyyy")){
                Helper.showMsg("Please enter check-in and check-out dates !");
            }else {
                new ReservationGUI();
            }

        });
        refreshButton1.addActionListener(e -> {
            loadReservationModel();
        });
        button_detail.addActionListener(e -> {
            if (field_selected_res.getText().isEmpty()) {
                Helper.showMsg("Please select reservation from list to continue !");
            } else {
                new ReservationDetail();
            }

        });
        deleteReservationButton.addActionListener(e -> {
            if (Helper.isFieldEmpty(field_selected_res)){
                Helper.showMsg("fill");
            }else {
                if (Helper.confirm("sure")){
                    if (Customer.resDelete(reserveID)){
                    Helper.showMsg("Selected reservation deleted from the list !");
                    loadReservationModel();
                    Room.incrementRoomStock(selected_res_room_id);
                    field_selected_res.setText(null);
                }else {
                    Helper.showMsg("error");
                }
            }
        }

        });
        deleteHotelButton.addActionListener(e -> {
            if (Helper.isFieldEmpty(field_hotel_settings)){
                Helper.showMsg("fill");
            }else {
                if (Helper.confirm("sure")){
                    if (Hotel.hotelDelete(selected_hotel_id)){
                        Helper.showMsg("Selected reservartion deleted from the list !");
                        loadHotelModel();
                        HotelFeatures.hotelFeatureDelete(selected_hotel_id);
                        Pension.pensionDelete(selected_hotel_id);
                        Room.roomDelete(selected_hotel_id);
                        Season.seasonDelete(selected_hotel_id);
                        Customer.resDeleteByHotelID(selected_hotel_id);
                        field_hotel_settings.setText(null);
                    }else {
                        Helper.showMsg("error");
                    }
                }
            }

        });
        refreshListButton.addActionListener(e -> {
            loadHotelModel();
        });
    }

    private void loadRoomModel() {
        DefaultTableModel clearModel = (DefaultTableModel) table_room_list.getModel();
        clearModel.setRowCount(0);
        for (Room room : Room.getList()) {
            row_room_list[0] = room.getId();
            row_room_list[1] = Hotel.getFetchHotelName(room.getHotel_id());
            row_room_list[2] = Hotel.getFetchHotelCity(room.getHotel_id());
            row_room_list[3] = Hotel.getFetchHotelRegion(room.getHotel_id());
            row_room_list[4] = Pension.getFetchPension(room.getPension_id());
            row_room_list[5] = Season.getFetchSeason(room.getSeason_id());
            row_room_list[6] = room.getAdult_price();
            row_room_list[7] = room.getChild_price();
            model_room_list.addRow(row_room_list);
        }
    }


    private void loadRoomModel(ArrayList<RoomTableDto> roomList) {
        DefaultTableModel clearModel = (DefaultTableModel) table_room_list.getModel();
        clearModel.setRowCount(0);
        for (RoomTableDto room : roomList) {
            row_room_list[0] = room.getId();
            row_room_list[1] = room.getName();
            row_room_list[2] = room.getCity();
            row_room_list[3] = room.getRegion();
            row_room_list[4] = room.getPension();
            row_room_list[5] = room.getSeason_type();
            row_room_list[6] = room.getAdult_price();
            row_room_list[7] = room.getChild_price();
            model_room_list.addRow(row_room_list);
        }
    }


    public void loadHotelModel() {
        DefaultTableModel clearModel = (DefaultTableModel) table_hotel_list.getModel();
        clearModel.setRowCount(0);

        for (Hotel hotel : Hotel.getList()) {
            row_hotel_list[0] = hotel.getId();
            row_hotel_list[1] = hotel.getName();
            row_hotel_list[2] = hotel.getCity();
            row_hotel_list[3] = hotel.getRegion();
            row_hotel_list[4] = hotel.getAdress();
            row_hotel_list[5] = hotel.getEmail();
            row_hotel_list[6] = hotel.getPhone_number();
            row_hotel_list[7] = hotel.getStar();
            model_hotel_list.addRow(row_hotel_list);
        }
    }


    public void loadReservationModel() {
        DefaultTableModel clearModel = (DefaultTableModel) table_reservation_list.getModel();
        clearModel.setRowCount(0);
        for (Customer customer : Customer.getList()) {
            row_reservation_list[0] = customer.getId();
            row_reservation_list[1] = customer.getHotel_id();
            row_reservation_list[2] = customer.getRoom_id();
            row_reservation_list[3] = customer.getNameSurname();
            row_reservation_list[4] = customer.getEmail();
            row_reservation_list[5] = customer.getPhoneNumber();
            row_reservation_list[6] = customer.getAdultNum();
            row_reservation_list[7] = customer.getChildNum();
            row_reservation_list[8] = customer.getTotalPrice();
            model_reservation_list.addRow(row_reservation_list);
        }

    }

}
