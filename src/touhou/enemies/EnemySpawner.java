package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.Physics;
import bases.pools.GameObjectPool;
import touhou.scenes.Background;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * Created by huynq on 8/9/17.
 */
public class EnemySpawner extends GameObject{
    private FrameCounter spawnCounter;
    private Random random;
    private Background background;
    private boolean addedBoss;

    public EnemySpawner() {
        spawnCounter = new FrameCounter(90);
        random = new Random();
        addedBoss = false;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (!background.isStopped()) {
            if (spawnCounter.run()) {
                spawnCounter.reset();
                Enemy enemy = GameObjectPool.recycle(Enemy.class);
                enemy.getPosition().set(random.nextInt(384), 100);
                enemy.setDefault(enemy.getType());
            }
        }else if (!addedBoss){
            //System.out.println("Boss added");
            Enemy enemy = new Enemy(2);
            enemy.getPosition().set(192, 60);
            addedBoss = true;
            GameObject.add(enemy);
        }
    }
}
