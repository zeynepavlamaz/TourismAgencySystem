package tourism_agency.View;

import tourism_agency.Helper.Config;
import tourism_agency.Helper.Helper;
import tourism_agency.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ReservationGUI extends JFrame {
    private JPanel wrapper;
    private JTable table_hotel_info_res;
    private JTable table_room_feat_res;
    private JTextField field_room_type_res;
    private JTextField field_check_in_res;
    private JTextField field_check_out_res;
    private JTextField field_acco_day;
    private JComboBox combo_room_pen_type;
    private JTextField field_total_price;
    private JButton applyReservationButton;
    private JTable table_hotel_feat_res;
    private JTable table_room_prices_res;
    private JTextField field_pen_type_res;
    private JTextField field_adult;
    private JTextField field_child;
    private JComboBox combo_adult;
    private JComboBox combo_child;
    private JComboBox combo_acco;
    private JTextArea area_hotel_features;
    private DefaultTableModel model_hotel_list;
    private Object[] row_hotel_list;

    private DefaultTableModel model_hotel_feat_list;
    private Object[] row_hotel_feat_list;
    private DefaultTableModel model_room_feat_list;
    private Object[] row_roomhotel_feat_list;
    private DefaultTableModel model_room_price_list;
    private Object[] row_room_price_list;
    public static int accomDay;
    public static int adultNum;
    public static int childNum;
    public static int totalPrice;
    public ReservationGUI(){
        add(wrapper);
        setSize(1000,800);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);
        field_check_in_res.setText(String.valueOf(UserGUI.checkInText));
        field_check_out_res.setText(String.valueOf(UserGUI.checkOutText));
        field_room_type_res.setText(Room.getFetchRoomType(UserGUI.selected_room_id));
        field_pen_type_res.setText(Pension.getFetchPensionType(UserGUI.selected_room_pension_id));

        model_hotel_feat_list = new DefaultTableModel();
        Object[] col_hotel_feat_list = {"Features"};
        model_hotel_feat_list.setColumnIdentifiers(col_hotel_feat_list);
        row_hotel_feat_list = new Object[col_hotel_feat_list.length];
        loadHotelFeaturesModel();
        table_hotel_feat_res.setModel(model_hotel_feat_list);
        table_hotel_feat_res.getTableHeader().setReorderingAllowed(false);


        model_room_feat_list = new DefaultTableModel();
        Object[] col_room_feat_list = {"Features"};
        model_room_feat_list.setColumnIdentifiers(col_room_feat_list);
        row_roomhotel_feat_list = new Object[col_room_feat_list.length];
        loadHotelRoomFeaturesModel();
        table_room_feat_res.setModel(model_room_feat_list);
        table_room_feat_res.getTableHeader().setReorderingAllowed(false);

        model_room_price_list = new DefaultTableModel();
        Object[] col_room_price_list = {"Adult Price","Child Price","Bed"};
        model_room_price_list.setColumnIdentifiers(col_room_price_list);
        row_room_price_list = new Object[col_room_price_list.length];
        loadHotelRoomPricesModel();
        table_room_prices_res.setModel(model_room_price_list);
        table_room_prices_res.getTableHeader().setReorderingAllowed(false);


        model_hotel_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_hotel_list = {"Hotel","City","Region","Adress","Email","Phone","Star"};
        model_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list = new Object[col_hotel_list.length];
        loadUserModel();
        table_hotel_info_res.setModel(model_hotel_list);
        table_hotel_info_res.getTableHeader().setReorderingAllowed(false);


        applyReservationButton.addActionListener(e -> {
            int adultNumber = Integer.parseInt(combo_adult.getSelectedItem().toString());
            int childNumber = Integer.parseInt(combo_child.getSelectedItem().toString());
            int bed = Room.getFetchBed(UserGUI.selected_room_id);

            if ((adultNumber+childNumber)>bed){
                Helper.showMsg("Number of bed in the room is not enough!");
            }else {
                new CustomerAddGUI();
            }

        });

        combo_acco.addActionListener(e -> {
            int accoDay = Integer.parseInt(combo_acco.getSelectedItem().toString());
            accomDay =accoDay;
            int adultPrice = Integer.parseInt(combo_adult.getSelectedItem().toString())*Room.getFetchAdultPrice(UserGUI.selected_room_id);
            adultNum = Integer.parseInt(combo_adult.getSelectedItem().toString());
            int childPrice = Integer.parseInt(combo_child.getSelectedItem().toString())*Room.getFetchChildPrice(UserGUI.selected_room_id);
            childNum = Integer.parseInt(combo_child.getSelectedItem().toString());
            int total = accoDay*(adultPrice+childPrice);
            totalPrice = total;
            field_total_price.setText(String.valueOf(total));

        });
    }




    public void loadUserModel(){
        DefaultTableModel clearModel = (DefaultTableModel) table_hotel_info_res.getModel();
        clearModel.setRowCount(0);
        for (Hotel hotel : Hotel.getHotelList(UserGUI.selected_room_id)){
            row_hotel_list[0]= hotel.getName();
            row_hotel_list[1]= hotel.getCity();
            row_hotel_list[2]= hotel.getRegion();
            row_hotel_list[3]= hotel.getAdress();
            row_hotel_list[4]= hotel.getEmail();
            row_hotel_list[5]= hotel.getPhone_number();
            row_hotel_list[6]= hotel.getStar();
            model_hotel_list.addRow(row_hotel_list);
        }

    }

    private void loadHotelRoomFeaturesModel() {
        DefaultTableModel clearModel = (DefaultTableModel) table_room_feat_res.getModel();
        clearModel.setRowCount(0);
        for (RoomFeatures feature : RoomFeatures.getRoomFeatures(UserGUI.selected_room_id)){
            row_roomhotel_feat_list[0]= feature.getFeatures();
            model_room_feat_list.addRow(row_roomhotel_feat_list);
        }
    }

    public void loadHotelFeaturesModel(){
        DefaultTableModel clearModel = (DefaultTableModel) table_hotel_feat_res.getModel();
        clearModel.setRowCount(0);
        for (HotelFeatures feature : HotelFeatures.getHotelFeatures(UserGUI.selected_room_id)){
             row_hotel_feat_list[0]= feature.getFeatures();
             model_hotel_feat_list.addRow(row_hotel_feat_list);
        }

    }
    private void loadHotelRoomPricesModel() {
        DefaultTableModel clearModel = (DefaultTableModel) table_room_prices_res.getModel();
        clearModel.setRowCount(0);
        for (Room room : Room.getRoomPrices(UserGUI.selected_room_id)){
            row_room_price_list[0]= room.getAdult_price();
            row_room_price_list[1]= room.getChild_price();
            row_room_price_list[2]= room.getBed();
            model_room_price_list.addRow(row_room_price_list);
        }
    }




}
