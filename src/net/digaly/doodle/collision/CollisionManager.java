package net.digaly.doodle.collision;

import net.digaly.doodle.Entity;
import net.digaly.doodle.events.EventDispatcher;
import net.digaly.doodle.events.NoEventDispatcher;

import java.util.List;

/**
 * Created by Digaly on 14/10/2016.
 */
public class CollisionManager
{
    private List<Entity> entities;
    private EventDispatcher eventDispatcher;

    public CollisionManager() {
        this.eventDispatcher = new NoEventDispatcher();
    }

    public void checkCollisions() {

    }

    public List<Entity> getEntities()
    {
        return entities;
    }

    public void setEntities(List<Entity> entities)
    {
        this.entities = entities;
    }

    public void setEventDispatcher(EventDispatcher eventDispatcher)
    {
        this.eventDispatcher = eventDispatcher;
    }
}
