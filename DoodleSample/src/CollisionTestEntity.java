import net.digaly.doodle.Entity;
import net.digaly.doodle.Sprite;
import net.digaly.doodle.collision.BoxCollider;
import net.digaly.doodle.events.CollisionEventListener;

/**
 * Created by Tom Dobbelaere on 16/10/2016.
 */
public class CollisionTestEntity extends Entity implements CollisionEventListener
{
    public CollisionTestEntity()
    {
        super(new Sprite("doodle\\ship_normal.png") ,100, 100);
        setCollider(new BoxCollider(this, 0, 0, getWidth(), getHeight()));
    }

    @Override
    public void onCollisionStay(Entity other)
    {

    }

    @Override
    public void onCollisionEnter(Entity other)
    {

    }

    @Override
    public void onCollisionExit(Entity other)
    {

    }
}
