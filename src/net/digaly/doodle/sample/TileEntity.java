package net.digaly.doodle.sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.digaly.doodle.*;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class TileEntity extends Entity implements FrameDrawListener, FrameUpdateListener
{
    public TileEntity(Sprite sprite, double x, double y)
    {
        super(sprite, x, y);
    }

    @Override
    public void onFrameDraw(GraphicsContext gc)
    {
        gc.save();

        double alpha = getAlpha();

        gc.setGlobalAlpha(alpha);

        double size = 32 + alpha * 16;

        gc.setFill(Color.WHITE);
        //gc.fillRect(getPosition().x - size / 2, getPosition().y - size / 2, size, size);
        //gc.fillOval(getPosition().x - 4, getPosition().y - 4, 8, 8);
        //gc.setStroke(Color.WHITE);

        //gc.strokeLine(getPosition().x, getPosition().y, e.getPosition().x + e.getSprite().getImage().getWidth() / 2, e.getPosition().y + e.getSprite().getImage().getHeight() / 2);
        gc.restore();
    }

    @Override
    public void onFrameUpdate()
    {
        PlayerEntity e = (PlayerEntity) DoodleApplication.getInstance().getCurrentRoom().findEntity(PlayerEntity.class);

        double dist = 64 / ((Math.max(getPosition().x, e.getPosition().x + e.getSprite().getImage().getWidth() / 2) - Math.min(getPosition().x, e.getPosition().x + e.getSprite().getImage().getWidth() / 2))
                + (Math.max(getPosition().y, e.getPosition().y + e.getSprite().getImage().getHeight() / 2) - Math.min(getPosition().y, e.getPosition().y + e.getSprite().getImage().getHeight() / 2)));
        setAlpha(dist);

    }
}
