package environment;

import java.awt.*;
import java.util.ArrayList;

import birde.Birde;
import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import gameCommons.IFrog;
import graphicalElements.Element;

public class Environment implements IEnvironment {
	protected Game game;
	protected ArrayList<Lane> lanes;
	protected ArrayList<Birde> birdes;

	public Environment(Game game) {
		this.game = game;
		lanes = new ArrayList<Lane>();
		birdes = new ArrayList<Birde>();
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
				for (int absGlissant : lane.absSlidingCase) {
					if (absGlissant == frog.getPosition().absc && lane.ord == frog.getPosition().ord)
						frog.move(frog.getDirection());
				}
				if (frog.getPosition().ord == lane.ord && lane.isRiver) {
					lane.moveFrog(frog);
				}
			}
			lane.update();
		}
		for(int i =0; i < birdes.size(); i++){
			if (birdes.get(i).update()) {
				game.getGraphic().add(new Element(birdes.get(i).getPos(), Color.YELLOW));
				System.out.println("!couleur");//-----------------------------------
			}else {
				birdes.remove(birdes.get(i));
				System.out.println("!disparition demander");//-----------------------------------
			}
		}
		for (IFrog frog : frogs) {
			if (game.randomGen.nextDouble() < 0.05 && frog.getPosition().ord > 1) {
				birdes.add(new birde.Birde(game, frog));

				System.out.println("!apparition demander");//-----------------------------------
			}
		}
	}
}
