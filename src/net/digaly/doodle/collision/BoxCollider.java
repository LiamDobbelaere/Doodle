package net.digaly.doodle.collision;

import net.digaly.doodle.Point;

/**
 * Created by Digaly on 14/10/2016.
 */
public class BoxCollider extends Collider
{
    private double width;
    private double height;

    public BoxCollider() {
        super();
        this.width = 0;
        this.height = 0;
    }

    public boolean overlaps(Collider other)
    {
        BoxCollider otherCollider;

        if ((other instanceof BoxCollider)) {
            otherCollider = (BoxCollider) other;

            return (position.x < otherCollider.position.x + otherCollider.width && position.x + width > otherCollider.position.x &&
                    position.y < otherCollider.position.y + otherCollider.height && position.y + height > otherCollider.position.y);
        }

        return false;
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