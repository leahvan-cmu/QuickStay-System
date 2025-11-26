package GUI;

import Model.Booking;
import Model.Property;
import Model.User;
import Service.BookingService;
import Service.PropertyService;
import java.time.LocalDate;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookingGUI {

    public BookingGUI(User currentUser, PropertyService propertyService, BookingService bookingService, Stage primaryStage) {
    }
    private Scene bookingScene;

    public Scene getBooking(User user, PropertyService propertyService, BookingService bookingService, Stage primaryStage, Scene startScene) throws Exception {
        
        Button goBackBtn = new Button("Go Back");
        goBackBtn.setOnAction(ev -> {
        primaryStage.setScene(startScene);
});
        Stage stage = new Stage();
        stage.setTitle("Create Booking");

        // Dropdown box with available properties
        ComboBox<Property> propertyBox = new ComboBox<>();
        propertyBox.getItems().addAll(propertyService.getAvailableProperties());
        propertyBox.setPromptText("Select Property");

        // Date pickers
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Start Date");

        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setPromptText("End Date");

        Label resultLabel = new Label();

        // Create booking button
        Button submit = new Button("Book Property");

        submit.setOnAction(e -> {
            Property selectedProperty = propertyBox.getValue();
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();

            if (selectedProperty == null) {
                resultLabel.setText("Please select a property.");
                return;
            }

            if (startDate == null || endDate == null) {
                resultLabel.setText("Please select both start and end dates.");
                return;
            }

            if (endDate.isBefore(startDate)) {
                resultLabel.setText("End date must be after start date.");
                return;
            }

            int daysBooked = (int) ((endDate.toEpochDay() - startDate.toEpochDay()) + 1);

            // Disable button to prevent multiple submissions
            submit.setDisable(true);
            resultLabel.setText("Processing booking...");

            // Create booking task
            Task<Booking> bookingTask = new Task<Booking>() {
                @Override
                protected Booking call() throws Exception {
                    Thread.sleep(1000);
                    return bookingService.createBooking(selectedProperty, user, daysBooked);

                }
            };

            bookingTask.setOnSucceeded(event -> {
                Booking booking = bookingTask.getValue();
                resultLabel.setText("Booking successful \n" + booking.toString());
                submit.setDisable(false);
            });
            bookingTask.setOnFailed(event -> {
                resultLabel.setText("Booking failed: " + bookingTask.getException().getMessage());
                submit.setDisable(false);
            });

            Thread thread = new Thread(bookingTask);
            thread.setDaemon(true);
            thread.start();

        });

        // Layout setup
        VBox root = new VBox(12);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(
                new Label("Select Property:"), propertyBox,
                new Label("Start Date:"), startDatePicker,
                new Label("End Date:"), endDatePicker, submit, resultLabel, goBackBtn);

        bookingScene = new Scene(root, 350, 400);

        return bookingScene;

    }

}
