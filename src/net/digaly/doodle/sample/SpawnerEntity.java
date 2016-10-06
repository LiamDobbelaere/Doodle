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
    private double spawnSpeed;

    public SpawnerEntity(double x, double y)
    {
        super(null, x, y);
        random = new Random();
        spawnSpeed = 4;
    }

    @Override
    public void onFrameUpdate()
    {
        if (framesPassed > 60 * spawnSpeed) {
            spawnEnemy();
            framesPassed = 0;
            if (spawnSpeed > 0.8) spawnSpeed -= 0.5;
        }

        framesPassed += 1;
    }

    public void spawnEnemy() {
        Room currentRoom = DoodleApplication.getInstance().getCurrentRoom();

        switch (random.nextInt(4)) {
            case 0: //Bottom
                currentRoom.addEntity(new EnemyEntity(random.nextInt((int) currentRoom.getSize().getWidth()), currentRoom.getSize().getHeight() + 32));
                break;
            case 1: //Top
                currentRoom.addEntity(new EnemyEntity(random.nextInt((int) currentRoom.getSize().getWidth()), -32));
                break;
            case 2: //Left side
                currentRoom.addEntity(new EnemyEntity(-32, random.nextInt((int) currentRoom.getSize().getHeight())));
                break;
            case 3: //Right side
                currentRoom.addEntity(new EnemyEntity(currentRoom.getSize().getWidth() + 32, random.nextInt((int) currentRoom.getSize().getHeight())));
                break;
        }
    }
}
