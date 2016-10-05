package net.digaly.doodle.sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import net.digaly.doodle.DoodleApplication;
import net.digaly.doodle.Entity;
import net.digaly.doodle.Point;
import net.digaly.doodle.Sprite;
import net.digaly.doodle.events.CollisionEventListener;
import net.digaly.doodle.events.FrameDrawListener;
import net.digaly.doodle.events.FrameUpdateListener;

/**
 * Created by Tom Dobbelaere on 5/10/2016.
 */
public class DefensePostEntity extends Entity implements FrameUpdateListener, CollisionEventListener
{
    private int speed;
    private double framesPassed;
    private double health;
    private double maxHealth;
    private Point originalPosition;

    private Sprite[] sprites;

    public DefensePostEntity(double x, double y)
    {
        super(new Sprite("v2\\defend_spinner.png"), x, y);
        speed = 6;
        framesPassed = 0;
        maxHealth = 100;
        health = maxHealth;
        originalPosition = new Point(getPosition().x, getPosition().y);

        sprites = new Sprite[2];
        sprites[0] = new Sprite("v2\\defend_spinner_damaged.png");
        sprites[1] = new Sprite("v2\\defend_spinner_critical.png");
    }

    @Override
    public void onFrameUpdate()
    {
        setAngle(getAngle() + (int) (speed * (health / maxHealth + 0.2)));
        DoodleApplication.getInstance().getCurrentRoom().addEntity(new TrailEntity(getSprite(), getPosition().x, getPosition().y, getAngle(), 0.5, 0.01));

        framesPassed++;

        getPosition().x = originalPosition.x + Math.cos(framesPassed) * (1.5 / (health / maxHealth));
        getPosition().y = originalPosition.y + Math.sin(framesPassed) * (1.5 / (health / maxHealth));

        if (framesPassed > 30) {
            framesPassed = 0;
            damage(5);
        }

        if (health / maxHealth < 0.3) {
            setSprite(sprites[1]);
        } else if (health / maxHealth < 0.6) {
            setSprite(sprites[0]);
        }
    }

    @Override
    public void onCollision(Entity other)
    {

    }

    public void damage(int amount) {
        health -= amount;

        if (health < 0) destroy();
    }
}
