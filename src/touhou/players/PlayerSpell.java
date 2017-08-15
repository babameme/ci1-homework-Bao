package touhou.players;

import bases.Constraints;
import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.ability.Ability;
import touhou.animation.Animation;
import touhou.animation.Sprite;
import touhou.enemies.Enemy;

import java.awt.*;

/**
 * Created by huynq on 8/2/17.
 */
public class PlayerSpell extends GameObject implements PhysicsBody{

    private Ability ability;
    private Animation animation;
    private BoxCollider boxCollider;
    private Vector2D direction;
    private Constraints constraints;

    public PlayerSpell() {
        super();
        animation = new Animation(Sprite.getSprites("assets/images/sphere/", 4), 10);
        renderer = new ImageRenderer(animation.getSprite());
        ability = new Ability(2, 2);
        direction = new Vector2D(0, -10);
        boxCollider = new BoxCollider(20,20);
        constraints = new Constraints(0, 768, 0, 384);
        this.children.add(boxCollider);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (constraints.isOut(screenPosition) || ability.getHealth() <= 0){
            this.setActive(false);
        }
        position.addUp(direction);
        animation.update();
        renderer.setImage(animation.getSprite());
        hitEnemy();
    }

    private void hitEnemy() {
        Enemy enemy = Physics.collideWithEnemy(this.boxCollider);
        if (enemy != null){
            System.out.println(enemy.getAbility().toString());
            enemy.getAbility().hurtHealth(this.ability.getDamage());
            this.ability.hurtHealth(enemy.getAbility().getDamage());
            if (enemy.getAbility().getHealth() == 0) {
                enemy.setActive(false);
            }
            if (this.getAbility().getHealth() == 0) {
                this.isActive = false;
            }
        }
    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public Vector2D getDirection() {
        return direction;
    }

    public Ability getAbility() {
        return ability;
    }
}
