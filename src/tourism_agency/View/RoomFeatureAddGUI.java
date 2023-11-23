package tourism_agency.View;

import tourism_agency.Helper.Config;
import tourism_agency.Helper.Helper;
import tourism_agency.Model.HotelFeatures;

import javax.swing.*;

public class RoomFeatureAddGUI extends JFrame {

    private JPanel wrapper;
    private JButton exitButton;
    private JComboBox combo_bed_number;
    private JCheckBox check_TV;
    private JCheckBox check_console;
    private JCheckBox check_air;
    private JCheckBox check_case;
    private JTextField field_selected_room_name;
    private JButton button_room_feature_apply;

    public RoomFeatureAddGUI(){
            add(wrapper);
            setSize(800,400);
            setLocation(Helper.screenCenter("x", getSize()),Helper.screenCenter("y",getSize()));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setTitle(Config.PROJECT_TITLE);
            setVisible(true);
            setResizable(true);

        field_selected_room_name.setText(UserGUI.selected_room_name);

        button_room_feature_apply.addActionListener(e -> {

            if (check_air.isSelected()){
                HotelFeatures.addRoomFeature(UserGUI.selected_room_id,"Air Conditioner");
            }if (check_case.isSelected()){
                HotelFeatures.addRoomFeature(UserGUI.selected_room_id,"Safe Case");
            }if (check_console.isSelected()){
                HotelFeatures.addRoomFeature(UserGUI.selected_room_id,"Gaming Console");
            }if (check_TV.isSelected()){
                HotelFeatures.addRoomFeature(UserGUI.selected_room_id,"TV");
            }
            Helper.showMsg("done");
            check_TV.setSelected(false);
            check_case.setSelected(false);
            check_console.setSelected(false);
            check_air.setSelected(false);
        });
        exitButton.addActionListener(e -> {
            dispose();
        });
    }

}
