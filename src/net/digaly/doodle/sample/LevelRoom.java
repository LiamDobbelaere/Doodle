package net.digaly.doodle.sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.digaly.doodle.FrameDrawListener;
import net.digaly.doodle.Room;
import net.digaly.doodle.Sprite;

import java.awt.*;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class LevelRoom extends Room
{
    public LevelRoom(int width, int height)
    {
        super(width, height);

        setBackground(new Sprite("space.png"));

        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                this.addEntity(new TileEntity(new Sprite("tile.png"), i * 64, j * 64));
            }
        }
        this.addEntity(new PlayerEntity(0, 0));
    }
}
