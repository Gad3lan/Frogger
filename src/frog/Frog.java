package frog;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;

public class Frog implements IFrog {

    private Game game;
    private Case pos;
    private Direction dir;

    public Frog(Game game) {
        this.game = game;
        this.dir = Direction.up;
        this.pos = new Case(game.width / 2, 0);
    }

    public Case getPosition() {
        return this.pos;
    }

    public Direction getDirection() {
        return this.dir;
    }

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
}
