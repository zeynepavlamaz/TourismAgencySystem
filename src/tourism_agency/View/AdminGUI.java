package tourism_agency.View;

import tourism_agency.Helper.Config;
import tourism_agency.Helper.Helper;
import tourism_agency.Model.Admin;
import tourism_agency.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI extends JFrame {
    private JPanel wrapper;
    private JButton button_admin_exit;
    private JLabel label_welcome;
    private JTable table_user_list;
    private JTextField field_name_create;
    private JTextField field_uname_create;
    private JTextField field_pass_create;
    private JButton createUserButton;
    private Admin admin;
    private  DefaultTableModel model_all_user_list;
    private Object[] row_all_user_list;


    public AdminGUI(Admin admin){
        this.admin = admin;
        add(wrapper);
        setSize(1000,600);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(false);
        label_welcome.setText("Welcome! : "+admin.getName());

        model_all_user_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_all_user_list = {"ID","Name-Surname","Username","Password","Register Type"};
        model_all_user_list.setColumnIdentifiers(col_all_user_list);
        row_all_user_list = new Object[col_all_user_list.length];
        loadUserModel();
        table_user_list.setModel(model_all_user_list);
        table_user_list.getTableHeader().setReorderingAllowed(false);


        createUserButton.addActionListener(e -> {
            if(Helper.isFieldEmpty(field_name_create) || Helper.isFieldEmpty(field_uname_create) || Helper.isFieldEmpty(field_pass_create)){
                Helper.showMsg("fill");
            }else {
                String name = field_name_create.getText();
                String username = field_uname_create.getText();
                String password = field_pass_create.getText();
                if (User.add(name,username,password,"user")){
                    Helper.showMsg("add");
                    loadUserModel();
                    field_pass_create.setText(null);
                    field_uname_create.setText(null);
                    field_name_create.setText(null);
                }else {
                    Helper.showMsg("error");
                }
            }
        });
        button_admin_exit.addActionListener(e -> {
            dispose();
            LoginGUI login = new LoginGUI();
        });
    }

    private void loadUserModel() {
        DefaultTableModel clearModel = (DefaultTableModel) table_user_list.getModel();
        clearModel.setRowCount(0);
        for (User obj : User.getList()){
            row_all_user_list[0]=obj.getId();
            row_all_user_list[1]=obj.getName();
            row_all_user_list[2]=obj.getUsername();
            row_all_user_list[3]=obj.getPassword();
            row_all_user_list[4]=obj.getType();
            model_all_user_list.addRow(row_all_user_list);
        }
    }
}
