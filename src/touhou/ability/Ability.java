package touhou.ability;

public class Ability {
    public int health, damage, power;

    public Ability() {
        health = 1;
        damage = 1;
        power = 0;
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
    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
