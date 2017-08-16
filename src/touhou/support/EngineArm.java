package touhou.support;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import touhou.ability.Ability;
import touhou.animation.Animation;
import touhou.animation.Sprite;

import java.awt.*;

public class EngineArm extends GameObject implements PhysicsBody{
    private Animation animation;
    private Ability ability;
    private BoxCollider boxCollider;

    public EngineArm(Vector2D position) {
        super();
        //animation = new Animation(Sprite.getSprites("assets/images/enemies/level0/pink/", 4), 5);
        animation = new Animation(Sprite.getSprites("assets/images/sphere-bullets/", 4), 5);
        renderer = new ImageRenderer(animation.getSprite());
        ability = new Ability(5, 15, 5);
        boxCollider = new BoxCollider(10, 10);
        this.position = position;
        this.children.add(boxCollider);
        this.setActive(true);
    }

    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        if (this.ability.getHealth() <= 0){
            this.setActive(false);
        }
        animation.update();
        renderer.setImage(animation.getSprite());
        System.out.println("Run Arm");
    }

    @Override
    public void render(Graphics2D g2d){
        //System.out.println("Render Arm");
        if (renderer != null) {
            //System.out.println("@@@@@@@@ Ve duoc ma");
            //System.out.println(renderer.image);
            renderer.render(g2d, screenPosition); // null.render() => NullPointerException
        }
        for (GameObject child : children){
            if (child.isActive())
                child.render(g2d);
        }

    }
    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
