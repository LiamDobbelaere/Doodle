package net.digaly.doodle.rendering;

import javafx.scene.Node;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import net.digaly.doodle.Entity;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Tom Dobbelaere on 12/10/2016.
 */
public class NodeBasedRenderer extends Renderer
{
    private ConcurrentMap<Entity, ImageView> entityNodeMap;
    private Box blegh;

    public NodeBasedRenderer() {
        entityNodeMap = new ConcurrentHashMap<>();
        blegh = new Box(32, 32, 256);
        blegh.setEffect(new Bloom(0.2));
    }

    @Override
    public void renderFrame()
    {
        if (!getRoot().getChildren().contains(blegh)) {
            getRoot().getChildren().add(blegh);
        }

        addUntrackedEntities();
        deleteDestroyedEntities();

        for (Entity entity : getEntities()) {
            ImageView newNode = entityNodeMap.get(entity);
            newNode.setImage(entity.getSprite().getImage());
            newNode.setTranslateX(entity.getPosition().x - entity.getSprite().getOffset().x);
            newNode.setTranslateY(entity.getPosition().y - entity.getSprite().getOffset().y);
            newNode.setRotate(entity.getAngle());
            newNode.setOpacity(entity.getAlpha());
            newNode.setTranslateZ(entity.getDepth());

            newNode.setEffect(new Bloom(0.2));
            getCamera().setTranslateX(entity.getPosition().x - entity.getSprite().getOffset().x - getRoot().getScene().getWidth() / 2);
            getCamera().setTranslateY(entity.getPosition().y - entity.getSprite().getOffset().y - getRoot().getScene().getHeight() / 2);

        }

    }

    private void addUntrackedEntities() {
        for (Entity entity : getEntities()) {
            if (!entityNodeMap.containsKey(entity)) {
                ImageView newNode = new ImageView();
                entityNodeMap.put(entity, newNode);
                getRoot().getChildren().add(newNode);
            }
        }
    }

    private void deleteDestroyedEntities() {
        for (Entity entity : entityNodeMap.keySet()) {
            if (!getEntities().contains(entity)) {
                getRoot().getChildren().remove(entityNodeMap.get(entity));
                entityNodeMap.remove(entity);
            }
        }
    }
}
