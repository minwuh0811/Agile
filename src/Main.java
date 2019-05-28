import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;


public class Main  {

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    static Varor varor;
    static User user;
    static VaruLager varuLager;
    static String dataBaseUserName = "root";
    static String dataBaseUserPassword = "password";
    static String dataBaseMySQLUrl="jdbc:mysql://192.168.99.100:3306/firstdb";
    static String productDataBaseTable = "products";
    static String adminDataBaseTable = "admin";
    static String userDataBaseTable = "user";
    static JTable table;// 声明表格

    public Main() {
        varuLager = new VaruLager();
        user = new User();
        varor = new Varor();
    }

    public static void main (String[]args){
        loadData();
        boolean newBoolean = true;
        while (newBoolean) {

            String string = JOptionPane.showInputDialog("For Administrator enter 1\nFor User press 2\nTo register new user press 3\nExist press 4");
            int choice = Integer.parseInt(string);
            if (choice == 1 && logInAdmin()) {


             string = JOptionPane.showInputDialog("To add products press 1\nTo remove a product press 2\nTo show administrator list press 3\nTo show user list press 4");
                choice = Integer.parseInt(string);
                switch (choice) {

                    case 1:
                        addProduct();
                        break;

                    case 2:
                        removeProduct();
                        break;
                    case 3:
                        AdminDataBase();
                        break;
                    case 4:
                        UserDataBase();
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
                    break;
                }
            }

                else if(choice==3){
                    registerUser();
                }

            else if (choice==4) {
                break;
            }
        }

    }
    public static void loadData(){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dataBaseMySQLUrl, dataBaseUserName, dataBaseUserPassword);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from "+userDataBaseTable+";");
            while (resultSet.next()) {
                User user = new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6));
                User.userRegister.add(user);

            }
            resultSet = statement.executeQuery("select * from "+adminDataBaseTable+";");

            while(resultSet.next()) {
                Administrator administrator = new Administrator(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
                Administrator.administrators.add(administrator);
            }
            resultSet =statement.executeQuery("select * from "+productDataBaseTable+";");
            while(resultSet.next()) {
                Varor varor = new Varor(resultSet.getString(1), resultSet.getDouble(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getDouble(6));
                VaruLager.products.add(varor);

            } }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public static void removeProduct () {


      String string=  JOptionPane.showInputDialog("Choose a product to be removed\n" + printArrayProduct()
        +"\nEnter the product ID");

        int id = Integer.parseInt(string);
        for (int i = 0; i < VaruLager.products.size(); i++) {
            if (VaruLager.products.get(i).getID() == id) {
                VaruLager.products.remove(i);
                JOptionPane.showMessageDialog(null,"The products has been removed");
                break;
            }
        }

        try{
            connection = DriverManager.getConnection(
                    dataBaseMySQLUrl,dataBaseUserName,dataBaseUserPassword);
            String sqlString= "DELETE FROM "+productDataBaseTable+" WHERE ID="+id+";";
            statement = connection.createStatement();
            statement.execute(sqlString);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
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
        int id =  new Random().nextInt(100) + 1;
        varor = new Varor (name, price, type, category, id, amount);

        try{
            connection = DriverManager.getConnection(
                    dataBaseMySQLUrl,dataBaseUserName,dataBaseUserPassword );

            statement =connection.createStatement();
        String string1= String.format("INSERT INTO " + productDataBaseTable +
                    " VALUES(\"%s\",\"%s\",\"%s\",\"%s\",\"%d\",\"%s\");",varor.getVarorNameName(), varor.getVarorPrice(), varor.getTyp(), varor.getVarorKategori(), varor.getID(), varor.getVarorAntal());
            statement.execute(string1);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        VaruLager.products.add (varor);
        JOptionPane.showMessageDialog(null,"Product has been added.");
        return VaruLager.products;


    }

    public static void addProductToCustomer(){

        boolean addProduct=false;
        do {
            String string = JOptionPane.showInputDialog(printArrayProduct() + "\nChoose a product by ID:");
            int chose = Integer.parseInt(string);
            for (int i = 0; i < VaruLager.products.size(); i++) {
                if (VaruLager.products.get(i).getID() == chose) {
                    User.shoppingCartList.add(VaruLager.products.get(i));
                    JOptionPane.showMessageDialog(null, "The products has been added to your Shopping Cart");
                    int choose = Integer.parseInt(JOptionPane.showInputDialog("To add another product enter 1 / to exit enter 2"));
                    if(choose==1)
                        addProduct=true;
                    else
                        addProduct=false;
                }
            }
        }
        while (addProduct);
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
        for (Varor product : VaruLager.products) {
            stringBuilder.append(product.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static boolean logInAdmin () {
        boolean verify = false;
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
       int ID = new Random().nextInt(100) + 1;
       User user = new User (name, lastName, mail, ID, userName, userPassword);
       User.userRegister.add(user);

        try{
            connection = DriverManager.getConnection(
                    dataBaseMySQLUrl,dataBaseUserName,dataBaseUserPassword            );
            statement=connection.createStatement();
            String string1= String.format("INSERT INTO "+ userDataBaseTable +
                    " VALUES(\"%s\",\"%s\",\"%s\",\"%d\",\"%s\",\"%s\");", name, lastName, mail, ID, userName, userPassword );
            statement.execute(string1);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
       JOptionPane.showMessageDialog(null,"Register successful");
    }

    public static boolean logInUser () {
        boolean verify = false;

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


    public static Vector getAdminDataVector() {

        Vector dataVector =new Vector();
        Vector rowVector = new Vector();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dataBaseMySQLUrl, dataBaseUserName, dataBaseUserPassword);
            statement = connection.createStatement();
            String sql = "SELECT * FROM admin";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                rowVector.add(rs.getString("administratorFirstName"));
                rowVector.add(rs.getString("administratorLastName"));
                rowVector.add(rs.getString("staffNumberID"));
                rowVector.add(rs.getString("administratorMail"));
                rowVector.add(rs.getString("loginName"));
                rowVector.add(rs.getString("loginPassword"));
            }
            dataVector.add(rowVector);


         } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return dataVector;
    }
    public static Vector getUserDataVector() {
        Vector dataVector = new Vector();
        Vector rowVector = new Vector();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dataBaseMySQLUrl, dataBaseUserName, dataBaseUserPassword);
            statement = connection.createStatement();
            String sql = "SELECT * FROM user";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                rowVector.add(rs.getString(1));
                rowVector.add(rs.getString(2));
                rowVector.add(rs.getString(3));
                rowVector.add(rs.getInt(4));
                rowVector.add(rs.getString(5));
                rowVector.add(rs.getString(6));
            }
            dataVector.add(rowVector);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return dataVector;
    }

    public static Vector getAdminColumnVector() {
        Vector columnVector = new Vector();
        columnVector.add("administratorFirstName");
        columnVector.add("administratorLastName");
        columnVector.add("staffNumberID");
        columnVector.add("administratorMail");
        columnVector.add("loginName");
        columnVector.add("loginPassword");
        return columnVector;
    }
    public static Vector getUserColumnVector() {
        Vector columnVector = new Vector();
        columnVector.add("fristname");
        columnVector.add("lastName");
        columnVector.add("mail");
        columnVector.add("userID");
        columnVector.add("userName");
        columnVector.add("userPassword");
        return columnVector;
    }



    public static void AdminDataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dataBaseMySQLUrl, dataBaseUserName, dataBaseUserPassword);
            statement = connection.createStatement();
            String sql = "SELECT * FROM admin";
            ResultSet rs = statement.executeQuery(sql);
            Vector columnVector = getAdminColumnVector();
            Vector dataVector = getAdminDataVector();
            System.out.println(dataVector.get(0).toString());
            final JScrollPane scrollPane = new JScrollPane();
            JFrame myframe=new JFrame();
            myframe.getContentPane().add(scrollPane, BorderLayout.CENTER);
            table = new JTable(dataVector, columnVector);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.setFont(new Font("",0,16));
            scrollPane.setViewportView(table);

            myframe.setTitle("连接远程docker数据库显示");
            myframe.setBounds(100, 100, 1000, 100);
            myframe.setVisible(true);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }

    }
    public static void UserDataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dataBaseMySQLUrl, dataBaseUserName, dataBaseUserPassword);
            statement = connection.createStatement();
            String sql = "SELECT * FROM user";
            ResultSet rs = statement.executeQuery(sql);
            Vector columnVector = getUserColumnVector();
            Vector dataVector = getUserDataVector();
            System.out.println(dataVector.get(0).toString());
            final JScrollPane scrollPane = new JScrollPane();
            JFrame myframe = new JFrame();
            myframe.getContentPane().add(scrollPane, BorderLayout.CENTER);
            table = new JTable(dataVector, columnVector);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.setFont(new Font("", 0, 16));
            scrollPane.setViewportView(table);

            myframe.setTitle("连接远程docker数据库显示");
            myframe.setBounds(100, 100, 1000, 100);
            myframe.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }



}

