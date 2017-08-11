package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;

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
    public void run() {
        //System.out.println("Spawn");
        super.run();
        if (spawnCounter.run()) {
            //System.out.println("Add enemy");
            spawnCounter.reset();
            Enemy enemy = new Enemy();
            enemy.getPosition().set(random.nextInt(384), 40);
            GameObject.add(enemy);
            BulletSpawner bulletSpawner = new BulletSpawner();
            bulletSpawner.setPosition(enemy.getPosition());
            GameObject.add(bulletSpawner);
        }
    }
}
