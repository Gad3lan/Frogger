package jeuInfinie;

import environment.Environment;
import environment.Lane;
import gameCommons.Game;
import gameCommons.IFrog;

import java.util.ArrayList;

public class EnvInf extends Environment {
    EnvInf(Game game, IFrog frog) {
        super(game, frog);
        lanes = new ArrayList<Lane>();
        for (int i = 0; i < game.height; i++) {
            boolean isEmptyLane;
            if (game.randomGen.nextDouble() < 0.2 || i < 2) {
                isEmptyLane = false;
            } else {
                isEmptyLane = true;
            }
            lanes.add(new Lane(this.game, i, isEmptyLane));
        }
    }

    public void downEnvironment(){
        lanes.remove(0);
        for (int i = 0; i < game.height-1; i++) {
            lanes.get(i).downLanes();
        }
        boolean isEmptyLane;
        if (game.randomGen.nextDouble() < 0.2) {
            isEmptyLane = false;
        } else {
            isEmptyLane = true;
        }
        new Lane(this.game, game.height - 1, isEmptyLane);
    }
}