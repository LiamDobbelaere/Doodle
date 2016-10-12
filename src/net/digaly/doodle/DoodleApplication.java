package net.digaly.doodle;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import net.digaly.doodle.audio.SoundManager;
import net.digaly.doodle.events.*;
import net.digaly.doodle.input.InputManager;
import net.digaly.doodle.rendering.Renderer;
import net.digaly.doodle.util.DoodleUtil;

import java.awt.*;
import java.util.Hashtable;

/**
 * Created by Tom Dobbelaere on 1/10/2016.
 */
public abstract class DoodleApplication extends Application
{
    protected static ApplicationSettings settings = new ApplicationSettings();
    private Room currentRoom;
    private Renderer renderer;
    private EventDispatcher eventDispatcher;
    private InputManager inputManager;
    private FrameUpdater frameUpdater;

    //Initialize inputmanager and stuff in start!!!
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.renderer = settings.getRenderer();
        this.eventDispatcher = new EventDispatcher();
        this.frameUpdater = new FrameUpdater();

        setCurrentRoom(new Room(800, 600));

        primaryStage.setTitle(settings.getTitle());
        primaryStage.setFullScreen(settings.isFullscreen());
        if (!settings.getIconPath().equals("")) {
            primaryStage.getIcons().add(new Image(settings.getIconPath()));
        }

        Group root = new Group();
        Scene mainScene = new Scene(root, currentRoom.getSize().getWidth(), currentRoom.getSize().getHeight(), false, SceneAntialiasing.BALANCED);
        mainScene.setFill(Color.BLACK);

        Camera camera = new PerspectiveCamera();
        mainScene.setCamera(camera);

        primaryStage.setScene(mainScene);

        renderer.setCamera(mainScene.getCamera());
        renderer.setRoot(root);
        renderer.setEntities(currentRoom.getEntities());

        inputManager = new InputManager(mainScene);
        inputManager.setEventDispatcher(eventDispatcher);

        frameUpdater.setEventDispatcher(eventDispatcher);
        frameUpdater.setRenderer(renderer);
        frameUpdater.setInputManager(inputManager);
        frameUpdater.start();

        //Canvas canvas = new Canvas(instance.currentRoom.getSize().getWidth(), instance.currentRoom.getSize().getHeight());
        //root.getChildren().add(canvas);*/

        primaryStage.show();

        onApplicationReady();
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
        this.currentRoom.setEventDispatcher(this.eventDispatcher);
        renderer.setEntities(this.currentRoom.getEntities());
    }

    public abstract void onApplicationReady();
}
