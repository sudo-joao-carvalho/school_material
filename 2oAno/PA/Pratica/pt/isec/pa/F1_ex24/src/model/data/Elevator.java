package model.data;

//os dados n tem qualquer nocao dos estados
public class Elevator {

    private int currentFloor;

    private String securityKey = null;

    private boolean underMaintenance = false;

    public Elevator(int currentFloor, String securityKey){
        this.currentFloor   = currentFloor;
        this.securityKey    = securityKey;
    }

    public int getCurrentFloor(){return this.currentFloor;}

    public boolean setCurrentFloor(int currentFloor){
        if(underMaintenance) //quando o elevador esta em manutencao nao muda de estado
            return false;

        this.currentFloor = currentFloor;
        return true;
    }

    //b)
    public boolean setSecurityKey(String oldSecurityKey, String newSecurityKey){
        if(securityKey != null && !securityKey.equals(oldSecurityKey))
            return false;

        this.securityKey = newSecurityKey;
        return true;
    }

    public boolean isUnderMaintenance(){return underMaintenance;}

    public boolean enterMaintenance(){
        this.underMaintenance = true;
        return true;
    }

    public boolean leaveMaintenance(String securityKey){
        if(this.securityKey != null && !this.securityKey.equals(securityKey))
            return false;

        this.underMaintenance = false;
        return true;
    }

    @Override
    public String toString(){
        return "Elevator: currentFloor = " + currentFloor + (underMaintenance ? " (under maintenance)":"");
    }
}
