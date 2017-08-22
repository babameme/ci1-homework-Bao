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

    public Enemy() {
        super();
        random = new Random();
        type = random.nextInt(2);
        ability = new Ability();
        constraints = new Constraints(0, Setting.instance.getGamePlayHeight(),0, Setting.instance.getGamePlayWidth());
        switch (type) {
            case 0:
                renderer = new Animation(
                        SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/blue/1.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/blue/2.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/blue/3.png")
                );
                setDefault(0);
                break;
            case 1:
                renderer = new Animation(
                        SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/pink/1.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/pink/2.png"),
                        SpriteUtils.loadImage("assets/images/enemies/level0/pink/3.png")
                );
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
        renderer = new Animation(
                SpriteUtils.loadImage("assets/images/enemies/level0/black/0.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/1.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/2.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/4.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/5.png")
        );
        setDefault(2);
        this.boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
        this.velocity = new Vector2D();
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
            this.setActive(false);
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
                setAbility(50, 3, 0);
                break;
            case 1:
                setAbility(60, 4, 0);
                break;
            case 2:
                setAbility(300, 10, 0);
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
        }
        //explosion.getAnimation().setEnded(false);
    }
}
