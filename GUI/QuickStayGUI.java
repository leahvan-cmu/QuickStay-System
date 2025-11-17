package GUI;

import Model.Property;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class QuickStayGUI extends Application {

	public static void main(String[] args) {

		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

//CODE TO GET DATA FROM FILE
ObservableList<Property> properties = FXCollections.observableArrayList();
try (BufferedReader br = new BufferedReader(new FileReader("Resources/CurrentListings.csv"))) {
	br.readLine();

	String line;	        
		            
	while ((line = br.readLine()) != null) {	                           
		String[] values = line.split(",");
		if (values.length == 8) {
			String name = values[0].trim();
			String address = values[1].trim();
			String city = values[2].trim();
			int bedroom = Integer.parseInt(values[3].trim());
			double bathroom = Double.parseDouble(values[4].trim());
			double price = Double.parseDouble(values[5].trim());
			int stayLength = Integer.parseInt(values[6].trim());
			boolean isAvailable = Boolean.parseBoolean(values[7].trim());

			properties.add(new Property(name, address, city, bedroom, bathroom, price, stayLength, isAvailable));
		}
}
} catch (IOException e) {
	e.printStackTrace();
}

//STARTING SCREEN CODE
		FlowPane startPane = new FlowPane();
		startPane.setPadding(new Insets(11,12,13,14));
		startPane.setHgap(15);
		startPane.setVgap(5);
		
		startPane.setAlignment(Pos.CENTER);
		
		Button searchBtn = new Button("Search");
		Button viewBtn = new Button("View All Listings");
		Button moneyBtn = new Button("Money Converter");
		Button exitBtn = new Button("Exit");
		
		startPane.getChildren().addAll(searchBtn, viewBtn, moneyBtn, exitBtn);
		
//TABLE CODE
		BorderPane tablePane = new BorderPane();
		TableView<Property> tableView = new TableView<>();
		tableView.setEditable(true);
		
		
		
//BUTTON CODE TO GET TO INDIVIDUAL SCENES
		viewBtn.setOnAction(event -> {
	        Scene tableScene = new Scene(tablePane, 500, 500);
	        primaryStage.setScene(tableScene);
	        primaryStage.setTitle("All Listings");
	    });

//EXIT BUTTON CODE 
		exitBtn.setOnAction(event -> {
			primaryStage.close();
		});

//SCENE CODE
		Scene scene = new Scene(startPane, 500, 500);
		
		primaryStage.setTitle("QuickStay");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

}
