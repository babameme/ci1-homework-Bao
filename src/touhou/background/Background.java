package touhou.background;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class Background extends GameObject{
    private static final float SPEED = 1;

    public Background() {
        super();
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/background/0.png"));
    }

    @Override
    public void run() {
        super.run();
        move();
    }

    public void move(){
        //position.addUp(0, SPEED);
    }
}
