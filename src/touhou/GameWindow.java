package touhou;

import bases.GameObject;
import tklibs.SpriteUtils;
import bases.Constraints;
import touhou.enemies.EnemySpawner;
import touhou.inputs.InputManager;
import touhou.items.ItemSpawner;
import touhou.players.Player;
import touhou.scenes.Level1Scene;
import touhou.settings.Setting;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

import static javax.swing.text.StyleConstants.Background;

//https://github.com/qhuydtvt/ci1-huynq

/**
 * Created by huynq on 7/29/17.
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;

    private BufferedImage blackBackground;

    private BufferedImage backbufferImage;
    private Graphics2D backbufferGraphics;

    Font font;
    Player player = new Player();

    InputManager inputManager = InputManager.instance;
    Level1Scene level1Scene;

    public GameWindow() {
        pack();
        setupGameLoop();
        setupWindow();
        setupLevel();
    }

    private void setupLevel() {
        level1Scene = new Level1Scene();
        level1Scene.init();
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(Setting.instance.getWindowWidth(), Setting.instance.getWindowHeight());

        this.setTitle("Touhou - Remade by Bui Quy Bao");
        this.setVisible(true);

        this.backbufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.backbufferGraphics = (Graphics2D) this.backbufferImage.getGraphics();

        this.blackBackground = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D backgroundGraphics = (Graphics2D) this.blackBackground.getGraphics();
        backgroundGraphics.setColor(Color.BLACK);
        backgroundGraphics.fillRect(0,0,this.getWidth(), this.getHeight());

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
//        backbufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        font = new Font("Serif", Font.PLAIN, 21);
//        backbufferGraphics.setFont(font);
//        backbufferGraphics.setColor(Color.white);

        Setting.instance.setWindowInsets(this.getInsets());
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

    private void render() {

        backbufferGraphics.drawImage(blackBackground, 0, 0, null);
        GameObject.renderAll(backbufferGraphics);

//        backbufferGraphics.drawString("Your health : " + Integer.toString(player.getAbility().health), 400, 50);
//        backbufferGraphics.drawString("Your damage : " + Integer.toString(player.getAbility().damage), 400, 90);
//        backbufferGraphics.drawString("Your power : " + Integer.toString(player.getAbility().power), 400, 130);
//
//        if (player.getAbility().health <= 0){
//            backbufferGraphics.drawString("GAMEOVER", 50, 130);
//        }

        getGraphics().drawImage(backbufferImage, 0, 0, null);
    }
}
