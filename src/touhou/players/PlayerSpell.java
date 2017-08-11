package touhou.players;

import bases.GameObject;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.ability.Ability;

import java.awt.*;

/**
 * Created by huynq on 8/2/17.
 */
public class PlayerSpell extends GameObject {

    private Ability ability;

    public PlayerSpell() {
        super();

        this.renderer = new ImageRenderer(SpriteUtils.loadImage(
                "assets/images/player-spells/a/0.png"
        ));
        ability = new Ability(2, 1);
    }

    public void run() {
        position.addUp(0, -10);
    }
}
