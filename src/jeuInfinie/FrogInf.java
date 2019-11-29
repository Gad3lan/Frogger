package jeuInfinie;

import environment.Environment;
import environment.Lane;
import frog.Frog;
import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class FrogInf extends Frog {

    private EnvInf environment;

    public FrogInf(Game game, EnvInf environment) {
        super(game);
        this.environment = environment;
    }

    public void move(Direction key) {
        switch (key) {
            case up:
                if (pos.ord < game.height/2){
                    pos = new Case(pos.absc, pos.ord + 1);
                }else{
                    this.environment.downEnvironment();
                }
                break;
            case down:
                if (pos.ord > 0)
                    pos = new Case(pos.absc, pos.ord - 1);
                break;
            case left:
                if (pos.absc > 0)
                    pos = new Case(pos.absc - 1, pos.ord);
                break;
            case right:
                if (pos.absc < game.width - 1)
                    pos = new Case(pos.absc + 1, pos.ord);
                break;
            default:
                break;
        }
        this.dir = key;
    }
}


