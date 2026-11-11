import controller.RentalController;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        // Initialize Controller and View layers
        RentalController controller = new RentalController();
        ConsoleView view = new ConsoleView(controller);

        // Launch the interactive console menu
        view.start();
    }
}