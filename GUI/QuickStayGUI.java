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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;


public class QuickStayGUI extends Application {

	public static void main(String[] args) {

		launch(args);
		
	}

	//REUSABLE TABLE CODE FOR SEARCH AND VIEW ALL LISTINGS - Chris Cantin
	public static TableView<Property> tableMaker(ObservableList<Property> properties) {
		TableView<Property> tableView = new TableView<>();
		tableView.setEditable(true);
		tableView.setItems(properties);
		
		TableColumn<Property, String> colName = new TableColumn<>("Name");
			colName.setCellValueFactory(new PropertyValueFactory<>("name"));
			colName.setCellFactory(TextFieldTableCell.forTableColumn());
			colName.setOnEditCommit(e -> {
				Property property = e.getRowValue();
				String newName = e.getNewValue();
				property.setName(newName);
			});

			TableColumn<Property, String> colAddress = new TableColumn<>("Address");
			colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
			colAddress.setCellFactory(TextFieldTableCell.forTableColumn());
			colAddress.setOnEditCommit(e -> {
				Property property = e.getRowValue();
				String newAddress = e.getNewValue();
				property.setAddress(newAddress);
			});

			TableColumn<Property, String> colCity = new TableColumn<>("City");
			colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
			colCity.setCellFactory(TextFieldTableCell.forTableColumn());
			colCity.setOnEditCommit(e -> {
				Property property = e.getRowValue();
				String newCity = e.getNewValue();
				property.setCity(newCity);
			});

			TableColumn<Property, Integer> colBedroom = new TableColumn<>("# of Bedrooms");
			colBedroom.setCellValueFactory(new PropertyValueFactory<>("bedroom"));
			colBedroom.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			colBedroom.setOnEditCommit(e -> {
				Property property = e.getRowValue();
				int newBedroom = e.getNewValue();
				property.setBedroom(newBedroom);
			});

			TableColumn<Property, Double> colBathroom = new TableColumn<>("# of Bathrooms");
			colBathroom.setCellValueFactory(new PropertyValueFactory<>("bathroom"));
			colBathroom.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
			colBathroom.setOnEditCommit(e -> {
				Property property = e.getRowValue();
				double newBathroom = e.getNewValue();
				property.setBathroom(newBathroom);
			});

			TableColumn<Property, Double> colPrice = new TableColumn<>("Price");
			colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
			colPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
			colPrice.setOnEditCommit(e -> {
				Property property = e.getRowValue();
				double newPrice = e.getNewValue();
				property.setPrice(newPrice);
			});

			TableColumn<Property, Integer> colStayLength = new TableColumn<>("Length of Stay");
			colStayLength.setCellValueFactory(new PropertyValueFactory<>("stayLength"));
			colStayLength.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			colStayLength.setOnEditCommit(e -> {
				Property property = e.getRowValue();
				int newStayLength = e.getNewValue();
				property.setStayLength(newStayLength);
			});

			TableColumn<Property, Boolean> colAvailable = new TableColumn<>("Available?");
			colAvailable.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
            colAvailable.setCellFactory(CheckBoxTableCell.forTableColumn(colAvailable));
			colAvailable.setOnEditCommit(e -> {
				Property property = e.getRowValue();
				boolean newAvailable = e.getNewValue();
				property.setAvailable(newAvailable);
			});


			colName.setPrefWidth(100);
			colAddress.setPrefWidth(200);
			colCity.setPrefWidth(100);
			colBedroom.setPrefWidth(100);
			colBathroom.setPrefWidth(100);
			colPrice.setPrefWidth(100);
			colStayLength.setPrefWidth(100);
			colAvailable.setPrefWidth(100);

			tableView.getColumns().addAll(colName, colAddress, colCity, colBedroom, colBathroom, colPrice, colStayLength, colAvailable);

			return tableView;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {

//CODE TO GET DATA FROM FILE - Chris Cantin
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
//LOGIN SCREEN CODE - Ethan


//STARTING SCREEN CODE - Chris Cantin
FlowPane startPane = new FlowPane();
startPane.setPadding(new Insets(11,12,13,14));
startPane.setHgap(15);
startPane.setVgap(5);

startPane.setAlignment(Pos.CENTER);

Button searchBtn = new Button("Search");
Button viewBtn = new Button("View All Listings");
Button moneyBtn = new Button("Money Converter");
Button exitBtn = new Button("Exit");
Button goBackBtn1 = new Button("Go Back");

//if you're looking at this do NOT add go back button to this code pls. just declaring it here for now.
startPane.getChildren().addAll(searchBtn, viewBtn, moneyBtn, exitBtn);


//VIEW ALL LISTINGS - Chris Cantin
	BorderPane tablePane = new BorderPane();
	TableView<Property> tableView = tableMaker(properties);

	HBox buttonPane = new HBox(10);
	buttonPane.getChildren().addAll(goBackBtn1);
	buttonPane.setAlignment(Pos.CENTER);
			
	tablePane.setCenter(tableView);
	tablePane.setBottom(buttonPane);

	Scene tableScene = new Scene(tablePane, 500, 500);
		
		
//MONEY CONVERTER MENU CODE - Someone else pls 
//SEARCH MENU CODE - Someone else pls 		
//BUTTON CODE TO GET TO INDIVIDUAL SCENES - Chris Cantin
		viewBtn.setOnAction(event -> {
	        primaryStage.setScene(tableScene);
	        primaryStage.setTitle("All Listings");
	    });

//EXIT BUTTON CODE - Chris Cantin
		exitBtn.setOnAction(event -> {
			primaryStage.close();
		});

//SCENE CODE (when Login Menu is finished, change the primaryStage startScene call to whatever you name the Login Scene)
		Scene startScene = new Scene(startPane, 500, 500);
		
		primaryStage.setTitle("QuickStay");
		primaryStage.setScene(startScene);
		primaryStage.show();

//GO BACK BUTTON CODE - Chris Cantin
goBackBtn1.setOnAction(event -> {
	primaryStage.setScene(startScene);
	
});
		
	}

}
