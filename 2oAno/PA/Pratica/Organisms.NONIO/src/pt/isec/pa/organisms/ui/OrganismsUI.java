package pt.isec.pa.organisms.ui;

import pt.isec.pa.organisms.gameengine.IGameEngine;
import pt.isec.pa.organisms.gameengine.IGameEngineEvolve;
import pt.isec.pa.organisms.model.EnvironmentManager;
import pt.isec.pa.organisms.model.ModelLog;
import pt.isec.pa.organisms.model.data.Environment;

public class OrganismsUI implements IGameEngineEvolve {
    EnvironmentManager environment;

    public OrganismsUI(EnvironmentManager environment) { this.environment = environment; }

    public void show() {
        ModelLog.getInstance().getLog().forEach(System.out::println);
        ModelLog.getInstance().reset();

        char [][] env = environment.getEnvironment();
        System.out.println();
        for(int y=0;y<env.length;y++) {
            for(int x = 0; x< env[0].length;x++)
                System.out.print(env[y][x]);
            System.out.println();
        }
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime){
        show();

        if(environment.onlyOneSpecies())
            gameEngine.stop();
    }
}
