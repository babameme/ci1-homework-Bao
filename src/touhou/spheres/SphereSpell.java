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
    private static final float ENEMY_PULL_FORCE = 1;
    private BoxCollider boxCollider;
    private Constraints constraints;
    private Vector2D direction;
    private Ability ability;
    private Enemy focusEnemy = null;

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
        if ((focusEnemy == null) || (!focusEnemy.isActive())) {
            focusEnemy = GameObject.minDistanceWith(this.screenPosition, Enemy.class);
        }
        if (focusEnemy != null) {
            Vector2D toEnemy = focusEnemy.getScreenPosition().subtract(this.screenPosition).normalizeThis().multiplyThis(ENEMY_PULL_FORCE);
            this.direction.addThis(toEnemy).normalizeThis().multiplyThis(SPEED);
        }
    }

    private void fly() {
        position.addThis(direction);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return null;
    }
}
