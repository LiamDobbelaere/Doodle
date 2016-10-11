package net.digaly.doodle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.transform.Rotate;

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

    public Entity(Sprite sprite, double x, double y) {
        this.sprite = sprite;
        this.angle = 0;
        this.position = new Point(x, y);
        this.visible = true;
        this.alpha = 1;
    }

    public Sprite getSprite()
    {
        return sprite;
    }

    public void setSprite(Sprite sprite)
    {
        this.sprite = sprite;
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
        //DoodleApplication.getInstance().getCurrentRoom().removeEntity(this);
    }

    public int getDepth()
    {
        return depth;
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
    }
}
