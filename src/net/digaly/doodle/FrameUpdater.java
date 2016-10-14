package net.digaly.doodle;

import javafx.animation.AnimationTimer;
import net.digaly.doodle.collision.CollisionManager;
import net.digaly.doodle.collision.NoCollisionManager;
import net.digaly.doodle.events.EventDispatcher;
import net.digaly.doodle.events.NoEventDispatcher;
import net.digaly.doodle.input.InputManager;
import net.digaly.doodle.input.NoInputManager;
import net.digaly.doodle.rendering.NoRenderer;
import net.digaly.doodle.rendering.Renderer;

/**
 * Created by Tom Dobbelaere on 12/10/2016.
 */
public class FrameUpdater extends AnimationTimer
{
    private Renderer renderer;
    private EventDispatcher eventDispatcher;
    private InputManager inputManager;
    private CollisionManager collisionManager;

    public FrameUpdater() {
        this.renderer = new NoRenderer();
        this.eventDispatcher = new NoEventDispatcher();
        this.inputManager = new NoInputManager();
        this.collisionManager = new NoCollisionManager();
    }

    public void setRenderer(Renderer renderer)
    {
        this.renderer = renderer;
    }

    public void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public void setCollisionManager(CollisionManager collisionManager) { this.collisionManager = collisionManager; }

    @Override
    public void handle(long now)
    {
        eventDispatcher.notifyFrameUpdateListeners();
        inputManager.notifyHeldKeys();
        collisionManager.checkCollisions();

        renderer.renderFrame();
    }
}
