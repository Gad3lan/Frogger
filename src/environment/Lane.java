package environment;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private boolean isRiver;
	private double density;
	private int frameCount;

	Lane(Game game, int ord, boolean isEmptyLane) {
		this.game = game;
		this.ord = ord;
		this.speed = this.game.randomGen.nextInt(4)+game.minSpeedInTimerLoops;
		this.leftToRight = this.game.randomGen.nextBoolean();
		if(isEmptyLane) {
			isRiver = false;
			if (game.randomGen.nextDouble() < 0.2) {
				isRiver = true;
			}
			this.density = this.game.defaultDensity;//game.randomGen.nextDouble()*game.defaultDensity;
		} else {
			this.density = 0;
		}
		this.frameCount = 1;
		for (int i = 0; i < this.game.width; i++) {
			Case c = new Case(i, ord);
			if (isSafe(c)) {
				if (game.randomGen.nextDouble() < density) {
					cars.add(new Car(game, c, leftToRight));
				}
			}
		}
	}

	public void update() {

		// TODO

		// Toutes les voitures se déplacent d'une case au bout d'un nombre "tic
		// d'horloge" égal à leur vitesse
		// Notez que cette méthode est appelée à chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e

		if (isRiver) {
			Color color = Color.BLUE;
			for (int i = 0; i < game.width; i++) {
				game.getGraphic().add(new Element(i, ord, color));
			}
		}
		for (Car c : cars) {
				c.addToGraphics();
		}
		if (frameCount%speed == 0) {
			for (Car c : cars) {
				c.move();
			}
			if (cars.size() > 10) {
				for (int i = 0; i < cars.size(); i++) {
					if (!cars.get(i).isOnScreen()) {
						cars.remove(i);
					}
				}
			}
		}
		frameCount++;
		mayAddCar();
	}

	public boolean isSafe(Case c) {
		for (Car car : cars) {

			if (car.getLeftPos() <= c.absc && car.getRightPos() >= c.absc) {
				if (isRiver) return true;
				return false;
			}
		}
		if (isRiver) return false;
		return true;
	}

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au début de la voie avec probabilité égale à la
	 * densité, si la première case de la voie est vide
	 */
	private void mayAddCar() {
		if ((!isRiver && isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) ||
			  isRiver && !isSafe(getFirstCase()) && !isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}
