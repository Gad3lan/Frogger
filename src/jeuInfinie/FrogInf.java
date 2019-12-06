package jeuInfinie;

import frog.Frog;
import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;

public class FrogInf extends Frog implements IFrogInf {

    public static boolean needToMoveDown = false;

    public FrogInf(Game game) {
        super(game);
    }

    /**
     * Bouge la grenouille dans la direction donee
     * @param key la direction de la grenouille
     */
    public void move(Direction key) {
        if(key == Direction.up){
            if (pos.ord < game.height/2){
                pos = new Case(pos.absc, pos.ord + 1);
            }else{
                needToMoveDown = true;
            }
        }else
            super.move(key);
    }
}


