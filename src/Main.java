import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Varor varor = new Varor ();
    static VaruLager varuLager = new VaruLager ();
    static Scanner scanner= new Scanner (System.in);

    public static void main(String[] args) {

        Scanner scanner = new Scanner (System.in);
        System.out.println("For Administrator enter 1");
        int choice = Integer.parseInt ( scanner.nextLine ());
        if(choice==1)
        System.out.println ("To add products press 1");
        choice= Integer.parseInt ( scanner.nextLine ());

        if (choice==1)
            addproduct();
        System.out.println (varuLager.products.toString ());
    }

    public static ArrayList addproduct () {
        System.out.print ("Enter the name of the product: ");
        String name = scanner.nextLine ();
        System.out.print ("Enter the price of the product: ");
        double price = Integer.parseInt ( scanner.nextLine ());
        System.out.print ("Enter the unit of the product: ");
        String type = scanner.nextLine ();
        System.out.print ("Enter the amount of the product: ");
        double amount = Integer.parseInt ( scanner.nextLine ());
        System.out.print ("Choose the category of the product: ");
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
