import java.util.Scanner;

public class Main {

    public static void main (String[] args){

        boolean begin = true;

        Scanner scan = new Scanner(System.in);

        while (begin) {

            System.out.print("1. Create review \n" +
                    "2. Quit \n" +
                    "Make a selection: ");

            int choice = scan.nextInt();

            if (choice == 1) {

                createReview();
            }

            else {

                begin = false;
            }
        }

    }

    public static void createReview(){

        Scanner scan = new Scanner(System.in);

        System.out.print("Name of drink: ");

        String name = scan.nextLine();

        System.out.print("\n Name of shop: ");

        String place = scan.nextLine();

        String date = createDate();

        String quality = drinkQuality();

        String bobaQuality = bobaQuality();

        System.out.print("Who is reviewing this drink: ");

        String person = scan.nextLine();

        Boba drink = new Boba(name, place, date, quality, bobaQuality, person);
    }

    public static String createDate() {

        Scanner scan = new Scanner(System.in);

        System.out.print("Month (01-12): ");

        String month = scan.nextLine();

        while (month.length() < 2 || month.length() > 3) {

            System.out.print("Incorrect format. Try again \n" +
                    "Month (01-12): ");

            month = scan.nextLine();
        }

        System.out.print("Day (00): ");

        String day = scan.nextLine();

        while (day.length() < 2 || day.length() > 2) {

            System.out.print("Incorrect format. Try again \n" +
                    "Day (00): ");

            day = scan.nextLine();
        }

        System.out.print("Year (0000): ");

        String year = scan.nextLine();

        while (year.length() < 4 || year.length() > 4) {

            System.out.print("Incorrect format. Try again \n" +
                    "Year (0000): ");

            year = scan.nextLine();
        }

        return month + "/" + day + "/" + year;
    }

    public static String drinkQuality() {

        Scanner scan = new Scanner(System.in);

        System.out.print("Was the drink perfect? Y/N: ");

        String input = scan.nextLine();

        if (input.equals("Y") || input.equals("y")) {

            return "Perfect";
        }

        else {

            System.out.println("Was the drink: \n" +
                    "1. Powdery \n" +
                    "2. Watery \n" +
                    "3. Too sweet \n" +
                    "4. Not sweet ");

            int choice = scan.nextInt();

            while (choice < 1 || choice > 4) {

                System.out.println("Incorrect input. Try again \n" +
                        "Was the drink: \n" +
                        "1. Powdery \n" +
                        "2. Watery \n" +
                        "3. Too sweet \n" +
                        "4. Not sweet ");

                choice = scan.nextInt();
            }

            if (choice == 1) {

                return "Powdery";
            }

            else if (choice == 2) {

                return "Watery";
            }

            else if (choice == 3) {

                return "Too sweet";
            }

            else {

                return "Not sweet";
            }
        }
    }

    public static String bobaQuality() {

        Scanner scan = new Scanner(System.in);

        System.out.print("Was the boba perfect? Y/N: ");

        String input = scan.nextLine();

        if (input.equals("Y") || input.equals("y")) {

            return "Perfect";
        }

        else {

            System.out.println("Was the boba: \n" +
                    "1. Hard \n" +
                    "2. Too soft \n" +
                    "3. Slimy ");

            int choice = scan.nextInt();

            while (choice < 1 || choice > 3) {

                System.out.println("Incorrect input. Try again \n" +
                        "Was the boba: \n" +
                        "1. Hard \n" +
                        "2. Too soft \n" +
                        "3. Slimy ");

                choice = scan.nextInt();
            }

            if (choice == 1) {

                return "Hard";
            }

            else if (choice == 2) {

                return "Too soft";
            }

            else {

                return "Slimy";
            }
        }
    }
}
