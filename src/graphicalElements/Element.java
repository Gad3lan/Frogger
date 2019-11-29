package graphicalElements;

import java.awt.*;

import gameCommons.Case;


public class Element extends Case {
    public final int spriteID;
    public final boolean isSprite;
    public final Color color;

    public Element(int absc, int ord, int spriteID) {
        super(absc, ord);
        this.spriteID = spriteID;
        this.color = new Color(0,0,0,0);
        this.isSprite = true;
    }
    
    public Element(Case c, int spriteID) {
        super(c.absc, c.ord);
        this.spriteID = spriteID;
        this.color = new Color(0,0,0,0);
        this.isSprite = true;
    }

    public Element(int absc, int ord, Color color) {
        super(absc, ord);
        this.spriteID = -1;
        this.color = color;
        this.isSprite = false;
    }

    public Element(Case c, Color color) {
        super(c.absc, c.ord);
        this.spriteID = -1;
        this.color = color;
        this.isSprite = false;
    }
}
