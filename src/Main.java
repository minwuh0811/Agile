import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class Main  {


    static Varor varor = new Varor();
    static User user = new User();
    static VaruLager varuLager = new VaruLager();
    //static User userRegister = new User();


    public static void main (String[]args){

        boolean newBoolean = true;
        while (newBoolean) {

            String string = JOptionPane.showInputDialog("For Administrator enter 1\nFor User press 2\nTo register new user press 3");
            int choice = Integer.parseInt(string);
            if (choice == 1) {
             logIn();

             string = JOptionPane.showInputDialog("To add products press 1\nTo remove a product press 2");
                choice = Integer.parseInt(string);
                switch (choice) {

                    case 1:
                        addProduct();
                        break;

                    case 2:
                        removeProduct();
                        break;

                    default:
                        JOptionPane.showMessageDialog(null,"Invalid choice");
                        newBoolean = false;
                }
            }
            else if (choice==2) {
                logIn();
                string = JOptionPane.showInputDialog("To add product press 1");
                choice = Integer.parseInt(string);
                if (choice == 2) {
                    addProductToCustomer();
                    JOptionPane.showMessageDialog(null, "Products in shopingcart: \n" + printArrayShopping());

                }
            }

                else if(choice==3){
                    registerUser();
                }

            else
                break;
        }

    }

    public static void removeProduct () {
      String string=  JOptionPane.showInputDialog("Choose a product to be removed\n" + printArrayProduct()
        +"\nEnter the product ID");

        int id = Integer.parseInt(string);

        for (int i = 0; i < varuLager.products.size(); i++) {
            if (varuLager.products.get(i).getID() == id) {
                varuLager.products.remove(i);
                JOptionPane.showMessageDialog(null,"The products has been removed");
                break;
            }
        }

    }

    public static ArrayList addProduct(){


        String name = JOptionPane.showInputDialog("Enter the name of the product: ");
        String string = JOptionPane.showInputDialog ("Enter the price of the product: ");      //Pris på produkten
        double price = Double.parseDouble (  string);
        String tempName = name.toLowerCase();
        String type = JOptionPane.showInputDialog("Enter the type of " /*the product"*/+ tempName +": ");
        string = JOptionPane.showInputDialog ("Enter the amount of the product: ");     //Antalet produkter
        double amount = Integer.parseInt ( string);
        string = JOptionPane.showInputDialog ("Choose the category of the product: ");  //Kategori av produkten
        String category = string;
        int id = getID ();
        varor = new Varor (name, price, type, category, id, amount);

        try{
            //Class.forName("com.mysql.jdbc.Driver");

            // 1. Get a connection to database
            Connection con= DriverManager.getConnection(
                    //"jdbc:mysql://localhost:3306/FruitShop","MagicDrunkMonkey","Katamaranbanan5!"
                    "jdbc:mysql://localhost:3306/OnlineShop","MagicDrunkMonkey","Katamaranbanan5!"
            );

            // 2. Create a statement
            Statement stmt=con.createStatement();
        String string1= String.format("INSERT INTO varulager" +
                    " VALUES(\"%s\",\"%s\",\"%s\",\"%s\",\"%d\",\"%s\");",varor.getVarorNameName(), varor.getVarorPrice(), varor.getTyp(), varor.getVarorKategori(), varor.getID(), varor.getVarorAntal());
            // 3. Execute SQL query
            stmt.execute(string1);
        }catch(Exception e){
            System.out.println(e);
        }
        varuLager.products.add (varor);
        JOptionPane.showMessageDialog(null,"Product has been added.");
        return varuLager.products;


    }

    public static void addProductToCustomer(){

        String string = JOptionPane.showInputDialog(printArrayProduct() +"\nChoose a product by ID:");
        int chose = Integer.parseInt(string);
        for (int i = 0; i < varuLager.products.size(); i++) {
            if (varuLager.products.get(i).getID() == chose) {
                user.shoppingCartList.add(varuLager.products.get(i));
                JOptionPane.showMessageDialog(null,"The products has been added to your Shopping Cart");
                break;
            }
        }
    }

    public static int getID (){

        return varor.getID ();
    }
    public static String printArrayShopping() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Varor product : user.shoppingCartList) {
            stringBuilder.append(product.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
    public static String printArrayProduct() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Varor product : varuLager.products) {
            stringBuilder.append(product.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void logIn () {
        Scanner input = new Scanner(System.in);

        try{
            //Class.forName("com.mysql.jdbc.Driver");

            // 1. Get a connection to database
            Connection con= DriverManager.getConnection(
                    //"jdbc:mysql://localhost:3306/FruitShop","MagicDrunkMonkey","Katamaranbanan5!"
                    "jdbc:mysql://localhost:3306/OnlineShop","MagicDrunkMonkey","Katamaranbanan5!"
            );

            // 2. Create a statement
            Statement stmt=con.createStatement();

            // 3. Execute SQL query
            ResultSet rs=stmt.executeQuery("select * from admin;");

            // 4. Process the result set
            while(rs.next()) {
                Administrator administrator = new Administrator(rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                Administrator.administrators.add(administrator);

            }
            con.close();
        }catch(Exception e){
            JOptionPane.showInputDialog(e);
        }

        String username;
        String password;

        username = JOptionPane.showInputDialog("Log in: \nusername: ");


        password = JOptionPane.showInputDialog("password: ");

        for (int i = 0; i < Administrator.administrators.size(); i++) {


            if (username.equals(Administrator.administrators.get(i).getLoginName()) && password.equals(Administrator.administrators.get(i).getLoginPassword())) {
                JOptionPane.showMessageDialog(null,"Welcome");
                break;
            } else if (username.equals(username)) {
                JOptionPane.showInputDialog("Password is invalid");
            } else if (password.equals(password)) {
                JOptionPane.showInputDialog("Username does not exist!");
            } else {
                JOptionPane.showInputDialog("Try again, invalid input!");
            }
        }
    }

    public static void registerUser(){


        Scanner input = new Scanner(System.in);

        String name;
        String lastName;
        String mail;
        String userName;
        String userPassword;

       name =  JOptionPane.showInputDialog("To register please enter your information: \nEnter your name:");
       lastName = JOptionPane.showInputDialog("Enter lastname:");
       userName = JOptionPane.showInputDialog("Enter a username:");
       userPassword= JOptionPane.showInputDialog("Enter a password:");
       mail = JOptionPane.showInputDialog("Enter your email adress: ");
       int ID = getID();
       User user = new User (name, lastName, mail, ID, userName, userPassword);
       User.userRegister.add(user);
       JOptionPane.showMessageDialog(null,"Register successful");
    }
}
