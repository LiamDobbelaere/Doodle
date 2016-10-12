import net.digaly.doodle.*;
import net.digaly.doodle.rendering.NoRenderer;
import net.digaly.doodle.rendering.NodeBasedRenderer;

/**
 * Created by Tom Dobbelaere on 11/10/2016.
 */
public class Main extends DoodleApplication
{
    public static void main(String[] args) {
        settings.setTitle("Doodle Sample");
        settings.setRenderer(new NodeBasedRenderer());

        /*settings.setDebugMode(false);
        settings.setFullscreen(true);*/

        launch(args);
    }

    @Override
    public void onApplicationReady()
    {
        Room myRoom = new Room(640, 480);
        setCurrentRoom(myRoom);

        Entity test = new PlayerEntity(64, 64);
        myRoom.addEntity(test);
    }
}
