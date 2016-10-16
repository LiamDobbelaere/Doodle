package net.digaly.doodle.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Tom Dobbelaere on 4/10/2016.
 */
public class SoundManager
{
    private MediaPlayer musicPlayer;
    private Map<String, Integer> soundPool;
    private List<MediaPlayer> soundSaver;
    private int maxSoundSpawn;

    public SoundManager() {
        soundPool = new Hashtable<>();
        soundSaver = new CopyOnWriteArrayList<>();
        maxSoundSpawn = 10;
    }

    public void playMusic(String filename, double volume) {
        Media media = new Media(new File(filename).toURI().toString()); //replace /Movies/test.mp3 with your file

        if (musicPlayer != null) {
            musicPlayer.stop();
        }

        musicPlayer = new MediaPlayer(media);
        musicPlayer.setVolume(volume);
        musicPlayer.play();
        musicPlayer.setOnEndOfMedia(() -> musicPlayer.seek(Duration.ZERO));
    }

    public void setMusicVolume(double volume) {
        musicPlayer.setVolume(volume);
    }

    private MediaPlayer spawnSound(String filename) {
        soundPool.putIfAbsent(filename, 1);

        //Only allow up to 3 of the same sound to be played for perfomance reasons
        if (soundPool.get(filename) < maxSoundSpawn) {
            soundPool.put(filename, soundPool.get(filename) + 1);

            Media media = new Media(new File(filename).toURI().toString()); //replace /Movies/test.mp3 with your file
            MediaPlayer m = new MediaPlayer(media);
            soundSaver.add(m);
            m.setOnEndOfMedia(() -> {
                soundPool.put(filename, soundPool.get(filename) - 1);
                soundSaver.remove(m);
            });

            return m;
        }

        return null;
    }

    public void playSound(String filename) {
        MediaPlayer mediaPlayer = spawnSound(filename);

        if (mediaPlayer != null) mediaPlayer.play();
    }

    public void playSound(String filename, double volume) {
        MediaPlayer mediaPlayer = spawnSound(filename);

        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        }
    }

    public int getSoundpoolSize() {
        int total = 0;

        for (Integer value : soundPool.values()) {
            total += value;
        }

        return total;
    }

    public void setMaxSoundSpawn(int value) {
        maxSoundSpawn = value;
    }
}
