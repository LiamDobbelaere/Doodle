package net.digaly.doodle.sample;

import net.digaly.doodle.Entity;
import net.digaly.doodle.Sprite;
import net.digaly.doodle.events.FrameUpdateListener;

/**
 * Created by Tom Dobbelaere on 3/10/2016.
 */
public class BulletEntity extends Entity implements FrameUpdateListener
{
    private int speed;
    private int frames;

    public BulletEntity(Sprite sprite, double x, double y, int angle, int speed)
    {
        super(sprite, x, y);
        setAngle(angle);
        this.speed = speed;
        this.frames = 0;
    }

    @Override
    public void onFrameUpdate()
    {
        getPosition().translate(Math.cos(getAngle() * 0.017) * speed, Math.sin(getAngle() * 0.017) * speed);

        frames++;

        setAlpha(getAlpha() - 0.01);

        if (getAlpha() <= 0) destroy();
    }
}
