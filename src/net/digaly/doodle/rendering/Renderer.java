package net.digaly.doodle.rendering;

import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Effect;
import net.digaly.doodle.Entity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Created by Tom Dobbelaere on 12/10/2016.
 */
public abstract class Renderer
{
    private Camera camera;
    private Group root;
    private List<Entity> entities;
    private Effect effect;

    public Renderer() {
        this.effect = new Bloom(1); //Small hack to enforce re-rendering to draw everything on top (effect causes re-draw)
    }

    public void renderFrame() {
        throw new NotImplementedException();
    }

    public void setCamera(Camera camera)
    {
        this.camera = camera;
    }

    public Camera getCamera()
    {
        return camera;
    }

    public void setRoot(Group root) {
        this.root = root;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public Group getRoot() {
        return this.root;
    }

    public Effect getEffect()
    {
        return effect;
    }

    public void setEffect(Effect effect)
    {
        this.effect = effect;
    }
}
