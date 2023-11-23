package tourism_agency.View;

import tourism_agency.Helper.Config;
import tourism_agency.Helper.Helper;
import tourism_agency.Model.Admin;
import tourism_agency.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginGUI extends  JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField field_login_uname;
    private JPasswordField field_login_pass;
    private JButton loginButton;

    public  LoginGUI(){

        add(wrapper);
        setSize(600,600);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);

        loginButton.addActionListener(e -> {
            if (Helper.isFieldEmpty(field_login_uname)|| Helper.isFieldEmpty(field_login_pass)){
                Helper.showMsg("fill");
            }else {
                User u = User.getFetch(field_login_uname.getText(), field_login_pass.getText());
                if (u == null){
                    Helper.showMsg("Wrong username or password !");
                }else {
                    switch (u.getType()){
                        case "admin":
                            new AdminGUI((Admin) u);
                            break;
                        case "user":
                            new UserGUI();
                            break;
                    }
                    dispose();
                }
            }
        });

    }
    

    public static void main(String[] args) {
        Helper.setLayout();
        new LoginGUI();
    }

    public JTextField getField_login_uname() {
        return field_login_uname;
    }

    public void setField_login_uname(JTextField field_login_uname) {
        this.field_login_uname = field_login_uname;
    }

    public JPasswordField getField_login_pass() {
        return field_login_pass;
    }

    public void setField_login_pass(JPasswordField field_login_pass) {
        this.field_login_pass = field_login_pass;
    }
}
