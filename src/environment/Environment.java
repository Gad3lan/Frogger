package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import gameCommons.IFrog;

public class Environment implements IEnvironment {
	protected Game game;
	protected ArrayList<Lane> lanes;

	public Environment(Game game) {
		this.game = game;
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

	public void update(ArrayList<IFrog> frogs) {
		for (Lane lane : lanes) {
			for (IFrog frog : frogs) {
				if (frog.getPosition().ord == lane.ord && lane.isRiver) {
					lane.moveFrog(frog);
				}
			}
			lane.update();
		}
	}

}
