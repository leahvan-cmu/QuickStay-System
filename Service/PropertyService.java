package Service;

import Model.Property;
import java.util.ArrayList;
import java.util.List;

public class PropertyService {

    private List<Property> properties = new ArrayList<>();

    /*
     * Adds a new property to the system.
     */
    public void addProperty(Property property) {
        properties.add(property);
    }

    /*
     * Returns a list of all properties.
     */
    public List<Property> getAllProperties() {
        return properties;
    }

    /*
     * Returns a list of available properties.
     */
    public List<Property> getAvailableProperties() {
        List<Property> availableProperties = new ArrayList<>();
        for (Property property : properties) {
            if (property.isAvailable()) {
                availableProperties.add(property);
            }
        }
        return availableProperties;
    }

}
