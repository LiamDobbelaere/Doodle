package net.digaly.doodle;

import javafx.scene.image.Image;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class Sprite
{
    private Image image;
    private Point offset;

    public Sprite() {
        this.offset = new Point(0, 0);
    }

    public Sprite(String filename) {
        this.image = new Image(filename);
        this.offset = new Point(image.getWidth() / 2, image.getHeight() / 2);
    }

    public Sprite(String filename, double xOffset, double yOffset) {
        this(filename);
        this.offset = new Point(xOffset, yOffset);
    }

    public Image getImage()
    {
        return image;
    }

    public Point getOffset()
    {
        return offset;
    }

    public void setOffset(Point offset) {
        this.offset = offset;
    }
}
