package touhou.spheres;

import bases.GameObject;
import bases.renderers.Animation;
import tklibs.SpriteUtils;

public class PlayerSphere extends GameObject {
    Animation animation;

    public PlayerSphere(){
        super();
        this.animation = new Animation(
                SpriteUtils.loadImage("assets/images/sphere/0.png"),
                SpriteUtils.loadImage("assets/images/sphere/1.png"),
                SpriteUtils.loadImage("assets/images/sphere/2.png"),
                SpriteUtils.loadImage("assets/images/sphere/3.png")
        );
        this.renderer = animation;
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
