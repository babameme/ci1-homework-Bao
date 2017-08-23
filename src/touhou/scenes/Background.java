package touhou.scenes;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.settings.Setting;

/**
 * Created by huynq on 8/19/17.
 */
public class Background extends GameObject {
    private ImageRenderer imageRenderer;
    private final float SPEED = 2;
    private final float imageHeight;
    private boolean stopped;
    private FrameCounter counter;

    public Background() {
        super();
        this.imageRenderer = new ImageRenderer(
                SpriteUtils.loadImage("assets/images/background/0.png")
        );
        this.imageRenderer.getAnchor().set(0, 1);
        this.position.set(0, Setting.instance.getGamePlayHeight());
        this.imageHeight = imageRenderer.image.getHeight();
        this.renderer = imageRenderer;
        stopped = false;
        counter = new FrameCounter(3);
    }

    @Override
    public void run(Vector2D parentPosition) {
        if (counter.run()) {
            super.run(parentPosition);
            position.y += SPEED;
            if (position.y >= imageHeight) {
                //System.out.println(stopped);
                position.y = imageHeight;
                stopped = true;
            }
            counter.reset();
        }
    }
    public boolean isStopped() {
        return stopped;
    }
}
