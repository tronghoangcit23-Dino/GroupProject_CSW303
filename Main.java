import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Calendar calStart = Calendar.getInstance();
        calStart.set(2026, Calendar.NOVEMBER, 1);
        Date startDate = calStart.getTime();
        Calendar calEnd = Calendar.getInstance();
        calEnd.set(2027, Calendar.OCTOBER, 31);
        Date endDate = calEnd.getTime();

        PropertyOwner owner = new PropertyOwner(1, "Anna", "Nguyen", "anna@example.com");
        Tenant tenant = new Tenant(2, "Minh", "Tran", "minh@example.com");
        Property property = owner.createProperty(101, "Apartment A101, two bedrooms", 5000);
        RentalContract contract = tenant.rentProperty(1001, property, startDate, endDate);

        System.out.println("--- SYSTEM TESTING RESULTS ---");
        System.out.println("Owner Full Name: " + owner.getFullName() + " (Expected: Anna Nguyen)");
        System.out.println("Number of owner's properties: " + owner.getProperties().size() + " (Expected: 1)");
        System.out.println("Number of tenant's contracts: " + tenant.getRentalContracts().size() + " (Expected: 1)");
        System.out.println("Property isAvailable: " + property.isAvailable() + " (Expected: false)");
        System.out.println("Contract isActive: " + contract.isActive() + " (Expected: true)");
        System.out.println("Contract RentAmount: " + contract.getRentAmount() + " (Expected: 5000)");

        property.updateRentalRate(5500);
        System.out.println("\n--- AFTER UPDATING PROPERTY RENTAL RATE TO 5500 ---");
        System.out.println("Property new RentalRate: " + property.getRentalRate() + " (Expected: 5500)");
        System.out.println("Contract RentAmount remains unchanged: " + contract.getRentAmount() + " (Expected: 5000)");

        contract.terminate();
        System.out.println("\n--- AFTER CALLING CONTRACT.TERMINATE() ---");
        System.out.println("Contract isActive: " + contract.isActive() + " (Expected: false)");
        System.out.println("Property isAvailable: " + property.isAvailable() + " (Expected: true)");
        System.out.println("Contract remains in tenant's list: "
                + (tenant.getRentalContracts().contains(contract) ? "True" : "False"));
    }
}