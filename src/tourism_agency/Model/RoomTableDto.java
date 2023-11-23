package tourism_agency.Model;

public class RoomTableDto {

    public int id;
    public  String name;
    public  String city;
    public  String region;
    public  String pension;
    public  String season_type;
    public  int adult_price;
    public  int child_price;

    public RoomTableDto() {

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

    public String getPension() {
        return pension;
    }

    public void setPension(String pension) {
        this.pension = pension;
    }

    public String getSeason_type() {
        return season_type;
    }

    public void setSeason_type(String season_type) {
        this.season_type = season_type;
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
}


/*
Bu RoomTableDto sınıfı, turizm ajansı uygulamasında odaların özelliklerini görüntülemek için kullanılabilecek veri transfer nesnesini (DTO - Data Transfer Object) temsil ediyor. DTO'lar, genellikle farklı katmanlar arasında veri taşımak için kullanılan basit sınıflardır.

Bu sınıfın üye değişkenleri (fields):

id: Oda ID'sini temsil eder.
name: Oda sahibi otelin adını saklar.
city: Oda sahibi otelin bulunduğu şehri tutar.
region: Oda sahibi otelin bulunduğu bölgeyi gösterir.
pension: Oda fiyatlandırmasında kullanılan konaklama tipini (pansiyon tipini) içerir.
season_type: Oda fiyatlandırmasında kullanılan sezon tipini (örneğin, yaz, kış, bahar) saklar.
adult_price: Yetişkinler için oda fiyatını gösterir.
child_price: Çocuklar için oda fiyatını temsil eder.
Bu sınıf aynı zamanda, getter ve setter metodlarıyla her bir üye değişkenine erişim sağlar. Getter metotları, ilgili alanın değerini döndürürken, setter metotları ise ilgili alanın değerini ayarlamak için kullanılır.

Bu tür bir sınıf, genellikle veritabanından veya başka bir kaynaktan gelen verileri saklamak ve sunmak için kullanılır. Bu durumda, oda bilgilerini kolayca bir arayüzde veya başka bir katmanda görüntülemek için kullanılabilir. Örneğin, bu sınıf, turizm ajansı web sitesindeki otel odalarının özelliklerini görüntülemek için kullanılabilir.
*/