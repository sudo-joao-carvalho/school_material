package model;

import java.util.Objects;

public class Vehicle implements Comparable<Vehicle>{

    protected String matricula;
    protected int ano;

    protected Vehicle(String matricula, int ano){
        this.matricula  = matricula;
        this.ano        = ano;
    }

    //GET & SET

    public String getMatricula() {return matricula;}

    public void setMatricula(String matricula) {this.matricula = matricula;}

    public int getAno() {return ano;}

    public void setAno(int ano) {this.ano = ano;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        if(matricula == null || vehicle.matricula == null) return false;
        return matricula.equals(vehicle.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "matricula='" + matricula + '\'' +
                ", ano=" + ano +
                '}';
    }

    @Override
    public int compareTo(Vehicle o) { //usados para ordenar por ano
        return this.ano - o.ano;
    }

    public static Vehicle getDummyVehicle(String matricula){return new Vehicle(matricula);}
    private Vehicle(String matricula){
        this.matricula = matricula;
        this.ano = Integer.parseInt(null);
    }
}
