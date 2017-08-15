package touhou.enemies;

import bases.Constraints;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.GameWindow;
import touhou.ability.Ability;
import touhou.animation.Animation;
import touhou.animation.Sprite;

public class EnemyBullet extends GameObject{
    private Vector2D direction;
    private Ability ability;
    private Animation animation;
    private BoxCollider boxCollider;

    private Constraints constraints;

    public EnemyBullet() {
        super();
        animation = new Animation(Sprite.getSprites("assets/images/enemies/bullets/",7), 10);
        renderer = new ImageRenderer(animation.getSprite());
        ability = new Ability(1, 2);
        direction = new Vector2D(0, 7);
        constraints = new Constraints(31, 650, 8, 384);
    }

    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        fly();
        animation.update();
        renderer.setImage(animation.getSprite());
        //constraints.make(position);
    }

    public void fly(){
        position.addUp(direction);
    }

    public Vector2D getDirection() {
        return direction;
    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
