package touhou;

import bases.GameObject;
import tklibs.SpriteUtils;
import bases.Constraints;
import touhou.background.Background;
import touhou.enemies.EnemySpawner;
import touhou.inputs.InputManager;
import touhou.items.ItemSpawner;
import touhou.players.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

//https://github.com/qhuydtvt/ci1-huynq

/**
 * Created by huynq on 7/29/17.
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;

    private BufferedImage backbufferImage;
    private Graphics2D backbufferGraphics;

    private Background background;
    Font font;
    Player player = new Player();

    InputManager inputManager = new InputManager();

    public GameWindow() {
        pack();
        addBackground();
        addPlayer();
        addEnemySpawner();
        addItemSpawner();
        setupGameLoop();
        setupWindow();
    }

    private void addItemSpawner() {
        ItemSpawner itemSpawner = new ItemSpawner();
        GameObject.add(itemSpawner);
    }

    private void addEnemySpawner() {
        EnemySpawner enemySpawner = new EnemySpawner();
        GameObject.add(enemySpawner);
    }

    private void addBackground() {
        background = new Background();
        GameObject.add(background);
    }

    private void addPlayer() {
        player.setInputManager(this.inputManager);
        player.setContraints(new Constraints(getInsets().top, 768, getInsets().left, 384));
        player.getPosition().set(384 / 2, 580);

        GameObject.add(player);
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(1024, 768);

        this.setTitle("Touhou - Remade by QHuyDTVT");
        this.setVisible(true);

        this.backbufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.backbufferGraphics = (Graphics2D) this.backbufferImage.getGraphics();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });
        backbufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        font = new Font("Serif", Font.PLAIN, 21);
        backbufferGraphics.setFont(font);
        backbufferGraphics.setColor(Color.white);
    }

    public void loop() {
        while(true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.nanoTime();
            }
            currentTime = System.nanoTime();
            if (currentTime - lastTimeUpdate > 17000000) {
                run();
                render();
                lastTimeUpdate = currentTime;
            }
        }
    }

    private void run() {
        GameObject.runAll();
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(backbufferImage, 0, 0, null);
    }
    private void render() {

        backbufferGraphics.setColor(Color.black);
        backbufferGraphics.fillRect(0, 0, 1024, 768);

        GameObject.renderAll(backbufferGraphics);

        backbufferGraphics.drawString("Your health : " + Integer.toString(player.getAbility().health), 400, 50);
        backbufferGraphics.drawString("Your damage : " + Integer.toString(player.getAbility().damage), 400, 90);
        backbufferGraphics.drawString("Your power : " + Integer.toString(player.getAbility().power), 400, 130);

        if (player.getAbility().health <= 0){
            backbufferGraphics.drawString("GAMEOVER", 50, 130);
        }
        repaint(); // ask to repaint
    }
}
