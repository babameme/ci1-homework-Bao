package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.pools.GameObjectPool;

import java.util.Random;

public class BulletSpawner extends GameObject{
    private FrameCounter spawnCounter;
    Random random;
    private int type;

    public BulletSpawner(int type){
        super();
        spawnCounter = new FrameCounter(120);
        random = new Random();
        this.type = type;
    }

    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        if (spawnCounter.run()){
            spawnCounter.reset();
            switch (type){
                case 0 :
                    cast0();
                    break;
                case 1 :
                    cast1();
                    break;
                case 2 :
                    //cast2();
                    break;
            }
        }
    }

    private void cast1() {
        for (int i = 1; i <=2 ; i++) {
            cast(i * 30, 0, -3, 0);
            cast(i * -30, 0, 3, 0);
            cast(0, i * 30, 0, -3);
            cast(0, i * -30, 0, 3);
            cast(i * -30, i * -30, 3, 3);
            cast(i * 30, i * 30, -3, - 3);
            cast (i * 30, i * -30, -3, 3);
            cast(i * -30, i * 30, 3, -3);
        }
    }

    private void cast0() {
        for (int i = -2; i <=2 ; i++) {
            cast(-60, i * 30, 3, -i);
            cast(60, i * 30, -3, -i);
            cast(i * 30, 60, -i, -3);
            cast(i * 30, -60, -i, 3);
        }
    }

    private void cast2(){

    }

    private void cast(int x, int y, int dx, int dy) {
        EnemyBullet enemyBullet = GameObjectPool.recycle(EnemyBullet.class);
        enemyBullet.getPosition().set(this.screenPosition.add(x, y));
        enemyBullet.getVelocity().set(dx, dy);
    }
}
