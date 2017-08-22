package touhou.spheres;

import bases.Constraints;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import touhou.ability.Ability;
import touhou.enemies.Enemy;
import touhou.settings.Setting;

public class SphereSpell extends GameObject implements PhysicsBody{
    private static final float SPEED = 10;
    private BoxCollider boxCollider;
    private Constraints constraints;
    private Vector2D direction;
    private Ability ability;

    public SphereSpell(){
        super();
        this.renderer = new Animation(
                SpriteUtils.loadImage("assets/images/sphere-bullets/0.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/1.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/2.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/3.png")
        );
        constraints = new Constraints(
                Setting.instance.getWindowInsets().top,
                Setting.instance.getGamePlayHeight(),
                Setting.instance.getWindowInsets().left,
                Setting.instance.getGamePlayWidth()
        );
        boxCollider = new BoxCollider(20, 20);
        ability = new Ability(2, 1, 0);
        children.add(boxCollider);
        direction = new Vector2D(0, -SPEED);
    }

    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        // TODO: And health <= 0
        if (constraints.isOut(screenPosition)){
            this.setActive(false);
        }
        fly();
        shoot();
        hitEnemy();
    }

    private void hitEnemy() {
        Enemy enemy = Physics.collideWith(this.boxCollider, Enemy.class);
        if (enemy != null) {
            enemy.getHit(ability.damage);
            this.isActive = false;
        }
    }

    private void shoot() {
        float dx, dy, h;
        Enemy enemy = GameObject.minDistanceWith(this.screenPosition, Enemy.class);
        if (enemy != null){
            dx = enemy.getScreenPosition().x - this.screenPosition.x;
            dy = enemy.getScreenPosition().y - this.screenPosition.y;
            h = (float) Math.sqrt(dx * dx + dy * dy);
            direction.x = (int) (dx / h * SPEED);
            direction.y = (int) (dy / h * SPEED);
        }
    }

    private void fly() {
        position.addUp(direction);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return null;
    }
}
