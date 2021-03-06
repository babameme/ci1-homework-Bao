package bases;

import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import bases.renderers.Renderer;
import touhou.players.Player;
import touhou.players.PlayerSpell;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by huynq on 8/9/17.
 */
public class GameObject {
    protected Vector2D position;
    protected Vector2D screenPosition;

    protected Renderer renderer;

    protected ArrayList<GameObject> children;
    protected boolean isActive;
    private boolean isRenewing;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObjects = new Vector<>();

    public static void runAll() {

        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive)
                gameObject.run(new Vector2D(0, 0)); // TODO: Optimize
        }

        for (GameObject newGameObject : newGameObjects) {
            if (newGameObject instanceof PhysicsBody) {
                Physics.add((PhysicsBody)newGameObject);
            }
        }

        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }

    public static void renderAll(Graphics2D g2d) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive && !gameObject.isRenewing)
                gameObject.render(g2d);
        }
    }

    public static void add(GameObject gameObject) {
        newGameObjects.add(gameObject);
    }

    public GameObject() {
        children = new ArrayList<>();
        position = new Vector2D();
        screenPosition = new Vector2D();
        isActive = true;
    }

    public void run(Vector2D parentPosition) {
        screenPosition = parentPosition.add(position);
        isRenewing = false;
        for (GameObject child: children) {
            if (child.isActive)
                child.run(screenPosition);
        }
    }

    public void render(Graphics2D g2d) {
        if (renderer != null) {
            renderer.render(g2d, screenPosition);
        }

        for (GameObject child: children) {
            if (child.isActive)
                child.render(g2d);
        }
    }

    public static <T extends GameObject> T minDistanceWith(Vector2D position, Class<T> classz){
        float minDis = 1000000000, t;
        T result = null;
        for (GameObject gameObject : gameObjects){
            if (gameObject.isActive()){
                if (gameObject.getClass().equals(classz)) {
//                    if (gameObject.getScreenPosition().y > position.y){
//                        continue;
//                    }
                    t = gameObject.getScreenPosition().distance(position);
                    if (t < minDis) {
                        minDis = t;
                        result = (T) gameObject;
                    }
                }
            }
        }
        return result;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        if (position != null)
            this.position = position;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Vector2D getScreenPosition() {
        return screenPosition;
    }

    public void setRenderer(Renderer renderer) {
        if (renderer != null)
            this.renderer = renderer;
    }
    public void reset(){
        this.isActive = true;
        this.isRenewing = true;
    }

    public static void deleteAll(){
        gameObjects.clear();
        newGameObjects.clear();
    }
}
