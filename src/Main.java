import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        System.out.println("For Administrator enter 1");
        int choice = scanner.nextInt ();
        if(choice==1)
        System.out.println ("To add products press 1");
        choice=scanner.nextInt ();
        if (choice==1)
            System.out.println ("Enter the name of the product");
            String name = scanner.nextLine ();
            System.out.println ("Enter the price of the product");
            double price = scanner.nextDouble ();
            System.out.println ("Enter the type of the product");
            String type = scanner.nextLine ();
            System.out.println ("Enter the amount of the product");
            double amount = scanner.nextDouble ();
            System.out.println ("Choose the category of the product");
            String category = scanner.nextLine ();









    }
}
