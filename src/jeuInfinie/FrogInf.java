package jeuInfinie;

import frog.Frog;
import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;

public class FrogInf extends Frog implements IFrogInf {

    public static boolean lEnvDoitIlDescendre = false;

    public FrogInf(Game game) {
        super(game);
    }

    public void move(Direction key) {
        switch (key) {
            case up:
                if (pos.ord < game.height/2){
                    pos = new Case(pos.absc, pos.ord + 1);
                }else{
                    lEnvDoitIlDescendre = true;
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


