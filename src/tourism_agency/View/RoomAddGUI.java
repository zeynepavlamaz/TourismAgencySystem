package tourism_agency.View;

import tourism_agency.Helper.Config;
import tourism_agency.Helper.Helper;
import tourism_agency.Model.Pension;
import tourism_agency.Model.Room;
import tourism_agency.Model.Season;

import javax.swing.*;

public class RoomAddGUI extends  JFrame{
    private JPanel wrapper;
    private JPanel panel_top;
    private JPanel panel_room_add;
    private JTextField field_selected_hotel;
    private JTextField field_add_stock;
    private JButton button_add_room;
    private JComboBox combo_room_season;
    private JComboBox combo_room_pension_type;
    private JTextField field_room_adult_price;
    private JComboBox combo_room_type;
    private JTextField field_room_child_price;
    private JComboBox combo_room_add_bed;

    private JComboBox combo_room_season_end;

    public RoomAddGUI(){
        add(wrapper);
        setSize(800,400);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);
        loadSeasonCombo();
        loadPensionCombo();
        field_selected_hotel.setText(UserGUI.selected_hotel_name);


       button_add_room.addActionListener(e -> {
            if (Helper.isFieldEmpty(field_add_stock)||
                Helper.isFieldEmpty(field_room_adult_price)){
                Helper.showMsg("fill");
            }else {
                String type = combo_room_type.getSelectedItem().toString();
                int stock = Integer.parseInt(field_add_stock.getText());
                int seasonID = Season.getFetchSeasonID(combo_room_season.getSelectedItem().toString(),UserGUI.selected_hotel_id) ;
                int pensionID = Pension.getFetcPensionID(combo_room_pension_type.getSelectedItem().toString(),UserGUI.selected_hotel_id);
                int adult_price = Integer.parseInt(field_room_adult_price.getText());
                int child_price = Integer.parseInt(field_room_child_price.getText());
                int bed = Integer.parseInt(combo_room_add_bed.getSelectedItem().toString());
                if (Room.add(type,UserGUI.selected_hotel_id, seasonID,pensionID,stock,adult_price,child_price,bed)){
                    Helper.showMsg("done");
                    loadPensionCombo();
                    loadSeasonCombo();
                }

            }
        });
    }
    public void loadSeasonCombo(){
        combo_room_season.removeAllItems();
        for (Season season : Season.getSeasonList(UserGUI.selected_hotel_id)){
            String seasonTime = season.getSeason_type();
            combo_room_season.addItem(seasonTime);
        }
    }

    public void loadPensionCombo(){
        combo_room_pension_type.removeAllItems();
        for (Pension pension : Pension.getPensionList(UserGUI.selected_hotel_id)){
            combo_room_pension_type.addItem(pension.getPension());
        }
    }
}
