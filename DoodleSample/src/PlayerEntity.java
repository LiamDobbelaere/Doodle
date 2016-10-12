import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import net.digaly.doodle.DoodleApplication;
import net.digaly.doodle.Entity;
import net.digaly.doodle.Sprite;
import net.digaly.doodle.events.*;

import java.util.Random;

import static javafx.scene.input.KeyCode.Z;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class PlayerEntity extends Entity implements FrameUpdateListener, KeyEventListener, MouseEventListener
{
    private double speed;
    private int turnSpeed = 5;

    private Sprite spriteForwards;
    private Sprite spriteBackwards;
    private Sprite spriteNone;
    private Sprite spriteBullet;

    private Random random;

    private int shootDelay;
    private int bulletPower;
    private double bulletSpread;

    public PlayerEntity(double x, double y)
    {
        super(new Sprite("ship_n.png"), x, y);

        setDepth(-100);

        spriteNone = new Sprite("ship_n.png");
        spriteForwards = new Sprite("ship_f.png");
        spriteBackwards = new Sprite("ship_b.png");
        spriteBullet = new Sprite("bullet.png");
        random = new Random();

        shootDelay = 0;
        speed = 0;
        bulletPower = 2;
        bulletSpread = 10;
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

        //DoodleApplication.getInstance().getCurrentRoom().addEntity(new TrailEntity(getSprite(), getPosition().x, getPosition().y, getAngle(), 0.2, 0.01));

        setSprite(spriteNone);

        if (shootDelay > 0) shootDelay -= 1;
    }

    @Override
    public void onKeyEvent(KeyEvent keyEvent, KeyState keyState)
    {
        if (keyState == KeyState.HOLDING) {
            switch (keyEvent.getCode()) {
                case Z:
                    speed += 0.2;
                    setSprite(spriteForwards);
                    break;
                case S:
                    speed -= 0.2;
                    setSprite(spriteBackwards);
                    break;
                case Q:
                    setAngle(getAngle() - turnSpeed);
                    break;
                case D:
                    setAngle(getAngle() + turnSpeed);
                    break;
                case E:
                    shoot();
                    break;
            }
        }

        if (keyState == KeyState.RELEASED) {
            if (keyEvent.getCode() == KeyCode.R) {
                //DoodleApplication.getInstance().setCurrentRoom(new LevelOtherRoom());
            }
        }
    }

    private void shoot() {
        if (shootDelay == 0) {
            int angleSwing;

            for (int i = 0; i < bulletPower; i++) {
                angleSwing = (int) -(bulletSpread/2) + random.nextInt((int) bulletSpread);
                getRoom().addEntity(new BulletEntity(spriteBullet, getPosition().x, getPosition().y, getAngle()+ angleSwing, 10));
            }

            shootDelay = 5;
            //DoodleApplication.getInstance().getSoundManager().playSound("res\\shoot.wav");
        }
    }

    @Override
    public void onMouseEvent(MouseEvent event, MouseState state, boolean isLocal)
    {
        if (event.getEventType() == MouseEvent.MOUSE_MOVED || event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            double deltaX = getPosition().x + getSprite().getOffset().x - event.getX();
            double deltaY = getPosition().y + getSprite().getOffset().y - event.getY();
            double angle = Math.atan2(deltaY, deltaX) * 180 / Math.PI;

            setAngle((int) angle + 180);
        }

        if (event.getButton() == MouseButton.PRIMARY && state == MouseState.HOLDING) {
            shoot();
        }
    }
}
