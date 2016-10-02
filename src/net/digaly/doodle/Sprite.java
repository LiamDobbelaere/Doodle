package net.digaly.doodle;

import javafx.scene.image.Image;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class Sprite
{
    private Image image;
    private String filename;

    public Sprite(String filename) {
        this.filename = filename;
    }

    public Image getImage()
    {
        if (image == null) {
            image = new Image(filename);
        }

        return image;
    }
}
