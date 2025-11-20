package GUI;

import Service.BookingService;
import Service.PropertyService;
import Model.Property;
import Model.User;
import Model.Booking;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;

public class BookingGUI {

    public void show(User user, PropertyService propertyService, BookingService bookingService) throws Exception {

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
            int daysBooked = (int) (endDate.toEpochDay() - startDate.toEpochDay());
            try {
                Booking booking = bookingService.createBooking(selectedProperty, user, daysBooked);
                resultLabel.setText("Booking created\n" + booking.toString());
            } catch (Exception ex) {
                resultLabel.setText("Booking failed: " + ex.getMessage());
            }

        });

        // Layout setup
        VBox root = new VBox(12);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(
                new Label("Select Property:"), propertyBox,
                new Label("Start Date:"), startDatePicker,
                new Label("End Date:"), endDatePicker, submit, resultLabel);

        Scene scene = new Scene(root, 350, 400);
        stage.setScene(scene);
        stage.show();

    }

}
