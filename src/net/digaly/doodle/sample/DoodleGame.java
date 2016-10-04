package net.digaly.doodle.sample;

import net.digaly.doodle.Entity;
import net.digaly.doodle.Room;
import net.digaly.doodle.events.ApplicationReadyListener;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class DoodleGame implements ApplicationReadyListener
{
    private Room currentRoom;
    private Entity testEntity;

    public DoodleGame() {
        Room testRoom = new LevelRoom(1024, 768);
        this.currentRoom = testRoom;
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    @Override
    public void onApplicationReady()
    {
        //DoodleApplication.getInstance().playMusic("res\\music.mp3");
        //DoodleApplication.getInstance().setMusicVolume(0.5);
    }
}
