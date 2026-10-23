import java.math.BigDecimal;
import java.util.Date;

public class RentalContract {
    private int contractID;
    private Property property;
    private Tenant tenant;
    private Date startDate;
    private Date endDate;
    private BigDecimal rentAmount;   // rental rate locked in when the contract is signed
    private boolean isActive;

    // Constructor: check that the property is available and endDate > startDate.
    // If any check fails, throw an exception and do NOT change the property's status.
    public RentalContract(int contractID, Property property, Tenant tenant, Date startDate, Date endDate) {
        if (property == null || tenant == null) {
            throw new IllegalArgumentException("Property and tenant must not be null.");
        }
        if (!property.isAvailable()) {
            throw new IllegalArgumentException("Property is not available for rent.");
        }
        if (startDate == null || endDate == null || !endDate.after(startDate)) {
            throw new IllegalArgumentException("End date must be later than start date.");
        }

        // All checks passed: assign values (copy the dates so outside code cannot modify them)
        this.contractID = contractID;
        this.property = property;
        this.tenant = tenant;
        this.startDate = new Date(startDate.getTime());
        this.endDate = new Date(endDate.getTime());
        this.rentAmount = property.getRentalRate();
        this.isActive = true;

        // Mark the property as rented only after everything is valid
        property.setAvailable(false);
    }

    // Terminate: deactivate the contract and make the property available again
    public void terminate() {
        // If already terminated, do nothing, so an old contract can never
        // re-open a property that has since been rented to someone else
        if (!this.isActive) {
            return;
        }
        this.isActive = false;
        this.property.setAvailable(true);
    }

    // Getters
    public int getContractID() {
        return contractID;
    }

    public Property getProperty() {
        return property;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public Date getStartDate() {
        return new Date(startDate.getTime());
    }

    public Date getEndDate() {
        return new Date(endDate.getTime());
    }

    public BigDecimal getRentAmount() {
        return rentAmount;
    }

    public boolean isActive() {
        return isActive;
    }
}