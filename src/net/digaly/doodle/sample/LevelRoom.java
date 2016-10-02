package net.digaly.doodle.sample;

import javafx.scene.canvas.GraphicsContext;
import net.digaly.doodle.FrameDrawListener;
import net.digaly.doodle.Room;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class LevelRoom extends Room implements FrameDrawListener
{
    public LevelRoom(int width, int height)
    {
        super(width, height);

        this.addEntity(new PlayerEntity(0, 0));
    }


    @Override
    public void onFrameDraw(GraphicsContext gc)
    {
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }
}
