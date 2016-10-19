package net.digaly.doodle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import net.digaly.doodle.collision.Collider;
import net.digaly.doodle.collision.NoCollider;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class Entity
{
    private Sprite sprite;
    private int angle;
    private Point position;
    private boolean visible;
    private double alpha;
    private int depth;
    private double width;
    private double height;
    private Collider collider;
    private Room room;

    public Entity(Sprite sprite, double x, double y) {
        setSprite(sprite);
        this.angle = 0;
        this.position = new Point(x, y);
        this.visible = true;
        this.alpha = 1;
        this.room = new NoRoom();
        this.collider = new NoCollider(this);
    }

    public Sprite getSprite()
    {
        return sprite;
    }
    
    public void setSprite(Sprite sprite)
    {
        this.sprite = sprite;

        if (sprite.getImage() != null) {
            setWidth(sprite.getImage().getWidth());
            setHeight(sprite.getImage().getHeight());
        }
    }

    public int getAngle()
    {
        return angle;
    }

    public void setAngle(int angle)
    {
        if (angle < 0) {
            while (angle <= -360) angle += 360;
        } else {
            while (angle > 360) angle -= 360;
        }

        this.angle = angle;
    }

    public Point getPosition()
    {
        return position;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    public double getAlpha()
    {

        return alpha;
    }

    public void setAlpha(double alpha)
    {
        if (alpha > 1) alpha = 1;
        if (alpha < 0) alpha = 0;

        this.alpha = alpha;
    }

    public void draw(GraphicsContext gc) {
        if (getSprite() == null) return;

        gc.save();
        Rotate rotate = new Rotate(getAngle(), getPosition().x,
                getPosition().y);
        gc.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
        gc.drawImage(getSprite().getImage(), getPosition().x - getSprite().getOffset().x, getPosition().y - getSprite().getOffset().y);
        gc.restore();
    }

    public void destroy() {
        getRoom().removeEntity(this);
    }

    public int getDepth()
    {
        return depth;
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    public Room getRoom()
    {
        return room;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }

    public double getWidth()
    {
        return width;
    }

    public void setWidth(double width)
    {
        this.width = width;
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public Collider getCollider()
    {
        return collider;
    }

    public void setCollider(Collider collider)
    {
        this.collider = collider;
    }
}
