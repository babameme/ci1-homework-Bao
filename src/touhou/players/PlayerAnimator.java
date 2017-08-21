package touhou.players;

import bases.Vector2D;
import bases.renderers.Animation;
import bases.renderers.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;

/**
 * Created by huynq on 8/19/17.
 */
public class PlayerAnimator implements Renderer {

    private Animation leftAnimation = new Animation(
            SpriteUtils.loadImage("assets/images/players/left/0.png"),
            SpriteUtils.loadImage("assets/images/players/left/1.png"),
            SpriteUtils.loadImage("assets/images/players/left/2.png"),
            SpriteUtils.loadImage("assets/images/players/left/3.png"),
            SpriteUtils.loadImage("assets/images/players/left/4.png"),
            SpriteUtils.loadImage("assets/images/players/left/5.png")
    );

    private Animation rightAnimation = new Animation(
            SpriteUtils.loadImage("assets/images/players/right/0.png"),
            SpriteUtils.loadImage("assets/images/players/right/1.png"),
            SpriteUtils.loadImage("assets/images/players/right/2.png"),
            SpriteUtils.loadImage("assets/images/players/right/3.png"),
            SpriteUtils.loadImage("assets/images/players/right/4.png"),
            SpriteUtils.loadImage("assets/images/players/right/5.png")
    );

    private Animation straightAnimation = new Animation(
            SpriteUtils.loadImage("assets/images/players/straight/0.png"),
            SpriteUtils.loadImage("assets/images/players/straight/1.png"),
            SpriteUtils.loadImage("assets/images/players/straight/2.png"),
            SpriteUtils.loadImage("assets/images/players/straight/3.png"),
            SpriteUtils.loadImage("assets/images/players/straight/4.png"),
            SpriteUtils.loadImage("assets/images/players/straight/5.png"),
            SpriteUtils.loadImage("assets/images/players/straight/6.png")
    );

    private Animation blinkAnimation = new Animation(
            12,
            true,
            false,
            SpriteUtils.loadImage("assets/images/players/blink/0.png"),
            SpriteUtils.loadImage("assets/images/players/blink/1.png"),
            SpriteUtils.loadImage("assets/images/players/blink/2.png"),
            SpriteUtils.loadImage("assets/images/players/blink/3.png"),
            SpriteUtils.loadImage("assets/images/players/blink/4.png"),
            SpriteUtils.loadImage("assets/images/players/blink/5.png"),
            SpriteUtils.loadImage("assets/images/players/blink/6.png")
    );

    private Animation currentAnimation = straightAnimation;

    public void update(Player player) {
        Vector2D velocity = player.getVelocity();
        if (player.isBlink()){
            currentAnimation = blinkAnimation;
            if (blinkAnimation.isStopped()){
                currentAnimation = straightAnimation;
                blinkAnimation.reset();
                player.setBlink(false);
            }
        }
        else {
            if (velocity.x < 0) {
                currentAnimation = leftAnimation;
            } else if (velocity.x > 0) {
                currentAnimation = rightAnimation;
            } else {
                currentAnimation = straightAnimation;
            }
        }
    }

    public Animation getBlinkAnimation() {
        return blinkAnimation;
    }

    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        currentAnimation.render(g2d, position);
    }
}
