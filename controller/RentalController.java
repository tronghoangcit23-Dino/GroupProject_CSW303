package controller;

import java.util.Date;

import model.Property;
import model.PropertyOwner;
import model.RentalContract;
import model.Tenant;

public class RentalController {

    // Create a new property by delegating to PropertyOwner.createProperty().
    // The model validates the data and throws IllegalArgumentException if invalid.
    public Property createProperty(PropertyOwner owner, int id,
                                   String description, double rate) {
        requireNotNull(owner, "Owner");
        return owner.createProperty(id, description, rate);
    }

    // Create a rental contract by delegating to Tenant.rentProperty().
    // The model validates the data and throws IllegalArgumentException if invalid.
    public RentalContract rentProperty(Tenant tenant, int contractID, Property property,
                                       Date startDate, Date endDate) {
        requireNotNull(tenant, "Tenant");
        return tenant.rentProperty(contractID, property, startDate, endDate);
    }

    // Terminate a contract by delegating to RentalContract.terminate().
    // The model makes the property available again.
    public void terminateContract(RentalContract contract) {
        requireNotNull(contract, "Contract");
        contract.terminate();
    }

    // Only guards against missing arguments (not a business rule),
    // so the View can catch a single exception type for every error.
    private void requireNotNull(Object value, String name) {
        if (value == null) {
            throw new IllegalArgumentException(name + " must not be null.");
        }
    }
}