<img src="logo.png" width="300">

#What is Doodle?
Doodle is a framework meant for creating games in Java. It wraps JavaFX for rendering, input handling, collisions and more.

It provides you with classes like Entity, Room, KeyEventListener,.. to put your game together in an easier way.  

The structure of Doodle is based off of Game Maker (Rooms, Sprites,..).

A better documentation is planned in the future.

#Reasons to use and not use Doodle

Doodle was created for rapid prototyping of games in Java. It is **not** a thoroughly production-tested framework.

**Use Doodle if you want:**
* Rapid prototyping
* An easy to understand framework
* A starting point to write your own game engine in JavaFX
* To spend more time developing your game and less time handling low-level stuff
* A native Java solution for writing a game engine

**DON'T use Doodle if you need:**
* An optimized game engine that can perform at high stress (1000+ moving sprites)
* More advanced 3D operations
* Control at the low-level of things
* A professional framework (seriously, use something like LibGDX instead)

#How do I use Doodle in my project?
Here's an example of how your program might look.
**Note: A better documentation is on the way**

**In your main class**
```java
    public static void main(String[] args) {
        settings.setTitle("Doodle Sample");
        NodeBasedRenderer renderer = new NodeBasedRenderer();
        //renderer.setEffect(new Bloom(0.2));
        settings.setRenderer(renderer);
        //settings.setDebugMode(true);

        /*settings.setDebugMode(false);
        settings.setFullscreen(true);*/

        launch(args);
    }

    @Override
    public void onApplicationReady()
    {
        Room myRoom = new Room(4096, 4096);
        setCurrentRoom(myRoom);
        
        Entity player = new PlayerEntity(64, 64);
        myRoom.addEntity(player);
    }
```
You may of course delegate the room creation to another class and even create a class that extends room for the entity creation.
Note that ```launch(args)``` is blocking, so any statements after ```launch(args)``` will only get called when the main window is closed.
After Doodle is ready setting up, ```onApplicationReady()``` is called.

**PlayerEntity class**

```java
public class PlayerEntity extends Entity implements KeyEventListener
{
    public PlayerEntity(int x, int y)
    {
        super(new Sprite("ship.png"), x, y);
    }

    @Override
    public void onKeyEvent(KeyEvent keyEvent, KeyState keyState)
    {
        if (keyState == KeyState.HOLDING) {
            switch (keyEvent.getCode()) {
                case UP:
                    getPosition().translate(0, -5);
                    break;
                case DOWN:
                    getPosition().translate(0, 5);
                    break;
                case LEFT:
                    getPosition().translate(-5, 0);
                    break;
                case RIGHT:
                    getPosition().translate(5, 0);
                    break;
            }
        }
    }
}
```

Whenever you're interested in receiving certain net.digaly.doodle.events from the application, such as keyboard input or frame updates, your class can implement one of the following:

* MouseEventListener  
_Use when you want to know about mouse clicks, presses, releases and more_
* KeyEventListener  
_Use when you want to know about button presses, releases and holds_
* FrameUpdateListener  
_Use when you want to know when a new frame starts_
* FrameDrawListener  
_Use when you want to know when a new frame is drawn, and maybe add custom draw code for your entity_
* CollisionEventListener  
_Use when you want to know when an entity Enters, Exits or Stays in collision with another entity. (Note that your entity must have a collider assigned by using setCollider(new BoxCollider(this, 0, 0, getWidth(), getHeight()) for example)_

When an entity is **added to a room**, it will automatically subscribe to the events that it needs and receive updates in the respective methods like you see above.
