package net.digaly.doodle;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.digaly.doodle.audio.SoundManager;
import net.digaly.doodle.events.EventDispatcher;
import net.digaly.doodle.input.InputManager;
import net.digaly.doodle.rendering.Renderer;

/**
 * Created by Tom Dobbelaere on 1/10/2016.
 */
public abstract class DoodleApplication extends Application
{
    protected static ApplicationSettings settings = new ApplicationSettings();
    private Room currentRoom;
    private Renderer renderer;
    private Stage stage;
    private EventDispatcher eventDispatcher;
    private InputManager inputManager;
    private FrameUpdater frameUpdater;
    private SoundManager soundManager;

    //Initialize inputmanager and stuff in start!!!
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.renderer = settings.getRenderer();
        this.eventDispatcher = new EventDispatcher();
        this.frameUpdater = new FrameUpdater();
        this.soundManager = new SoundManager();
        this.stage = primaryStage;

        setCurrentRoom(new Room(800, 600));

        stage.setTitle(settings.getTitle());
        stage.setFullScreen(settings.isFullscreen());
        if (!settings.getIconPath().equals("")) {
            stage.getIcons().add(new Image(settings.getIconPath()));
        }

        Group root = new Group();
        Scene mainScene = new Scene(root, currentRoom.getSize().getWidth(), currentRoom.getSize().getHeight(), settings.getDepthBuffer(), SceneAntialiasing.BALANCED);
        mainScene.setFill(Color.BLACK);

        Camera camera = new PerspectiveCamera();
        mainScene.setCamera(camera);

        stage.setScene(mainScene);

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

        stage.show();

        onApplicationReady();
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
        this.currentRoom.setEventDispatcher(this.eventDispatcher);
        this.currentRoom.setRenderer(this.renderer);
        this.currentRoom.setSoundManager(this.soundManager);

        renderer.setEntities(this.currentRoom.getEntities());

        stage.setWidth(this.currentRoom.getSize().getWidth());
        stage.setHeight(this.currentRoom.getSize().getHeight());
    }

    public abstract void onApplicationReady();
}
