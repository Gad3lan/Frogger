package environment;


import java.util.ArrayList;

import bird.Bird;
import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import gameCommons.IFrog;

public class Environment implements IEnvironment {
	protected Game game;
	protected ArrayList<Lane> lanes;
	protected ArrayList<Bird> birds;

	public Environment(Game game) {
		this.game = game;
		lanes = new ArrayList<Lane>();
		birds = new ArrayList<Bird>();
		for (int i = 0; i < game.height; i++) {
			boolean isEmptyLane;
			isEmptyLane = !(game.randomGen.nextDouble() < 0.2) && i >= 2 && i != game.height - 1;
			lanes.add(new Lane(this.game, i, isEmptyLane));
		}
	}

	/**
	 *
	 * @param c La case a verifier
	 * @return True si la grenouille peut aller sur cette case, false sinon
	 */
	public boolean isSafe(Case c) {
		for(Bird b : birds){
			if(b.getPos().absc == c.absc && b.getPos().ord == c.ord){
				return false;
			}
		}
		return lanes.get(c.ord).isSafe(c);
	}

	/**
	 *
	 * @param c La case a verifier
	 * @return True si cette case declanche la victoire
	 */
	public boolean isWinningPosition(Case c) {
		return(c.ord == game.height - 1);
	}

	/**
	 * Met a jour l'environnement
	 * @param frogs Les différentes grenouilles
	 */
	public void update(ArrayList<IFrog> frogs) {
		for (Lane lane : lanes) {
			for (IFrog frog : frogs) {
				for (int SlipperyAbs : lane.splipperyCaseAbs) {
					if (SlipperyAbs == frog.getPosition().absc && lane.ord == frog.getPosition().ord)
						frog.move(frog.getDirection());
				}
				if (frog.getPosition().ord == lane.ord && lane.isRiver) {
					lane.moveFrog(frog);
				}
			}
			lane.update();
		}
		for (IFrog frog : frogs) {
			if (game.randomGen.nextDouble() < 0.015 && frog.getPosition().ord > 1) {
				birds.add(new Bird(game, frog));
			}
		}
		for(int i = 0; i < birds.size(); i++){
			if (!birds.get(i).update()) {
				birds.remove(birds.get(i));
			}
		}
	}
}
