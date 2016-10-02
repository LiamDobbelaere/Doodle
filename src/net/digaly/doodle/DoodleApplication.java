package net.digaly.doodle;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Tom Dobbelaere on 1/10/2016.
 */
public class DoodleApplication extends Application
{
    private static DoodleApplication instance;
    private Room currentRoom;
    private List<FrameUpdateListener> frameUpdateListeners;
    private List<FrameDrawListener> frameDrawListeners;
    private List<KeyEventListener> keyEventListeners;
    private List<ApplicationReadyListener> applicationReadyListeners;
    private HashMap<KeyCode, KeyEvent> heldKeys;
    private GraphicsContext gc;
    private MediaPlayer musicPlayer;


    public static DoodleApplication getInstance() {
        if (instance == null) {
            instance = new DoodleApplication();
            instance.frameUpdateListeners = new CopyOnWriteArrayList<>();
            instance.keyEventListeners = new CopyOnWriteArrayList<>();
            instance.applicationReadyListeners = new CopyOnWriteArrayList<>();
            instance.frameDrawListeners = new CopyOnWriteArrayList<>();
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

        notifyApplicationReadyListener();
        primaryStage.show();
    }

    private void onFrame(double t) {
        notifyFrameUpdateListeners();

        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        for (KeyCode key : instance.heldKeys.keySet()) {
            notifyKeyEventListeners(instance.heldKeys.get(key), KeyState.HOLDING);
        }

        if (!(getCurrentRoom() instanceof FrameDrawListener)) {
            gc.drawImage(getCurrentRoom().getBackground().getImage(), 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        } else {
            ((FrameDrawListener) getCurrentRoom()).onFrameDraw(gc);
        }


        for (Entity entity : instance.getCurrentRoom().getEntities()) {
            if (!(entity instanceof FrameDrawListener)) {
                gc.save();
                gc.setGlobalAlpha(entity.getAlpha());
                entity.draw(gc);
                gc.restore();
            }
        }

        notifyFrameDrawListeners(gc);
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

    private void notifyFrameUpdateListeners() {
        for (FrameUpdateListener listener : instance.frameUpdateListeners) {
            listener.onFrameUpdate();
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

    public void addApplicationReadyListener(ApplicationReadyListener listener) {
        instance.applicationReadyListeners.add(listener);
    }

    public void removeApplicationReadyListener(ApplicationReadyListener listener) {
        instance.applicationReadyListeners.remove(listener);
    }

    private void notifyApplicationReadyListener() {
        for (ApplicationReadyListener listener : instance.applicationReadyListeners) {
            listener.onApplicationReady();
        }
    }

    public void addFrameDrawListener(FrameDrawListener listener) {
        instance.frameDrawListeners.add(listener);
    }

    public void removeFrameDrawListener(FrameDrawListener listener) {
        instance.frameDrawListeners.remove(listener);
    }

    private void notifyFrameDrawListeners(GraphicsContext passedgc) {
        for (FrameDrawListener listener : instance.frameDrawListeners) {
            listener.onFrameDraw(passedgc);
        }
    }

    public void playMusic(String filename) {
        Media media = new Media(new File(filename).toURI().toString()); //replace /Movies/test.mp3 with your file

        if (instance.musicPlayer != null) {
            instance.musicPlayer.stop();
        }

        instance.musicPlayer = new MediaPlayer(media);
        instance.musicPlayer.play();
        instance.musicPlayer.setOnEndOfMedia(new Runnable()
        {
            @Override
            public void run()
            {
                instance.musicPlayer.seek(Duration.ZERO);
            }
        });
    }

    public void setMusicVolume(double volume) {
        instance.musicPlayer.setVolume(volume);
    }
}
