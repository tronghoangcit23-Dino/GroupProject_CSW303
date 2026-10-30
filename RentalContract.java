import java.math.BigDecimal;
import java.util.Date;

public class RentalContract {
    private int contractID;
    private Property property;
    private Tenant tenant;
    private Date startDate;
    private Date endDate;
    private BigDecimal rentAmount;
    private boolean isActive;

    public RentalContract(int contractID, Property property, Tenant tenant, Date startDate, Date endDate) {
        if (contractID <= 0) {
            throw new IllegalArgumentException("ContractID must be a positive integer.");
        }

        if (property == null || tenant == null) {
            throw new IllegalArgumentException("Property and tenant must not be null.");
        }

        if (!property.isAvailable()) {
            throw new IllegalArgumentException("Property is not available for rent.");
        }

        if (startDate == null || endDate == null || !endDate.after(startDate)) {
            throw new IllegalArgumentException("End date must be later than start date.");
        }

        this.contractID = contractID;
        this.property = property;
        this.tenant = tenant;
        this.startDate = new Date(startDate.getTime());
        this.endDate = new Date(endDate.getTime());
        this.rentAmount = property.getRentalRate();
        this.isActive = true;

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

    public BigDecimal getRentAmount() {
        return rentAmount;
    }

    public boolean isActive() {
        return isActive;
    }
}