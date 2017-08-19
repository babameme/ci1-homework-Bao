package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.pools.GameObjectPool;

import java.util.Random;

public class BulletSpawner extends GameObject{
    private FrameCounter spawnCounter;
    Random random;

    public BulletSpawner(){
        super();
        spawnCounter = new FrameCounter(30);
        random = new Random();
    }

    public BulletSpawner(int maxCounter){
        super();
        spawnCounter = new FrameCounter(maxCounter);
        random = new Random();
    }

    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        if (spawnCounter.run()){
            spawnCounter.reset();
            cast();
        }
    }

    private void cast() {
        EnemyBullet enemyBullet = GameObjectPool.recycle(EnemyBullet.class);
        enemyBullet.getPosition().set(this.screenPosition.add(0, 30));
    }
}
