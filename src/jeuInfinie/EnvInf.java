package jeuInfinie;

import environment.Environment;
import environment.Lane;
import gameCommons.Game;
import gameCommons.Direction;

import java.util.ArrayList;

public class EnvInf extends Environment implements IEnvInf {

    protected Direction dir;

    public EnvInf(Game game, IFrogInf frog) {
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
        System.out.print("!" + (game.height-1) + "\n");//---------------------------
        for (Lane lane : lanes) {
            System.out.print("!");//------------------------------------
            lane.downLanes();
        }
    }

    public void update() {
        for (Lane lane : lanes) {
            if (frog.getPosition().ord == lane.ord && lane.isRiver) {
                lane.moveFrog(frog);
            }
            lane.update();
        }
        if(this.dir == Direction.up && this.frog.getPosition().ord >= game.height/2) {
            downEnvironment();
        }
    }

}