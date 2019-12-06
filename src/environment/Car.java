package environment;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private boolean isLog;
	private int length;
	private int spriteID;

	Car(Game game, Case leftPosition, boolean leftToRight, boolean isLog) {
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		this.isLog = isLog;
		if (isLog) {
			length = game.randomGen.nextInt(4) + 2;
		} else {
			length = game.randomGen.nextInt(2) + 1;
		}
		if (this.leftToRight) {
			if (this.length == 1) {
				this.spriteID = game.randomGen.nextInt(2) + 2;
			} else {
				this.spriteID = 5;
			}
		} else {
			if (this.length == 1) {
				this.spriteID = game.randomGen.nextInt(2);
			} else {
				this.spriteID = 4;
			}
		}
		addToGraphics();
	}

	/**
	 * Deplace la voiture suivant le sens de circulation
	 */
	public void move() {
		if (this.leftToRight) {
			this.leftPosition = new Case(this.leftPosition.absc + 1, this.leftPosition.ord);
		}
		else {
			this.leftPosition = new Case(this.leftPosition.absc-1, this.leftPosition.ord);
		}
	}

	/**
	 *
	 * @return La position de la gauche de la voiture
	 */
	public int getLeftPos() {
		return leftPosition.absc;
	}

	/**
	 *
	 * @return La position de la droite de la voiture
	 */
	public int getRightPos() {
		return leftPosition.absc + length-1;
	}

	/**
	 *
	 * @return True si la voiture est visible, false sinon
	 */
	public boolean isOnScreen() {
		return(this.leftPosition.absc > -6 && this.leftPosition.absc < game.width+6);
	}

	/**
	 * Permet d'ajouter un element graphique correspondant a la voiture
	 */
	public void addToGraphics() {
		if (isLog) {
			game.getGraphic().add(new Element(leftPosition.absc, leftPosition.ord, 15));
			for (int i = 1; i < length - 1; i++) {
				game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, 16));
			}
			game.getGraphic().add(new Element(leftPosition.absc + length-1, leftPosition.ord, 17));
		} else {
			game.getGraphic().add(new Element(leftPosition.absc, leftPosition.ord, spriteID));
		}
	}

	/**
	 * Baisse la voitude d'une case
	 */
	public void downCar(){
		this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord-1);
	}

}
