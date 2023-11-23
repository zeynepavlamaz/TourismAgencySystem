package tourism_agency.Model;

import tourism_agency.Helper.DBConnector;
import tourism_agency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Season {
    private int id;
    private int hotel_id;
    private String season_start;
    private String season_end;
    private String season_type;
    public Season(int id, int hotel_id, String season_start,String season_end,String season_type) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.season_start = season_start;
        this.season_end = season_end;
        this.season_type = season_type;
    }

    public Season() {

    }

    public Season(String season) {
    }

    public static boolean seasonDelete(int id){
        String query = "DELETE FROM season WHERE hotel_id = ?";
        ArrayList<Season> seasonList = Season.getSeasonListByHotelID(id);
        seasonList.removeIf(obj -> obj.getHotel_id() == id);
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }
    public static ArrayList<Season> getSeasonListByHotelID(int hotel_id){
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM season WHERE hotel_id = ?";
        Season season;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                season = new Season();
                season.setId(rs.getInt("id"));
                season.setHotel_id(rs.getInt("hotel_id"));
                season.setSeason_start(rs.getString("season_start"));
                season.setSeason_end(rs.getString("season_end"));
                season.setSeason_type(rs.getString("season_type"));
                seasonList.add(season);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  seasonList;
    }

    public static boolean addSeason(int hotel_id,String season_start, String season_end,String season_type) {

        String query = "INSERT INTO  season(hotel_id,season_start,season_end,season_type) VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            pr.setString(2,season_start);
            pr.setString(3,season_end);
            pr.setString(4,season_type);
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

    public static String getFetchSeason(int id){
        String season = "";
        Season time;
        String sql = "SELECT*FROM season WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                time = new Season();
                time.setSeason_start(rs.getString("season_type"));
                season = time.getSeason_start();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  season;
    }
    public static int getFetchSeasonID(String season_type,int hotel_id){
        int  seaasonID = 0;
        Season season;
        String sql = "SELECT * FROM season WHERE season_type = ? AND hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setString(1,season_type);
            pr.setInt(2,hotel_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                season = new Season();
                season.setId(rs.getInt("id"));
                seaasonID = season.getId();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  seaasonID;
    }

    public static ArrayList<Season> getSeasonList(int hotel_id){
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM season WHERE hotel_id = ? ";
        Season season;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                season = new Season();
                season.setId(rs.getInt("id"));
                season.setHotel_id(rs.getInt("hotel_id"));
                season.setSeason_start(rs.getString("season_start"));
                season.setSeason_end(rs.getString("season_end"));
                season.setSeason_type(rs.getString("season_type"));
                seasonList.add(season);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonList;
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

    public String getSeason_start() {
        return season_start;
    }

    public void setSeason_start(String season_start) {
        this.season_start = season_start;
    }

    public String getSeason_end() {
        return season_end;
    }

    public void setSeason_end(String season_end) {
        this.season_end = season_end;
    }

    public String getSeason_type() {
        return season_type;
    }

    public void setSeason_type(String season_type) {
        this.season_type = season_type;
    }
}

/*
Bu Season sınıfı, turizm ajansı uygulamasında otellerin sezon bilgilerini yönetmek için kullanılır. Bu sınıf, otel sezonlarına ait verileri veritabanına eklemek, güncellemek, silmek ve sorgulamak için gereken metotları içerir.

Sınıfın üye değişkenleri (fields):

id: Sezonun ID'sini temsil eder.
hotel_id: Sezonun ait olduğu otelin ID'sini tutar.
season_start: Sezonun başlangıç tarihini saklar.
season_end: Sezonun bitiş tarihini saklar.
season_type: Sezon tipini belirtir (örneğin, yaz, kış, bahar gibi).
Bu sınıfın bazı metotları şunlardır:

seasonDelete: Belirli bir otel için sezon silme işlemini gerçekleştirir.
getSeasonListByHotelID: Bir otelin ID'sine göre otele ait sezon listesini getirir.
addSeason: Yeni bir sezon ekler ve veritabanına kaydeder.
getFetchSeason: Belirli bir sezonun başlangıç tarihini getirir.
getFetchSeasonID: Belirli bir sezon tipinin (örneğin yaz, kış) oteldeki ID'sini getirir.
getSeasonList: Belirli bir otelin tüm sezon bilgilerini getirir.
*/