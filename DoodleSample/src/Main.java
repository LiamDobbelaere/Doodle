import net.digaly.doodle.ApplicationSettings;
import net.digaly.doodle.DoodleApplication;

/**
 * Created by Tom Dobbelaere on 11/10/2016.
 */
public class Main extends DoodleApplication
{
    public static void main(String[] args) {
        settings.setTitle("Doodle Sample");
        settings.setDebugMode(false);
        settings.setFullscreen(true);


        launch(args);
    }
}
