package net.digaly.doodle.sample;

import javafx.scene.effect.Bloom;
import net.digaly.doodle.DoodleApplication;

/**
 * Created by Tom Dobbelaere on 1/10/2016.
 */
public class DoodleTest
{
    public static void main(String[] args) {
        DoodleGame myGame = new DoodleGame();
        DoodleApplication app = DoodleApplication.getInstance();

        app.getEventDispatcher().addApplicationReadyListener(myGame);
        app.setTitle("Doodle Sample Game");
        app.setIcon("icon.png");
        //app.setRenderEffect(new Bloom(0.2));
        app.setDrawColliders(true);
        app.setFullscreen(true);

        app.run();
    }
}
