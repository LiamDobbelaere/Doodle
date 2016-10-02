package net.digaly.doodle;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class Entity
{
    private Sprite sprite;
    private int angle;
    private Point position;
    private boolean visible;
    private float alpha;

    public Entity(Sprite sprite, int x, int y) {
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

    public float getAlpha()
    {
        return alpha;
    }

    public void setAlpha(float alpha)
    {
        this.alpha = alpha;
    }
}
