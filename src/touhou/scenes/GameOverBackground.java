package touhou.scenes;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class GameOverBackground extends GameObject{
    private ImageRenderer imageRenderer;
    public GameOverBackground(){
        imageRenderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/scenes/game_over.png"));
        this.imageRenderer.getAnchor().set(0,0);
        this.position.set(0,0);
        this.renderer = imageRenderer;
        System.out.println("Add background");
    }
}
