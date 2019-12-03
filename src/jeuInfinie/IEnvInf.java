package jeuInfinie;

import gameCommons.IEnvironment;

public interface IEnvInf extends IEnvironment {
    /*ecris ca pi import tout dans main pi new les bonne choses*/
    /**
     * supprime la lane du bas, reorganise les donnees de l'instance d'environment,
     * appel des fonctions pour baisser tous les ordonnées des elements de l'environment et ajoute une ligne en haut
     */
    void downEnvironment();

    /**
     *surcharge de update pour que l'instance puisse utiliser downEnvironment en fonction de
     *la position de la Frog et de la direction
     */
    void update();
}
