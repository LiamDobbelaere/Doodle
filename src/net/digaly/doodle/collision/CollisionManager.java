package net.digaly.doodle.collision;

import net.digaly.doodle.Entity;
import net.digaly.doodle.events.CollisionEventListener;
import net.digaly.doodle.events.EventDispatcher;
import net.digaly.doodle.events.NoEventDispatcher;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Digaly on 14/10/2016.
 */
public class CollisionManager
{
    private List<Entity> entities;
    private List<Collision> collisionTracker;

    public CollisionManager() {
        this.collisionTracker = new CopyOnWriteArrayList<>();
    }

    public void checkCollisions() {
        for (Entity entity : entities) {
            if (!(entity instanceof CollisionEventListener)) continue;

            for (Entity other : entities) {
                if (!(other instanceof CollisionEventListener) || other == entity) continue;

                Collision collision = new Collision(entity, other);

                if (entity.getCollider().overlaps(other.getCollider())) {
                    ((CollisionEventListener) entity).onCollisionStay(other);

                    if (!collisionTracker.contains(collision)) {
                        collisionTracker.add(collision);
                        ((CollisionEventListener) entity).onCollisionEnter(other);
                    }
                } else {
                    if (collisionTracker.contains(collision)) {
                        collisionTracker.remove(collision);
                        ((CollisionEventListener) entity).onCollisionExit(other);
                    }
                }
            }
        }

        for (Collision collision : collisionTracker) {
            //Cleanup collisions of entities that no longer exist
            if (!entities.contains(collision.getA()) || !entities.contains(collision.getB())) {
                collisionTracker.remove(collision);
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
