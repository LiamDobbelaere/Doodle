package net.digaly.doodle;

import javafx.scene.image.Image;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class Sprite
{
    private Image image;
    private String filename;
    private Point origin;
    private boolean centerOrigin;

    public Sprite(String filename) {
        this.filename = filename;
        this.origin = new Point(0, 0);
        this.centerOrigin = true;
    }

    public Sprite(String filename, double originX, double originY) {
        this(filename);
        this.origin = new Point(originX, originY);
        this.centerOrigin = false;
    }

    public Image getImage()
    {
        if (image == null) {
            image = new Image(filename);
        }

        return image;
    }

    private void recalculateOrigin() {
        getImage();
        if (centerOrigin) origin = new Point(image.getWidth() / 2, image.getHeight() / 2);
    }

    public Point getOrigin()
    {
        recalculateOrigin();
        return origin;
    }

    public void setOrigin(double x, double y)
    {
        this.origin = new Point(x, y);
    }
}
