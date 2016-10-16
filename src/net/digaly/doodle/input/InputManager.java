package net.digaly.doodle.input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import net.digaly.doodle.events.EventDispatcher;
import net.digaly.doodle.events.KeyState;
import net.digaly.doodle.events.MouseState;
import net.digaly.doodle.events.NoEventDispatcher;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Tom Dobbelaere on 12/10/2016.
 */
public class InputManager
{
    private Scene scene;
    private EventDispatcher eventDispatcher;
    private Map<KeyCode, KeyEvent> heldKeys;
    private Map<MouseButton, MouseEvent> heldMouse;

    public InputManager(Scene scene) {
        this.eventDispatcher = new NoEventDispatcher();
        this.scene = scene;
        this.heldKeys = new Hashtable<>();
        this.heldMouse = new Hashtable<>();

        if (scene != null) bindInputs();
    }

    public void setEventDispatcher(EventDispatcher eventDispatcher)
    {
        this.eventDispatcher = eventDispatcher;
    }

    private void bindInputs() {
        scene.setOnMouseClicked(event -> eventDispatcher.notifyMouseEventListeners(event, MouseState.CLICKED, false));

        scene.setOnMousePressed(event -> {
            heldMouse.put(event.getButton(), event);
            eventDispatcher.notifyMouseEventListeners(event, MouseState.PRESSED, false);
        });

        scene.setOnMouseReleased(event -> {
            heldMouse.remove(event.getButton());
            eventDispatcher.notifyMouseEventListeners(event, MouseState.RELEASED, false);
        });

        scene.setOnMouseMoved(event -> eventDispatcher.notifyMouseEventListeners(event, MouseState.MOVED, false));
        scene.setOnMouseDragged(event -> eventDispatcher.notifyMouseEventListeners(event, MouseState.DRAGGED, false));


        scene.setOnKeyPressed(event -> {
            heldKeys.put(event.getCode(), event);
            eventDispatcher.notifyKeyEventListeners(event, KeyState.PRESSED);
        });

        scene.setOnKeyReleased(event -> {
            heldKeys.remove(event.getCode());
            eventDispatcher.notifyKeyEventListeners(event, KeyState.RELEASED);
        });
    }

    public void notifyHeldKeys() {
        for (KeyCode key : heldKeys.keySet()) {
            eventDispatcher.notifyKeyEventListeners(heldKeys.get(key), KeyState.HOLDING);
        }

        for (MouseButton button : heldMouse.keySet()) {
            eventDispatcher.notifyMouseEventListeners(heldMouse.get(button), MouseState.HOLDING, false);
        }
    }
}
