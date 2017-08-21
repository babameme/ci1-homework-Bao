package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import bases.renderers.Animation;
import bases.renderers.Renderer;
import touhou.ability.Ability;
import touhou.enemies.Enemy;
import touhou.enemies.EnemyBullet;
import touhou.items.Item;
import touhou.spheres.PlayerSphere;
import tklibs.SpriteUtils;
import bases.Constraints;
import bases.FrameCounter;
import bases.renderers.ImageRenderer;
import touhou.inputs.InputManager;

/**
 * Created by huynq on 8/2/17.
 */
public class Player extends GameObject implements PhysicsBody{
    private static final int SPEED = 5;

    private InputManager inputManager;
    private Constraints constraints;

    private FrameCounter coolDownCounter;
    private boolean spellLock, isBlink;
    private BoxCollider boxCollider;

    private Ability ability;
    private Vector2D velocity;
    private PlayerAnimator animator;

    public Player() {
        super();
        this.spellLock = false;
        this.animator = new PlayerAnimator();
        this.renderer = animator;
        this.coolDownCounter = new FrameCounter(5);
        ability = new Ability(40, 5, 0);
        velocity = new Vector2D();
        isBlink = false;

        this.boxCollider = new BoxCollider(8, 8);
        children.add(boxCollider);

        addSpheres();
    }

    public void run(Vector2D parentPostion) {
        super.run(parentPostion);

        velocity.set(0,0);
        if (inputManager.upPressed) {
            velocity.y -= SPEED;
        }
        if (inputManager.downPressed){
            velocity.y += SPEED;
        }
        if (inputManager.leftPressed) {
            velocity.x -= SPEED;
        }
        if (inputManager.rightPressed) {
            velocity.x += SPEED;
        }
        if (constraints != null) {
            constraints.make(position);
        }
        position.addUp(velocity);
        animator.update(this);
        castSpell();
        giveBullet();
        hitEnemy();
        getItem();
        //System.out.println(ability.health);
    }

    public boolean isBlink() {
        return isBlink;
    }

    public void setBlink(boolean blink) {
        isBlink = blink;
    }

    private void getItem() {
        Item item = Physics.collideWith(this.boxCollider, Item.class);
        if (item != null){
            this.getAbility().add(item.getAbility());
            item.setActive(false);
        }
    }

    private void giveBullet() {
        EnemyBullet enemyBullet = Physics.collideWith(this.boxCollider, EnemyBullet.class);
        if (enemyBullet != null){
            if (!isBlink) {
                enemyBullet.setActive(false);
                this.getAbility().hurt(enemyBullet.getAbility().damage);
            }
        }
    }

    private void hitEnemy(){
        Enemy enemy = Physics.collideWith(this.boxCollider, Enemy.class);
        if (enemy != null){
            if (!isBlink) {
                enemy.getAbility().hurt(this.ability.damage);
                this.getAbility().hurt(enemy.getAbility().damage);
            }
            isBlink = true;
            animator.getBlinkAnimation().setStopped(false);
        }
    }

    private void castSpell() {
        if (inputManager.xPressed && !spellLock) {
            PlayerSpell newSpell = GameObjectPool.recycle(PlayerSpell.class);
            newSpell.getPosition().set(this.position.add(0, -30));
            spellLock = true;
            coolDownCounter.reset();
        }
        unlockSpell();
    }

    private void unlockSpell() {
        if (spellLock) {
            if (coolDownCounter.run()) {
                spellLock = false;
            }
        }
    }

    private void addSpheres(){
        PlayerSphere leftSphere = new PlayerSphere();
        leftSphere.getPosition().set(-20,0);
        PlayerSphere rightSphere = new PlayerSphere();
        rightSphere.getPosition().set(20,0);
        rightSphere.setReverse(true);
        children.add(leftSphere);
        children.add(rightSphere);
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public void setContraints(Constraints contraints) {
        this.constraints = contraints;
    }

    public Ability getAbility() {
        return ability;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    public Vector2D getVelocity() {
        return velocity;
    }
}
