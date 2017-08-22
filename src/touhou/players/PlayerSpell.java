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
import touhou.enemies.Enemy;
import touhou.settings.Setting;

import java.awt.image.BufferedImage;

/**
 * Created by huynq on 8/2/17.
 */
public class PlayerSpell extends GameObject implements PhysicsBody {

    private BoxCollider boxCollider;
    private Ability ability;
    private Constraints constraints;
    private int type;
    private static ImageRenderer normalSpell = new ImageRenderer(SpriteUtils.loadImage("assets/images/player-spells/a/1.png"));
    private static ImageRenderer thunderSpell = new ImageRenderer(SpriteUtils.loadImage("assets/images/player-spells/a/thunder_spell.png"));

    private Vector2D velocity;

    public PlayerSpell() {
        super();
        velocity = new Vector2D(0, -10);
        ability = new Ability();
        type = 0;
        setDefault(type);
        this.boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
        constraints = new Constraints(0, Setting.instance.getGamePlayHeight(),0, Setting.instance.getGamePlayWidth());
    }

    public void setDefault(int type){
        switch (type){
            case 0 :
                renderer = normalSpell;
                ability.set(2,3,0);
                break;
            case 1 :
                renderer = thunderSpell;
                ability.set(3,5, 0);
                break;
        }
    }

    public void setVelocity(int x, int y) {
        this.velocity.set(x,y);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addThis(velocity);
        hitEnemy();
        if (constraints.isOut(this.screenPosition)){
            this.setActive(false);
        }
    }

    private void hitEnemy() {

        Enemy enemy = Physics.collideWith(this.boxCollider, Enemy.class);
        if (enemy != null) {
            enemy.getHit(ability.damage);
            this.isActive = false;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
