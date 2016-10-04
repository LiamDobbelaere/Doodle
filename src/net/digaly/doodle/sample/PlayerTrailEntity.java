package net.digaly.doodle.sample;

import net.digaly.doodle.Entity;
import net.digaly.doodle.Sprite;
import net.digaly.doodle.events.FrameUpdateListener;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class PlayerTrailEntity extends Entity implements FrameUpdateListener
{
    private int framesPassed = 0;

    public PlayerTrailEntity(Sprite sprite, double x, double y, int angle)
    {
        super(sprite, x, y);
        setAngle(angle);
        setAlpha(0.2);
    }


    @Override
    public void onFrameUpdate()
    {
        framesPassed += 1;
        setAlpha(getAlpha() - 0.01);

        if (framesPassed > 10)
        {
            destroy();
        }
    }
}
