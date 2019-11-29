package environment;

import java.awt.Color;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private int spriteID;

	Car(Game game, Case leftPosition, boolean leftToRight) {
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		length = game.randomGen.nextInt(2) + 1;
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
	
	public void move() {
		if (this.leftToRight) {
			this.leftPosition = new Case(this.leftPosition.absc + 1, this.leftPosition.ord);
		}
		else {
			this.leftPosition = new Case(this.leftPosition.absc-1, this.leftPosition.ord);
		}
	}

	public int getLeftPos() {
		return leftPosition.absc;
	}

	public int getRightPos() {
		return leftPosition.absc + length;
	}

	public boolean isOnScreen() {
		return(this.leftPosition.absc < -6 || this.leftPosition.absc > game.width+6);
	}
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	public void addToGraphics() {
		game.getGraphic().add(new Element(leftPosition.absc, leftPosition.ord, spriteID));
	}

	public void downCar(){
		this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord-1);
	}

}
