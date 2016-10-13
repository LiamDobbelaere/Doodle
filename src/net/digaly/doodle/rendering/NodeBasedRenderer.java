package net.digaly.doodle.rendering;

import javafx.scene.canvas.Canvas;
import javafx.scene.effect.Bloom;
import javafx.scene.shape.Box;
import net.digaly.doodle.AnimatedSprite;
import net.digaly.doodle.Entity;
import net.digaly.doodle.events.FrameDrawListener;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Tom Dobbelaere on 12/10/2016.
 */
public class NodeBasedRenderer extends Renderer
{
    private ConcurrentMap<Entity, Canvas> entityNodeMap;

    public NodeBasedRenderer() {
        entityNodeMap = new ConcurrentHashMap<>();
    }

    @Override
    public void renderFrame()
    {
        addUntrackedEntities();
        deleteDestroyedEntities();

        for (Entity entity : getEntities()) {
            Canvas newNode = entityNodeMap.get(entity);
            newNode.setWidth(entity.getWidth());
            newNode.setHeight(entity.getHeight());
            newNode.getGraphicsContext2D().clearRect(0, 0, entity.getWidth(), entity.getHeight());

            if (entity instanceof FrameDrawListener) {
                ((FrameDrawListener) entity).onFrameDraw(newNode.getGraphicsContext2D());
            } else {
                newNode.getGraphicsContext2D().drawImage(entity.getSprite().getImage(), 0, 0,
                        entity.getWidth(), entity.getHeight());
            }

            if (entity.getSprite() instanceof AnimatedSprite) {
                ((AnimatedSprite) entity.getSprite()).incrementFramesPassed();
            }

            newNode.setTranslateX(entity.getPosition().x - entity.getSprite().getOffset().x);
            newNode.setTranslateY(entity.getPosition().y - entity.getSprite().getOffset().y);
            newNode.setRotate(entity.getAngle());
            newNode.setOpacity(entity.getAlpha());
            newNode.toFront();

            newNode.setEffect(getEffect());
            //getCamera().setTranslateX(entity.getPosition().x - entity.getSprite().getOffset().x - getRoot().getScene().getWidth() / 2);
            //getCamera().setTranslateY(entity.getPosition().y - entity.getSprite().getOffset().y - getRoot().getScene().getHeight() / 2);
        }



    }

    private void addUntrackedEntities() {
        for (Entity entity : getEntities()) {
            if (!entityNodeMap.containsKey(entity)) {
                Canvas newNode = new Canvas();
                newNode.setTranslateZ(-1); //Black border fix
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
