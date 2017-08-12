package touhou.enemies;

import bases.GameObject;
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
public class Enemy extends GameObject {
    private Vector2D direction;
    private Ability ability;
    private Animation animation;
    private boolean boss;
    public Enemy(int type) {
        super();
        boss = false;
        switch (type){
            case 0:
                animation = new Animation(Sprite.getSprites("assets/images/enemies/level0/pink/", 4),5);
                break;
            case 1:
                animation = new Animation(Sprite.getSprites("assets/images/enemies/level0/blue/", 4),5);
                break;
            case 3:
                animation = new Animation(Sprite.getSprites("assets/images/enemies/level0/black/", 9),5);
                boss = true;
                break;
            case 4:
                animation = new Animation(Sprite.getSprites("assets/images/enemies/level0/fireball/",1),5);
                break;
        }
        renderer = new ImageRenderer(animation.getSprite());
        ability = new Ability(3, 10);
        direction = new Vector2D(0, 3);
    }

    // Controller
    public void run() {
        super.run();
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
}
