package birde;

import frog.Frog;
import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import graphicalElements.Element;

import java.awt.*;

public class Birde {

    private int frameCount;
    private Game game;
    private Case pos;
    private Direction direction;
    private  int speed;

    Birde(Game game, Frog frog){
        this.game = game;
        switch(this.game.randomGen.nextInt(3)){
            case 0:
                this.direction = Direction.right;
                break;
            case 1:
                this.direction = Direction.up;
                break;
            case 2:
                this.direction = Direction.left;
                break;
            default:
                this.direction = Direction.down;
                break;
        }
        this.pos = new Case(game.height-1, frog.getPosition().ord);
        this.speed = 20;
        this.frameCount = 1;
    }

    public boolean update(){
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
        if(cetOiseauDisparaitIl)
            game.getGraphic().add(new Element(this.pos, Color.YELLOW));
        this.frameCount++;
        return cetOiseauDisparaitIl;
    }
}
