package touhou.scenes;

import bases.GameObject;

public class GameOverScene{
    GameOverBackground background;
    public GameOverScene(){
        addBackground();
    }

    private void addBackground() {
        background = new GameOverBackground();
        GameObject.add(background);
    }
}
