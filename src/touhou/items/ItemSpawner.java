package touhou.items;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.pools.GameObjectPool;
import com.sun.javafx.geom.Vec2d;

import java.util.Random;

public class ItemSpawner extends GameObject{
    private FrameCounter spawnCounter;
    private Random random;

    public ItemSpawner() {
        super();
        spawnCounter = new FrameCounter(250);
        random = new Random();
    }

    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        if (spawnCounter.run()){
            spawnCounter.reset();
            for (int i = 0; i < 3; i++) {
                Item item = GameObjectPool.recycle(Item.class);
                item.getPosition().set(random.nextInt(384), 20);
            }
        }
    }
}