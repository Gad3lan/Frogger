package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int frameCount;

	Lane(int ord) {
		this.ord = ord;
		this.speed = game.randomGen.nextInt(4);
		this.leftToRight = game.randomGen.nextBoolean();
		this.density = game.randomGen.nextDouble()*game.defaultDensity;
		this.frameCount = 0;
	}

	public void update() {

		// TODO

		// Toutes les voitures se déplacent d'une case au bout d'un nombre "tic
		// d'horloge" égal à leur vitesse
		// Notez que cette méthode est appelée à chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e
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
		mayAddCar();
	}

	// TODO : ajout de methodes

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au début de la voie avec probabilité égale à la
	 * densité, si la première case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
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
