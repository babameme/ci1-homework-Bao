package touhou.items;

import bases.Constraints;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.ability.Ability;

import java.util.Random;

public class Item extends GameObject implements PhysicsBody{
    private Vector2D direction;
    private BoxCollider boxCollider;
    private Ability ability;
    private Constraints constraints;
    private Random random;
    private int type;

    public Item() {
        super();
        random = new Random();
        type = random.nextInt(100);
        if (type <= 40){
            type = 0;
        }
        else if (type <= 80){
            type = 1;
        }
        else {
            type = 2;
        }
        switch (type){
            case 0:
                renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/items/health-up.png"));
                ability = new Ability(5, 0, 2);
                break;
            case 1:
                renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/items/power-up-blue.png"));
                ability = new Ability(0, 1, 10);
                break;
            case 2:
                renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/items/power-up-red.png"));
                ability = new Ability(3, 1, 15);
                break;
        }
        //System.out.println("Item created");
        direction = new Vector2D(0, 4);
        constraints = new Constraints(31, 650, 8, 384);
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        fly();
    }

    public void fly(){
        position.addThis(direction);
    }

    public Vector2D getDirection() {
        return direction;
    }

    public Ability getAbility() {
        return ability;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}