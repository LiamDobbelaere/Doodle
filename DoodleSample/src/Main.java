import javafx.scene.effect.Bloom;
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
        NodeBasedRenderer renderer = new NodeBasedRenderer();
        renderer.setEffect(new Bloom(0.2));
        settings.setRenderer(renderer);

        /*settings.setDebugMode(false);
        settings.setFullscreen(true);*/

        launch(args);
    }

    @Override
    public void onApplicationReady()
    {
        Room myRoom = new Room(1024, 768);
        setCurrentRoom(myRoom);

        Entity background = new BackgroundEntity(new Sprite());
        background.setSprite(new Sprite("space.png"));
        myRoom.addEntity(background);

        Entity test = new PlayerEntity(64, 64);
        myRoom.addEntity(test);
        myRoom.addEntity(new GeomEntity(128, 128));
        myRoom.addEntity(new GeomEntity(140, 128));
        myRoom.addEntity(new GeomEntity(130, 170));
        myRoom.addEntity(new GeomEntity(140, 150));

        Entity waveIntro = new WaveIntro("STAGE 1", "Do nothing you scrub");
        //myRoom.addEntity(waveIntro);
    }
}
