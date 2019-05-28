import java.util.ArrayList;

public class User {

    private String name;
    private String lastName;
    private String mail;
    private String userName;
    private String userPassword;
    Varor varor;
    int id;
    static ArrayList<Varor> shoppingCartList;
    static ArrayList<User> userRegister = new ArrayList<>();


    public User (){}

    public User (String name, String lastname, String mail, int id, String userName, String userPassword) {

        this.name = name;
        this.lastName = lastname;
        this.mail = mail;
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.shoppingCartList = new ArrayList<>();
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                ", product=" + varor +
                ", userName=" + userName +
                ", userPassword=" + userPassword +
                ", id=" + id +
                ", shoppingCartList=" + shoppingCartList +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Varor getProduct() {
        return varor;
    }

    public void setProduct(Varor product) {
        this.varor = product;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.userPassword;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public ArrayList<Varor> getShoppingCartList() {
        return shoppingCartList;
    }

    public void setShoppingCartList(ArrayList<Varor> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }
}
