package model;

import java.util.*;

public class Fleet {

    private final String nome;
    private Set<Vehicle> frotaSet;

    public Fleet(String nome){
        this.nome       = nome;
        this.frotaSet   = new HashSet<>();
    }

    public String getNome(){return this.nome;}

    public String addVehicle(Vehicle vehicle){
        frotaSet.add(vehicle);
        return vehicle.matricula;
    }

    public String addVehicle(String matricula, int ano){return addVehicle(new Vehicle(matricula, ano));}

    public boolean removeVehicle(String matricula){

        /*Iterator<Vehicle> it = frotaSet.iterator();

        if(it.hasNext()){
            Vehicle vehicle = it.next();

            if(vehicle.getMatricula() == matricula){
                it.remove();
                return true;
            }
        }*/

        //frotaSet.removeIf(o -> o.matricula.equals(matricula))

        if(frotaSet == null || frotaSet.isEmpty())
            return false;

        return frotaSet.remove(Vehicle.getDummyVehicle(matricula));
    }

    /*public String listVehicles(){
        return frotaSet.toString();
    }*/

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("Fleet: %s\n", nome));
        sb.append("Vehicles:\n");

        if(frotaSet == null || frotaSet.isEmpty()){
            sb.append("No Vehicles");
        }else{
            for(Vehicle v: frotaSet){
                sb.append(String.format(String.format("\t- %s\n", v.toString())));
            }
        }

        return sb.toString(); //alternativa sb+""
    }

    public String toStringSortByMaxPassengers(){

        StringBuilder sb = new StringBuilder(String.format("Fleet: %s\n", nome));
        sb.append("Vehicles:\n");

        if(frotaSet == null || frotaSet.isEmpty()){
            sb.append("No Vehicles");
        }

        ArrayList<IPassengers> temp = new ArrayList<IPassengers>();

        for(Vehicle v: frotaSet){
            //if(v instanceof LigeiroPa || v instanceof PesadosPa) //existe uma maneira mais facil que e ver se é instanceof da interface
            if(v instanceof IPassengers vp){
                temp.add(vp);
            }
        }

        if(temp.size() == 0)
            sb.append("No passanger vehicles");
        else{
            Collections.sort(temp, new NumberOfPassengersComparator());
            for(var v: temp){
                sb.append(String.format("\t- %s\n", v));
            }
        }

        return sb.toString();
    }

    public String toStringByMaxLoad(){
        StringBuilder sb = new StringBuilder(String.format("Fleet: %s\n", nome));
        sb.append("Vehicles:\n");

        if(frotaSet == null || frotaSet.isEmpty()){
            sb.append("No Vehicles");
        }

        ArrayList<IMaxLoad> temp = new ArrayList<IMaxLoad>();

        for(Vehicle v: frotaSet){
            if(v instanceof IMaxLoad vl){
                temp.add(vl);
            }
        }

        if(temp.size() == 0)
            sb.append("No load vehicles");
        else{
            Collections.sort(temp, new MaxLoadComparator());
            for(var v: temp){
                sb.append(String.format("\t- %s\n", v));
            }
        }

        return sb.toString();

    }
}
