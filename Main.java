import java.util.Scanner;

public class Main {

    public static void main (String[] args){

        createReview();


    }

    public static void createReview(){

        Scanner scan = new Scanner(System.in);

        System.out.print("Name of drink: ");

        String name = scan.nextLine();

        System.out.print("\n Name of shop: ");

        String place = scan.nextLine();

        System.out.print("\n Date: ");

        String date = scan.nextLine();


    }
}
