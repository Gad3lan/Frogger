package jeuInfinie;

import frog.Frog;
import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;

public class FrogInf extends Frog implements IFrogInf {

    public FrogInf(Game game) {
        super(game);
    }

    public void move(Direction key) {
        switch (key) {
            case up:
                if (pos.ord < game.height/2){
                    pos = new Case(pos.absc, pos.ord + 1);
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


