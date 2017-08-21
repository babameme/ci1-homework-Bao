package touhou.enemies;

import bases.GameObject;
import bases.renderers.Animation;
import tklibs.SpriteUtils;

import java.awt.*;

/**
 * Created by huynq on 8/19/17.
 */
public class EnemyExplosion extends GameObject {
    private Animation animation;

    public EnemyExplosion() {

        super();

        this.animation = new Animation(
                6,
                true,
                false,
                SpriteUtils.loadImage("assets/images/enemies/explosion/1.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/3.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/5.png")
        );

        this.renderer = animation;
    }

    @Override
    public void reset() {
        super.reset();
        animation.reset();
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);

        if (animation.isStopped()) {
            this.isActive = false;
        }
    }
}
