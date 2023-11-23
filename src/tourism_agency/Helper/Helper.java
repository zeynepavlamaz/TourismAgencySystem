package tourism_agency.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if ("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }
    public static int screenCenter(String axis, Dimension ordinate){

        int point;
        switch (axis){
            case "x":
                point = ((Toolkit.getDefaultToolkit().getScreenSize().width - ordinate.width)/2);
                break;
            case "y":
                point = ((Toolkit.getDefaultToolkit().getScreenSize().height - ordinate.width)/2);
            default:
                point=0;
        }
        return  point;

    }

    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    public static void showMsg(String str){
        String msg;
        String title;
        switch (str){
            case "fill":
                msg = "Please fill all the blanks!";
                title = "Error!";
                break;
            case "add":
                msg = "Successfully added!";
                title="Add";
                break;
            case "error":
                msg ="Error occurred!";
                title ="Error";
                break;
            default:
                msg=str;
                title="Message";

        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }

    public  static boolean confirm(String str){
        String msg;
        switch (str){
            case "sure":
                msg = "Are you sure to apply the changes?";
                break;
            default:
                msg = str;
        }

        return JOptionPane.showConfirmDialog(null,msg,"Sure ?",JOptionPane.YES_NO_OPTION)==0;
    }
}
