package GUI;

import Model.Property;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;


public class QuickStayGUI {

	private Scene startScene;
    private Scene viewAllScene;

	public Scene getStartScene() {
		return startScene;
	}

	public void setStartScene(Scene startScene) {
		this.startScene = startScene;
	}

	public Scene getViewAllScene() {
		return viewAllScene;
	}

	public void setViewAllScene(Scene viewAllScene) {
		this.viewAllScene = viewAllScene;
	}
	
	

	public QuickStayGUI(Scene startScene, Scene viewAllScene) {
		super();
		this.startScene = startScene;
		this.viewAllScene = viewAllScene;
	}

	//REUSABLE TABLE CODE FOR SEARCH AND VIEW ALL LISTINGS - Chris Cantin
	public static TableView<Property> tableMaker(ObservableList<Property> properties) {
		TableView<Property> tableView = new TableView<>();
		tableView.setItems(properties);
		
		TableColumn<Property, String> colName = new TableColumn<>("Name");
			colName.setCellValueFactory(new PropertyValueFactory<>("name"));
			colName.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<Property, String> colAddress = new TableColumn<>("Address");
			colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
			colAddress.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<Property, String> colCity = new TableColumn<>("City");
			colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
			colCity.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<Property, Integer> colBedroom = new TableColumn<>("# of Bedrooms");
			colBedroom.setCellValueFactory(new PropertyValueFactory<>("bedroom"));
			colBedroom.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

			TableColumn<Property, Double> colBathroom = new TableColumn<>("# of Bathrooms");
			colBathroom.setCellValueFactory(new PropertyValueFactory<>("bathroom"));
			colBathroom.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

			TableColumn<Property, Double> colPrice = new TableColumn<>("Price");
			colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
			colPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

			TableColumn<Property, Integer> colStayLength = new TableColumn<>("Length of Stay");
			colStayLength.setCellValueFactory(new PropertyValueFactory<>("stayLength"));
			colStayLength.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

			TableColumn<Property, Boolean> colAvailable = new TableColumn<>("Available?");
			colAvailable.setCellValueFactory(new PropertyValueFactory<>("Available"));
			colAvailable.setCellValueFactory(cellData ->
		    new SimpleBooleanProperty(cellData.getValue().isAvailable()));
			colAvailable.setCellFactory(tc -> new CheckBoxTableCell<>());
           // colAvailable.setCellFactory(CheckBoxTableCell.forTableColumn(colAvailable));


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


public ObservableList<Property> viewList = FXCollections.observableArrayList();
	
public QuickStayGUI(Stage primaryStage) {

//CODE TO GET DATA FROM FILE - Chris Cantin
	ObservableList<Property> properties = FXCollections.observableArrayList();
	

try (BufferedReader br = new BufferedReader(new FileReader("src/Resources/CurrentListings.csv"))) {
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

//STARTING SCREEN CODE - Chris Cantin
FlowPane startPane = new FlowPane();
startPane.setPadding(new Insets(11,12,13,14));
startPane.setHgap(15);
startPane.setVgap(5);

startPane.setAlignment(Pos.CENTER);


Button viewBtn = new Button("View All Listings");
Button exitBtn = new Button("Exit");
Button goBackBtn1 = new Button("Go Back");
Button filterSearchBtn = new Button("Search By Filter");

startPane.getChildren().addAll(viewBtn, filterSearchBtn, exitBtn);
startScene = new Scene(startPane, 500, 500);

//VIEW ALL LISTINGS - Chris Cantin
	BorderPane tablePane = new BorderPane();
	TableView<Property> tableView = tableMaker(viewList);

	HBox buttonPane = new HBox(10);
	buttonPane.getChildren().addAll(goBackBtn1);
	buttonPane.setAlignment(Pos.CENTER);
			
	tablePane.setCenter(tableView);
	tablePane.setBottom(buttonPane);

	Scene tableScene = new Scene(tablePane, 900, 600);	
	
	
	//FilteredSearch scene Avery
		GridPane filterPane = new GridPane();
		filterPane.setAlignment(Pos.CENTER);
		
		Slider priceSlider = new Slider();
		priceSlider.setMin(120);
		priceSlider.setMax(780);
		priceSlider.setValue(780);
		
		Label priceValue = new Label("Price: 780");
		
	//Code provided from GeeksForGeeks
		priceSlider.valueProperty().addListener(
	             new ChangeListener<Number>() {

	            public void changed(ObservableValue <? extends Number > 
	                      observable, Number oldValue, Number newValue)
	            {

	            	priceValue.setText(String.format("Price: %.0f", newValue));
	            }
	        });
		Slider bedSlider = new Slider(1, 8, 1);
		bedSlider.setMajorTickUnit(1);
		bedSlider.setMinorTickCount(0);
		bedSlider.setSnapToTicks(true);
		bedSlider.setShowTickLabels(true);
		bedSlider.setShowTickMarks(true);
		
		
		filterPane.add(new Label("Maximum Price: "), 0, 0);
		filterPane.add(priceSlider, 1, 0);
		filterPane.add(priceValue, 2, 0);
		filterPane.add(new Label("Select City: "), 0, 1);
		ComboBox<String> citySelect = new ComboBox<String>();
		citySelect.getItems().addAll("Any", "Mt. Pleasant", "Midland", "Lansing", "Grand Rapids", "Mackinac Island", "Detroit", "Ann Arbor", "Traverse City");
		citySelect.setValue("Any");
    		filterPane.add(citySelect, 1, 1);
		filterPane.add(new Label("Minimum Bedrooms: "), 0, 2);
		filterPane.add(bedSlider, 1, 2);
		filterPane.add(new Label("Include non-vacant: "), 0, 3);
		CheckBox includeNonvacant = new CheckBox();
		filterPane.add(includeNonvacant, 1, 3);
		
		Button searchBtn = new Button("Search");
		Button goBackBtn2 = new Button("Go Back");
		
		filterPane.add(searchBtn, 0, 4);
		filterPane.add(goBackBtn2, 1, 4);
		
		
	    
	    Scene filteredSearchScene = new Scene (filterPane, 500, 500);
	
	
//BUTTON CODE TO GET TO INDIVIDUAL SCENES - Chris Cantin
		viewBtn.setOnAction(event -> {
			viewList.clear();
			viewList.addAll(properties);
	        primaryStage.setScene(tableScene);
	        primaryStage.setTitle("All Listings");
	    });
		
//Filtered search screen
		filterSearchBtn.setOnAction(event -> {
		primaryStage.setScene(filteredSearchScene);
		});
		
//Filter search logic
		searchBtn.setOnAction(event -> {
			LinkedList<Property> filteredProps = new LinkedList<>();
		//Add all properties that fall into price tolerance
			for(int i = 0; i < properties.size(); i++) {
				if(properties.get(i).getPrice() <= priceSlider.getValue()) {
					filteredProps.add(properties.get(i));
				}
			}
			
		//Remove all properties that don't match filtered city
			for(int i = filteredProps.size()-1; i > -1 ; i--) {
				if(!citySelect.getValue().equals("Any") && !filteredProps.get(i).getCity().equalsIgnoreCase(citySelect.getValue())) {
					filteredProps.remove(i);
				}
			}
			
		//Remove all properties that don't fall into bedroom count
			for(int i = filteredProps.size()-1; i > -1; i--) {
				if(filteredProps.get(i).getBedroom() < (int) bedSlider.getValue()) {
					filteredProps.remove(i);
				}
			}
		//remove non vacant properties if box is unchecked
			for(int i = filteredProps.size()-1; i > -1; i--) {
				if(!includeNonvacant.isSelected() && !filteredProps.get(i).isAvailable()) {
					filteredProps.remove(i);
				}
			}			
			
			viewList.clear();
			viewList.addAll(filteredProps);
			primaryStage.setScene(tableScene);
	        primaryStage.setTitle("Filtered Listings");
			
			
		});
		
//EXIT BUTTON CODE - Chris Cantin
		exitBtn.setOnAction(event -> {
			primaryStage.close();
		});

//GO BACK BUTTON CODE - Chris Cantin
	goBackBtn1.setOnAction(event -> {
		primaryStage.setScene(startScene);
		
	});
	goBackBtn2.setOnAction(event -> {
		primaryStage.setScene(startScene);
		
	});
		
	}

}
