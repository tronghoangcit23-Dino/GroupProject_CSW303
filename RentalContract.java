import java.util.Date;

public class RentalContract {
    private int contractID;
    private Property property;
    private Tenant tenant;
    private Date startDate;
    private Date endDate;
    private double rentAmount;   // rental rate locked in when the contract is signed
    private boolean isActive;

    // Constructor: validate everything first.
    // If any check fails, throw an exception and do NOT change the property's status.
    public RentalContract(int contractID, Property property, Tenant tenant, Date startDate, Date endDate) {
        // Validate ContractID: must be a positive integer
        if (contractID <= 0) {
            throw new IllegalArgumentException("Contract ID must be a positive integer.");
        }
        // Require a Property object
        if (property == null) {
            throw new IllegalArgumentException("Property must not be null.");
        }
        // Require a Tenant object
        if (tenant == null) {
            throw new IllegalArgumentException("Tenant must not be null.");
        }
        // Validate startDate and endDate: must be actual date values
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date must not be null.");
        }
        // Validate endDate is strictly greater than startDate
        if (!endDate.after(startDate)) {
            throw new IllegalArgumentException("End date must be later than start date.");
        }
        // Require the Property to be available
        if (!property.isAvailable()) {
            throw new IllegalArgumentException("Property is not available for rent.");
        }

        this.contractID = contractID;
        this.property = property;
        this.tenant = tenant;
        this.startDate = new Date(startDate.getTime());
        this.endDate = new Date(endDate.getTime());
        this.rentAmount = property.getRentalRate();
        this.isActive = true;

        // Change the property's status only at the very end, after everything is valid
        property.setAvailable(false);
    }

    public void terminate() {
        if (!this.isActive) {
            return;
        }
        this.isActive = false;
        this.property.setAvailable(true);
    }

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

    public double getRentAmount() {
        return rentAmount;
    }

    public boolean isActive() {
        return isActive;
    }
}