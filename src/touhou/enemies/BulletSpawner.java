package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;

import java.util.Random;

public class BulletSpawner extends GameObject{
    private FrameCounter spawnCounter;
    Random random;
    public BulletSpawner() {
        super();
        spawnCounter = new FrameCounter(60);
        random = new Random();
    }

    public BulletSpawner(int maxCounter) {
        super();
        spawnCounter = new FrameCounter(maxCounter);
        random = new Random();
    }

    public void cast(int x, int y, int dx, int dy){
        EnemyBullet enemyBullet = new EnemyBullet();
        enemyBullet.getPosition().set(screenPosition.add(x,y));
        enemyBullet.getDirection().set(dx, dy);
        GameObject.add(enemyBullet);
    }

    public void castType1(){
        for (int i = -3; i <=3; i++) {
            if (i == 0) continue;
            cast(i * 10, 20, i * 2, 0);
        }
    }

    public void castType2(){
        for (int i = -3; i <=3 ; i++) {
            if (i == 0) continue;
            cast(i * 10, -20, i * 2, 0);
        }
    }

    public void castType3(){
        for (int i = -3; i <=3 ; i++) {
            if (i == 0) continue;
            cast(-20, i * 10, 0, i * 2);
        }
    }

    public void castType4(){
        for (int i = -3; i <=3 ; i++) {
            if (i == 0) continue;
            cast(20, i * 10, 0, i * 2);
        }
    }

    public void castType5(){
        for (int i = -2; i <= 2 ; i++) {
            for (int j = -2; j <= 2 ; j++) {
                if (Math.abs(i) != 2 && Math.abs(j) != 2) continue;
                cast(i * 10, j * 10, i , j);
            }
        }
    }

    public void castType6(){
        for (int i = -3; i <= 3; i++){
            cast(i * 10, 20, i, 2);
        }
    }

    public void castType0(){
        for (int i = -3; i <= 3; i++){
            cast(i * 10, -20, i, -2);
        }
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (spawnCounter.run()) {
            spawnCounter.reset();
            switch (random.nextInt(7)){
                case 0:
                    castType0();
                    break;
                case 1:
                    castType1();
                    break;
                case 2:
                    castType2();
                    break;
                case 3:
                    castType3();
                    break;
                case 4:
                    castType4();
                    break;
                case 5:
                    castType5();
                    break;
                case 6:
                    castType6();
                    break;
            }
        }
    }

}
