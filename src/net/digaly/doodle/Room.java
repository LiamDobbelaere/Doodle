package net.digaly.doodle;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class Room
{
    private List<Entity> entities;
    private Dimension size;

    private Sprite background;

    public Room(int width, int height) {
        this.entities = new ArrayList<>();
        this.size = new Dimension(width, height);
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);

        if (entity instanceof FrameUpdateListener) {
            DoodleApplication.getInstance().addFrameUpdateListener((FrameUpdateListener) entity);
        }

        if (entity instanceof KeyEventListener) {
            DoodleApplication.getInstance().addKeyEventListener((KeyEventListener) entity);
        }

        if (entity instanceof FrameDrawListener) {
            DoodleApplication.getInstance().addFrameDrawListener((FrameDrawListener) entity);
        }
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);

        if (entity instanceof FrameUpdateListener) {
            DoodleApplication.getInstance().removeFrameUpdateListener((FrameUpdateListener) entity);
        }

        if (entity instanceof KeyEventListener) {
            DoodleApplication.getInstance().removeKeyEventListener((KeyEventListener) entity);
        }

        if (entity instanceof FrameDrawListener) {
            DoodleApplication.getInstance().removeFrameDrawListener((FrameDrawListener) entity);
        }

        entity = null;
    }

    public Entity findEntity(Class search) {
        for (Entity entity : entities) {
            if (entity.getClass() == search) {
                return entity;
            }
        }

        return null;
    }

    protected List<Entity> getEntities() {
        return this.entities;
    }

    public Dimension getSize() {
        return this.size;
    }

    public Sprite getBackground()
    {
        return background;
    }

    public void setBackground(Sprite background)
    {
        this.background = background;
    }
}
