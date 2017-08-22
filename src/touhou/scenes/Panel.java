package touhou.scenes;

import bases.GameObject;
import bases.Vector2D;
import tklibs.SpriteUtils;
import touhou.ability.Ability;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends GameObject{
    private Ability ability;
    private BufferedImage healthImage0, healthImage1, powerImage0, powerImage1;
    int health0, health1, power0, power1;

    public Panel(){

    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        health0 = ((int) ability.health) / 10;
        health1 = ((int) ability.health) % 10;
        power0 = ((int) ability.power) / 10;
        power1 = ((int) ability.power) % 10;
        healthImage0 = SpriteUtils.loadImage("assets/images/hud/numbers/" + Integer.toString(health0)+ ".png");
        healthImage1 = SpriteUtils.loadImage("assets/images/hud/numbers/" + Integer.toString(health1)+ ".png");
        powerImage0 = SpriteUtils.loadImage("assets/images/hud/numbers/" + Integer.toString(power0)+ ".png");
        powerImage1 = SpriteUtils.loadImage("assets/images/hud/numbers/" + Integer.toString(power1)+ ".png");
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        g2d.drawImage(healthImage0, 810, 261, null);
        g2d.drawImage(healthImage1, 830, 261, null);
        g2d.drawImage(powerImage0, 810, 306, null);
        g2d.drawImage(powerImage1, 830, 306, null);
    }
}
