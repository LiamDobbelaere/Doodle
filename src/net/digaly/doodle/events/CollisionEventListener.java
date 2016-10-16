package net.digaly.doodle.events;

import net.digaly.doodle.Entity;

/**
 * Created by Tom Dobbelaere on 4/10/2016.
 */
public interface CollisionEventListener
{
    void onCollisionStay(Entity other);
    void onCollisionEnter(Entity other);
    void onCollisionExit(Entity other);
}
