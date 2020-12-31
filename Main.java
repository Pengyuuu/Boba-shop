import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.nio.file.*;

public class Main extends Application {

    Button login;

    public static void main (String[] args){

        launch(args);
    	
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ArrayList<Boba> drinks = readBoba();

        // Title of the window
        primaryStage.setTitle("Boba Reviewer");

        // Create some buttons
        Button login = new Button("Enter");
        Button exit = new Button("Exit");
        Button exit2 = new Button("Exit");
        Button createReview = new Button("Create a Review");
        Button goBack = new Button("Back");
        Button createObject = new Button("Create");
        Button viewAndEdit = new Button ("View/Edit");
        Button delete = new Button("Delete");
        Button viewAndEditBack = new Button("Back");

        /**
        StackPane layout = new StackPane();
        layout.getChildren().add(login);
        layout.getChildren().add(exit2);
        */

        // Creates a box for the login and exit button and position it in the center of the window
        VBox loginMenu = new VBox(10);
        loginMenu.getChildren().addAll(login, exit);
        loginMenu.setAlignment(Pos.CENTER);

        // Creates a box for the main menu
        VBox mainMenu = new VBox(10);
        mainMenu.getChildren().addAll(createReview, viewAndEdit, exit2);
        mainMenu.setAlignment(Pos.CENTER);

        // Stuff in the create review window
        VBox reviewFields = new VBox(10);

        // Drink name option
        Label drinkName = new Label("Drink:");
        TextField drinkInput = new TextField();
        drinkInput.setMaxWidth(250);

        // Boba shop option
        Label shop = new Label("Shop:");
        TextField shopInput = new TextField();
        shopInput.setMaxWidth(250);

        // Dropdown menu for the drink quality
        Label drinkQuality = new Label("Drink Quality:");
        ChoiceBox<String> drinkQualityBox = new ChoiceBox<>();
        drinkQualityBox.getItems().addAll("1. Powdery", "2. Watery", "3. Sweet", "4. Not Sweet", "5. Perfect");
        drinkQualityBox.setMaxWidth(250);

        // Dropdown menu for the boba quality
        Label bobaQuality = new Label("Boba Quality:");
        ChoiceBox<String> bobaQualityBox = new ChoiceBox<>();
        bobaQualityBox.getItems().addAll("1. Hard", "2. Too Soft", "3. Slimy", "4. Perfect");
        bobaQualityBox.setMaxWidth(250);

        // Reviewer option
        Label reviewer = new Label("Person Reviewing:");
        TextField reviewerInput = new TextField();
        reviewerInput.setMaxWidth(250);

        reviewFields.getChildren().addAll(drinkName, drinkInput, shop, shopInput, drinkQuality, drinkQualityBox, 
            bobaQuality, bobaQualityBox, reviewer, reviewerInput);
        reviewFields.setAlignment(Pos.CENTER);

        // Options to create the review or go back
        HBox reviewFieldsOptions = new HBox(30);
        reviewFieldsOptions.getChildren().addAll(createObject, goBack);
        reviewFieldsOptions.setAlignment(Pos.BOTTOM_CENTER);

        // Page that's going to hold the reivew options
        BorderPane reviewWindow = new BorderPane();
        reviewWindow.setCenter(reviewFields);
        reviewWindow.setBottom(reviewFieldsOptions);

        // Page to hold View/Edit options
        BorderPane viewEditWindow = new BorderPane();
        ListView<String> listView = new ListView<>();
        populateList(drinks, listView);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        viewEditWindow.setCenter(listView);

        HBox viewAndEditOptions = new HBox(30);
        viewAndEditOptions.getChildren().addAll(delete, viewAndEditBack);
        viewEditWindow.setBottom(viewAndEditOptions);

        // Creates the windows with the boxes
        Scene program = new Scene(loginMenu, 500, 500);
        Scene menu = new Scene(mainMenu, 500, 500);
        Scene review = new Scene(reviewWindow, 500, 500);
        Scene viewEdit = new Scene(viewEditWindow, 500, 500);

        // When they click the enter button, go to the main window
        login.setOnAction(e -> primaryStage.setScene(menu));

        // When they click create a review, go to the review creation page
        createReview.setOnAction(e -> primaryStage.setScene(review));

        // When they click View/Edit, go to the data page
        viewAndEdit.setOnAction(e -> primaryStage.setScene(viewEdit));

        // If they click the back button, go back to the main menu
        goBack.setOnAction(e -> primaryStage.setScene(menu));
        viewAndEditBack.setOnAction(e -> primaryStage.setScene(menu));

        createObject.setOnAction(e -> {
            createBoba(drinkInput.getText(), shopInput.getText(), 
            drinkQualityBox.getValue(), bobaQualityBox.getValue(), reviewerInput.getText(), drinks);
            listView.getItems().clear();
            populateList(drinks, listView);
            drinkInput.clear();
            shopInput.clear();
            drinkQualityBox.hide();
            bobaQualityBox.hide();
            reviewerInput.clear();
            primaryStage.setScene(menu);
        });

        // If they click any of the exits, close the program
        exit.setOnAction(e -> exitDisplay("Exit", "Are you sure you want to quit?", drinks));
        exit2.setOnAction(e -> exitDisplay("Exit", "Are you sure you want to quit?", drinks));
        primaryStage.setOnCloseRequest(e -> exitDisplay("Exit", "Are you sure you want to quit?", drinks));

        // The login window shows first
        primaryStage.setScene(program);

        // Begin the GUI
        primaryStage.show();
    }

    public static void exitDisplay(String title, String message, ArrayList<Boba> drinks) {

        Stage exitWindow = new Stage();

        // User cannot click out of the window until they deal with it first
        exitWindow.initModality(Modality.APPLICATION_MODAL);

        exitWindow.setTitle(title);

        Label exitLabel = new Label(message);

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            writeBoba(drinks);
            System.exit(0);
        });
        noButton.setOnAction(e -> exitWindow.close());

        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(exitLabel, yesButton, noButton);
        buttonLayout.setAlignment(Pos.CENTER);

        Scene exitScene = new Scene(buttonLayout, 350, 50);
        exitWindow.setScene(exitScene);

        // Display the window and cannpt go back until it is closed
        exitWindow.showAndWait();
    }

    public static ArrayList<Boba> createBoba(String name, String shop, String drinkQuality, String bobaQuality, String reviewer, ArrayList<Boba> drinks){

        drinks.add(new Boba(name, shop, java.time.LocalDate.now().toString(), drinkQuality, bobaQuality, reviewer));

        return drinks;
    }

    public static ArrayList<Boba> readBoba() {

        ArrayList<Boba> drinks = new ArrayList<>();

        try {

            Scanner read = new Scanner(new File("/Users/pengu/OneDrive/Documents/Java/Boba-shop/src/data/drinks.txt"));

            while (read.hasNext()) {

                String line = read.nextLine();

                String[] tokens = line.split(";");

                System.out.println(tokens.length);

                for (String i : tokens) {

                    System.out.println(i);
                }

                drinks.add(new Boba(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]));
            }
        } catch (FileNotFoundException fnf) {

            System.out.println("File not found");
        }

        return drinks;
    }

    public static void writeBoba(ArrayList<Boba> drinks) {

        try {

            PrintWriter writer = new PrintWriter("/Users/pengu/OneDrive/Documents/Java/Boba-shop/src/data/drinks.txt");

            for (Boba i : drinks) {

                writer.write(i.getName() + ";" + i.getPlace() + ";" + i.getDate() + ";" + i.getRateDrink() + 
                    ";" + i.getRateBoba() + ";" + i.getReviewer() + "\n");
            }
            
            writer.close();
        } catch (FileNotFoundException fnf) {

            System.out.println("File not found");
        }
    }

    public static void populateList(ArrayList<Boba> drinks, ListView<String> listView) {

        for (Boba i : drinks) {

            listView.getItems().add(i.toString());
        }
    }
}
