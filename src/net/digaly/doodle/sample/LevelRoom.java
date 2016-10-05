package net.digaly.doodle.sample;

import net.digaly.doodle.DoodleApplication;
import net.digaly.doodle.Room;
import net.digaly.doodle.Sprite;

import java.util.Random;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class LevelRoom extends Room
{
    public LevelRoom()
    {
        super(1024, 768);

        Random random = new Random();

        setBackground(new Sprite("space.png"));

        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                this.addEntity(new TileEntity(new Sprite("tile.png"), i * 64, j * 64));
            }
        }
        this.addEntity(new PlayerEntity(250, 250));

        this.addEntity(new DefensePostEntity(250, 250));


        this.addEntity(new SpawnerEntity(0, 0));


        System.out.println("level loaded");

        //DoodleApplication.getInstance().getSoundManager().playMusic("res\\menu.mp3");
    }
}
