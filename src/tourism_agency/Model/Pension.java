package tourism_agency.Model;

import tourism_agency.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Pension {
    private int id;
    private int hotel_id;
    private String pension;

    public Pension(int id, int hotel_id, String pension) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.pension = pension;
    }

    public Pension() {

    }

    public static boolean pensionDelete(int id){
        String query = "DELETE FROM pension WHERE hotel_id = ?";
        ArrayList<Pension> pensionList = Pension.getPensionList(id);
        pensionList.removeIf(obj -> obj.getHotel_id() == id);
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }

    public static int getFetcPensionID(int room_id){
        int pID = 0;
        Pension pensionID;

        String sql = "SELECT*FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,room_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                pensionID = new Pension();
                pensionID.setHotel_id(rs.getInt("pension_id"));
                pID = pensionID.getHotel_id();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  pID;
    }
    public static int getFetcPensionID(String pension,int hotel_id){
        int pID = 0;
        Pension pensionID;

        String sql = "SELECT*FROM pension WHERE pension = ? AND hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setString(1,pension);
            pr.setInt(2,hotel_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                pensionID = new Pension();
                pensionID.setHotel_id(rs.getInt("id"));
                pID = pensionID.getHotel_id();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  pID;
    }

    public static String getFetchPension(int id){
        String pension = "";
        Pension type;
        String sql = "SELECT*FROM pension WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                type = new Pension();
                type.setPension(rs.getString("pension"));
                pension = type.getPension();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  pension;
    }

    public static String getFetchPensionType(int pension_id){
        String pension = "";
        Pension type;
        String sql = "SELECT * FROM pension WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,pension_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                type = new Pension();
                type.setPension(rs.getString("pension"));
                pension = type.getPension();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  pension;
    }

    public static ArrayList<Pension> getPensionList(int hotel_id){
        ArrayList<Pension> pensionList = new ArrayList<>();
        String query = "SELECT * FROM pension WHERE hotel_id = ?";
        Pension pension;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                pension = new Pension();
                pension.setId(rs.getInt("id"));
                pension.setHotel_id(rs.getInt("hotel_id"));
                pension.setPension(rs.getString("pension"));
                pensionList.add(pension);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pensionList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getPension() {
        return pension;
    }

    public void setPension(String pension) {
        this.pension = pension;
    }
}



/*
Bu Pension sınıfı, turizm ajansı uygulamasında bir otelin pansiyon özelliklerini yönetmek için oluşturulmuş. Sınıfın içeriği şu özelliklere sahip:

Üye Değişkenler (Fields):

id: Pansiyon özelliğine ait bir ID'yi temsil eder.
hotel_id: Pansiyon özelliğiyle ilişkilendirilmiş olan otelin ID'sini saklar.
pension: Otele ait pansiyon özelliklerini tanımlayan bir metin içeriğini temsil eder.
Yöntemler (Methods):

public static boolean pensionDelete(int id): Belirli bir otel ID'sine sahip pansiyon özelliklerini siler.
public static int getFetcPensionID(int room_id): Belirli bir oda ID'sine sahip pansiyonun ID'sini getirir.
public static int getFetcPensionID(String pension, int hotel_id): Belirli bir otel için belirli bir pansiyon adına ait ID'yi getirir.
public static String getFetchPension(int id): Belirli bir pansiyon ID'sine sahip pansiyon adını getirir.
public static String getFetchPensionType(int pension_id): Belirli bir pansiyon ID'sine sahip pansiyon adını getirir.
public static ArrayList<Pension> getPensionList(int hotel_id): Belirli bir otel ID'sine sahip pansiyon listesini getirir.
Getter ve Setter Metotları (Accessor ve Mutator Methods):

Değişkenlerin değerlerini almak ve ayarlamak için kullanılan metotlardır.
*/
