package net.digaly.doodle.collision;

import net.digaly.doodle.Point;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Digaly on 14/10/2016.
 */
public abstract class Collider
{
    protected Point position;

    public boolean overlaps(Collider other)
    {
        throw new NotImplementedException();
    }
}
