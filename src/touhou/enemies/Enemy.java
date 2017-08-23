package touhou.enemies;

import bases.Constraints;
import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import bases.Vector2D;
import touhou.ability.Ability;
import touhou.players.Player;
import touhou.settings.Setting;

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
    private Vector2D velocity;
    private Constraints constraints;
    private static Animation blueAnimation = new Animation(
            SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/blue/1.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/blue/2.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/blue/3.png")
    );
    private static Animation pinkAnimation = new Animation(
            SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/pink/1.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/pink/2.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/pink/3.png")
    );
    private static Animation blackAnimation = new Animation(
            SpriteUtils.loadImage("assets/images/enemies/level0/black/0.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/black/1.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/black/2.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/black/4.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/black/5.png")
    );

    public Enemy() {
        super();
        random = new Random();
        type = random.nextInt(2);
        ability = new Ability();
        constraints = new Constraints(0, Setting.instance.getGamePlayHeight(),0, Setting.instance.getGamePlayWidth());
        switch (type) {
            case 0:
                setDefault(0);
                break;
            case 1:
                setDefault(1);
                break;
        }
        this.boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
        this.velocity = new Vector2D(0, SPEED);
        addBulletSpawner(type);
    }

    public Enemy(int type){
        super();
        this.type = type;
        ability = new Ability();
        constraints = new Constraints(0, Setting.instance.getGamePlayHeight(),0, Setting.instance.getGamePlayWidth());
        setDefault(2);
        this.boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
        this.velocity = new Vector2D(-1, 0);
        addBulletSpawner(type);
    }

    private void addBulletSpawner(int type) {
        bulletSpawner = new BulletSpawner(type);
        children.add(bulletSpawner);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        fly();
        shoot();
        if (constraints.isOut(this.screenPosition)){
            if (type == 2){
                position.set(350, 60);
            }else {
                this.setActive(false);
            }
        }
    }

    private void shoot() {
        // TODO: create enemy bullet and shoot
    }

    private void fly() {
        position.addThis(velocity);
    }

    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(int a, int b, int c){
        ability.setHealth(a);
        ability.setDamage(b);
        ability.setPower(c);
    }

    public void setDefault(int type){
        switch (type){
            case 0:
                renderer = blueAnimation;
                setAbility(50, 5, 0);
                break;
            case 1:
                renderer = pinkAnimation;
                setAbility(60, 6, 0);
                break;
            case 2:
                renderer = blackAnimation;
                setAbility(400, 10, 0);
        }
    }

    public int getType() {
        return type;
    }

    public void getHit(int damage) {
        this.ability.hurt(damage);
        EnemyExplosion explosion = GameObjectPool.recycle(EnemyExplosion.class);
        explosion.getPosition().set(this.position);
        if (ability.health <= 0){
            setActive(false);
            addScore(this.type);
        }
        //explosion.getAnimation().setEnded(false);
    }

    public void addScore(int type){
        switch (type){
            case 0:
                Player.score += 5;
                break;
            case 1:
                Player.score += 7;
                break;
            case 2:
                Player.score += 20;
                break;
        }
    }
}
