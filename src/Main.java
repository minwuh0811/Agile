import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Varor varor = new Varor();
    static VaruLager varuLager = new VaruLager();
    static Scanner scanner = new Scanner (System.in);


    public static void main (String[]args){
        boolean newBoolean = true;
        while (newBoolean) {
            System.out.println("For Administrator enter 1");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1)
                System.out.println("To add products press 1");
            System.out.println("To remove a product press 2");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addProduct();
                    break;

                case 2:
                    removeProduct();
                    break;

                default:
                    System.out.println("Invalid choice");
                    newBoolean = false;

            }
        }
    }

    public static void removeProduct () {
        System.out.println("Choose a product to be removed");
        System.out.println(varuLager.products.toString());
        System.out.println("Enter the product ID");
        int id = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < varuLager.products.size(); i++) {
            if (varuLager.products.get(i).getID() == id) {
                varuLager.products.remove(i);
                System.out.println("The products has been removed");
                break;
            }
        }

    }

    public static ArrayList addProduct(){




        System.out.print ("Enter the name of the product: ");       //Tillverkarens namn på produkten

        String name = scanner.nextLine ();

        System.out.print ("Enter the price of the product: ");      //Pris på produkten

        double price = Integer.parseInt ( scanner.nextLine ());


        String tempName = name.toLowerCase();

        System.out.print ("Enter the type of " /*the product"*/+ tempName +": ");       //Varianten på produkten ex Ekologisk

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
