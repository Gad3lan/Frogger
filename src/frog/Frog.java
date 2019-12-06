package frog;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;

public class Frog implements IFrog {

    protected Game game;
    public Case pos;
    protected Direction dir;

    public Frog(Game game) {
        this.game = game;
        this.dir = Direction.up;
        this.pos = new Case(game.width / 2, 0);
    }

    /**
     *
     * @return la position de la grenouille
     */
    public Case getPosition() {
        return this.pos;
    }

    /**
     *
     * @return la direction de la grenouille
     */
    public Direction getDirection() {
        return this.dir;
    }

    /**
     * Deplace la grenouille dans la direction voulue
     * @param key la direction de la grenouille
     */
    public void move(Direction key) {
        switch (key) {
            case up:
                if (pos.ord < game.height - 1)
                    pos = new Case(pos.absc, pos.ord + 1);
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

    /**
     * Bouge la grenouille si elle est sur une buche
     * @param leftToRight la direction des buches de la rivière
     */
    public void riverMove(boolean leftToRight) {
        if (leftToRight && pos.absc < game.width - 1) {
            this.pos = new Case(this.pos.absc + 1, this.pos.ord);
        } else if (pos.absc > 0) {
            this.pos = new Case(this.pos.absc - 1, this.pos.ord);
        }
    }
}
