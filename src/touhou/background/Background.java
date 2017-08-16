package touhou.background;

import bases.Constraints;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends GameObject{
    private static final float SPEED = 1;
    private BufferedImage image;
    private Constraints constraints;

    public Background() {
        super();
        image = SpriteUtils.loadImage("assets/images/background/0.png");
        constraints = new Constraints(-2341, 0,0,0);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
    }

    public void move(){
        position.addUp(0, SPEED);
        //constraints.make(position);
        if (position.y > 0)
            position.y -= 2341;
    }

    @Override
    public void render(Graphics2D g2d){
        //System.out.println("Draw background");
        g2d.drawImage(image, (int) position.x, (int) position.y, null);
    }
}
