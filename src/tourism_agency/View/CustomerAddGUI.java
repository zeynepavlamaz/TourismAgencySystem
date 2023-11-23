package tourism_agency.View;

import tourism_agency.Helper.Config;
import tourism_agency.Helper.Helper;
import tourism_agency.Model.Customer;
import tourism_agency.Model.Room;
import tourism_agency.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerAddGUI extends  JFrame{
    private JPanel wrapper;
    private JTextField field_customer_name;
    private JTextField field_customer_mail;
    private JTextField field_customer_number;
    private JTextField field_customer_tc;
    private JButton saveReservationButton;
    private JTextField field_total_price;

    public CustomerAddGUI(){

        add(wrapper);
        setSize(700,700);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);
        field_total_price.setText(String.valueOf(ReservationGUI.totalPrice));

        saveReservationButton.addActionListener(e -> {
            if(Helper.isFieldEmpty(field_customer_name) || Helper.isFieldEmpty(field_customer_mail) || Helper.isFieldEmpty(field_customer_tc)||Helper.isFieldEmpty(field_customer_number)){
                Helper.showMsg("fill");
            }else {
                int hotel_id = UserGUI.selected_room_hotel_id;
                int room_id = UserGUI.selected_room_id;
                String nameSurname = field_customer_name.getText();
                String email = field_customer_mail.getText();
                String phone = field_customer_number.getText();
                String ID = field_customer_tc.getText();
                int adultNumber = ReservationGUI.adultNum;
                int childNumber = ReservationGUI.childNum;
                int totalPrice = ReservationGUI.totalPrice;

                if (Customer.add(hotel_id,room_id,nameSurname,email,phone,ID,adultNumber,childNumber,totalPrice)){
                    Helper.showMsg("add");
                    field_customer_number.setText(null);
                    field_customer_mail.setText(null);
                    field_customer_name.setText(null);
                    field_customer_tc.setText(null);
                }else {
                    Helper.showMsg("error");
                }

                Room.updateRoomStock(UserGUI.selected_room_id);
                dispose();
            }
        });
    }
}
