package net.digaly.doodle.sample;

import net.digaly.doodle.DoodleApplication;
import net.digaly.doodle.Entity;
import net.digaly.doodle.FrameUpdateListener;
import net.digaly.doodle.Sprite;

/**
 * Created by Tom Dobbelaere on 3/10/2016.
 */
public class EnemyEntity extends Entity implements FrameUpdateListener
{
    double frames = 0;

    public EnemyEntity(double x, double y)
    {
        super(new Sprite("hex.png"), x, y);
    }

    @Override
    public void onFrameUpdate()
    {
        PlayerEntity e = (PlayerEntity) DoodleApplication.getInstance().getCurrentRoom().findEntity(PlayerEntity.class);

        double deltaX = getPosition().x - e.getPosition().x;
        double deltaY = getPosition().y - e.getPosition().y;
        double angle = Math.atan2(deltaY, deltaX) * 180 / Math.PI;

        getPosition().translate(Math.cos(frames) + Math.cos(angle * 0.017) * -2, Math.sin(frames) + Math.sin(angle * 0.017) * -2);

        frames += 0.1;
    }
}
