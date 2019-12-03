package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import gameCommons.IFrog;

public class Environment implements IEnvironment {
	protected Game game;
	protected ArrayList<Lane> lanes;
	protected IFrog frog;

	public Environment(Game game, IFrog frog) {
		this.game = game;
		this.frog = frog;
		lanes = new ArrayList<Lane>();
		for (int i = 0; i < game.height; i++) {
			boolean isEmptyLane;
			isEmptyLane = !(game.randomGen.nextDouble() < 0.2) && i >= 2 && i != game.height - 1;
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
			if (frog.getPosition().ord == lane.ord && lane.isRiver) {
				lane.moveFrog(frog);
			}
			lane.update();
		}
	}

}
