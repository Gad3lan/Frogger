package birde;


import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;
import graphicalElements.Element;

import java.awt.*;

public class Birde {

    private int frameCount;
    private Game game;
    private Case pos;
    private Direction direction;
    private  int speed;

    public Birde(Game game, IFrog frog){
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

    public Case getPos(){ return this.pos; }

    public void down(){
        this.pos = new Case(pos.absc, pos.ord - 1);
    }

    public boolean update(){
        this.frameCount++;
        boolean cetOiseauDisparaitIl = true;
        if(frameCount%this.speed == 0) {
            switch (this.direction) {
                case right:
                    if (pos.absc == game.width - 1)
                        cetOiseauDisparaitIl = false;
                    else {
                        pos = new Case(pos.absc + 1, pos.ord);
                        cetOiseauDisparaitIl = true;
                    }
                    break;
                case up:
                    if (pos.ord == game.height - 1)
                        cetOiseauDisparaitIl = false;
                    else {
                        pos = new Case(pos.absc, pos.ord + 1);
                        cetOiseauDisparaitIl = true;
                    }
                    break;
                case left:
                    if (pos.absc == 0)
                        cetOiseauDisparaitIl = false;
                    else {
                        pos = new Case(pos.absc - 1, pos.ord);
                        cetOiseauDisparaitIl = true;
                    }
                    break;
                default:
                    if (pos.ord == 0)
                        cetOiseauDisparaitIl = false;
                    else {
                        pos = new Case(pos.absc, pos.ord - 1);
                        cetOiseauDisparaitIl = true;
                    }
                    break;
            }
        }
        game.getGraphic().add(new Element(this.pos, Color.YELLOW));
        return cetOiseauDisparaitIl;
    }
}
