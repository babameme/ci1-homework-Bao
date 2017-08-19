package touhou.enemies;

import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.ability.Ability;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by huynq on 8/9/17.
 */
public class Enemy extends GameObject implements PhysicsBody {
    private static final float SPEED = 2;
    private BoxCollider boxCollider;
    private BulletSpawner bulletSpawner;
    private Ability ability;
    private int type;
    private Random random;

    public Enemy() {
        super();
        random = new Random();
        type = random.nextInt(3);
        switch (type) {
            case 0:
                renderer = new Animation(
                        SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/blue/1.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/blue/2.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/blue/3.png")
                );
                ability = new Ability(25, 3, 0);
                break;
            case 1:
                renderer = new Animation(
                        SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/pink/1.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/pink/2.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/pink/3.png")
                );
                ability = new Ability(30, 4, 0);
                break;
            case 2:
                renderer = new Animation(
                        SpriteUtils.loadImage("assets/images/enemies/level0/black/0.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/black/1.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/black/2.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/black/4.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/black/5.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/black/6.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/black/7.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/black/8.png")
                );
                ability = new Ability(80, 6, 0);
        }
        this.boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);

        addBulletSpawner();
    }

    private void addBulletSpawner() {
        bulletSpawner = new BulletSpawner();
        children.add(bulletSpawner);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        fly();
        shoot();
    }

    private void shoot() {
        // TODO: create enemy bullet and shoot
    }

    private void fly() {
        position.addUp(0, SPEED);
    }

    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    public Ability getAbility() {
        return ability;
    }
}
