package touhou.background;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends GameObject{
    private static final float SPEED = 1;
    private BufferedImage image = SpriteUtils.loadImage("assets/images/background/0.png");

    public Background() {
        super();
    }

    @Override
    public void run() {
        super.run();
        move();
    }

    public void move(){
        position.addUp(0, SPEED);
    }

    public void render(Graphics2D g2d){
        g2d.drawImage(image, (int) position.x, (int) position.y, null);
    }
}