import net.digaly.doodle.Entity;
import net.digaly.doodle.Sprite;
import net.digaly.doodle.events.FrameUpdateListener;

/**
 * Created by Tom Dobbelaere on 5/10/2016.
 */
public class TrailEntity extends Entity implements FrameUpdateListener
{
    int framesPassed;
    private double alphaDescent;

    public TrailEntity(Sprite sprite, double x, double y, int angle, double startAlpha, double alphaDescent)
    {
        super(sprite, x, y);
        setAngle(angle);
        setAlpha(startAlpha);
        setDepth(-100);
        this.alphaDescent = alphaDescent;
    }

    @Override
    public void onFrameUpdate()
    {
        framesPassed += 1;
        setAlpha(getAlpha() - alphaDescent);

        if (framesPassed > 10)
        {
            destroy();
        }
    }
}
