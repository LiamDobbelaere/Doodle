import net.digaly.doodle.Entity;
import net.digaly.doodle.Sprite;
import net.digaly.doodle.collision.BoxCollider;
import net.digaly.doodle.events.CollisionEventListener;
import net.digaly.doodle.events.FrameUpdateListener;

/**
 * Created by Tom Dobbelaere on 13/10/2016.
 */
public class GeomEntity extends Entity implements FrameUpdateListener, CollisionEventListener
{
    private double speed;
    private boolean foundPlayer;
    private boolean canPickup;

    public GeomEntity(double x, double y)
    {
        super(new Sprite("v2\\geom.png"), x, y);
        setCollider(new BoxCollider(this));
        canPickup = false;
    }

    @Override
    public void onFrameUpdate()
    {
        PlayerEntity target = (PlayerEntity) getRoom().findEntity(PlayerEntity.class);


        if (target != null) {
            double deltaX = getPosition().x - target.getPosition().x;
            double deltaY = getPosition().y - target.getPosition().y;
            double angle = Math.atan2(deltaY, deltaX) * 180 / Math.PI;
            double distance = Math.abs(deltaX) + Math.abs(deltaY);

            if (distance < 120 && !foundPlayer) foundPlayer = true;

            if (foundPlayer) {
                getPosition().translate(Math.cos(angle * 0.017) * -speed, Math.sin(angle * 0.017) * -speed);
            }

            if (distance <= 16) canPickup = true;
        }

        if (speed < 10) speed+=0.15;
    }

    @Override
    public void onCollision(Entity other)
    {

    }

    public boolean canPickup() {
        return canPickup;
    }
}
