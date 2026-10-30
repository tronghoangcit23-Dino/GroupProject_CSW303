public class Property {
    private int propertyID;
    private String description;
    private double rentalRate;
    private boolean isAvailable;

    public Property(int propertyID, String description, double rentalRate) {
        // Validate PropertyID: positive integer
        if (propertyID <= 0) {
            throw new IllegalArgumentException("PropertyID must be a positive integer.");
        }

        // Validate Description: nonempty after trimming
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description must be a nonempty string.");
        }

        // Validate RentalRate: finite, positive decimal number
        if (!Double.isFinite(rentalRate) || rentalRate <= 0.0) {
            throw new IllegalArgumentException("RentalRate must be a finite, positive decimal number.");
        }

        this.propertyID = propertyID;
        this.description = description.trim();
        this.rentalRate = rentalRate;
        this.isAvailable = true;
    }

    // Updates the rental rate. Preserves the existing rental rate if the new rate
    // is invalid.
    public boolean updateRentalRate(double newRate) {
        if (!Double.isFinite(newRate) || newRate <= 0.0) {
            // Failed update: keep prior rate unchanged and return false
            return false;
        }
        this.rentalRate = newRate;
        return true;
    }

    // Update description: assign new value to description
    public void updateDescription(String description) {
        this.description = description;
    }

    // getters and setters for all attributes
    public int getPropertyID() {
        return propertyID;
    }

    public String getDescription() {
        return description;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}