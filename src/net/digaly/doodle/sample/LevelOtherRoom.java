package net.digaly.doodle.sample;

import net.digaly.doodle.DoodleApplication;
import net.digaly.doodle.Room;
import net.digaly.doodle.Sprite;

import java.util.Random;

/**
 * Created by Tom Dobbelaere on 5/10/2016.
 */
public class LevelOtherRoom extends Room
{
    public LevelOtherRoom()
    {
        super(800, 600);

        Random random = new Random();

        setBackground(new Sprite("space2.png"));

        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                this.addEntity(new TileEntity(new Sprite("tile.png"), i * 64, j * 64));
            }
        }

        this.addEntity(new PlayerEntity(0, 0));

        this.addEntity(new SpawnerEntity(0, 0));

        System.out.println("level loaded");

        //DoodleApplication.getInstance().getSoundManager().playMusic("res\\music.mp3");
    }

}
