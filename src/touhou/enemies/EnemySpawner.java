package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.Physics;
import bases.pools.GameObjectPool;
import touhou.ability.Ability;
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
    public static boolean addedBoss;
    public static Ability bossAbility;

    public EnemySpawner() {
        spawnCounter = new FrameCounter(100);
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
                enemy.getPosition().set(random.nextInt(384), 30);
                enemy.setDefault(enemy.getType());
            }
        }else if (!addedBoss){
            //System.out.println("Boss added");
            Enemy enemy = new Enemy(2);
            enemy.getPosition().set(370, 60);
            addedBoss = true;
            bossAbility = enemy.getAbility();
            GameObject.add(enemy);
        }
    }
}
