package tourism_agency.View;

import tourism_agency.Helper.Config;
import tourism_agency.Helper.Helper;
import tourism_agency.Model.HotelFeatures;

import javax.swing.*;

public class HotelFeatureAddGUI extends JFrame {
    private JPanel wrapper;
    private JCheckBox check_wifi;
    private JCheckBox check_park;
    private JCheckBox check_SPA;
    private JTextField field_hotel_feature;
    private JButton button_apply_features;
    private JCheckBox check_pool;
    private JCheckBox check_fitness;
    private JCheckBox check_concierge;
    private JCheckBox check_7_24;
    private JCheckBox check_ultra;
    private JCheckBox check_full;
    private JCheckBox check_room_service;
    private JCheckBox check_half_pension;
    private JCheckBox check_bed;
    private JCheckBox check_no_alcohol;
    private JButton button_apply_pension;
    private JButton exitButton;

    public HotelFeatureAddGUI(){
        add(wrapper);
        setSize(800,400);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);

        field_hotel_feature.setText(UserGUI.selected_hotel_name);


        button_apply_features.addActionListener(e -> {
            if (check_wifi.isSelected()){
                HotelFeatures.add(UserGUI.selected_hotel_id,"Wifi");
            }if (check_7_24.isSelected()){
                HotelFeatures.add(UserGUI.selected_hotel_id,"7/24 Service");
            }if (check_concierge.isSelected()){
                HotelFeatures.add(UserGUI.selected_hotel_id,"Concierge");
            }if (check_fitness.isSelected()){
                HotelFeatures.add(UserGUI.selected_hotel_id,"Fitness");
            }if (check_park.isSelected()){
                HotelFeatures.add(UserGUI.selected_hotel_id,"Park");
            }if (check_pool.isSelected()){
                HotelFeatures.add(UserGUI.selected_hotel_id,"Pool");
            }if (check_SPA.isSelected()){
                HotelFeatures.add(UserGUI.selected_hotel_id,"SPA");
            }
            Helper.showMsg("done");
            check_wifi.setSelected(false);
            check_7_24.setSelected(false);
            check_concierge.setSelected(false);
            check_fitness.setSelected(false);
            check_park.setSelected(false);
            check_pool.setSelected(false);
            check_SPA.setSelected(false);

        });

        button_apply_pension.addActionListener(e -> {
            if (check_bed.isSelected()){
                HotelFeatures.addPension(UserGUI.selected_hotel_id,"Only Bed");
            }if (check_full.isSelected()){
                HotelFeatures.addPension(UserGUI.selected_hotel_id,"Full Pension");
            }if (check_room_service.isSelected()){
                HotelFeatures.addPension(UserGUI.selected_hotel_id,"Room Service");
            }if (check_half_pension.isSelected()){
                HotelFeatures.addPension(UserGUI.selected_hotel_id,"Half Pension");
            }if (check_ultra.isSelected()){
                HotelFeatures.addPension(UserGUI.selected_hotel_id,"Ultra Pension");
            }if (check_no_alcohol.isSelected()){
                HotelFeatures.addPension(UserGUI.selected_hotel_id,"No Alcohol Full Credit");
            }
            Helper.showMsg("done");

            check_bed.setSelected(false);
            check_full.setSelected(false);
            check_room_service.setSelected(false);
            check_half_pension.setSelected(false);
            check_ultra.setSelected(false);
            check_no_alcohol.setSelected(false);
        });
        exitButton.addActionListener(e -> {
            dispose();
        });
    }
}
