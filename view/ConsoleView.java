package view;

import controller.RentalController;
import model.Property;
import model.PropertyOwner;
import model.RentalContract;
import model.Tenant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final RentalController controller;
    private final Scanner scanner;
    private final SimpleDateFormat dateFormat;

    // In-memory collections to track created entities during runtime
    private final List<Property> properties = new ArrayList<>();
    private final List<RentalContract> contracts = new ArrayList<>();

    // Mock users for demonstration
    private final PropertyOwner defaultOwner;
    private final Tenant defaultTenant;

    public ConsoleView(RentalController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.dateFormat.setLenient(false);

        this.defaultOwner = new PropertyOwner(1, "Anna", "Nguyen", "anna@example.com");
        this.defaultTenant = new Tenant(2, "Minh", "Tran", "minh@example.com");
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- PROPERTY RENTAL MANAGEMENT ---");
            System.out.println("1. Create property");
            System.out.println("2. Rent property");
            System.out.println("3. Terminate contract");
            System.out.println("4. Exit");
            System.out.print("Enter choice (1-4): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    handleCreateProperty();
                    break;
                case "2":
                    handleRentProperty();
                    break;
                case "3":
                    handleTerminateContract();
                    break;
                case "4":
                    System.out.println("Exiting application.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }

    private void handleCreateProperty() {
        try {
            System.out.print("Enter Property ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Description: ");
            String description = scanner.nextLine().trim();

            System.out.print("Enter Rental Rate: ");
            double rate = Double.parseDouble(scanner.nextLine().trim());

            Property property = controller.createProperty(defaultOwner, id, description, rate);
            properties.add(property);

            System.out.println("SUCCESS: Property created. ID: " + property.getPropertyID() + 
                               ", Available: " + property.isAvailable());
        } catch (NumberFormatException e) {
            System.out.println("INPUT ERROR: ID and Rate must be valid numbers.");
        } catch (IllegalArgumentException e) {
            // Catch domain validation rules from Model (e.g., rate <= 0)
            System.out.println("VALIDATION ERROR: " + e.getMessage());
        }
    }

    private void handleRentProperty() {
        try {
            if (properties.isEmpty()) {
                System.out.println("INFO: No properties available. Create one first.");
                return;
            }

            System.out.print("Enter Property ID to rent: ");
            int propId = Integer.parseInt(scanner.nextLine().trim());

            Property targetProperty = null;
            for (Property p : properties) {
                if (p.getPropertyID() == propId) {
                    targetProperty = p;
                    break;
                }
            }

            if (targetProperty == null) {
                System.out.println("ERROR: Property ID not found.");
                return;
            }

            System.out.print("Enter Contract ID: ");
            int contractId = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Start Date (yyyy-MM-dd): ");
            Date startDate = dateFormat.parse(scanner.nextLine().trim());

            System.out.print("Enter End Date (yyyy-MM-dd): ");
            Date endDate = dateFormat.parse(scanner.nextLine().trim());

            RentalContract contract = controller.rentProperty(defaultTenant, contractId, targetProperty, startDate, endDate);
            if (contract != null) {
                contracts.add(contract);
                System.out.println("SUCCESS: Property rented. Available: " + targetProperty.isAvailable());
            }
        } catch (NumberFormatException e) {
            System.out.println("INPUT ERROR: IDs must be integers.");
        } catch (ParseException e) {
            System.out.println("INPUT ERROR: Date must follow format yyyy-MM-dd.");
        } catch (IllegalArgumentException e) {
            // Catch domain validation rules (e.g., endDate <= startDate, property not available)
            System.out.println("VALIDATION ERROR: " + e.getMessage());
        }
    }

    private void handleTerminateContract() {
        try {
            if (contracts.isEmpty()) {
                System.out.println("INFO: No active contracts found.");
                return;
            }

            System.out.print("Enter Contract ID to terminate: ");
            int contractId = Integer.parseInt(scanner.nextLine().trim());

            RentalContract targetContract = null;
            for (RentalContract c : contracts) {
                if (c.getContractID() == contractId) {
                    targetContract = c;
                    break;
                }
            }

            if (targetContract == null) {
                System.out.println("ERROR: Contract ID not found.");
                return;
            }

            controller.terminateContract(targetContract);
            System.out.println("SUCCESS: Contract terminated. Available: " + targetContract.getProperty().isAvailable());
        } catch (NumberFormatException e) {
            System.out.println("INPUT ERROR: Contract ID must be an integer.");
        } catch (IllegalArgumentException e) {
            System.out.println("VALIDATION ERROR: " + e.getMessage());
        }
    }
}