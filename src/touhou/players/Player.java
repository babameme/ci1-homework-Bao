package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import tklibs.SpriteUtils;
import bases.Constraints;
import bases.FrameCounter;
import bases.renderers.ImageRenderer;
import touhou.ability.Ability;
import touhou.animation.Animation;
import touhou.animation.Sprite;
import touhou.inputs.InputManager;
import touhou.item.Item;

import java.util.Vector;

/**
 * Created by huynq on 8/2/17.
 */
public class Player extends GameObject implements PhysicsBody{
    private static final int SPEED = 5;

    private InputManager inputManager;
    private Constraints constraints;
    private boolean moveLeftRight;
    private Animation walkLeft, walkRight, stand, animation;

    private Ability ability;

    private BoxCollider boxCollider;

    private FrameCounter coolDownCounter;
    private boolean spellLock;

    private SpellSpawner spellSpawner;

    public Player() {
        super();
        this.spellLock = false;
        walkLeft = new Animation(Sprite.getSprites("assets/images/players/left/", 6),5);
        walkRight = new Animation(Sprite.getSprites("assets/images/players/right/", 6), 5);
        stand = new Animation(Sprite.getSprites("assets/images/players/straight/", 7),5);
        animation = stand;
        renderer = new ImageRenderer(animation.getSprite());
        ability = new Ability(3, 50);
        this.coolDownCounter = new FrameCounter(3);
        boxCollider = new BoxCollider(10,10);
        this.children.add(boxCollider);
        spellSpawner = new SpellSpawner(this.ability, this.screenPosition);
    }

    public void setContraints(Constraints contraints) {
        this.constraints = contraints;
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        moveLeftRight = false;

        if (inputManager.upPressed)
            position.addUp(0, -SPEED);
        if (inputManager.downPressed)
            position.addUp(0, SPEED);
        if (inputManager.leftPressed){
            position.addUp(-SPEED, 0);
            moveLeftRight = true;
            animation = walkLeft;
        }
        if (inputManager.rightPressed){
            position.addUp(SPEED, 0);
            moveLeftRight = true;
            animation = walkRight;
        }
        if (!moveLeftRight){
            animation = stand;
        }

        if (constraints != null) {
            constraints.make(position);
        }

        castSpell();
        getItem();
        animation.update();
        renderer.setImage(animation.getSprite());
        //System.out.println(Integer.toString(ability.getDamage()) + " " + Integer.toString(ability.getHealth()) + " " + Integer.toString(ability.getPower()));
    }

    private void castSpell() {
        if (inputManager.xPressed && !spellLock) {
            spellSpawner.shoot(screenPosition);
            spellLock = true;
            coolDownCounter.reset();
        }

        unlockSpell();
    }

    private void getItem(){
        Item item = Physics.collideWithItem(this.boxCollider);
        if (item != null){
            this.ability.addUp(item.getAbility());
            item.getAbility().setHealth(0);
            item.setActive(false);
        }
    }

    private void unlockSpell() {
        if (spellLock) {
            if (coolDownCounter.run()) {
                spellLock = false;
            }
        }
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public Ability getAbility() {
        return ability;
    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
