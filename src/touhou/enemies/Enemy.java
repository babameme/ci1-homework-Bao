package touhou.enemies;

import bases.Constraints;
import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.ability.Ability;
import touhou.animation.Animation;
import touhou.animation.Sprite;

import java.awt.*;

/**
 * Created by huynq on 8/9/17.
 */
public class Enemy extends GameObject implements PhysicsBody{
    private Vector2D direction;
    private Ability ability;
    private Animation animation;
    private boolean boss;
    private BoxCollider boxCollider;
    private Constraints constraints;
    public Enemy(int type) {
        super();
        boss = false;
        switch (type){
            case 0:
                animation = new Animation(Sprite.getSprites("assets/images/enemies/level0/pink/", 4),5);
                ability = new Ability(4, 40);
                break;
            case 1:
                animation = new Animation(Sprite.getSprites("assets/images/enemies/level0/blue/", 4),5);
                ability = new Ability(5, 30);
                break;
            case 2:
                animation = new Animation(Sprite.getSprites("assets/images/enemies/level0/black/", 9),5);
                ability = new Ability(7, 70);
                boss = true;
                break;
        }
        renderer = new ImageRenderer(animation.getSprite());
        ability = new Ability(3, 10);
        direction = new Vector2D(0, 3);
        boxCollider = new BoxCollider(25,25);
        constraints = new Constraints(0, 768, 0, 384);
        this.children.add(boxCollider);
    }

    // Controller
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (constraints.isOut(screenPosition) || ability.getHealth() <= 0){
            this.setActive(false);
        }
        animation.update();
        renderer.setImage(animation.getSprite());
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

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
