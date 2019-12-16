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

import java.util.Scanner;

public class Main {

	private static int width, height, minSpeedInTimerLoops;
	private static double defaultDensity;
	private static IFroggerGraphics graphic;

	public static Game start(int gameMode) {
		//Création de l'interface graphique
		graphic = new FroggerGraphic(width, height);
		//Création de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
		switch (gameMode) {
			case 0:
				//Création et liason de la grenouille
				IFrog frog = new Frog(game);
				game.setFrog(frog);
				graphic.setFrog(frog);
				//Création et liaison de l'environnement
				//IEnvironment env = new Environment(game, frog);
				Environment env = new Environment(game);
				game.setEnvironment(env);
				break;
			case 1:
				//Création et liason de la grenouille
				IFrogInf frogInf = new FrogInf(game);
				game.setFrog(frogInf);
				graphic.setFrog(frogInf);
				//Création et liaison de l'environnement
				//IEnvironment env = new Environment(game, frog);
				IEnvInf envInf = new EnvInf(game);
				game.setEnvironment(envInf);
				break;
			case 2:
				// Création et liaison de la grenouille des 2 joueurs
				IFrog frog1 = new Frog(game);
				IFrog frog2 = new Frog(game);
				game.setFrog(frog1);
				game.setFrog(frog2);
				graphic.setFrog(frog1);
				graphic.setFrog(frog2);
				//Création et liaison de l'environnement
				//IEnvironment env = new Environment(game, frog);
				IEnvironment twoPEnv = new EnvInf(game);
				game.setEnvironment(twoPEnv);
				break;
			case 3:
				// Création et liaison de la grenouille des 2 joueurs
				IFrogInf infFrog1 = new FrogInf(game);
				IFrogInf infFrog2 = new FrogInf(game);
				game.setFrog(infFrog1);
				game.setFrog(infFrog2);
				graphic.setFrog(infFrog1);
				graphic.setFrog(infFrog2);
				//Création et liaison de l'environnement
				//IEnvironment env = new Environment(game, frog);
				IEnvInf twoPEnvInf = new EnvInf(game);
				game.setEnvironment(twoPEnvInf);
				break;
			default:
				break;
		}
		return game;
	}

	public static void main(String[] args) {

		//Caractéristiques du jeu
		width = 20;
		height = 32;
		minSpeedInTimerLoops = 3;
		defaultDensity = 0.05;
		int tempo = 100;


        int gameMode;
        System.out.println(
        		"pour jouer la version:    taper:\n" +
				"standard                   '0'\n" +
				"infinie                    '1'\n" +
				"standard 2 joueurs         '2'\n" +
				"infinie 2 joueurs          '3'\n");
        Scanner scanner = new Scanner (System.in);
        try {
	        gameMode = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
        	System.out.println(e);
        	return;
        }

		Game game = start(gameMode);
				
		//Boucle principale : l'environnement s'actualise tous les tempo milisecondes
		Timer timer = new Timer(tempo, e -> {
			game.update();
			graphic.repaint();
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}
