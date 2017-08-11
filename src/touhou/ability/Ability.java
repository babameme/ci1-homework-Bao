package touhou.ability;

public class Ability {
    private int damage;
    private int health;

    public Ability(int damage, int health) {
        this.damage = damage;
        this.health = health;
    }

    public Ability() {
        this.damage = 0;
        this.health = 0;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
