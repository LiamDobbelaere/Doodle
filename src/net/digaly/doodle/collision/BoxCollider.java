package net.digaly.doodle.collision;

import net.digaly.doodle.Entity;
import net.digaly.doodle.Point;

/**
 * Created by Digaly on 14/10/2016.
 */
public class BoxCollider extends Collider
{
    private double width;
    private double height;

    public BoxCollider(Entity entity) {
        super(entity, 0, 0);
        this.width = entity.getWidth();
        this.height = entity.getHeight();
    }

    public BoxCollider(Entity entity, double x, double y, double width, double height) {
        super(entity, x, y);
        this.width = width;
        this.height = height;
    }

    public boolean overlaps(Collider other)
    {
        BoxCollider otherCollider;

        if ((other instanceof BoxCollider)) {
            otherCollider = (BoxCollider) other;

            return (getActualX() < otherCollider.getActualX() + otherCollider.width && getActualX() + width > otherCollider.getActualX() &&
                    getActualY() < otherCollider.getActualY() + otherCollider.height && getActualY() + height > otherCollider.getActualY());
        }

        return false;
    }

    public double getActualX() {
        return entity.getPosition().x - entity.getSprite().getOffset().x + position.x;
    }

    public double getActualY() {
        return entity.getPosition().y - entity.getSprite().getOffset().y + position.y;
    }

    public void setWidth(double width)
    {
        this.width = width;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }
}