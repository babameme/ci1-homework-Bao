package touhou.ability;

public class Ability {
    private int damage;
    private int health;
    private int power;

    public Ability(int damage, int health) {
        this.damage = damage;
        this.health = health;
        this.power = 0;
    }

    public Ability(int damage, int health, int power) {
        this.damage = damage;
        this.health = health;
        this.power = power;
    }

    public Ability() {
        this.damage = 0;
        this.health = 0;
        this.power = 0;
    }

    public void copy(Ability other){
        this.health = other.health;
        this.damage = other.damage;
        this.power = other.power;
    }
    public void hurtHealth(int t){
        this.health -= t;
        if (this.health < 0)
            this.health = 0;
    }

    public void addUp(Ability other){
        this.health += other.getHealth();
        this.power += other.getPower();
        this.damage += other.getDamage();
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

    public void set(int damage, int health){
        this.damage = damage;
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Ability{" +
                "damage=" + damage +
                ", health=" + health +
                ", power=" + power +
                '}';
    }
}
