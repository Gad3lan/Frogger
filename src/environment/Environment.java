package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
	private Game game;
	private ArrayList<Lane> lanes;

	public Environment(Game game) {
		this.game = game;
		lanes = new ArrayList<Lane>();
		for (int i = 0; i < game.height; i++) {
			boolean isEmptyLane;
			if (game.randomGen.nextDouble() < 0.2 || i < 2 || i == game.height-1) {
				isEmptyLane = false;
			} else {
				isEmptyLane = true;
			}
			lanes.add(new Lane(this.game, i, isEmptyLane));
		}
	}

	public boolean isSafe(Case c) {
		return lanes.get(c.ord).isSafe(c);
	}

	public boolean isWinningPosition(Case c) {
		return(c.ord == game.height - 1);
	}

	public void update() {
		for (Lane lane : lanes) {
			lane.update();
		}
	}

}
