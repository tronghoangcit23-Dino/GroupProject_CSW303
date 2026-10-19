import java.math.BigDecimal;

public class Property {
    private int propertyID;
    private String description;
    private BigDecimal rentalRate;
    private boolean isAvailable;

    // Constructor: check (rentalRate > 0), assign values and set (isAvailable = true)
    public Property(int propertyID, String description, BigDecimal rentalRate) {
        if (rentalRate == null || rentalRate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Rental rate must be greater than zero.");
        }
        this.propertyID = propertyID;
        this.description = description;
        this.rentalRate = rentalRate;
        this.isAvailable = true;
    }

    // Update description: assign new value to description
    public void updateDescription(String description) {
        this.description = description;
    }

    // Update rental rate: check (rentalRate > 0), if false throw exception
    public void updateRentalRate(BigDecimal rentalRate) {
        if (rentalRate == null || rentalRate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Rental rate must be greater than zero.");
        }
        this.rentalRate = rentalRate;
    }

    // getters and setters for all attributes
    public int getPropertyID() {
        return propertyID;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}