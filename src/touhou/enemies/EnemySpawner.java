package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * Created by huynq on 8/9/17.
 */
public class EnemySpawner extends GameObject{
    private FrameCounter spawnCounter;
    private Random random;

    public EnemySpawner() {
        spawnCounter = new FrameCounter(100);
        random = new Random();
    }

    @Override
    public void run(Vector2D parentPosition) {
        //System.out.println("Spawn");
        super.run(parentPosition);
        if (spawnCounter.run()) {
            //System.out.println("Add enemy");
            spawnCounter.reset();
            Enemy enemy = new Enemy(random.nextInt(2));
            enemy.getPosition().set(random.nextInt(384), 20);
            GameObject.add(enemy);

            BulletSpawner bulletSpawner = new BulletSpawner();
            enemy.getChildren().add(bulletSpawner);
        }
    }
}
