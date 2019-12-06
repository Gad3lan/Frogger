package frog;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;

public class Frog implements IFrog {

    protected Game game;
    protected Case pos;
    protected Direction dir;

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
                this.dir = Direction.up;
                if (pos.ord < game.height - 1)
                    pos = new Case(pos.absc, pos.ord + 1);
                break;
            case down:
                this.dir = Direction.down;
                if (pos.ord > 0)
                    pos = new Case(pos.absc, pos.ord - 1);
                break;
            case left:
                this.dir = Direction.left;
                if (pos.absc > 0)
                    pos = new Case(pos.absc - 1, pos.ord);
                break;
            case right:
                this.dir = Direction.right;
                if (pos.absc < game.width - 1)
                    pos = new Case(pos.absc + 1, pos.ord);
                break;
            default:
                break;
        }
        this.dir = key;
    }

    public void riverMove(boolean leftToRight) {
        if (leftToRight && pos.absc < game.width - 1) {
            this.pos = new Case(this.pos.absc + 1, this.pos.ord);
        } else if (pos.absc > 0) {
            this.pos = new Case(this.pos.absc - 1, this.pos.ord);
        }
    }
}
