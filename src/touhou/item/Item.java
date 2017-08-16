package touhou.item;

import bases.Constraints;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.ability.Ability;

public class Item extends GameObject implements PhysicsBody{
    private Vector2D direction;
    private BoxCollider boxCollider;
    private Ability ability;
    private Constraints constraints;

    public Item(int type) {
        super();
        if (type <= 1){
            type = 0;
        }
        else if (type <= 6){
            type = 1;
        }
        else {
            type = 2;
        }
        switch (type){
            case 0:
                renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/items/health-up.png"));
                ability = new Ability(0, 3, 1);
                break;
            case 1:
                renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/items/power-up-blue.png"));
                ability = new Ability(0, 1, 3);
                break;
            case 2:
                renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/items/power-up-red.png"));
                ability = new Ability(3, 1, 1);
                break;
        }
        //System.out.println("Item created");
        direction = new Vector2D(0, 7);
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
        position.addUp(direction);
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
