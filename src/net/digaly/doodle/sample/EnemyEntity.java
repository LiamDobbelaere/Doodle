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
    private Entity target;
    double frames = 0;
    private int health;
    private double hitAlpha;
    private Random random;
    private int speed;

    public EnemyEntity(double x, double y)
    {
        super(new Sprite("hex.png"), x, y);
        health = 25;
        random = new Random();
        speed = 1;
    }

    @Override
    public void onFrameUpdate()
    {
        if (target == null) {
            switch(random.nextInt(2)) {
                case 0:
                    setSprite(new Sprite("hex_red.png"));
                    target = DoodleApplication.getInstance().getCurrentRoom().findEntity(DefensePostEntity.class);
                    break;
                case 1:
                    speed = 3;
                    health = 8;
                    target = DoodleApplication.getInstance().getCurrentRoom().findEntity(PlayerEntity.class);
                    break;
            }
        }


        if (target != null) {
            double deltaX = getPosition().x - target.getPosition().x;
            double deltaY = getPosition().y - target.getPosition().y;
            double angle = Math.atan2(deltaY, deltaX) * 180 / Math.PI;

            getPosition().translate(Math.cos(frames) + Math.cos(angle * 0.017) * -speed, Math.sin(frames) + Math.sin(angle * 0.017) * -speed);
        }

        frames += 0.1;

        if (hitAlpha < 1)
        {
            //getPosition().translate(-8 + random.nextInt(16), -8 + random.nextInt(16));
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
