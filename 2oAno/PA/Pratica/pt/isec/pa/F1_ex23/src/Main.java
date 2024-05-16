import model.Fleet;
import ui.FleetUI;

public class Main {
    public static void main(String[] args) {
        FleetUI fleetUI = new FleetUI(new Fleet("Ronald's cars"));
        fleetUI.start();
    }
}