package GUI;

import Service.PropertyService;
import Model.User;
import Service.BookingService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class QuickStayLogin extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Image image = new Image("Resources/QuickStayImage.png", 350, 350, true, true);
        ImageView imageView = new ImageView(image);
        imageView.setStyle("-fx-border-color: red;");

        // --- Class GUI ---
        TextField txtUserName = new TextField();
        PasswordField txtPassword = new PasswordField();

        GridPane gridPane = new GridPane();
        Label lblUser = new Label("User Name");
        lblUser.setStyle("-fx-text-fill: #D4AF37; -fx-font-weight: bold;");
        gridPane.add(lblUser, 0, 0);
        gridPane.add(txtUserName, 1, 0);
        
        Label lblPass = new Label("Password");
        lblPass.setStyle("-fx-text-fill: #D4AF37; -fx-font-weight: bold;");
        gridPane.add(lblPass, 0, 1);
        gridPane.add(txtPassword, 1, 1);

        Button btnLogin = new Button("Login");
        Button btnSignup = new Button("Sign Up");
        Button btnCancel = new Button("Cancel");

        // --- Buttons at the bottom ---
        HBox hBox = new HBox(10); // gap between buttons
        hBox.getChildren().addAll(btnLogin, btnSignup, btnCancel);
        hBox.setAlignment(Pos.CENTER);
        HBox.setMargin(btnLogin, new Insets(0, 10, 0, 0));

        gridPane.add(hBox, 1, 2);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(8);
        gridPane.setVgap(10);

        BorderPane pane = new BorderPane();
        pane.setTop(new StackPane(imageView));
        pane.setBottom(new StackPane(new Label("Copyright @ 2025. All rights reserved to CMU.")));
        pane.setCenter(new StackPane(gridPane));
        
        pane.setStyle("-fx-background-color: #111927;");

        Scene scene = new Scene(pane, 500, 500);

        primaryStage.setTitle("QuickStay");
        primaryStage.setScene(scene);
        primaryStage.show();

        // --- Button SHIT --- 

        // Login button
        btnLogin.setOnAction(e -> {
            String username = txtUserName.getText();
            String password = txtPassword.getText();

            if (UserStorage.validateLogin(username, password)) {

                // Get user's preferred currency from users.txt
                String userCurrency = UserStorage.getUserCurrency(username);

                
                User loggedInUser = new User(username, "", userCurrency, password);

                PropertyService propertyService = new PropertyService();
                BookingService bookingService = new BookingService();

                try {
                    QuickStayGUI gui = new QuickStayGUI(loggedInUser, propertyService, bookingService, primaryStage);
                    primaryStage.setScene(gui.getStartScene());
                    primaryStage.setTitle("QuickStay - Home");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to open main GUI.");
                }
            } else {
                showAlert(AlertType.ERROR, "Login Failed", "Invalid username or password.");
            }
        });

        // Sign Up button
        //FIXME
        btnSignup.setOnAction(e -> {
        	
        	Stage signUp = new Stage();
        	signUp.setTitle("QuickStay: Create Account");

        	
        	GridPane signUpPane = new GridPane();
        	
        	TextField createUsername = new TextField();
            PasswordField createPassword = new PasswordField();
            TextField emailField = new TextField();
        	
        	signUpPane.add(new Label("Create Username"), 0, 0);
        	signUpPane.add(createUsername, 1, 0);
        	signUpPane.add(new Label("Create Password"), 0, 1);
        	signUpPane.add(createPassword, 1, 1);
        	signUpPane.add(new Label("Add Email"), 0, 2);
        	signUpPane.add(emailField, 1, 2);
        	
        	ComboBox<String> currency = new ComboBox<String>();
        	currency.getItems().addAll("USD", "CAD", "AUD", "EUR", "GBP", "JPY");
        	currency.setValue("USD");
        	signUpPane.add(currency, 1, 3);
        	signUpPane.add(new Label("Local Currency"), 0, 3);
        	
        	signUpPane.setAlignment(Pos.TOP_CENTER);
        	signUpPane.setHgap(8);
        	signUpPane.setVgap(10);
        	
        	  Button btnCreateAcc = new Button("Create");
              Button btnClose = new Button("Close");
              
              HBox accountHBox = new HBox(10); // gap between buttons
              accountHBox.getChildren().addAll(btnCreateAcc, btnClose);
              accountHBox.setAlignment(Pos.BOTTOM_CENTER);
        	
              signUpPane.add(accountHBox, 0, 16);
        	

            
            btnCreateAcc.setOnAction(e1 -> {
            	String username = createUsername.getText();
            	String password = createPassword.getText();
            	String email = emailField.getText();
            	String userCurrency = currency.getValue();
            	//System.out.println(currency.getValue());
            	
            	if (username.isEmpty() || password.isEmpty() || email.isEmpty() || userCurrency == null) {
            		showAlert(AlertType.ERROR, "Sign Up Failed", "Add all required fields.");
            		return;
            	}
            	
            	boolean registered = UserStorage.registerUser(username, password, email, userCurrency);
            	
            	if(registered) {
            		showAlert(AlertType.INFORMATION, "Sign Up Successful", "Account made for " + username);
            	} else {
            		showAlert(AlertType.ERROR, "Sign Up Failed", "not printed to file.");
            	}
            });

            btnClose.setOnAction(e1 -> {
            	signUp.close();
         
            });
            
        	Scene signUpScene = new Scene(signUpPane, 300, 300);
        	signUp.setScene(signUpScene);
        	signUp.show();

        });

        // Cancel button
        btnCancel.setOnAction(e -> {
            txtUserName.clear();
            txtPassword.clear();
        });
    }

    //show alerts
    public static void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
