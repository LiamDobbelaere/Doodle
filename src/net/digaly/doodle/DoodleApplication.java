package net.digaly.doodle;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
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
import net.digaly.doodle.util.DoodleUtil;

import java.util.Hashtable;

/**
 * Created by Tom Dobbelaere on 1/10/2016.
 */
public class DoodleApplication extends Application
{
    protected static ApplicationSettings settings = new ApplicationSettings();

    //Initialize inputmanager and stuff in start!!!
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle(settings.getTitle());
        primaryStage.setFullScreen(settings.isFullscreen());
        if (!settings.getIconPath().equals("")) {
            primaryStage.getIcons().add(new Image(settings.getIconPath()));
        }

        Group root = new Group();
        Scene mainScene = new Scene(root);
        mainScene.setFill(Color.BLACK);
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }
}
