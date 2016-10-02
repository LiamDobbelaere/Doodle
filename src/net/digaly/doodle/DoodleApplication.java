package net.digaly.doodle;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import javafx.scene.paint.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tom Dobbelaere on 1/10/2016.
 */
public class DoodleApplication extends Application
{
    private static DoodleApplication instance;
    private Room currentRoom;
    private List<FrameUpdateListener> frameUpdateListeners;
    private List<KeyEventListener> keyEventListeners;
    private HashMap<KeyCode, KeyEvent> heldKeys;
    private GraphicsContext gc;

    public static DoodleApplication getInstance() {
        if (instance == null) {
            instance = new DoodleApplication();
            instance.frameUpdateListeners = new ArrayList<>();
            instance.keyEventListeners = new ArrayList<>();
            instance.heldKeys = new HashMap<>();
        }

        return instance;
    }

    public void run() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("DoodleApplication");

        Group root = new Group();
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);

        Canvas canvas = new Canvas(instance.currentRoom.getSize().getWidth(), instance.currentRoom.getSize().getHeight());
        root.getChildren().add(canvas);

        instance.gc = canvas.getGraphicsContext2D();
        final long startNanoTime = System.nanoTime();

        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                instance.heldKeys.put(event.getCode(), event);

                instance.notifyKeyEventListeners(event, KeyState.PRESSED);
            }
        });

        mainScene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                instance.heldKeys.remove(event.getCode());

                instance.notifyKeyEventListeners(event, KeyState.RELEASED);
            }
        });

        new AnimationTimer() {
            @Override
            public void handle(long now)
            {
                double t = (now - startNanoTime) / 1000000000.0;

                instance.onFrame(t);
            }
        }.start();

        primaryStage.show();
    }

    private void onFrame(double t) {
        notifyFrameUpdateListeners(t);

        for (KeyCode key : instance.heldKeys.keySet()) {
            notifyKeyEventListeners(instance.heldKeys.get(key), KeyState.HOLDING);
        }

        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        for (Entity entity : instance.getCurrentRoom().getEntities()) {
            gc.save();
            Rotate rotate = new Rotate(entity.getAngle(), entity.getPosition().x + entity.getSprite().getImage().getWidth() / 2,
                    entity.getPosition().y + entity.getSprite().getImage().getHeight() / 2);
            gc.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
            gc.drawImage(entity.getSprite().getImage(), entity.getPosition().x, entity.getPosition().y);
            gc.restore();
        }
    }

    public Room getCurrentRoom()
    {
        return instance.currentRoom;
    }

    public void setCurrentRoom(Room room)
    {
        instance.currentRoom = room;
    }

    public void addFrameUpdateListener(FrameUpdateListener listener) {
        instance.frameUpdateListeners.add(listener);
    }

    public void removeFrameUpdateListener(FrameUpdateListener listener) {
        instance.frameUpdateListeners.remove(listener);
    }

    private void notifyFrameUpdateListeners(double t) {
        for (FrameUpdateListener listener : instance.frameUpdateListeners) {
            listener.onFrameUpdate(t);
        }
    }

    public void addKeyEventListener(KeyEventListener listener) {
        instance.keyEventListeners.add(listener);
    }

    public void removeKeyEventListener(KeyEventListener listener) {
        instance.keyEventListeners.remove(listener);
    }

    private void notifyKeyEventListeners(KeyEvent keyEvent, KeyState keyState) {
        for (KeyEventListener listener : instance.keyEventListeners) {
            listener.onKeyEvent(keyEvent, keyState);
        }
    }
}
