package touhou.spheres;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.pools.GameObjectPool;

public class SpellSpawner extends GameObject{
    private FrameCounter spawnCounter;

    public SpellSpawner() {
        super();
        spawnCounter = new FrameCounter(10);
    }

    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        if (spawnCounter.run()){
            spawnCounter.reset();
            cast();
        }
    }

    private void cast(){
        SphereSpell sphereSpell = GameObjectPool.recycle(SphereSpell.class);
        sphereSpell.getPosition().set(this.screenPosition);
    }
}
