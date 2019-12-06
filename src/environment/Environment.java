package environment;


import java.util.ArrayList;

import birde.Birde;
import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import gameCommons.IFrog;

public class Environment implements IEnvironment {
	protected Game game;
	protected ArrayList<Lane> lanes;
	protected ArrayList<Birde> birdes;

	public Environment(Game game) {
		this.game = game;
		lanes = new ArrayList<>();
		birdes = new ArrayList<>();
		for (int i = 0; i < game.height; i++) {
			boolean isEmptyLane;
			isEmptyLane = !(game.randomGen.nextDouble() < 0.2) && i >= 2 && i != game.height - 1;
			lanes.add(new Lane(this.game, i, isEmptyLane));
		}
	}

	public boolean isSafe(Case c) {
		for(Birde b : birdes){
			if(b.getPos().absc == c.absc && b.getPos().ord == c.ord){
				return false;
			}
		}
		return lanes.get(c.ord).isSafe(c);
	}

	public boolean isWinningPosition(Case c) {
		return(c.ord == game.height - 1);
	}

	public void update(ArrayList<IFrog> frogs) {
		for (Lane lane : lanes) {
			for (IFrog frog : frogs) {
				for (int absGlissant : lane.absSlidingCase) {
					if (absGlissant == frog.getPosition().absc && lane.getOrd() == frog.getPosition().ord)
						frog.move(frog.getDirection());
				}
				if (frog.getPosition().ord == lane.getOrd() && lane.getIsRiver()) {
					lane.moveFrog(frog);
				}
			}
			lane.update();
		}
		for (IFrog frog : frogs) {
			if (game.randomGen.nextDouble() < 0.015 && frog.getPosition().ord > 1) {
				birdes.add(new birde.Birde(game, frog));
			}
		}
		for(int i =0; i < birdes.size(); i++){
			if (!birdes.get(i).update()) {
				birdes.remove(birdes.get(i));
			}
		}
	}
}
