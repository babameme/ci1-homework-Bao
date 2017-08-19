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
        spawnCounter = new FrameCounter(70);
        random = new Random();
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (spawnCounter.run()) {
            spawnCounter.reset();
            Enemy enemy = new Enemy(random.nextInt(3));
            enemy.getPosition().set(random.nextInt(384), 40);
            GameObject.add(enemy);
        }
    }
}
