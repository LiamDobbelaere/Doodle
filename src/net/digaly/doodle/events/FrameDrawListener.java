package net.digaly.doodle.events;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public interface FrameDrawListener
{
    void onFrameDraw(GraphicsContext gc);
}
