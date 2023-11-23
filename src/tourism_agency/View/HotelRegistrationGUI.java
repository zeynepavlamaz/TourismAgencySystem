package tourism_agency.View;

import tourism_agency.Helper.Config;
import tourism_agency.Helper.Helper;
import tourism_agency.Model.Hotel;
import tourism_agency.Model.Season;

import javax.swing.*;

public class HotelRegistrationGUI extends JFrame{
    private JPanel wrapper;
    private JTextField field_add_hotel_name;
    private JTextField field_add_city;
    private JTextField field_add_region;
    private JTextField field_add_adress;
    private JTextField field_add_email;
    private JTextField field_add_phone;
    private JTextField field_add_star;
    private JCheckBox wifiCheckBox;
    private JButton button_create_hotel;
    private JPanel panel_add_hotel;
    private JButton button_exit_hotel_reg;
    private JButton saveSeasonButton;

    private JButton button_add_features;
    private  int id;

    public HotelRegistrationGUI(){
        add(wrapper);
        setSize(700,700);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(false);



        button_create_hotel.addActionListener(e -> {
            if(Helper.isFieldEmpty(field_add_adress)||
               Helper.isFieldEmpty(field_add_city) ||
               Helper.isFieldEmpty(field_add_email)||
               Helper.isFieldEmpty(field_add_phone)||
               Helper.isFieldEmpty(field_add_star)||
               Helper.isFieldEmpty(field_add_hotel_name)||
               Helper.isFieldEmpty(field_add_region)){
                Helper.showMsg("fill");
            }else {
                String name = field_add_hotel_name.getText();
                String city = field_add_city.getText();
                String adress = field_add_adress.getText();
                String email = field_add_email.getText();
                String phone = field_add_phone.getText();
                int star = Integer.parseInt(field_add_star.getText());
                String region = field_add_region.getText();
                if (Hotel.add(name,city,region,adress,email,phone,star) ){
                    Helper.showMsg("add");
                    dispose();
                }else {
                    Helper.showMsg("error");
                }
            }
        });
        button_exit_hotel_reg.addActionListener(e -> dispose());
    }

}
