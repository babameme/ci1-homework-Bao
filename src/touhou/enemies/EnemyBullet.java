package touhou.enemies;

import bases.Constraints;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.GameWindow;
import touhou.ability.Ability;

public class EnemyBullet extends GameObject{
    private Vector2D direction;
    private Ability ability;

    private Constraints constraints;

    public EnemyBullet() {
        super();
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/bullets/0.png"));
        ability = new Ability(1, 2);
        direction = new Vector2D(0, 7);
        constraints = new Constraints(31, 650, 8, 384);
    }

    @Override
    public void run(){
        super.run();
        fly();
        //constraints.make(position);
    }

    public void fly(){
        position.addUp(direction);
    }


    public Vector2D getDirection() {
        return direction;
    }
}
