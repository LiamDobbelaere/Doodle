package net.digaly.doodle.sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import net.digaly.doodle.*;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class PlayerEntity extends Entity implements FrameUpdateListener, KeyEventListener
{
    private double speed;
    private int turnSpeed = 5;

    private Sprite spriteForwards;
    private Sprite spriteBackwards;
    private Sprite spriteNone;

    private AudioClip bark;

    public PlayerEntity(double x, double y)
    {
        super(new Sprite("ship_n.png"), x, y);

        spriteNone = new Sprite("ship_n.png");
        spriteForwards = new Sprite("ship_f.png");
        spriteBackwards = new Sprite("ship_b.png");
        bark = DoodleApplication.getInstance().createAudioClip("bark.wav");

        speed = 0;
    }

    @Override
    public void onFrameUpdate()
    {
        getPosition().translate(Math.cos(getAngle() * 0.017) * speed, Math.sin(getAngle() * 0.017) * speed);

        if (Math.abs(speed) > 5)
        {
            if (speed > 0) speed = 5;
            if (speed < 0) speed = -5;
        }

        if (speed > 0) speed -= 0.1;
        if (speed < 0) speed += 0.1;

        turnSpeed = 5 - (int) speed / 2;

        DoodleApplication.getInstance().getCurrentRoom().addEntity(new PlayerTrailEntity(getSprite(), getPosition().x, getPosition().y, getAngle()));

        setSprite(spriteNone);

        //gc.fillOval(getPosition().x, getPosition().y, 32, 32);
    }

    @Override
    public void onKeyEvent(KeyEvent keyEvent, KeyState keyState)
    {
        if (keyState == KeyState.HOLDING) {
            switch (keyEvent.getCode()) {
                case UP:
                    speed += 0.2;
                    setSprite(spriteForwards);
                    break;
                case DOWN:
                    speed -= 0.2;
                    setSprite(spriteBackwards);
                    break;
                case LEFT:
                    setAngle(getAngle() - turnSpeed);
                    break;
                case RIGHT:
                    setAngle(getAngle() + turnSpeed);
                    break;
            }
        }

        if (keyState == KeyState.RELEASED) {
            if (keyEvent.getCode() == KeyCode.Z) {
                DoodleApplication.getInstance().getCurrentRoom().addEntity(new PlayerEntity(0, 0));
            }

            if (keyEvent.getCode() == KeyCode.E) {
                DoodleApplication.getInstance().playSound(bark);
            }
        }
    }
}
