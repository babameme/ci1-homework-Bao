package touhou.scenes;

import bases.GameObject;
import bases.Vector2D;
import tklibs.SpriteUtils;
import touhou.ability.Ability;
import touhou.players.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends GameObject{
    private Ability ability;
    private BufferedImage healthImage0, healthImage1, powerImage0, powerImage1, scoreImage0, scoreImage1, scoreImage2;
    int health0, health1, power0, power1, score0 , score1, score2;

    public Panel(){

    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        ability.health = ability.health > 99 ? 99 : ability.health;
        ability.power = ability.power > 99 ? 99 : ability.power;
        Player.score = Player.score > 999 ? 999 : Player.score;

        health0 = ((int) ability.health) / 10;
        health1 = ((int) ability.health) % 10;

        power0 = ((int) ability.power) / 10;
        power1 = ((int) ability.power) % 10;

        score0 = ((int) Player.score) / 100;
        score1 = (((int) Player.score) / 10) % 10;
        score2 = ((int) Player.score) % 10;

        healthImage0 = SpriteUtils.loadImage("assets/images/hud/numbers/" + Integer.toString(health0)+ ".png");
        healthImage1 = SpriteUtils.loadImage("assets/images/hud/numbers/" + Integer.toString(health1)+ ".png");
        powerImage0 = SpriteUtils.loadImage("assets/images/hud/numbers/" + Integer.toString(power0)+ ".png");
        powerImage1 = SpriteUtils.loadImage("assets/images/hud/numbers/" + Integer.toString(power1)+ ".png");
        scoreImage0 = SpriteUtils.loadImage("assets/images/hud/numbers/" + Integer.toString(score0)+ ".png");
        scoreImage1 = SpriteUtils.loadImage("assets/images/hud/numbers/" + Integer.toString(score1)+ ".png");
        scoreImage2 = SpriteUtils.loadImage("assets/images/hud/numbers/" + Integer.toString(score2)+ ".png");
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        g2d.drawImage(healthImage0, 810, 261, null);
        g2d.drawImage(healthImage1, 830, 261, null);
        g2d.drawImage(powerImage0, 810, 306, null);
        g2d.drawImage(powerImage1, 830, 306, null);
        g2d.drawImage(scoreImage0, 810, 369, null);
        g2d.drawImage(scoreImage1, 830, 369, null);
        g2d.drawImage(scoreImage2, 850, 369, null);
    }
}
