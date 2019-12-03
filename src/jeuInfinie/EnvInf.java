package jeuInfinie;

import gameCommons.IFrog;
import environment.Environment;
import environment.Lane;
import gameCommons.Game;
import gameCommons.Direction;

import java.util.ArrayList;

public class EnvInf extends Environment implements IEnvInf {

    protected Direction dir;

    public EnvInf(Game game, IFrog frog) {
        super(game, frog);
        lanes = new ArrayList<Lane>();
        for (int i = 0; i < game.height; i++) {
            boolean isEmptyLane;
            isEmptyLane = !(game.randomGen.nextDouble() < 0.2) && i >= 2;
            lanes.add(new Lane(this.game, i, isEmptyLane));
        }
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

    public void update() {
        for (Lane lane : lanes) {
            for (IFrog frog : frogs) {
                if (frog.getPosition().ord == lane.ord && lane.isRiver) {
                    lane.moveFrog(frog);
                }
            }
            lane.update();
        }
        if(FrogInf.needToMoveDown){
            FrogInf.needToMoveDown = false;
            downEnvironment();
        }
    }

}