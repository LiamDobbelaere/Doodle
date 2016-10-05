package net.digaly.doodle.sample;

import net.digaly.doodle.DoodleApplication;
import net.digaly.doodle.Entity;
import net.digaly.doodle.Sprite;
import net.digaly.doodle.events.CollisionEventListener;
import net.digaly.doodle.events.FrameUpdateListener;

/**
 * Created by Tom Dobbelaere on 3/10/2016.
 */
public class BulletEntity extends Entity implements FrameUpdateListener, CollisionEventListener
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

    @Override
    public void onCollision(Entity other)
    {
        if (other instanceof EnemyEntity) {
            DoodleApplication.getInstance().getSoundManager().playSound("res\\hit.wav", 0.2);
            ((EnemyEntity) other).damage(1);
            destroy();
        }

        /*if (other instanceof DefensePostEntity) {
            DoodleApplication.getInstance().getSoundManager().playSound("res\\hit.wav", 0.2);
            ((DefensePostEntity) other).damage(1);
            destroy();
        }*/
    }
}
