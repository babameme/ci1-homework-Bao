package bases.physics;

import touhou.enemies.Enemy;

import java.util.Vector;

public class Physics {
    private static Vector<PhysicsBody> bodies = new Vector<>();
    public static Enemy collideWithEnemy(BoxCollider boxCollider){
        for (PhysicsBody body : bodies){
            if (body.isActive()) {
                if (body instanceof Enemy && body.getBoxCollider().intersect(boxCollider)) {
                    return (Enemy) body; // TODO : Lam va cham mot luc nhieu cai
                }
            }
        }
        return null;
    }

    public static void add(PhysicsBody body) {
        bodies.add(body);
    }
}
