package gameCommons;

import java.util.ArrayList;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
	private int frameCount;

	// Lien aux objets utilisés
	private IEnvironment environment;
	private ArrayList<IFrog> frogs;
	private IFroggerGraphics graphic;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant déplacement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.frogs = new ArrayList<IFrog>();
	}

	/**
	 * Lie l'objet frog à la partie
	 * 
	 * @param frog l'objet frog
	 */
	public void setFrog(IFrog frog) {
		this.frogs.add(frog);
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment l'environnement du jeu
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un écran de fin approprié si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		for (IFrog frog : frogs) {
			if (!environment.isSafe(frog.getPosition())) {
				graphic.endGameScreen("You Lose!\n" + frameCount / 10.0 + "s");
				return true;
			}
		}
		return false;
	}

	/**
	 * Teste si la partie est gagnee et lance un écran de fin approprié si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagnée
	 */
	public boolean testWin() {
		for (IFrog frog : frogs) {
			if (environment.isWinningPosition(frog.getPosition())) {
				graphic.endGameScreen("You win\n" + frameCount / 10.0 + "s");
				return true;
			}
		}
		return false;
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		graphic.clear();
		if (!testLose() && !testWin()) {
			environment.update();
			float time = frameCount / 10.0f;
			for (IFrog frog : frogs) {
				this.graphic.add(new Element(frog.getPosition(), 6));
			}
			this.graphic.timer(time);
			frameCount++;
		}
	}

}
