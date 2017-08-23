package touhou.ability;

public class Ability {
    public int health, damage, power;

    public Ability() {
        this.health = 1;
        this.damage = 1;
        this.power = 0;
    }

    public Ability(int health, int damage, int power) {
        this.health = health;
        this.damage = damage;
        this.power = power;
    }

    public void hurt(int damage){
        this.health -= damage;
        if (this.health < 0){
            this.health = 0;
        }
    }
    public void add(Ability other){
        this.health += other.health;
        //this.damage += other.damage;
        this.power += other.power;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void reducePower(int t){
        this.power -= t;
        if (this.power < 0){
            this.power = 0;
        }
    }

    public void set(int a, int b, int c){
        health = a;
        damage = b;
        power = c;
    }
}
