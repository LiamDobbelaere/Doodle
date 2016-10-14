package net.digaly.doodle.collision;

import net.digaly.doodle.Entity;

/**
 * Created by Digaly on 14/10/2016.
 */
public class NoCollider extends Collider
{
    public NoCollider(Entity entity)
    {
        super(entity, 0, 0);
    }

    @Override
    public boolean overlaps(Collider other)
    {
        return false;
    }
}
