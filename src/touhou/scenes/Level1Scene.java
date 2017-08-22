package touhou.scenes;

import bases.Constraints;
import bases.GameObject;
import touhou.enemies.EnemySpawner;
import touhou.inputs.InputManager;
import touhou.items.ItemSpawner;
import touhou.players.Player;
import touhou.settings.Setting;

public class Level1Scene {
    Player player;
    Background background;
    EnemySpawner enemySpawner;
    ItemSpawner itemSpawner;
    Panel panel;

    public void init(){
        addBackground();
        addPlayer();
        addPanel();
        addEnemySpawner();
        addItemSpawner();
    }

    private void addPanel() {
        panel = new Panel();
        panel.setAbility(player.getAbility());
        GameObject.add(panel);
    }

    private void addItemSpawner() {
        itemSpawner = new ItemSpawner();
        GameObject.add(itemSpawner);
    }

    private void addEnemySpawner() {
        enemySpawner = new EnemySpawner();
        GameObject.add(enemySpawner);
        enemySpawner.setBackground(background);
    }

    private void addPlayer() {
        player = new Player();
        player.setInputManager(InputManager.instance);
        player.setContraints(new Constraints(
                Setting.instance.getWindowInsets().top,
                Setting.instance.getGamePlayHeight(),
                Setting.instance.getWindowInsets().left,
                Setting.instance.getGamePlayWidth())
        );
        player.getPosition().set(
                Setting.instance.getGamePlayWidth() / 2,
                Setting.instance.getGamePlayHeight() * 3 / 4
        );
        GameObject.add(player);
    }

    private void addBackground() {
        background = new Background();
        GameObject.add(background);
    }
}
