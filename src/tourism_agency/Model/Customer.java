package tourism_agency.Model;

import tourism_agency.Helper.DBConnector;
import tourism_agency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Customer {
    public int id;
    public int hotel_id;
    public int room_id;
    public  String nameSurname;
    public  String email;
    public  String phoneNumber;
    public  String customer_ID;
    public int adultNum;
    public int childNum;
    public  int totalPrice;

    public static boolean resDeleteByHotelID(int id){
        String query = "DELETE FROM customer WHERE hotel_id = ?";
        ArrayList<Customer> customerList = Customer.getListByID(id);
        customerList.removeIf(obj -> obj.getId() == id);
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }
    public static boolean resDelete(int id){
        String query = "DELETE FROM customer WHERE id = ?";
        ArrayList<Customer> customerList = Customer.getListByID(id);
        customerList.removeIf(obj -> obj.getId() == id);
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }

    public static boolean add(int hotel_id, int room_id, String nameSurname,String email,String phoneNumber,String customer_ID,int adultNum,int childNum,int totalPrice) {

        String query = "INSERT INTO customer (hotel_id,room_id,name_surname,email,phone_number,customer_ID,adult_number,child_number,total_price) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            pr.setInt(2,room_id);
            pr.setString(3,nameSurname);
            pr.setString(4,email);
            pr.setString(5,phoneNumber);
            pr.setString(6,customer_ID);
            pr.setInt(7,adultNum);
            pr.setInt(8,childNum);
            pr.setInt(9,totalPrice);
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
    public static ArrayList<Customer> getList(){
        ArrayList<Customer> customerList = new ArrayList<>();
        String query = "SELECT * FROM customer";
        Customer customer;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setHotel_id(rs.getInt("hotel_id"));
                customer.setRoom_id(rs.getInt("room_id"));
                customer.setNameSurname(rs.getString("name_surname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setCustomer_ID(rs.getString("customer_ID"));
                customer.setAdultNum(rs.getInt("adult_number"));
                customer.setChildNum(rs.getInt("child_number"));
                customer.setTotalPrice(rs.getInt("total_price"));
                customerList.add(customer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }
    public static ArrayList<Customer> getListByID(int costumer_id){
        ArrayList<Customer> customerInfo = new ArrayList<>();
        String query = "SELECT * FROM customer " +
                        "WHERE id = ? ";
        Customer customer;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,costumer_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setHotel_id(rs.getInt("hotel_id"));
                customer.setRoom_id(rs.getInt("room_id"));
                customer.setNameSurname(rs.getString("name_surname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setCustomer_ID(rs.getString("customer_ID"));
                customer.setAdultNum(rs.getInt("adult_number"));
                customer.setChildNum(rs.getInt("child_number"));
                customer.setTotalPrice(rs.getInt("total_price"));
                customerInfo.add(customer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerInfo;
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

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        this.customer_ID = customer_ID;
    }

    public int getAdultNum() {
        return adultNum;
    }

    public void setAdultNum(int adultNum) {
        this.adultNum = adultNum;
    }

    public int getChildNum() {
        return childNum;
    }

    public void setChildNum(int childNum) {
        this.childNum = childNum;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}


/*
Bu kod parçası, bir turizm ajansı uygulamasında müşteri (customer) bilgilerini yönetmek için oluşturulmuş bir Customer sınıfını içerir. Bu sınıfın işlevleri şu şekildedir:

Üye Değişkenleri (Fields):

id: Müşteriye ait bir ID değerini temsil eder.
hotel_id, room_id: Müşterinin rezervasyon yaptığı otel ve oda ID'lerini saklamak için kullanılır.
nameSurname, email, phoneNumber, customer_ID: Müşterinin adı-soyadı, e-posta, telefon numarası ve müşteri kimlik bilgilerini tutar.
adultNum, childNum: Yetişkin ve çocuk sayısını belirtir.
totalPrice: Rezervasyonun toplam fiyatını tutar.
Yöntemler (Methods):

public static boolean resDeleteByHotelID(int id): Belirli bir otel ID'sine sahip müşterileri silmeyi sağlar.
public static boolean resDelete(int id): Belirli bir müşteri ID'sine sahip müşteriyi silmeyi sağlar.
public static boolean add(...): Yeni bir müşteri eklemek için veritabanına istek gönderir.
public static ArrayList<Customer> getList(): Tüm müşteri kayıtlarını bir liste olarak getirir.
public static ArrayList<Customer> getListByID(int costumer_id): Belirli bir müşteri ID'sine sahip müşteri kayıtlarını getirir.
Getter ve Setter Yöntemleri (Accessor ve Mutator Methods):

Yukarıda belirtilen değişkenlerin değerlerini almak ve ayarlamak için kullanılan metotlardır.
*/