package bird;


import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;
import graphicalElements.Element;

import java.awt.*;

public class Bird {

    private int frameCount;
    private Game game;
    private Case pos;
    private Direction direction;
    private  int speed;

    public Bird(Game game, IFrog frog){
        this.game = game;
        switch(this.game.randomGen.nextInt(4)){
            case 0:
                this.direction = Direction.right;
                this.pos = new Case(0, frog.getPosition().ord);
                break;
            case 1:
                this.direction = Direction.up;
                this.pos = new Case(frog.getPosition().absc, 0);
                break;
            case 2:
                this.direction = Direction.left;
                this.pos = new Case(game.width, frog.getPosition().ord);
                break;
            default:
                this.direction = Direction.down;
                this.pos = new Case(frog.getPosition().absc, game.height);
                break;
        }
        this.speed = 2;
        this.frameCount = 1;
    }

    /**
     *
     * @return la position de la grenouille
     */
    public Case getPos(){ return this.pos; }

    /**
     * Baisse les coordonnées de l'oiseau
     */
    public void down(){
        this.pos = new Case(pos.absc, pos.ord - 1);
    }

    /**
     * Met à jour l'oiseau
     * @return True si l'oiseau est toujours visible, false sinon
     */
    public boolean update(){
        this.frameCount++;
        boolean isOnScreen = true;
        if(frameCount%this.speed == 0) {
            switch (this.direction) {
                case right:
                    if (pos.absc == game.width - 1)
                        isOnScreen = false;
                    else {
                        pos = new Case(pos.absc + 1, pos.ord);
                        isOnScreen = true;
                    }
                    break;
                case up:
                    if (pos.ord == game.height - 1)
                        isOnScreen = false;
                    else {
                        pos = new Case(pos.absc, pos.ord + 1);
                        isOnScreen = true;
                    }
                    break;
                case left:
                    if (pos.absc == 0)
                        isOnScreen = false;
                    else {
                        pos = new Case(pos.absc - 1, pos.ord);
                        isOnScreen = true;
                    }
                    break;
                default:
                    if (pos.ord == 0)
                        isOnScreen = false;
                    else {
                        pos = new Case(pos.absc, pos.ord - 1);
                        isOnScreen = true;
                    }
                    break;
            }
        }
        game.getGraphic().add(new Element(this.pos, Color.YELLOW));
        return isOnScreen;
    }
}
