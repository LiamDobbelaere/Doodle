package net.digaly.doodle.collision;

import net.digaly.doodle.Entity;

/**
 * Created by Tom Dobbelaere on 16/10/2016.
 */
public class Collision
{
    private Entity a;
    private Entity b;

    public Collision(Entity a, Entity b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Collision collision = (Collision) o;

        return a.equals(collision.a) && b.equals(collision.b);
    }

    @Override
    public int hashCode()
    {
        int result = a.hashCode();
        result = 31 * result + b.hashCode();
        return result;
    }

    public Entity getA()
    {
        return a;
    }

    public Entity getB()
    {
        return b;
    }
}
