package bases;

import bases.renderers.ImageRenderer;
import touhou.players.PlayerSpell;

import java.awt.*;
import java.util.Vector;

/**
 * Created by huynq on 8/9/17.
 */
public class GameObject {
    protected Vector2D position;
    protected ImageRenderer renderer;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObjects = new Vector<>();

    public static void runAll() {
        // instanceof
        for (GameObject gameObject : gameObjects) {
            gameObject.run();
        }

        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }

    public static void renderAll(Graphics2D g2d) {
        for (GameObject gameObject : gameObjects) {
            gameObject.render(g2d);
        }
    }

    public static void add(GameObject gameObject) {
        newGameObjects.add(gameObject);
    }

    public GameObject() {
        position = new Vector2D();
    }

    public void run() {

    }

    public void render(Graphics2D g2d) {
        if (renderer != null) {
            renderer.render(g2d, position); // null.render() => NullPointerException
        }
    }

    public boolean collision(GameObject other){
        float left1 = this.position.x - this.renderer.image.getWidth()/2;
        float right1 = this.position.x + this.renderer.image.getWidth()/2;
        float top1 = this.position.y - this.renderer.image.getHeight()/2;
        float bottom1 = this.position.y + this.renderer.image.getHeight()/2;
        float left2 = other.position.x - other.renderer.image.getWidth()/2;
        float right2 = other.position.x + other.renderer.image.getWidth()/2;
        float top2 = other.position.y - other.renderer.image.getHeight()/2;
        float bottom2 = other.position.y + other.renderer.image.getHeight()/2;
        return !(right1 < left2 || right2 < left1 || bottom1 < top2 || bottom2 < top1);

    }
    // Setter and Getter
    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        if (position != null)
            this.position = position;
    }

    public ImageRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(ImageRenderer renderer) {
        if (renderer != null)
            this.renderer = renderer;
    }
}
