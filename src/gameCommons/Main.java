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

	public static void main(String[] args) {

		//Caractéristiques du jeu
		int width = 26;
		int height = 20;
		int tempo = 100;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.1;

		//mis de cote pour laisser place au jeu inf---------------------
        String choixDeJeux = "1";
        System.out.println("si vous souhaitez joué la version standard taper '0', la version infinie taper '1':");
        Scanner scanner = new Scanner (System.in);
        choixDeJeux = scanner.nextLine();

		//Création de l'interface graphique
		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Création de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
        if(choixDeJeux == "O"){
            //Création et liason de la grenouille
            IFrog frog = new Frog(game);
            game.setFrog(frog);
            graphic.setFrog(frog);
            //Création et liaison de l'environnement
            //IEnvironment env = new Environment(game, frog);
            Environment envInf = new Environment(game, frog);
            game.setEnvironment(envInf);
        }else{
            //Création et liason de la grenouille
            IFrogInf frogInf = new FrogInf(game);
            game.setFrog(frogInf);
            graphic.setFrog(frogInf);
            //Création et liaison de l'environnement
            //IEnvironment env = new Environment(game, frog);
            IEnvInf envInf = new EnvInf(game, frogInf);
            game.setEnvironment(envInf);
        }
				
		//Boucle principale : l'environnement s'actualise tous les tempo milisecondes
		Timer timer = new Timer(tempo, e -> {
			game.update();
			graphic.repaint();
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}
