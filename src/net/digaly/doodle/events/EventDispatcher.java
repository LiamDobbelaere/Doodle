package net.digaly.doodle.events;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import net.digaly.doodle.Entity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Tom Dobbelaere on 4/10/2016.
 */
public class EventDispatcher
{
    private List<FrameUpdateListener> frameUpdateListeners;
    private List<FrameDrawListener> frameDrawListeners;
    private List<KeyEventListener> keyEventListeners;
    private List<MouseEventListener> mouseEventListeners;
    private List<ApplicationReadyListener> applicationReadyListeners;
    private List<CollisionEventListener> collisionEventListeners;

    public EventDispatcher() {
        frameUpdateListeners = new CopyOnWriteArrayList<>();
        keyEventListeners = new CopyOnWriteArrayList<>();
        applicationReadyListeners = new CopyOnWriteArrayList<>();
        frameDrawListeners = new CopyOnWriteArrayList<>();
        mouseEventListeners = new CopyOnWriteArrayList<>();
        collisionEventListeners = new CopyOnWriteArrayList<>();
    }

    public void addFrameUpdateListener(FrameUpdateListener listener) {
        frameUpdateListeners.add(listener);
    }

    public void removeFrameUpdateListener(FrameUpdateListener listener) {
        frameUpdateListeners.remove(listener);
    }

    public void notifyFrameUpdateListeners() {
        for (FrameUpdateListener listener : frameUpdateListeners) {
            listener.onFrameUpdate();
        }
    }

    public void addKeyEventListener(KeyEventListener listener) {
        keyEventListeners.add(listener);
    }

    public void removeKeyEventListener(KeyEventListener listener) {
        keyEventListeners.remove(listener);
    }

    public void notifyKeyEventListeners(KeyEvent keyEvent, KeyState keyState) {
        for (KeyEventListener listener : keyEventListeners) {
            listener.onKeyEvent(keyEvent, keyState);
        }
    }

    public void addApplicationReadyListener(ApplicationReadyListener listener) {
        applicationReadyListeners.add(listener);
    }

    public void removeApplicationReadyListener(ApplicationReadyListener listener) {
        applicationReadyListeners.remove(listener);
    }

    public void notifyApplicationReadyListener() {
        for (ApplicationReadyListener listener : applicationReadyListeners) {
            listener.onApplicationReady();
        }
    }

    public void addFrameDrawListener(FrameDrawListener listener) {
        frameDrawListeners.add(listener);
    }

    public void removeFrameDrawListener(FrameDrawListener listener) {
        frameDrawListeners.remove(listener);
    }

    public void notifyFrameDrawListeners(GraphicsContext passedContext) {
        for (FrameDrawListener listener : frameDrawListeners) {
            listener.onFrameDraw(passedContext);
        }
    }

    public void addMouseEventListener(MouseEventListener listener) {
        mouseEventListeners.add(listener);
    }

    public void removeMouseEventListener(MouseEventListener listener) {
        mouseEventListeners.remove(listener);
    }

    public void notifyMouseEventListeners(MouseEvent event, boolean isLocal) {
        for (MouseEventListener listener : mouseEventListeners) {
            listener.onMouseEvent(event, isLocal);
        }
    }

    public void addCollisionEventListener(CollisionEventListener listener) {
        collisionEventListeners.add(listener);
    }

    public void removeCollisionEventListener(CollisionEventListener listener) {
        collisionEventListeners.remove(listener);
    }

    public void notifyCollisionEventListeners(Entity other) {
        for (CollisionEventListener listener : collisionEventListeners) {
            listener.onCollision(other);
        }
    }
}
