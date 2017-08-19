package touhou.background;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

import java.awt.*;

public class Background extends GameObject{
    private static final float SPEED = 1;

    public Background() {
        super();
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/background/0.png"), new Vector2D(0,0.75297523319395303956256030878096f));
    }

    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        move();
    }

    private void move() {
        this.getPosition().addUp(0, SPEED);
        if (position.y > 0){
            position.y = 0;
        }
    }
}
