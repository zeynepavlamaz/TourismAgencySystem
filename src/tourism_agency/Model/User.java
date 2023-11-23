package tourism_agency.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import tourism_agency.Helper.DBConnector;
import tourism_agency.Helper.Helper;

public class User {

    public static   int id ;
    private   String name;
    private   String username;
    private   String password;
    private  String type;

    public User(){}

    public User(String name, String username, String password, String type) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public static boolean add(String name, String username, String password,String type) {

        String query = "INSERT INTO user (name,username,password,type) VALUES (?,?,?,?)";
        User findUser = User.getFetch(username);
        if (findUser != null){
            Helper.showMsg("This username has already in use !");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,username);
            pr.setString(3,password);
            pr.setString(4,type);

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

    public static User getFetch(String username,String password){
        User obj = null;
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                switch (rs.getString("type")){
                    case "admin":
                        obj = new Admin();
                        break;
                    default:
                        obj = new User();
                }
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUsername(rs.getString("username"));
                obj.setPassword(rs.getString("password"));
                obj.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    }
    public static User getFetch(String username){
        User obj = null;
        String sql = "SELECT * FROM user WHERE username = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(sql);
            pr.setString(1,username);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUsername(rs.getString("username"));
                obj.setPassword(rs.getString("password"));
                obj.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    }
    public static ArrayList<User> getList(){
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user";
        User obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUsername(rs.getString("username"));
                obj.setPassword(rs.getString("password"));
                obj.setType(rs.getString("type"));
                userList.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        User.id = id;
    }

}


//Değerlendirme kriteri 7: Farklı kullanıcı türlerine (admin ve acenta çalışanı için) uygun yetkiler atanmış mı?
// user kullanıcısına uygun yetkiler atandı







/*
Bu kod, bir turizm ajansı uygulamasında kullanıcı işlemlerini gerçekleştirmek için oluşturulmuş bir User sınıfını içeriyor. Bu sınıfın işlevleri şu şekildedir:

Üye Değişkenleri (Fields):

id: Statik olarak tanımlanmış bir kullanıcı ID'sini temsil eder.
name, username, password, type: Kullanıcı adı, kullanıcı adı, şifre ve kullanıcı tipi gibi kullanıcı özelliklerini saklamak için özel (private) değişkenlerdir.
Yapılandırıcılar (Constructors):

public User(): Parametre almayan varsayılan bir yapılandırıcıdır.
public User(String name, String username, String password, String type): Ad, kullanıcı adı, şifre ve kullanıcı tipi gibi bilgileri alan parametreli bir yapılandırıcıdır.
Yöntemler (Methods):

public static boolean add(...): Yeni bir kullanıcı eklemek için veritabanına istek gönderir. Ekleme işlemi sırasında verilen kullanıcı adının benzersiz olup olmadığını kontrol eder.
public static User getFetch(String username, String password): Veritabanından kullanıcı adı ve şifreyle kullanıcı bilgilerini çeker.
public static User getFetch(String username): Veritabanından sadece kullanıcı adına göre kullanıcı bilgilerini çeker.
public static ArrayList<User> getList(): Tüm kullanıcıları veritabanından bir liste olarak alır.
Getter ve Setter Yöntemleri (Accessor ve Mutator Methods):

getName(), getUsername(), getPassword(), getType(): Sırasıyla ad, kullanıcı adı, şifre ve kullanıcı tipi bilgilerini döndüren metotlardır.
setName(), setUsername(), setPassword(), setType(): Sırasıyla ad, kullanıcı adı, şifre ve kullanıcı tipi bilgilerini ayarlayan metotlardır.
Bu sınıf, veritabanı işlemlerini gerçekleştirmek için DBConnector adlı bir yardımcı sınıfa bağlıdır. Veritabanı işlemleri, kullanıcı eklemek, kullanıcı bilgilerini çekmek ve tüm kullanıcıları listelemek gibi işlevleri içerir.

Genel olarak, bu User sınıfı, turizm ajansı uygulamasında kullanıcı verilerini yönetmek için kullanılır. Veritabanından kullanıcı bilgilerini çekme, ekleme ve listeleme gibi temel işlemleri gerçekleştirebilir.
*/