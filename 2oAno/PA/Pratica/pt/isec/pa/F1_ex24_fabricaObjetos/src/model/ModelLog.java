package model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

//PADRAO SINGLETON

public class ModelLog {

    private static ModelLog _instance = null;

    public static ModelLog getInstance(){ //esta funcao garante que so haja uma instancia do log
        if(_instance == null){
            _instance = new ModelLog();
        }

        return _instance;
    }

    private ArrayList<String> log;

    protected ModelLog(){
        log = new ArrayList<>();
    }

    public void reset(){
        log.clear();
    }

    public void add(String msg){ log.add(msg); }

    public List<String> getLog(){ return new ArrayList<>(log); }
}
