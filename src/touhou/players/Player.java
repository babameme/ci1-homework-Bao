package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import bases.pools.GameObjectPool;
import bases.renderers.Animation;
import bases.renderers.Renderer;
import touhou.spheres.PlayerSphere;
import tklibs.SpriteUtils;
import bases.Constraints;
import bases.FrameCounter;
import bases.renderers.ImageRenderer;
import touhou.inputs.InputManager;

/**
 * Created by huynq on 8/2/17.
 */
public class Player extends GameObject {
    private static final int SPEED = 5;

    private InputManager inputManager;
    private Constraints constraints;

    private FrameCounter coolDownCounter;
    private boolean spellLock;
    public Renderer left, right, straight, blink;
    boolean moveLeftRight;

    public Player() {
        super();
        this.spellLock = false;
        straight = new Animation(
                SpriteUtils.loadImage("assets/images/players/straight/0.png"),
                SpriteUtils.loadImage("assets/images/players/straight/1.png"),
                SpriteUtils.loadImage("assets/images/players/straight/2.png"),
                SpriteUtils.loadImage("assets/images/players/straight/3.png"),
                SpriteUtils.loadImage("assets/images/players/straight/4.png"),
                SpriteUtils.loadImage("assets/images/players/straight/5.png"),
                SpriteUtils.loadImage("assets/images/players/straight/6.png")
        );
        left = new Animation(
                SpriteUtils.loadImage("assets/images/players/left/0.png"),
                SpriteUtils.loadImage("assets/images/players/left/1.png"),
                SpriteUtils.loadImage("assets/images/players/left/2.png"),
                SpriteUtils.loadImage("assets/images/players/left/3.png"),
                SpriteUtils.loadImage("assets/images/players/left/4.png"),
                SpriteUtils.loadImage("assets/images/players/left/5.png")
        );
        right = new Animation(
                SpriteUtils.loadImage("assets/images/players/right/0.png"),
                SpriteUtils.loadImage("assets/images/players/right/1.png"),
                SpriteUtils.loadImage("assets/images/players/right/2.png"),
                SpriteUtils.loadImage("assets/images/players/right/3.png"),
                SpriteUtils.loadImage("assets/images/players/right/4.png"),
                SpriteUtils.loadImage("assets/images/players/right/5.png")
        );
        blink = new Animation(
                SpriteUtils.loadImage("assets/images/players/blink/0.png"),
                SpriteUtils.loadImage("assets/images/players/blink/1.png")
        );
        renderer = straight;
        this.coolDownCounter = new FrameCounter(5);
        addSpheres();
    }

    public void setContraints(Constraints contraints) {
        this.constraints = contraints;
    }

    public void run(Vector2D parentPostion) {
        super.run(parentPostion);
        moveLeftRight = false;
        if (inputManager.upPressed)
            position.addUp(0, -SPEED);
        if (inputManager.downPressed)
            position.addUp(0, SPEED);
        if (inputManager.leftPressed) {
            position.addUp(-SPEED, 0);
            moveLeftRight = true;
            renderer = left;
        }
        if (inputManager.rightPressed) {
            position.addUp(SPEED, 0);
            moveLeftRight = true;
            renderer = right;
        }
        if (!moveLeftRight){
            renderer = straight;
        }
        if (constraints != null) {
            constraints.make(position);
        }
        castSpell();
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
}
