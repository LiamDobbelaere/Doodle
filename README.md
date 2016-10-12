<img src="logo.png" width="300">

#What is Doodle?
Doodle is a framework meant for creating games in Java. It wraps JavaFX for rendering, input handling and more.

It provides you with classes like Entity, Room, KeyEventListener,.. to put your game together in an easier way.  

The structure of Doodle is based off of Game Maker (Rooms, Sprites,..).

**Note that this project is still heavily in development**

**This readme will need new examples, as the core has been completely rewritten in the upcoming revision and as a result will change the API**

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

**Your main class**
```java
public static void main(String[] args) {
        DoodleGame myGame = new DoodleGame();
        DoodleApplication app = DoodleApplication.getInstance();

        app.getEventDispatcher().addApplicationReadyListener(myGame);
        app.setTitle("Doodle Sample Game");
        app.setIcon("icon.png");
        app.run();
}
```
DoodleGame is a class *you* make, that may contain a room and that room may contain entities.
Note that ```app.run()``` is blocking, so any statements after ```app.run()``` will only get called when the main window is closed.
This is why we move our game logic to a separate class called ```DoodleGame```.  
**You have to set a current room before calling app.run()**

**DoodleGame class**
```java
public class DoodleGame implements ApplicationReadyListener
{
    private Room currentRoom;
    private Entity testEntity;

    public DoodleGame() {

    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    @Override
    public void onApplicationReady()
    {
        this.currentRoom = new LevelRoom();
        DoodleApplication.getInstance().setCurrentRoom(currentRoom);
        //DoodleApplication.getInstance().playMusic("res\\music.mp3");
        //DoodleApplication.getInstance().setMusicVolume(0.5);
    }
}
```

**LevelRoom class**
```java
public class LevelRoom extends Room
{
    public LevelRoom()
    {
        super(1024, 768);

        this.addEntity(new PlayerEntity(250, 250));
    }
}
```

At this point, our game will show a blank window that's 1024 by 768 wide.  
Let's create the PlayerEntity class to add some life to our game!

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

When an entity is **added to a room**, it will automatically subscribe to the net.digaly.doodle.events that it needs and receive updates in the respective methods like you see above.
