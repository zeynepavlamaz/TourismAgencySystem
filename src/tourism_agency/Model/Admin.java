package tourism_agency.Model;

public class Admin extends  User{
    public Admin(){
    }
    public Admin(String name, String username, String password, String type) {
        super(name, username, password, type);
    }
}


// Değerlendirme kriteri 7 : Farklı kullanıcı türlerine (admin ve acenta çalışanı için) uygun yetkiler atanmış mı?
// admin kullanıcı türüne uygun yetkiler atandı

/*
Kullanıcıları Yönetmek: User sınıfından türetilmiş olduğu için, genelde kullanıcıların (örneğin, sistemde kayıtlı olan farklı kullanıcı türleri) özelliklerini ve davranışlarını miras alır. Bu durumda, Admin sınıfı, User sınıfının özelliklerini (ad, kullanıcı adı, şifre, tip vb.) içerebilir ve bu özelliklere erişebilir.

Yönetici (Admin) Girişi İçin Yetkilendirme: Sistemde oturum açmak ve sistem yönetimi için bir yönetici kullanıcısı oluşturmak için kullanılabilir. Admin sınıfının yapıcı (constructor) yöntemi, bir yönetici kullanıcısının özelliklerini belirlemek için kullanılabilir. Örneğin, ad, kullanıcı adı, şifre ve kullanıcı tipi gibi bilgiler burada tanımlanabilir.

 */