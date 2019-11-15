package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import gameCommons.Case;
import gameCommons.Direction;

import static gameCommons.Direction.up;

public class Frog implements IFrog {
	
	private Game game;
	private Case pos;
	private Direction dir;

	public Frog(Game game) {
		this.game = game;
		this.dir = up;
		this.pos = new Case(game.width/2, 0);
	}

	public Case getPosition() {
		return this.pos;
	}

	public Direction getDirection() {
		return this.dir;
	}

	public void move(Direction key) {
		switch (key) {
			case up:    pos = new Case(pos.absc, pos.ord+1); //.ord++:
						break;
			case down:  pos = new Case(pos.absc, pos.ord-1); //.ord--;
						break;
			case left:  pos = new Case(pos.absc-1, pos.ord); //.absc--;
						break;
			case right: pos = new Case(pos.absc+1, pos.ord); //.absc++;
						break;
			default:    break;
		}
		this.dir = key;
	}
}
