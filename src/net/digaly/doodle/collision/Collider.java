package net.digaly.doodle.collision;

import javafx.scene.canvas.GraphicsContext;
import net.digaly.doodle.Entity;
import net.digaly.doodle.Point;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Digaly on 14/10/2016.
 */
public abstract class Collider
{
    protected Point position;
    protected Entity entity;

    public Collider(Entity entity, double x, double y) {
        this.position = new Point(x, y);
        this.entity = entity;
    }

    public boolean overlaps(Collider other)
    {
        throw new NotImplementedException();
    }

    public void debugDraw(GraphicsContext gc) {
        throw new NotImplementedException();
    }
}
