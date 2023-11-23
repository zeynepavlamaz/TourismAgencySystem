package tourism_agency.Model;

import tourism_agency.Helper.DBConnector;
import tourism_agency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {

    private int id;
    private String room;
    private int hotel_id;
    private int season_id;
    private int pension_id;
    private int stock;
    private int adult_price;
    private int child_price;
    private int bed;

    public Room(int id, String room, int hotel_id, int season_id, int pension_id,int stock,int adult_price,int child_price,int bed) {
        this.id = id;
        this.room = room;
        this.hotel_id = hotel_id;
        this.season_id = season_id;
        this.pension_id = pension_id;
        this.stock = stock;
        this.adult_price = adult_price;
        this.child_price = child_price;
        this.bed = bed;
    }

    public Room() {}

    public static boolean roomDelete(int id){
        String query = "DELETE FROM room WHERE hotel_id = ?";
        ArrayList<Room> roomList = Room.getRoomListByHotelID(id);
        roomList.removeIf(obj -> obj.getHotel_id() == id);
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }

    public static int getFetchBed(int room_id){
        int bed = 0;
        Room room;
        String sql = "SELECT bed FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,room_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                room = new Room();
                room.setBed(rs.getInt("bed"));
                bed = room.getBed();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bed;
    }

    public static void updateRoomStock(int room_id){
        String query = "UPDATE room SET stock = stock -1 WHERE id = ? AND stock > 0";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_id);
            pr.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void incrementRoomStock(int room_id){
        String query = "UPDATE room SET stock = stock+1 WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_id);
            pr.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int getFetchChildPrice(int room_id){
        int childPrice = 0;
        Room room;
        String sql = "SELECT child_price FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,room_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                room = new Room();
                room.setChild_price(rs.getInt("child_price"));
                childPrice = room.getChild_price();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return childPrice;
    }
    public static int getFetchAdultPrice(int room_id){
        int adultPrice = 0;
        Room room;
        String sql = "SELECT adult_price FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,room_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                room = new Room();
                room.setAdult_price(rs.getInt("adult_price"));
                adultPrice = room.getAdult_price();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return adultPrice;
    }
    public static ArrayList<Room> getRoomPrices(int room_id){
        ArrayList<Room> roomPrices = new ArrayList<>();
        String query = "SELECT adult_price,child_price,bed FROM room " +
                       "WHERE id = ? ";
        Room room;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                room = new Room();
                room.setAdult_price(rs.getInt("adult_price"));
                room.setChild_price(rs.getInt("child_price"));
                room.setBed(rs.getInt("bed"));
                roomPrices.add(room);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomPrices;
    }

    public static String getFetchRoomType(int room_id){
        String roomType = "";
        Room room;
        String sql = "SELECT room FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,room_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                room = new Room();
                room.setRoom(rs.getString("room"));
                roomType = room.getRoom();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  roomType;
    }

    public static String searchQuery(String searchText,String checkIn,String checkOut){
        String query = "SELECT r.id,h.name,h.city,h.region,p.pension,s.season_type,r.adult_price,r.child_price,r.stock FROM room AS r\n" +
                "INNER JOIN hotel AS h\n" +
                "ON r.hotel_id = h.id\n" +
                "INNER JOIN season AS s\n" +
                "ON s.id = r.season_id\n" +
                "INNER JOIN pension as p\n" +
                "ON p.id = r.pension_id\n" ;
                if (!searchText.isEmpty() || !checkIn.isEmpty()  || !checkOut.isEmpty() ){
                    query += " WHERE r.stock > 0 AND ";
                    if (!searchText.isEmpty() ){
                        query+= "(h.region LIKE '%{{region}}%' OR h.name LIKE '%{{name}}%' OR h.city LIKE '%{{city}}%')";
                        query = query.replace("{{region}}",searchText);
                        query = query.replace("{{name}}",searchText);
                        query = query.replace("{{city}}",searchText);
                    }
                    if (!checkIn.isEmpty()){
                        if (!searchText.isEmpty()){
                            query += " AND ";
                        }
                        query+= "('{{checkin}}' BETWEEN s.season_start AND s.season_end)";
                        query = query.replace("{{checkin}}",checkIn);
                    }
                    if (!checkOut.isEmpty() ){
                        if (!searchText.isEmpty() || !checkIn.isEmpty()){
                            query += " AND ";
                        }
                        query+= "('{{checkout}}' BETWEEN s.season_start AND s.season_end)";
                        query = query.replace("{{checkout}}",checkOut);
                    }
                }
        return query;
    }
    public static ArrayList<RoomTableDto> searchRoomList(String query){
        ArrayList<RoomTableDto> roomList = new ArrayList<>();
        RoomTableDto room;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = pr.executeQuery(query);
            while (rs.next()){
                room = new RoomTableDto();
                room.setId(rs.getInt("id"));
                room.setName(rs.getString("name"));
                room.setCity(rs.getString("city"));
                room.setRegion(rs.getString("region"));
                room.setPension(rs.getString("pension"));
                room.setSeason_type(rs.getString("season_type"));
                room.setAdult_price(rs.getInt("adult_price"));
                room.setChild_price(rs.getInt("child_price"));
                roomList.add(room);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }



    public static boolean add(String room,int hotel_id,int season_id,int pension_id,int stock,int adult_price,int child_price,int bed){

        String query = "INSERT INTO room (room,hotel_id,season_id,pension_id,stock,adult_price,child_price,bed) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,room);
            pr.setInt(2,hotel_id);
            pr.setInt(3,season_id);
            pr.setInt(4,pension_id);
            pr.setInt(5,stock);
            pr.setInt(6,adult_price);
            pr.setInt(7,child_price);
            pr.setInt(8,bed);

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

    public static ArrayList<Room> getList(){
        ArrayList<Room> hotelList = new ArrayList<>();
        String query = "SELECT * FROM room";
        Room room;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                room = new Room();
                room.setId(rs.getInt("id"));
                room.setHotel_id(rs.getInt("hotel_id"));
                room.setSeason_id(rs.getInt("season_id"));
                room.setPension_id(rs.getInt("pension_id"));
                room.setAdult_price(rs.getInt("adult_price"));
                room.setChild_price(rs.getInt("child_price"));
                hotelList.add(room);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }
    public static ArrayList<Room> getRoomListByHotelID(int hotel_id){
        ArrayList<Room> pensionList = new ArrayList<>();
        String query = "SELECT * FROM room WHERE hotel_id = ?";
        Room room;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoom(rs.getString("room"));
                room.setHotel_id(rs.getInt("hotel_id"));
                room.setSeason_id(rs.getInt("season_id"));
                room.setPension_id(rs.getInt("pension_id"));
                room.setAdult_price(rs.getInt("adult_price"));
                room.setChild_price(rs.getInt("child_price"));
                room.setBed(rs.getInt("bed"));
                pensionList.add(room);
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

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getPension_id() {
        return pension_id;
    }

    public void setPension_id(int pension_id) {
        this.pension_id = pension_id;
    }

    public int getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(int adult_price) {
        this.adult_price = adult_price;
    }

    public int getChild_price() {
        return child_price;
    }

    public void setChild_price(int child_price) {
        this.child_price = child_price;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getBed() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }
}


/*
Bu Room sınıfı, turizm ajansı uygulamasında odaları ve odalara ait özellikleri yönetmek için oluşturulmuş. Sınıfın içeriği şu özelliklere sahip:

Üye Değişkenler (Fields):

id: Oda için bir ID'yi temsil eder.
room: Oda tipini (örneğin, oda adı) saklar.
hotel_id: Oda ile ilişkilendirilmiş olan otelin ID'sini tutar.
season_id: Oda ile ilişkilendirilmiş olan mevsimin ID'sini saklar.
pension_id: Oda ile ilişkilendirilmiş olan pansiyonun ID'sini temsil eder.
stock: Odada bulunan stok sayısını belirtir.
adult_price: Yetişkin fiyatını temsil eder.
child_price: Çocuk fiyatını temsil eder.
bed: Oda içindeki yatak sayısını belirtir.
Yöntemler (Methods):

public static boolean roomDelete(int id): Belirli bir oda ID'sine sahip odaları siler.
public static int getFetchBed(int room_id): Belirli bir oda ID'sine sahip odadaki yatak sayısını getirir.
public static void updateRoomStock(int room_id): Belirli bir oda ID'sine sahip odanın stok sayısını günceller.
public static void incrementRoomStock(int room_id): Belirli bir oda ID'sine sahip odanın stok sayısını artırır.
public static int getFetchChildPrice(int room_id): Belirli bir oda ID'sine sahip odadaki çocuk fiyatını getirir.
public static int getFetchAdultPrice(int room_id): Belirli bir oda ID'sine sahip odadaki yetişkin fiyatını getirir.
public static ArrayList<Room> getRoomPrices(int room_id): Belirli bir oda ID'sine sahip odanın fiyatlarını getirir.
public static String getFetchRoomType(int room_id): Belirli bir oda ID'sine sahip odanın tipini getirir.
public static String searchQuery(String searchText, String checkIn, String checkOut): Arama sorgusu oluşturur.
public static ArrayList<RoomTableDto> searchRoomList(String query): Belirli bir sorgu ile odaları listeler.
public static boolean add(String room, int hotel_id, int season_id, int pension_id, int stock, int adult_price, int child_price, int bed): Yeni bir oda ekler.
public static ArrayList<Room> getList(): Odaların listesini getirir.
public static ArrayList<Room> getRoomListByHotelID(int hotel_id): Belirli bir otel ID'sine sahip odaların listesini getirir.
*/