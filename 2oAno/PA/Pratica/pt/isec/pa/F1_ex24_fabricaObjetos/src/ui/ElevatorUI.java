package ui;

import model.fsm.ElevatorContext;
import utils.PAInput;

//Na interface do utilizador nao deve aparecer nenhum tipo de logica (fortemente penalizado)

//no trabalho pratico vamos precisar do menu contextualizado... menu diferente para cada estados (podemos usar o fsm.getState())
public class ElevatorUI {

    ElevatorContext fsm;

    boolean finish = false;

    public ElevatorUI(ElevatorContext fsm){
        this.fsm = fsm;
    }
    /*public void start(){

        int op;
        do{
            System.out.println("Elevator current floor: " + fsm.getCurrentFloor() +
                    " (" + fsm.getState() + ") ");

            op = PAInput.chooseOption("Elevator", "Up", "Down", "Quit");
            switch (op){
                case 1 -> fsm.up();
                case 2 -> fsm.down();
            }
        }while(op != 3);
    }*/


    //boolean finish = false;

    public void start(){
        while(!finish){
            switch(fsm.getState()){
                case GROUND_FLOOR -> groundFloorUI();
                case FIRST_FLOOR -> firstFloorUI();
                case SECOND_FLOOR -> secondFloorIU();
                case UNDER_MAINTENANCE -> underMaintenanceUI();
            }
        }
    }

    public void groundFloorUI(){
        System.out.println("Ground Floor");

        if(PAInput.chooseOption("Elevator", "Up", "Quit") == 1)
            fsm.up();
        else finish = true;
    }

    public void firstFloorUI(){
        System.out.println("First Floor");

        if(PAInput.chooseOption("Elevator", "Up", "Down", "Quit") == 1)
            fsm.up();
        if(PAInput.chooseOption("Elevator", "Up", "Down", "Quit") == 2)
            fsm.down();
        else finish = true;
    }

    public void secondFloorIU(){
        System.out.println("Second Floor");

        if(PAInput.chooseOption("Elevator", "Down", "Quit") == 1)
            fsm.down();
        else finish = true;
    }

    public void underMaintenanceUI(){
        System.out.println("Elevator current floor: " + fsm.getCurrentFloor());
        System.out.println("*** Elevator under maintenance *** ");
        if(PAInput.chooseOption("Elevator", "Use Security Key", "Quit") == 1){
            String key = PAInput.readString("Security key: ", false);
            if(!fsm.useSecurityKey(key))
                System.out.println("The key was not accepted.");

        }else finish = true;
    }
}
