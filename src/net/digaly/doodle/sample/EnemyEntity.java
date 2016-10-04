package net.digaly.doodle.sample;

import net.digaly.doodle.DoodleApplication;
import net.digaly.doodle.Entity;
import net.digaly.doodle.Sprite;
import net.digaly.doodle.events.FrameUpdateListener;

import java.util.Random;

/**
 * Created by Tom Dobbelaere on 3/10/2016.
 */
public class EnemyEntity extends Entity implements FrameUpdateListener
{
    private PlayerEntity player;
    double frames = 0;
    private int health;
    private double hitAlpha;
    private Random random;

    public EnemyEntity(double x, double y)
    {
        super(new Sprite("hex.png"), x, y);
        health = 32;
        random = new Random();
    }

    @Override
    public void onFrameUpdate()
    {
        if (player == null) player = (PlayerEntity) DoodleApplication.getInstance().getCurrentRoom().findEntity(PlayerEntity.class);

        double deltaX = getPosition().x - player.getPosition().x;
        double deltaY = getPosition().y - player.getPosition().y;
        double angle = Math.atan2(deltaY, deltaX) * 180 / Math.PI;

        getPosition().translate(Math.cos(frames) + Math.cos(angle * 0.017) * -2, Math.sin(frames) + Math.sin(angle * 0.017) * -2);

        frames += 0.1;

        if (hitAlpha < 1)
        {
            getPosition().translate(-8 + random.nextInt(16), -8 + random.nextInt(16));
            setAlpha(hitAlpha);
            hitAlpha += 0.05;
        }
    }

    public void damage(int value) {
        health -= value;
        hitAlpha = 0.5;

        if (health < 0) destroy();
    }
}
