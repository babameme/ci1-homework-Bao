package touhou.spheres;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import touhou.ability.Ability;

public class PlayerSphere extends GameObject {
    Animation animation;
    private Ability ability;

    public PlayerSphere(){
        super();
        this.animation = new Animation(
                SpriteUtils.loadImage("assets/images/sphere/0.png"),
                SpriteUtils.loadImage("assets/images/sphere/1.png"),
                SpriteUtils.loadImage("assets/images/sphere/2.png"),
                SpriteUtils.loadImage("assets/images/sphere/3.png")
        );
        this.renderer = animation;
        ability = new Ability(2, 1, 0);
        addSpellSpawner();
    }

    public void setReverse(boolean reverse){
        this.animation.setReverse(reverse);
    }

    private void addSpellSpawner() {
        SpellSpawner spellSpawner = new SpellSpawner();
        children.add(spellSpawner);
    }
    
}
