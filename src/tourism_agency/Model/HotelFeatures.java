package tourism_agency.Model;

import tourism_agency.Helper.DBConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelFeatures {

    public int id;
    public  int hotel_id;
    public String features;


    public static boolean hotelFeatureDelete(int id){
        String query = "DELETE FROM facility_features WHERE hotel_id = ?";
        ArrayList<HotelFeatures> hotelFeatureList = HotelFeatures.getListByID(id);
        hotelFeatureList.removeIf(obj -> obj.getHotel_id() == id);
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }
    public static ArrayList<HotelFeatures> getListByID(int id){
        ArrayList<HotelFeatures> hotelFeatList = new ArrayList<>();
        String query = "SELECT * FROM facility_features WHERE hotel_id = ?";
        HotelFeatures hotelFeatures;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                hotelFeatures = new HotelFeatures();
                hotelFeatures.setId(rs.getInt("id"));
                hotelFeatures.setHotel_id(rs.getInt("hotel_id"));
                hotelFeatures.setFeatures(rs.getString("features"));
                hotelFeatList.add(hotelFeatures);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelFeatList;
    }
    public static ArrayList<HotelFeatures> getHotelFeatures(int room_id){
        ArrayList<HotelFeatures> hotelFeatures = new ArrayList<>();
        String query = "SELECT f.features FROM facility_features AS f " +
                "INNER JOIN hotel " +
                "ON hotel.id = f.hotel_id " +
                "INNER JOIN room AS r " +
                "ON r.hotel_id = f.hotel_id" +
                " WHERE r.id = ? ";
        HotelFeatures feature;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                feature = new HotelFeatures();
                feature.setFeatures(rs.getString("features"));
                hotelFeatures.add(feature);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelFeatures;
    }

    public static boolean add(int hotel_id,String features) {

        String query = "INSERT INTO facility_features(hotel_id,features) VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            pr.setString(2,features);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }

    public static boolean addPension(int hotel_id,String pension) {

        String query = "INSERT INTO pension(hotel_id,pension) VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            pr.setString(2, pension);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }
    public static boolean addRoomFeature(int room_id,String features) {

        String query = "INSERT INTO room_features(room_id,features) VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_id);
            pr.setString(2, features);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

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

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
}

/*
Bu kod parçası, turizm ajansı uygulamasında otellere ait özellikleri yönetmek için oluşturulmuş bir HotelFeatures sınıfını içermektedir. Bu sınıfın işlevleri şu şekildedir:

Üye Değişkenleri (Fields):

id: Otel özelliğine ait bir ID'yi temsil eder.
hotel_id: Özellikle ilişkilendirilmiş olan otelin ID'sini saklar.
features: Otele ait özellikleri tanımlayan bir metin içeriğini temsil eder.
Yöntemler (Methods):

public static boolean hotelFeatureDelete(int id): Belirli bir otel ID'sine sahip otelin özelliklerini siler.
public static ArrayList<HotelFeatures> getListByID(int id): Belirli bir otel ID'sine sahip otelin özelliklerini listeler.
public static ArrayList<HotelFeatures> getHotelFeatures(int room_id): Belirli bir oda ID'sine sahip otelin özelliklerini listeler.
public static boolean add(int hotel_id,String features): Yeni bir otel özelliği eklemek için veritabanına istek gönderir.
Diğer metotlar, otel özelliklerini eklemek için kullanılır.
Getter ve Setter Metotları (Accessor ve Mutator Methods):

Değişkenlerin değerlerini almak ve ayarlamak için kullanılan metotlardır.
*/