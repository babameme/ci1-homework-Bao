package touhou.settings;

import java.awt.*;

/**
 * Created by huynq on 8/19/17.
 */
public class Setting {
    private int windowWidth;
    private int windowHeight;

    private int gamePlayWidth;
    private int gamePlayHeight;

    private Insets windowInsets;

    public static final Setting instance = new Setting();

    private Setting(int windowWidth, int windowHeight, int gamePlayWidth, int gamePlayHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.gamePlayWidth = gamePlayWidth;
        this.gamePlayHeight = gamePlayHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getGamePlayWidth() {
        return gamePlayWidth;
    }

    public int getGamePlayHeight() {
        return gamePlayHeight;
    }

    public Insets getWindowInsets() {
        return windowInsets;
    }

    public void setWindowInsets(Insets windowInsets) {
        this.windowInsets = windowInsets;
    }

    private Setting() {
        this(1024, 768, 384, 768);
    }
}
