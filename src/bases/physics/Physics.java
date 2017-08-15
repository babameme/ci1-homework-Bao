package bases.physics;

import touhou.enemies.Enemy;
import touhou.item.Item;
import touhou.players.Player;

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

    public static Player collideWithPlayer(BoxCollider boxCollider){
        for (PhysicsBody body : bodies){
            if (body.isActive()){
                if (body instanceof Player && body.getBoxCollider().intersect(boxCollider)){
                    return (Player) body;
                }
            }
        }
        return  null;
    }

    public static Item collideWithItem(BoxCollider boxCollider){
        for (PhysicsBody body : bodies){
            if (body.isActive()){
                if (body instanceof Item && body.getBoxCollider().intersect(boxCollider)){
                    return (Item) body;
                }
            }
        }
        return  null;
    }

    public static void add(PhysicsBody body) {
        bodies.add(body);
    }
}
