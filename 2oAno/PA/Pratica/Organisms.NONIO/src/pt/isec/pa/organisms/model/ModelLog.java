package pt.isec.pa.organisms.model;

import java.util.ArrayList;
import java.util.List;

// singleton
public class ModelLog {
    private static ModelLog _instance=null;

    public static ModelLog getInstance() {
        if (_instance == null)
            _instance = new ModelLog();
        return _instance;
    }

    protected ArrayList<String> log;

    private ModelLog() {
        log = new ArrayList<>();
    }

    public void reset() {
        log.clear();
    }

    public void add(String msg) {
        log.add(msg);
    }

    public List<String> getLog() {
        return new ArrayList<>(log);
    }
}
