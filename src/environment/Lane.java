package environment;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IFrog;
import graphicalElements.Element;

import java.awt.*;
import java.util.ArrayList;

public class Lane {
	private Game game;
	private ArrayList<Car> cars = new ArrayList<>();
	public int ord;
	private int speed;
	private int frameCount;
	private double density;
	private double slipperyCaseDensity;
	private boolean leftToRight;
	public boolean isRiver;
	public ArrayList<Integer> splipperyCaseAbs = new ArrayList<>();

	public Lane(Game game, int ord, boolean isEmptyLane) {
		this.game = game;
		this.ord = ord;
		this.slipperyCaseDensity = 0.02;
		this.speed = this.game.randomGen.nextInt(4)+game.minSpeedInTimerLoops;
		this.leftToRight = this.game.randomGen.nextBoolean();
		if(isEmptyLane) {
			isRiver = false;
			this.density = this.game.defaultDensity;//game.randomGen.nextDouble()*game.defaultDensity;
			if (game.randomGen.nextDouble() < density) {
				isRiver = true;
			}
		} else {
			this.density = 0;
		}
		this.frameCount = 1;
		for (int i = 0; i < this.game.width; i++) {
			if(game.randomGen.nextDouble() < slipperyCaseDensity && !isRiver){
				splipperyCaseAbs.add(i);
			}
			Case c = new Case(i, ord);
			if ((isRiver && !isSafe(c)) || (!isRiver && isSafe(c))) {
				if (game.randomGen.nextDouble() < density) {
					cars.add(new Car(game, c, leftToRight, isRiver));
				}
			}
		}
	}

	public void update() {
		if (isRiver) {
			Color color = Color.BLUE;
			for (int i = 0; i < game.width; i++) {
				game.getGraphic().add(new Element(i, ord, color));
			}
		}
		for (int i : splipperyCaseAbs){
			Color slipperyCaseColor = Color.BLACK;
			game.getGraphic().add(new Element(i, this.ord, slipperyCaseColor));
		}
		for (Car c : cars) {
			c.addToGraphics();
		}
		if (frameCount%speed == 0) {
			for (Car c : cars) {
				c.move();
			}
			for (int i = 0; i < cars.size(); i++) {
				if (!cars.get(i).isOnScreen()) {
					cars.remove(i);
				}
			}
		}
		frameCount++;
		mayAddCar();
	}

	public boolean isSafe(Case c) {
		for (Car car : cars) {
			if (car.getLeftPos() <= c.absc && car.getRightPos() >= c.absc) {
				return isRiver;
			}
		}
		return !isRiver;
	}

	public void moveFrog(IFrog frog) {
		if (isSafe(frog.getPosition()) && frameCount%speed == 1) {
			frog.riverMove(leftToRight);
		}
	}

	public void downLanes() {
		this.ord--;
		for (Car c : cars) {
			c.downCar();
		}
	}

	/**
	 * Ajoute une voiture au début de la voie avec probabilité égale à la
	 * densité, si la première case de la voie est vide
	 */
	private void mayAddCar() {
		if ((!isRiver && isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) ||
			  isRiver && !isSafe(getFirstCase()) && !isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight, isRiver));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(-5, ord);
		} else
			return new Case(game.width +4, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-6, ord);
		} else
			return new Case(game.width+5, ord);
	}

}
