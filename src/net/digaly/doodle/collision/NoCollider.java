package net.digaly.doodle.collision;

/**
 * Created by Digaly on 14/10/2016.
 */
public class NoCollider extends Collider
{
    @Override
    public boolean overlaps(Collider other)
    {
        return false;
    }
}
