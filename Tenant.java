import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tenant extends User {
    private List<RentalContract> rentalContracts;

    public Tenant(int userID, String firstName, String lastName, String email) {
        super(userID, firstName, lastName, email);
        this.rentalContracts = new ArrayList<>();
    }

    public RentalContract rentProperty(int contractID, Property property, Date startDate, Date endDate) {
        try {
            RentalContract contract = new RentalContract(contractID, property, this, startDate, endDate);
            this.rentalContracts.add(contract);
            return contract;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public List<RentalContract> getRentalContracts() {
        return this.rentalContracts;
    }
}