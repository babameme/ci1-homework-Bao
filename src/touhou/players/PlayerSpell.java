package touhou.players;

import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.ability.Ability;
import touhou.enemies.Enemy;
import touhou.explosions.Explosion;

/**
 * Created by huynq on 8/2/17.
 */
public class PlayerSpell extends GameObject implements PhysicsBody {

    private BoxCollider boxCollider;
    private Ability ability;

    public PlayerSpell() {
        super();

        this.renderer = new ImageRenderer(SpriteUtils.loadImage(
                "assets/images/player-spells/a/0.png"
        ));
        ability = new Ability(2, 3, 0);
        this.boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addUp(0, -10);
        hitEnemy();
        deactiveIfNeeded();
    }
    //TODO : Fix
    private void deactiveIfNeeded() {
        if (this.screenPosition.y < 0) {
            this.isActive = false;
        }
    }

    private void hitEnemy() {

        Enemy enemy = Physics.collideWith(this.boxCollider, Enemy.class);
        if (enemy != null) {
            enemy.getAbility().hurt(this.ability.damage);
            Explosion explosion = GameObjectPool.recycle(Explosion.class);
            explosion.getPosition().set(enemy.getScreenPosition());
            explosion.getAnimation().setEnded(false);
            if (enemy.getAbility().health == 0){
                enemy.setActive(false);
            }
            this.isActive = false;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
