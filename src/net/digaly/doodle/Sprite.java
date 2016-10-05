package net.digaly.doodle;

import javafx.scene.image.Image;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class Sprite
{
    private Image image;
    private String filename;
    private boolean centerOrigin;

    public Sprite(String filename) {
        this.filename = filename;
        this.centerOrigin = true;
        this.image = new Image(filename);
    }

    public Image getImage()
    {
        /*if (image == null) {
            image = new Image(filename);
        }*/

        return image;
    }
}
