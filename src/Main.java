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
        loadData();
        boolean newBoolean = true;
        while (newBoolean) {

            String string = JOptionPane.showInputDialog("For Administrator enter 1\nFor User press 2\nTo register new user press 3");
            int choice = Integer.parseInt(string);
            if (choice == 1 && logInAdmin()) {


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
            else if (choice==2 && logInUser()) {
                string = JOptionPane.showInputDialog("To add product press 1");
                choice = Integer.parseInt(string);
                if (choice == 1) {
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
    public static void loadData(){
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
            ResultSet rs=stmt.executeQuery("select * from varulager;");

            // 4. Process the result set
            while(rs.next()) {
                Varor varor = new Varor(rs.getString(1), rs.getDouble(2),rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6));
                VaruLager.products.add(varor);

            }
            con.close();
        }catch(Exception e){
            JOptionPane.showInputDialog(e);
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

        try{
            Connection con= DriverManager.getConnection(
                    //"jdbc:mysql://localhost:3306/FruitShop","MagicDrunkMonkey","Katamaranbanan5!"
                    "jdbc:mysql://localhost:3306/OnlineShop","MagicDrunkMonkey","Katamaranbanan5!"
            );
            String sqlString= "DELETE FROM varulager WHERE ID="+id+";";
            Statement statement = con.createStatement();
            statement.execute(sqlString);
        }catch(Exception e){
            JOptionPane.showInputDialog(e);
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
        for (int i = 0; i < VaruLager.products.size(); i++) {
            if (VaruLager.products.get(i).getID() == chose) {
                user.shoppingCartList.add(VaruLager.products.get(i));
                JOptionPane.showMessageDialog(null,"The products has been added to your Shopping Cart");
                break;
            }
        }
    }

    public static int getID (){

        return varor.getID ();
    }
    public static int getIDuser (){

        return user.getId ();
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

    public static boolean logInAdmin () {
        boolean verify = false;

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
        boolean bool = true;

        username = JOptionPane.showInputDialog("Log in: \nusername: ");
        password = JOptionPane.showInputDialog("password: ");

        for (int i = 0; i < Administrator.administrators.size(); i++) {


            if (username.equals(Administrator.administrators.get(i).getLoginName()) && password.equals(Administrator.administrators.get(i).getLoginPassword())) {
                JOptionPane.showMessageDialog(null,"Welcome ");
                verify = true;
                bool = false;

            }
        }
        if (bool)
            JOptionPane.showMessageDialog(null, "Try again, invalid Username or/and password!");

        return verify;
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
       mail = JOptionPane.showInputDialog("Enter a mail:");
       userName= JOptionPane.showInputDialog("Enter your username:");
       userPassword = JOptionPane.showInputDialog("Enter your password: ");
       int ID = getIDuser();
       User user = new User (name, lastName, mail, ID, userName, userPassword);
       User.userRegister.add(user);

        try{
            //Class.forName("com.mysql.jdbc.Driver");

            // 1. Get a connection to database
            Connection con= DriverManager.getConnection(
                    //"jdbc:mysql://localhost:3306/FruitShop","MagicDrunkMonkey","Katamaranbanan5!"
                    "jdbc:mysql://localhost:3306/OnlineShop","MagicDrunkMonkey","Katamaranbanan5!"
            );

            // 2. Create a statement
            Statement stmt=con.createStatement();
            String string1= String.format("INSERT INTO user" +
                    " VALUES(\"%s\",\"%s\",\"%s\",\"%d\",\"%s\",\"%s\");", name, lastName, mail, ID, userName, userPassword );
            // 3. Execute SQL query
            stmt.execute(string1);
        }catch(Exception e){
            System.out.println(e);
        }





       JOptionPane.showMessageDialog(null,"Register successful");
    }

    public static boolean logInUser () {
        boolean verify = false;

        try {
            //Class.forName("com.mysql.jdbc.Driver");

            // 1. Get a connection to database
            Connection con = DriverManager.getConnection(
                    //"jdbc:mysql://localhost:3306/FruitShop","MagicDrunkMonkey","Katamaranbanan5!"
                    "jdbc:mysql://localhost:3306/OnlineShop", "MagicDrunkMonkey", "Katamaranbanan5!"
            );

            // 2. Create a statement
            Statement stmt = con.createStatement();

            // 3. Execute SQL query
            ResultSet rs = stmt.executeQuery("select * from user;");

            // 4. Process the result set
            while (rs.next()) {
                User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
                User.userRegister.add(user);

            }
            con.close();
        } catch (Exception e) {
            JOptionPane.showInputDialog(e);
        }

        String username;
        String password;
        boolean bool = true;


            username = JOptionPane.showInputDialog("Log in: \nusername: ");
            password = JOptionPane.showInputDialog("password: ");


            for (int i = 0; i < User.userRegister.size(); i++) {

                if (username.equals(User.userRegister.get(i).getUserName()) && password.equals(User.userRegister.get(i).getPassword())) {
                    verify = true;
                    JOptionPane.showMessageDialog(null, "Welcome ");
                    bool = false;
                    break;
                }

            }
            if (bool)
            JOptionPane.showMessageDialog(null, "Try again, invalid Username or/and password!");

        return verify;
    }


}

