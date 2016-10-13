import javafx.scene.canvas.GraphicsContext;
import net.digaly.doodle.Entity;
import net.digaly.doodle.Point;
import net.digaly.doodle.Room;
import net.digaly.doodle.Sprite;
import net.digaly.doodle.events.FrameDrawListener;

/**
 * Created by Tom Dobbelaere on 13/10/2016.
 */
public class BackgroundEntity extends Entity implements FrameDrawListener
{

    public BackgroundEntity(Sprite sprite)
    {
        super(sprite, 0, 0);
        setDepth(Integer.MIN_VALUE);
    }

    @Override
    public void onFrameDraw(GraphicsContext gc)
    {
        Room currentRoom = getRoom();
        /*setPosition(new Point(currentRoom.getRenderer().getCamera().getTranslateX() + getSprite().getImage().getWidth() / 2,
                currentRoom.getRenderer().getCamera().getTranslateY() + + getSprite().getImage().getHeight() / 2));
*/
        gc.drawImage(getSprite().getImage(), 0, 0, getWidth(), getHeight());
    }
}
