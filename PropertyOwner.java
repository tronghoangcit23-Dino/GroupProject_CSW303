import java.util.ArrayList;
import java.util.List;

public class PropertyOwner extends User {
    private final List<Property> ownedProperties;

    public PropertyOwner(int userID, String firstName, String lastName, String email) {
        // Inherit User checks through the parent constructor
        super(userID, firstName, lastName, email);
        this.ownedProperties = new ArrayList<>();
    }

    //Delegates validation to the Property constructor. Appends the property to the list ONLY after successful construction.
    public Property createProperty(int propertyID, String description, double rentalRate) {
        // Validation delegated to Property constructor (throws IllegalArgumentException on failure)
        Property newProperty = new Property(propertyID, description, rentalRate);

        // Appended only after successful creation
        this.ownedProperties.add(newProperty);
        return newProperty;
    }

    // Return the list of properties owned by the owner
    public List<Property> getProperties() {
        return this.ownedProperties;
    }
}