import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Varor varor = new Varor ();
    static VaruLager varuLager = new VaruLager ();
    static Scanner scanner = new Scanner (System.in);

    public static void main(String[] args) {

        System.out.println("For Administrator enter 1");
        int choice = Integer.parseInt ( scanner.nextLine ());
        if(choice==1)
        System.out.println ("To add products press 1");
        choice= Integer.parseInt ( scanner.nextLine ());

        if (choice==1)
           addproduct();
            //System.out.println (varuLager.products.toString ());
            System.out.println(varuLager.products.toString ());


        }

        public static ArrayList addproduct(){

            System.out.print ("Enter the name of the product: ");       //Tillverkarens namn på produkten
            String name = scanner.nextLine ();
            System.out.print ("Enter the price of the product: ");      //Pris på produkten
            double price = Integer.parseInt ( scanner.nextLine ());
            System.out.print ("Enter the type of " /*the product"*/+ name +": ");       //Varianten på produkten ex Ekologisk
            String type = scanner.nextLine ();
            System.out.print ("Enter the amount of the product: ");     //Antalet produkter
            double amount = Integer.parseInt ( scanner.nextLine ());
            System.out.print ("Choose the category of the product: ");  //Kategori av produkten
            String category = scanner.nextLine ();
            int id = getID ();
            varor = new Varor (name, price, type,category, id, amount);
            varuLager.products.add (varor);
            return varuLager.products;
        }

        public static int getID (){
            return varor.getID ();
    }
}
