import java.util.ArrayList;
import java.util.Random;

public class User {

    private String name;
    private String lastName;
    private String city;
    private String userName;
    private String userPassword;
    Varor varor;
    int id= new Random().nextInt(100) + 1;
    ArrayList<Varor> shoppingCartList = new ArrayList<>();

    public User (){}

    public User (String name, String lastname, String city, int id, String userName, String password) {

        this.name = name;
        this.lastName = lastname;
        this.city = city;
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        shoppingCartList = new ArrayList<>();

    }





    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
