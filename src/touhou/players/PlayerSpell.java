package touhou.players;

import bases.GameObject;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.ability.Ability;
import touhou.animation.Animation;
import touhou.animation.Sprite;

import java.awt.*;

/**
 * Created by huynq on 8/2/17.
 */
public class PlayerSpell extends GameObject {

    private Ability ability;
    private Animation animation;

    public PlayerSpell() {
        super();
        animation = new Animation(Sprite.getSprites("assets/images/sphere/", 4), 10);
        renderer = new ImageRenderer(animation.getSprite());
        ability = new Ability(2, 1);
    }

    public void run() {
        position.addUp(0, -10);
        animation.update();
        renderer.setImage(animation.getSprite());
    }
}
