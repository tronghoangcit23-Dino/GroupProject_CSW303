import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PropertyOwner extends User {
    private List<Property> ownedProperties;

    // Constructor: initialize ownedProperties as an empty list
    public PropertyOwner(int userID, String firstName, String lastName, String email) {
        super(userID, firstName, lastName, email);
        this.ownedProperties = new ArrayList<>();
    }

    // Create property: add to list if valid, catch exception if rate is invalid
    public Property createProperty(int propertyID, String description, BigDecimal rentalRate) {
        try {
            Property property = new Property(propertyID, description, rentalRate);
            this.ownedProperties.add(property);
            return property;
        } catch (IllegalArgumentException e) {
            // If there's an error (e.g., rentalRate <= 0), do not add to the list
            return null;
        }
    }

    // Return the list of properties owned by the owner
    public List<Property> getProperties() {
        return this.ownedProperties;
    }
}