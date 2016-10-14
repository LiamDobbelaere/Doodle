package net.digaly.doodle.collision;

import net.digaly.doodle.Entity;
import net.digaly.doodle.events.CollisionEventListener;
import net.digaly.doodle.events.EventDispatcher;
import net.digaly.doodle.events.NoEventDispatcher;

import java.util.List;

/**
 * Created by Digaly on 14/10/2016.
 */
public class CollisionManager
{
    private List<Entity> entities;

    public CollisionManager() {

    }

    public void checkCollisions() {
        for (Entity entity : entities) {
            if (!(entity instanceof CollisionEventListener)) continue;

            for (Entity other : entities) {
                if (!(other instanceof CollisionEventListener) || other == entity) continue;

                if (entity.getCollider().overlaps(other.getCollider())) {
                    ((CollisionEventListener) entity).onCollision(other);
                }
            }
        }
    }

    public List<Entity> getEntities()
    {
        return entities;
    }

    public void setEntities(List<Entity> entities)
    {
        this.entities = entities;
    }
}
