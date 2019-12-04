package jeuInfinie;

import gameCommons.IFrog;
import environment.Environment;
import environment.Lane;
import gameCommons.Game;
import gameCommons.Direction;

import java.util.ArrayList;

public class EnvInf extends Environment implements IEnvInf {

    protected Direction dir;

    public EnvInf(Game game) {
        super(game);
        boolean isEmptyLane = !(game.randomGen.nextDouble() < 0.2);
        lanes.add(new Lane(this.game, game.height - 1, isEmptyLane));
        this.dir = Direction.up;
    }

    public void downEnvironment(){
        boolean isEmptyLane;
        isEmptyLane = !(game.randomGen.nextDouble() < 0.2);
        lanes.add(new Lane(this.game, game.height, isEmptyLane));
        lanes.remove(0);
        for (Lane lane : lanes) {
            lane.downLanes();
        }
    }

    public void update(ArrayList<IFrog> frogs) {
        super.update(frogs);
        if(FrogInf.needToMoveDown){
            FrogInf.needToMoveDown = false;
            downEnvironment();
        }
    }
}