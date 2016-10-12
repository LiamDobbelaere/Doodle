package net.digaly.doodle.rendering;

import javafx.animation.AnimationTimer;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import net.digaly.doodle.Entity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Map;

/**
 * Created by Tom Dobbelaere on 12/10/2016.
 */
public abstract class Renderer
{
    private Camera camera;
    private Group root;
    private List<Entity> entities;

    public Renderer() {

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
}
