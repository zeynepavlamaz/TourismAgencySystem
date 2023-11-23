package tourism_agency.View;

import tourism_agency.Helper.Config;
import tourism_agency.Helper.Helper;
import tourism_agency.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReservationDetail extends  JFrame{
    private JPanel wrapper;
    private JTable table_hotel_info;
    private JTable table_hotel_feat;
    private JTable table_customer_info;
    private JButton okeyButton;
    private JTable table_room_feat;
    private JTable table_room_info;
    private DefaultTableModel model_res_hotel_inf;
    private Object[] row_res_hotel_inf_list;
    private DefaultTableModel model_res_hotel_fet;
    private Object[] row_res_hotel_fet_list;

    private DefaultTableModel model_room_info;
    private Object[] row_room_info;
    private DefaultTableModel model_room_feat;
    private Object[] row_room_feat;
    private DefaultTableModel model_customer;
    private Object[] row_customer;

    public ReservationDetail(){
        add(wrapper);
        setSize(1000,800);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);


        model_res_hotel_inf = new DefaultTableModel();
        Object[] col_res_hotel_inf_list = {"Hotel","City","Region","Address","E-mail","Phone","Star"};
        model_res_hotel_inf.setColumnIdentifiers(col_res_hotel_inf_list);
        row_res_hotel_inf_list = new Object[col_res_hotel_inf_list.length];
        table_hotel_info.setModel(model_res_hotel_inf);
        table_hotel_info.getTableHeader().setReorderingAllowed(false);
        loadHotelInfo();

        model_res_hotel_fet = new DefaultTableModel();
        Object[] col_res_hotel_fet_list = {"Features"};
        model_res_hotel_fet.setColumnIdentifiers(col_res_hotel_fet_list);
        row_res_hotel_fet_list = new Object[col_res_hotel_fet_list.length];
        table_hotel_feat.setModel(model_res_hotel_fet);
        table_hotel_feat.getTableHeader().setReorderingAllowed(false);
        loadHotelFeat();

        model_room_feat = new DefaultTableModel();
        Object[] col_room = {"Features"};
        model_room_feat.setColumnIdentifiers(col_room);
        row_room_feat = new Object[col_room.length];
        table_room_feat.setModel(model_room_feat);
        table_room_feat.getTableHeader().setReorderingAllowed(false);
        loadRoomFeatures();

        model_room_info = new DefaultTableModel();
        Object[] col_room_info = {"Adult Price","Child Price","Bed"};
        model_room_info.setColumnIdentifiers(col_room_info);
        row_room_info = new Object[col_room_info.length];
        table_room_info.setModel(model_room_info);
        table_room_info.getTableHeader().setReorderingAllowed(false);
        loadRoomInfo();

        model_customer = new DefaultTableModel();
        Object[] col_customer = {"Name-Surname","E-mail","Phone","Adult Number","Child Number","Total Price"};
        model_customer.setColumnIdentifiers(col_customer);
        row_customer= new Object[col_customer.length];
        table_customer_info.setModel(model_customer);
        table_customer_info.getTableHeader().setReorderingAllowed(false);
        loadCustomer();

        okeyButton.addActionListener(e -> {
            dispose();
        });
    }

    private void loadCustomer() {
        DefaultTableModel clearModel = (DefaultTableModel) table_customer_info.getModel();
        clearModel.setRowCount(0);
        for (Customer customer : Customer.getListByID(UserGUI.reserveID)){
            row_customer[0]= customer.getNameSurname();
            row_customer[1]= customer.getEmail();
            row_customer[2]= customer.getPhoneNumber();
            row_customer[3]= customer.getAdultNum();
            row_customer[4]= customer.getChildNum();
            row_customer[5]= customer.getTotalPrice();
            model_customer.addRow(row_customer);
        }
    }

    private void loadRoomInfo() {
        DefaultTableModel clearModel = (DefaultTableModel) table_room_info.getModel();
        clearModel.setRowCount(0);
        for (Room room : Room.getRoomPrices(UserGUI.selected_res_room_id)){
            row_room_info[0]= room.getAdult_price();
            row_room_info[1]= room.getChild_price();
            row_room_info[2]= room.getBed();
            model_room_info.addRow(row_room_info);
        }
    }

    private void loadRoomFeatures() {
        DefaultTableModel clearModel = (DefaultTableModel) table_room_feat.getModel();
        clearModel.setRowCount(0);
        for (RoomFeatures feature : RoomFeatures.getRoomFeatures(UserGUI.selected_res_room_id)){
            row_room_feat[0]= feature.getFeatures();
            model_room_feat.addRow(row_room_feat);
        }
    }

    private void loadHotelFeat() {
        DefaultTableModel clearModel = (DefaultTableModel) table_hotel_feat.getModel();
        clearModel.setRowCount(0);
        for (HotelFeatures feature : HotelFeatures.getHotelFeatures(UserGUI.selected_res_room_id)){
            row_res_hotel_fet_list[0]= feature.getFeatures();
            model_res_hotel_fet.addRow(row_res_hotel_fet_list);
        }
    }

    public void loadHotelInfo(){
        DefaultTableModel clearModel = (DefaultTableModel) table_hotel_info.getModel();
        clearModel.setRowCount(0);
        for (Hotel hotel : Hotel.getHotelList(UserGUI.selected_res_room_id)){
            row_res_hotel_inf_list[0]= hotel.getName();
            row_res_hotel_inf_list[1]= hotel.getCity();
            row_res_hotel_inf_list[2]= hotel.getRegion();
            row_res_hotel_inf_list[3]= hotel.getAdress();
            row_res_hotel_inf_list[4]= hotel.getEmail();
            row_res_hotel_inf_list[5]= hotel.getPhone_number();
            row_res_hotel_inf_list[6]= hotel.getStar();
            model_res_hotel_inf.addRow(row_res_hotel_inf_list);
        }

    }

}
