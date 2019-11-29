package jeuInfinie;

import environment.Environment;
import environment.Lane;
import gameCommons.Game;
import gameCommons.IFrog;
import gameCommons.Direction;

import java.util.ArrayList;

public class EnvInf extends Environment implements IEnvInf {

    protected Direction dir;

    public EnvInf(Game game, IFrog frog) {
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
        this.dir = Direction.up;
    }

    public void downEnvironment(){
        lanes.remove(0);
        System.out.print("!" + (game.height-1) + "\n");//---------------------------
        for (int i = 0; i < game.height-1; i++) {
            System.out.print("!");//------------------------------------
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

    public void update() {
        for (Lane lane : lanes) {
            if (frog.getPosition().ord == lane.ord && lane.isRiver) {
                lane.moveFrog(frog);
            }
            lane.update();
        }
        if(this.dir == Direction.up && this.frog.getPosition().ord == game.height/2)
            downEnvironment();
    }

}