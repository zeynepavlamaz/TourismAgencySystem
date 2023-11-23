package tourism_agency.View;

import tourism_agency.Helper.Config;
import tourism_agency.Helper.Helper;
import tourism_agency.Model.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HotelSeasonAddGUI extends JFrame{
    private JPanel wrapper;
    private JTextField field_selected_hotel;
    private JComboBox combo_day_start;
    private JComboBox combo_month_start;
    private JComboBox combo_year_start;
    private JComboBox combo_day_end;
    private JComboBox combo_month_end;
    private JComboBox combo_year_end;
    private JTextField field_season_description;
    private JButton button_add_season;

    public HotelSeasonAddGUI(){
        add(wrapper);
        setSize(800,400);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);
        field_selected_hotel.setText(UserGUI.selected_hotel_name);


        button_add_season.addActionListener(e -> {

            String season_start = combo_day_start.getSelectedItem().toString()+"/"+combo_month_start.getSelectedItem().toString()+"/"+combo_year_start.getSelectedItem().toString();
            season_start = LocalDate.parse(season_start, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
            String season_end = combo_day_end.getSelectedItem().toString()+"/"+combo_month_end.getSelectedItem().toString()+"/"+combo_year_end.getSelectedItem().toString();
            season_end = LocalDate.parse(season_end, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
            String season_type = field_season_description.getText();
            Season.addSeason(UserGUI.selected_hotel_id,season_start,season_end,season_type);
            Helper.showMsg("done");
        });
    }
}
