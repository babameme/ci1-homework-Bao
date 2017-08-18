package touhou.enemies;

import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by huynq on 8/9/17.
 */
public class Enemy extends GameObject implements PhysicsBody {
    private static final float SPEED = 3;
    private BoxCollider boxCollider;
    private BulletSpawner bulletSpawner;

    public Enemy() {
        super();

        renderer = new Animation(
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/1.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/2.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/3.png")
        );

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
}
