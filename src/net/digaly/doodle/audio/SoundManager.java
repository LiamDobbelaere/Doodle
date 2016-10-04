package net.digaly.doodle.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Tom Dobbelaere on 4/10/2016.
 */
public class SoundManager
{
    private MediaPlayer musicPlayer;
    private List<MediaPlayer> soundPool;

    public SoundManager() {
        soundPool = new CopyOnWriteArrayList<>();
    }

    public void playMusic(String filename) {
        Media media = new Media(new File(filename).toURI().toString()); //replace /Movies/test.mp3 with your file

        if (musicPlayer != null) {
            musicPlayer.stop();
        }

        musicPlayer = new MediaPlayer(media);
        musicPlayer.play();
        musicPlayer.setOnEndOfMedia(() -> musicPlayer.seek(Duration.ZERO));
    }

    public void setMusicVolume(double volume) {
        musicPlayer.setVolume(volume);
    }



    private MediaPlayer spawnSound(String filename) {
        Media media = new Media(new File(filename).toURI().toString()); //replace /Movies/test.mp3 with your file
        MediaPlayer m = new MediaPlayer(media);
        soundPool.add(m);
        m.setOnEndOfMedia(() -> soundPool.remove(m));
        return m;
    }

    public void playSound(String filename) {
        spawnSound(filename).play();
    }

    public void playSound(String filename, double volume) {
        MediaPlayer m = spawnSound(filename);
        m.setVolume(volume);
        m.play();
    }
}
