package tourism_agency.Model;

import tourism_agency.Helper.DBConnector;
import tourism_agency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {

    private int id;
    private String name;
    private String city;
    private String region;
    private String adress;
    private String email;
    private String phone_number;
    private int star;

    public Hotel(int id, String name, String city, String region, String adress, String email, String phone_number, int star) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.region = region;
        this.adress = adress;
        this.email = email;
        this.phone_number = phone_number;
        this.star = star;
    }

    public Hotel() {
    }

    public Hotel(int id, String name) {
    }

    public static boolean hotelDelete(int id){
        String query = "DELETE FROM hotel WHERE id = ?";
        ArrayList<Hotel> hotelList = Hotel.getListByID(id);
        hotelList.removeIf(obj -> obj.getId() == id);
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }

    public static int getFetchHotelID(int room_id){
        int hotel_id = 0;
        Room room ;
        String sql = "SELECT hotel_id FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,room_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                room = new Room();
                room.setHotel_id(rs.getInt("hotel_id"));
                hotel_id = room.getHotel_id();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  hotel_id;
    }
    public static boolean add(String name, String city, String region,String adress,String email, String phone_number, int star) {

        String query = "INSERT INTO  hotel(name,city,region,adress,email,phone_number,star) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,city);
            pr.setString(3,region);
            pr.setString(4,adress);
            pr.setString(5,email);
            pr.setString(6,phone_number);
            pr.setInt(7,star);
            int response = pr.executeUpdate();
            if (response==-1){
                Helper.showMsg("error");
            }
            return response !=-1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return true;

    }
    public static ArrayList<Hotel> getListByID(int id){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotel WHERE id = ?";
        Hotel hotel;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setCity(rs.getString("city"));
                hotel.setRegion(rs.getString("region"));
                hotel.setAdress(rs.getString("adress"));
                hotel.setEmail(rs.getString("email"));
                hotel.setPhone_number(rs.getString("phone_number"));
                hotel.setStar(rs.getInt("star"));
                hotelList.add(hotel);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }
    public static ArrayList<Hotel> getList(){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotel";
        Hotel hotel;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setCity(rs.getString("city"));
                hotel.setRegion(rs.getString("region"));
                hotel.setAdress(rs.getString("adress"));
                hotel.setEmail(rs.getString("email"));
                hotel.setPhone_number(rs.getString("phone_number"));
                hotel.setStar(rs.getInt("star"));
                hotelList.add(hotel);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }
    public static ArrayList<Hotel> getHotelList(int room_id){
        ArrayList<Hotel> seasonList = new ArrayList<>();
        String query = "SELECT h.name,h.city,h.region,h.adress,h.email,h.phone_number,h.star FROM hotel AS h JOIN room ON h.id = room.hotel_id WHERE room.id = ? ";
        Hotel hotel;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                hotel = new Hotel();
                hotel.setName(rs.getString("name"));
                hotel.setCity(rs.getString("city"));
                hotel.setRegion(rs.getString("region"));
                hotel.setAdress(rs.getString("adress"));
                hotel.setEmail(rs.getString("email"));
                hotel.setPhone_number(rs.getString("phone_number"));
                hotel.setStar(rs.getInt("star"));
                seasonList.add(hotel);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonList;
    }

    public static String getFetchHotelName(int hotel_id){
         String obj = "";
         Hotel name ;
         String sql = "SELECT*FROM hotel WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                name = new Hotel();
                name.setName(rs.getString("name"));
                obj = name.getName();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    }
    public static String getFetchHotelCity(int hotel_id){
        String obj = "";
        Hotel name ;
        String sql = "SELECT*FROM hotel WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                name = new Hotel();
                name.setName(rs.getString("city"));
                obj = name.getName();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    }
    public static String getFetchHotelRegion(int hotel_id){
        String obj = "";
        Hotel name ;
        String sql = "SELECT*FROM hotel WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                name = new Hotel();
                name.setName(rs.getString("region"));
                obj = name.getName();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}



/*
Bu kod parçası, turizm ajansı uygulamasında otel bilgilerini yönetmek için oluşturulmuş bir Hotel sınıfını içermektedir. Sınıfın işlevleri şu şekildedir:

Üye Değişkenleri (Fields):

id: Otelin bir ID'sini temsil eder.
name, city, region, adress, email, phone_number: Otelin adı, şehir, bölge, adres, e-posta ve telefon numarası gibi bilgilerini saklar.
star: Otelin yıldız sayısını temsil eder.
Yapılandırıcılar (Constructors):

public Hotel(int id, String name, String city, String region, String adress, String email, String phone_number, int star): Otel bilgilerini alan parametreli bir yapılandırıcıdır.
public Hotel(): Parametre almayan bir varsayılan yapılandırıcıdır.
public Hotel(int id, String name): ID ve ad bilgilerini alan yapılandırıcıdır.
Yöntemler (Methods):

public static boolean hotelDelete(int id): Belirli bir otel ID'sine sahip oteli siler.
public static int getFetchHotelID(int room_id): Belirli bir oda ID'sine sahip otelin ID'sini getirir.
public static boolean add(...): Yeni bir otel eklemek için veritabanına istek gönderir.
public static ArrayList<Hotel> getListByID(int id): Belirli bir otel ID'sine sahip otel bilgilerini getirir.
public static ArrayList<Hotel> getList(): Tüm otel kayıtlarını bir liste olarak getirir.
Diğer yöntemler belirli bir oda ID'sine veya otel ID'sine sahip otel bilgilerini getirmek için kullanılır.
Getter ve Setter Yöntemleri (Accessor ve Mutator Methods):

Yukarıda belirtilen değişkenlerin değerlerini almak ve ayarlamak için kullanılan metotlardır.
*/
