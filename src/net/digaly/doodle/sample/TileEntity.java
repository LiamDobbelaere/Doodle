package net.digaly.doodle.sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.digaly.doodle.*;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class TileEntity extends Entity implements FrameDrawListener, FrameUpdateListener
{
    private PlayerEntity player;

    public TileEntity(Sprite sprite, double x, double y)
    {
        super(sprite, x, y);
    }

    @Override
    public void onFrameDraw(GraphicsContext gc)
    {
        gc.save();

        double alpha = getAlpha();

        gc.setGlobalAlpha(alpha / 3);

        double size = 32 + alpha * 32;

        gc.drawImage(getSprite().getImage(), getPosition().x - size / 2, getPosition().y - size / 2, size, size);
        //gc.setFill(Color.YELLOW);
        //gc.fillRect(getPosition().x - size / 2, getPosition().y - size / 2, size, size);
        //gc.fillOval(getPosition().x - 4, getPosition().y - 4, 8, 8);
        //gc.setStroke(Color.WHITE);

        //gc.strokeLine(getPosition().x, getPosition().y, e.getPosition().x + e.getSprite().getImage().getWidth() / 2, e.getPosition().y + e.getSprite().getImage().getHeight() / 2);
        gc.restore();
    }

    @Override
    public void onFrameUpdate()
    {
        if (player == null) player = (PlayerEntity) DoodleApplication.getInstance().getCurrentRoom().findEntity(PlayerEntity.class);

        double dist = 64 / ((Math.max(getPosition().x, player.getPosition().x + player.getSprite().getImage().getWidth() / 2) - Math.min(getPosition().x, player.getPosition().x + player.getSprite().getImage().getWidth() / 2))
                + (Math.max(getPosition().y, player.getPosition().y + player.getSprite().getImage().getHeight() / 2) - Math.min(getPosition().y, player.getPosition().y + player.getSprite().getImage().getHeight() / 2)));
        setAlpha(dist);

    }
}
