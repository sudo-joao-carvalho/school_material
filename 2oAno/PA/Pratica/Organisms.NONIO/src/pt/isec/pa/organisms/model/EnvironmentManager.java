package pt.isec.pa.organisms.model;

import pt.isec.pa.organisms.gameengine.IGameEngine;
import pt.isec.pa.organisms.gameengine.IGameEngineEvolve;
import pt.isec.pa.organisms.model.data.Environment;
import pt.isec.pa.organisms.model.data.Evolver;
import pt.isec.pa.organisms.model.data.Organism;
import pt.isec.pa.organisms.model.data.Virus;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class EnvironmentManager implements IGameEngineEvolve {

    private static final String FILE = "files/environment.txt";

    private static final String LOGFILE = "files/logs.txt";

    Environment environment;

    public EnvironmentManager(){
        environment = getEnvFromFile(FILE);
        if(environment == null){
            environment = new Environment(10,10);
            for(int i = 0;i<10;i++) {
                int y = (int)(Math.random()*10);
                int x = (int)(Math.random()*10);
                environment.addOrganism(new Virus(environment),y,x);
            }
            for(int i = 0;i<20;i++) {
                int y = (int)(Math.random()*10);
                int x = (int)(Math.random()*10);
                environment.addOrganism(new Evolver(environment),y,x);
            }
        }
        try {new FileWriter(LOGFILE).close();}catch(IOException e){}
    }


    private static Environment getEnvFromFile(String filepath){
        Environment environment = null;
        try{
            if(!new File(filepath).exists())
                return null;

            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            Scanner sc = new Scanner(br);

            sc.useDelimiter(",|\r?\n");

            int h = sc.nextInt();
            int w = sc.nextInt();

            environment = new Environment(h,w);

            while(sc.hasNext()){
                char c = sc.next().charAt(0);
                int y = sc.nextInt();
                int x = sc.nextInt();

                Organism o = switch(c){
                    case Evolver.SYMBOL -> new Evolver(environment);
                    case Virus.SYMBOL -> new Virus(environment);
                    default -> null;
                };
                environment.addOrganism(o, y, x);

                sc.close();
                fr.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO");
            return null;
        }

        return environment;
    }

    private void saveLogs(){
        try(    FileWriter fw = new FileWriter(LOGFILE, true); // ao ter estas coisas dentro dos parentesis faz com que faça o close automatico --> limitaçao: scope, vars nao sao vistas fora da funcao
                PrintWriter pw = new PrintWriter(fw);
                ){
            List<String> lstLog = ModelLog.getInstance().getLog();
            for(String msg : lstLog)
                pw.println(msg);

            ModelLog.getInstance().reset();
        }catch(IOException e){

        }
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime){
        if(environment == null)
            return ;
        environment.evolve();
        saveLogs();
    }
    public char[][] getEnvironment(){return environment.getEnvironment();}

    public boolean onlyOneSpecies(){
        return environment.onlyOneSpecies();
    }
}
