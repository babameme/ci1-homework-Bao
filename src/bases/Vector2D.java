package bases;

/**
 * Created by huynq on 8/2/17.
 */
public class Vector2D {
    public float x;
    public float y;

    public Vector2D() {
        this(0, 0);
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D clone() {
        return new Vector2D(this.x, this.y);
    }

    public Vector2D set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2D set(Vector2D other) {
        return set(other.x, other.y);
    }

    public Vector2D add(float dx, float dy) {
        return new Vector2D(this.x + dx, this.y + dy);
    }

    public Vector2D add(Vector2D other) {
        return add(other.x, other.y);
    }

    public Vector2D addThis(float dx, float dy) {
        this.x += dx;
        this.y += dy;
        return this;
    }

    public Vector2D addThis(Vector2D other) {
        return addThis(other.x, other.y);
    }

    public Vector2D subtract(float dx, float dy) {
        return new Vector2D(x - dx, y - dy);
    }

    public Vector2D subtract(Vector2D other) {
        return subtract(other.x, other.y);
    }

    public Vector2D subtracttThis(Vector2D other) {
        return subtractThis(other.x, other.y); // tro xuong ham duoi
    }

    public Vector2D subtractThis(float dx, float dy) {
        this.x -= dx;
        this.y -= dy;
        return this;
    }

    public Vector2D multiply(float f) {
        return this.clone().multiplyThis(f);
    }

    public Vector2D multiplyThis(float f) {
        return this.set(this.x * f, this.y * f);
    }

    public float magnitude() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2D normalize() {
        float mag = magnitude();
        return new Vector2D(this.x / mag, this.y / mag);
    }

    public Vector2D normalizeThis() {
        float mag = magnitude();
        return this.set(this.x / mag, this.y / mag);
    }

    public float distance(Vector2D other){
        return (float) Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
