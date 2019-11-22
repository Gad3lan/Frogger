package environment;

import java.awt.Color;
import java.util.Random;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.DARK_GRAY;

	Car(Game game, Case leftPosition, boolean leftToRight) {
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		length = game.randomGen.nextInt(3) + 1;
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
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}
