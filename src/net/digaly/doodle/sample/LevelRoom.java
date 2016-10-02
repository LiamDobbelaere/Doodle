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
public class LevelRoom extends Room implements FrameDrawListener
{
    public LevelRoom(int width, int height)
    {
        super(width, height);

        setBackground(new Sprite("background.jpg"));

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                this.addEntity(new TileEntity(null, i * 64, j * 64));
            }
        }
        this.addEntity(new PlayerEntity(0, 0));
    }

    @Override
    public void onFrameDraw(GraphicsContext gc)
    {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }
}
