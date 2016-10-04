package net.digaly.doodle.sample;

import net.digaly.doodle.DoodleApplication;
import net.digaly.doodle.Entity;
import net.digaly.doodle.Room;
import net.digaly.doodle.Sprite;
import net.digaly.doodle.events.FrameUpdateListener;

import java.util.Random;

/**
 * Created by Tom Dobbelaere on 4/10/2016.
 */
public class SpawnerEntity extends Entity implements FrameUpdateListener
{
    private double framesPassed;
    private Random random;

    public SpawnerEntity(double x, double y)
    {
        super(null, x, y);
        random = new Random();
    }

    @Override
    public void onFrameUpdate()
    {
        if (framesPassed > 60 * 4) {
            spawnEnemy();
            framesPassed = 0;
        }

        framesPassed += 1;
    }

    public void spawnEnemy() {
        Room currentRoom = DoodleApplication.getInstance().getCurrentRoom();


        currentRoom.addEntity(new EnemyEntity(currentRoom.getSize().getWidth(), random.nextInt((int) currentRoom.getSize().getHeight())));
    }
}
