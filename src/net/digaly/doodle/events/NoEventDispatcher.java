package net.digaly.doodle.events;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import net.digaly.doodle.Entity;

/**
 * Created by Tom Dobbelaere on 12/10/2016.
 */
public class NoEventDispatcher extends EventDispatcher
{
    @Override
    public void addFrameUpdateListener(FrameUpdateListener listener)
    {

    }

    @Override
    public void removeFrameUpdateListener(FrameUpdateListener listener)
    {

    }

    @Override
    public void notifyFrameUpdateListeners()
    {

    }

    @Override
    public void addKeyEventListener(KeyEventListener listener)
    {

    }

    @Override
    public void removeKeyEventListener(KeyEventListener listener)
    {

    }

    @Override
    public void notifyKeyEventListeners(KeyEvent keyEvent, KeyState keyState)
    {

    }

    @Override
    public void addApplicationReadyListener(ApplicationReadyListener listener)
    {

    }

    @Override
    public void removeApplicationReadyListener(ApplicationReadyListener listener)
    {

    }

    @Override
    public void notifyApplicationReadyListener()
    {

    }

    @Override
    public void addFrameDrawListener(FrameDrawListener listener)
    {

    }

    @Override
    public void removeFrameDrawListener(FrameDrawListener listener)
    {

    }

    @Override
    public void notifyFrameDrawListeners(GraphicsContext passedContext)
    {

    }

    @Override
    public void addMouseEventListener(MouseEventListener listener)
    {

    }

    @Override
    public void removeMouseEventListener(MouseEventListener listener)
    {

    }

    @Override
    public void notifyMouseEventListeners(MouseEvent event, MouseState state, boolean isLocal)
    {

    }

    @Override
    public void addCollisionEventListener(CollisionEventListener listener)
    {

    }

    @Override
    public void removeCollisionEventListener(CollisionEventListener listener)
    {

    }

    @Override
    public void notifyCollisionEventListeners(Entity other)
    {

    }
}
