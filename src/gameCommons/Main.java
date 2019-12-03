package gameCommons;

import javax.swing.Timer;

import frog.Frog;
import environment.Environment;
import jeuInfinie.FrogInf;
import jeuInfinie.EnvInf;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;
import jeuInfinie.IFrogInf;
import jeuInfinie.IEnvInf;

public class Main {

	public static void main(String[] args) {

		//Caractéristiques du jeu
		int width = 26;
		int height = 20;
		int tempo = 100;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.1;
		
		//Création de l'interface graphique
		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Création de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
		//Création et liason de la grenouille
		//IFrog frog = new Frog(game); ------------------mis de cote pour laisser place au jeu inf----------------------
		IFrogInf frogInf = new FrogInf(game);
		game.setFrog(frogInf);
		graphic.setFrog(frogInf);
		//Création et liaison de l'environnement
		//IEnvironment env = new Environment(game, frog); mis de cote pour laisser place au jeu inf---------------------
		IEnvInf envInf = new EnvInf(game, frogInf);
		game.setEnvironment(envInf);
				
		//Boucle principale : l'environnement s'actualise tous les tempo milisecondes
		Timer timer = new Timer(tempo, e -> {
			game.update();
			graphic.repaint();
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}
