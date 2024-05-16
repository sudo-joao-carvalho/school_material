package ui;

import model.Carga;
import model.Fleet;
import model.LigeiroPa;
import model.PesadosPa;
import util.PAInput;

import java.util.ArrayList;

public class FleetUI {

    //HashSet
    Fleet fleet;

    public FleetUI(Fleet fleet){this.fleet = fleet;}

    void addVehicle() {
        int type = PAInput.chooseOption("Vehicle type","Car (LigeiroPa)","Bus (PesadoPa)","Truck (Carga)");
        if (type > 3 || type < 1)
            return;

        String matricula;
        matricula = PAInput.readString("License Plate [\'exit\' to finish]: ",true);

        int ano;
        ano = PAInput.readInt("Year: ");

        /*do {
            author = PAInput.readString("Name of one author [\'exit\' to finish]: ",false);
            if (author!=null && !author.equalsIgnoreCase("exit"))
                authors.add(author);
        } while (!author.equalsIgnoreCase("exit"));

        if (authors.isEmpty())
            authors.add("Unknown author");*/

        String licensePl = switch (type) {
            case 1 -> {
                int nPassangers = PAInput.readInt("Number of passangers: ");
                yield fleet.addVehicle(new LigeiroPa(matricula, ano, nPassangers));
            }
            case 2 -> {
                int nPassangers = PAInput.readInt("Number of passangers: ");
                int nCargo = PAInput.readInt("Maximum Weight: ");
                yield fleet.addVehicle(new PesadosPa(matricula, ano, nPassangers, nCargo));
            }
            case 3 -> {
                int nCargo = PAInput.readInt("Maximum Weight: ");
                yield fleet.addVehicle(new Carga(matricula, ano, nCargo));
            }

            default -> "Invalid";
        };

        if (licensePl.equalsIgnoreCase("Invalid"))
            System.out.println("Error adding this new cars");
        else
            System.out.printf("The License Plate of the new car is: %s\n",licensePl);

    }

    /*void toStringPassangers() {
        int bookId = PAInput.readInt("ID of the book to search: ");
        Book book = lib.findBook(bookId);
        if (book == null)
            System.out.println("Book not found");
        else
            System.out.println("Book found: "+book);
    }

    void toStringWeight() {
        int bookId = PAInput.readInt("ID of the book to search: ");
        Book book = lib.findBook(bookId);
        if (book == null)
            System.out.println("Book not found");
        else
            System.out.println("Book found: "+book);
    }*/

    void removeCar() {
        String licensePl = PAInput.readString("License Plate of the car you qant to remove: ", true);
        boolean deleted = fleet.removeVehicle(licensePl);
        if (!deleted)
            System.out.println("Car not found");
        else
            System.out.println("Car deleted");
    }

    public void start() {
        while (true) {
            switch (PAInput.chooseOption("Fleet Manager - " + fleet.getNome(),
                    "Add new car", "Show passanger cars", "Show cargo cars", "Show cars", "Remove cars",
                    "Quit")) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    System.out.println(fleet.toStringSortByMaxPassengers());
                    break;
                case 3:
                    System.out.println(fleet.toStringByMaxLoad());
                    break;
                case 4:
                    System.out.println(fleet.toString());
                    break;
                case 5:
                    removeCar();
                case 6:
                    return;
            }
        }
    }
}
