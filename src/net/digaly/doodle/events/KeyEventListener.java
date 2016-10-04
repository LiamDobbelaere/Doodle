package net.digaly.doodle.events;

import javafx.scene.input.KeyEvent;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public interface KeyEventListener
{
    void onKeyEvent(KeyEvent keyEvent, KeyState keyState);
}
