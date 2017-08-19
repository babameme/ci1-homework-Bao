package touhou.enemies;

import bases.Constraints;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import touhou.ability.Ability;

public class EnemyBullet extends GameObject implements PhysicsBody{
    private BoxCollider boxCollider;
    private Constraints constraints;
    private Vector2D direction;
    private Ability ability;

    public EnemyBullet() {
        super();
        this.renderer = new Animation(
                SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/cyan.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/green.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/pink.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/red.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/white.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/yellow.png")
        );
        boxCollider = new BoxCollider(14, 14);
        children.add(boxCollider);
        direction = new Vector2D(0, 4);
        constraints = new Constraints(0,768,0, 384);
        ability = new Ability(2, 3, 0);
        //System.out.println("Bullet added");
    }

    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        // TODO: And health <= 0
        if (constraints.isOut(screenPosition)){
            this.setActive(false);
        }
        fly();
        //TODO : Hit Player
    }

    public Ability getAbility() {
        return ability;
    }

    private void fly() {
        position.addUp(direction);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
