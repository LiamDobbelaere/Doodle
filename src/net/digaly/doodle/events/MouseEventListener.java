package net.digaly.doodle.events;

import javafx.scene.input.MouseEvent;

/**
 * Created by Tom Dobbelaere on 3/10/2016.
 */
public interface MouseEventListener
{
    void onMouseEvent(MouseEvent event, MouseState state, boolean isLocal);
}
