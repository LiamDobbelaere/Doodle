package net.digaly.doodle;

import net.digaly.doodle.events.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class Room
{
    private List<Entity> entities;
    private Dimension size;
    private EventDispatcher eventDispatcher;
    private Sprite background;

    public Room(int width, int height) {
        this.entities = new CopyOnWriteArrayList<>();
        this.size = new Dimension(width, height);
        this.eventDispatcher = new NoEventDispatcher();
    }

    public void sortEntitiesByDepth() {
        entities.sort(Comparator.comparing(Entity::getDepth));
    }

    public void addEntity(Entity entity) {
        entity.setRoom(this);
        this.entities.add(entity);

        if (entity instanceof FrameUpdateListener) {
            eventDispatcher.addFrameUpdateListener((FrameUpdateListener) entity);
        }

        if (entity instanceof KeyEventListener) {
            eventDispatcher.addKeyEventListener((KeyEventListener) entity);
        }

        if (entity instanceof FrameDrawListener) {
            eventDispatcher.addFrameDrawListener((FrameDrawListener) entity);
        }

        if (entity instanceof MouseEventListener) {
            eventDispatcher.addMouseEventListener((MouseEventListener) entity);
        }

        if (entity instanceof CollisionEventListener) {
            eventDispatcher.addCollisionEventListener((CollisionEventListener) entity);
        }

        sortEntitiesByDepth();
    }

    public void removeEntity(Entity entity) {
        entity.setRoom(new NoRoom());
        this.entities.remove(entity);

        if (entity instanceof FrameUpdateListener) {
            eventDispatcher.removeFrameUpdateListener((FrameUpdateListener) entity);
        }

        if (entity instanceof KeyEventListener) {
            eventDispatcher.removeKeyEventListener((KeyEventListener) entity);
        }

        if (entity instanceof FrameDrawListener) {
            eventDispatcher.removeFrameDrawListener((FrameDrawListener) entity);
        }

        if (entity instanceof MouseEventListener) {
            eventDispatcher.removeMouseEventListener((MouseEventListener) entity);
        }

        if (entity instanceof CollisionEventListener) {
            eventDispatcher.removeCollisionEventListener((CollisionEventListener) entity);
        }

        sortEntitiesByDepth();

        entity = null;
    }

    public Entity findEntity(Class search) {
        for (Entity entity : entities) {
            if (entity.getClass() == search) {
                return entity;
            }
        }

        return null;
    }

    public List<Entity> findEntities(Class search) {
        List<Entity> entityList = new ArrayList<>();

        for (Entity entity : entities) {
            if (entity.getClass() == search) {
                entityList.add(entity);
            }
        }

        return entityList;
    }

    protected List<Entity> getEntities() {
        return this.entities;
    }

    public Dimension getSize() {
        return this.size;
    }

    public Sprite getBackground()
    {
        return background;
    }

    public void setBackground(Sprite background)
    {
        this.background = background;
    }

    public void destroy() {
        for (Entity e : entities) {
            removeEntity(e);
        }

        entities = null;
    }

    public void setEventDispatcher(EventDispatcher eventDispatcher)
    {
        this.eventDispatcher = eventDispatcher;
    }
}
