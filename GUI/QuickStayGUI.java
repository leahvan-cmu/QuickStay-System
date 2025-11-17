package GUI;

import javafx.application.Application;
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
		TableView<String> tableView = new TableView<>();
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
