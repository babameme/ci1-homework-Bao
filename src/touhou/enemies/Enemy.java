package touhou.enemies;

import bases.GameObject;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.ability.Ability;

import java.awt.*;

/**
 * Created by huynq on 8/9/17.
 */
public class Enemy extends GameObject {
    private Vector2D direction;
    private Ability ability;

    public Enemy() {
        super();
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"));
        ability = new Ability(3, 10);
        direction = new Vector2D(0, 3);
    }

    // Controller
    public void run() {
        super.run();
        fly();
        shoot();
    }

    private void shoot() {
        //EnemyBullet enemyBullet = new EnemyBullet();
        //enemyBullet.getPosition().set(position.add(0, 20));
        //GameObject.add(enemyBullet);
        // TODO: create enemy bullet and shoot
    }

    private void fly() {
        position.addUp(direction);
    }

    public Vector2D getDirection() {
        return direction;
    }

    public Ability getAbility() {
        return ability;
    }
}
