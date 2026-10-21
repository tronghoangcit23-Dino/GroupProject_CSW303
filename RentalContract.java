import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalContract {
    private int contractID;
    private Tenant tenant;
    private Property property;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal rentalRate;   // rental rate locked in at the time the contract is signed
    private boolean isActive;

    // Constructor: check that the property is available and endDate > startDate.
    // If any check fails, throw an exception and do NOT change the property's status.
    public RentalContract(int contractID, Tenant tenant, Property property,
                          LocalDate startDate, LocalDate endDate) {
        if (tenant == null || property == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("Tenant, property and dates must not be null.");
        }
        if (!property.isAvailable()) {
            throw new IllegalStateException("Property is not available for rent.");
        }
        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("End date must be after start date.");
        }

        // Only assign values and change the property's status after all checks pass
        this.contractID = contractID;
        this.tenant = tenant;
        this.property = property;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentalRate = property.getRentalRate();
        this.isActive = true;

        property.setAvailable(false);
    }

    // Terminate: deactivate the contract and make the property available again
    public void terminate() {
        // If the contract is already terminated, do nothing,
        // so we never re-open a property that has since been rented to someone else
        if (!this.isActive) {
            return;
        }
        this.isActive = false;
        this.property.setAvailable(true);
    }

    // Getters
    public int getContractID() { return contractID; }
    public Tenant getTenant() { return tenant; }
    public Property getProperty() { return property; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public BigDecimal getRentalRate() { return rentalRate; }
    public boolean isActive() { return isActive; }
}