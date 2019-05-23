import com.sun.glass.ui.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;


public abstract class Main extends Application {


    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300,275));
        primaryStage.show();

    }

    static Varor varor = new Varor();
    static User user = new User();
    static VaruLager varuLager = new VaruLager();


    public static void main (String[]args){

        boolean newBoolean = true;
        while (newBoolean) {

            String string = JOptionPane.showInputDialog("For Administrator enter 1\nFor User press 2");
            int choice = Integer.parseInt(string);
            if (choice == 1) {
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
            else if (choice==2){
                string = JOptionPane.showInputDialog("To add product press 1\nTo register press 2");
                choice = Integer.parseInt(string);
                if (choice==1) {
                    addProductToCustomer();
                    JOptionPane.showMessageDialog(null,"Products in shopingcart: \n" + printArrayShoping() );

                }
                else if(choice==2){
                    JOptionPane.showInputDialog("Enter your name");
                }
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
    public static String printArrayShoping() {
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
}
