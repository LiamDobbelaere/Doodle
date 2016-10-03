package net.digaly.doodle.sample;

import net.digaly.doodle.DoodleApplication;

/**
 * Created by Tom Dobbelaere on 1/10/2016.
 */
public class DoodleTest
{
    public static void main(String[] args) {
        DoodleGame myGame = new DoodleGame();
        DoodleApplication app = DoodleApplication.getInstance();

        app.addApplicationReadyListener(myGame);
        app.setCurrentRoom(myGame.getCurrentRoom());
        app.setTitle("Doodle Sample Game");
        app.setIcon("icon.png");
        //app.setFullscreen(true);

        app.run();
    }
}
