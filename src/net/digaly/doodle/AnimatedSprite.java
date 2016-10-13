package net.digaly.doodle;

import javafx.scene.image.Image;

/**
 * Created by Digaly on 13/10/2016.
 */
public class AnimatedSprite extends Sprite
{
    private Image[] images;
    private int currentImage;
    private int framesPassed;
    private int animationSpeed;

    public AnimatedSprite(String[] filenames, int animationSpeed) {
        super(filenames[0]); //Failsafe

        images = new Image[filenames.length];

        for (int i = 0; i < images.length; i++) {
            images[i] = new Image(filenames[i]);
        }

        this.animationSpeed = animationSpeed;
    }

    public AnimatedSprite(String filename, double xOffset, double yOffset)
    {
        super(filename, xOffset, yOffset);
        this.images = new Image[1];
        this.images[0] = new Image(filename);
    }

    public AnimatedSprite(String filename)
    {
        super(filename);
        this.images = new Image[1];
        this.images[0] = new Image(filename);
    }

    public AnimatedSprite()
    {
        super();
        this.images = new Image[0];
        this.currentImage = 0;
        this.animationSpeed = 1;
    }


    @Override
    public Image getImage()
    {
        return images[currentImage];
    }

    public void incrementFramesPassed() {
        framesPassed += 1;
        if (framesPassed >= animationSpeed) {
            framesPassed = 0;
            incrementCurrentImage();
        }
    }

    public void incrementCurrentImage() {
        currentImage += 1;

        if (currentImage > images.length - 1) {
            currentImage = 0;
        }
    }
}
