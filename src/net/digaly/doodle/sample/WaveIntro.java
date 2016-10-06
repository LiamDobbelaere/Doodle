package net.digaly.doodle.sample;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import net.digaly.doodle.DoodleApplication;
import net.digaly.doodle.Entity;
import net.digaly.doodle.Room;
import net.digaly.doodle.Sprite;
import net.digaly.doodle.events.FrameDrawListener;

/**
 * Created by Tom Dobbelaere on 6/10/2016.
 */
public class WaveIntro extends Entity implements FrameDrawListener
{
    private String text;
    private String subtext;
    private int textPosition;
    private int subtextPosition;
    private int frames;
    private int doneFrames;

    public WaveIntro(String text, String subtext)
    {
        super(null, 0, 0);
        setDepth(1000);
        this.text = text;
        this.subtext = subtext;
        this.textPosition = 0;
        this.subtextPosition = 0;
        this.frames = 0;
        this.doneFrames = 0;
        setAlpha(0.8);
    }

    @Override
    public void onFrameDraw(GraphicsContext gc)
    {
        Room currentRoom = DoodleApplication.getInstance().getCurrentRoom();

        gc.setFill(Color.DARKBLUE);
        gc.save();
        //gc.setGlobalAlpha(0.7);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, currentRoom.getSize().getWidth(), currentRoom.getSize().getHeight());
        gc.restore();
        gc.setFont(new Font(72));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.WHITE);
        gc.fillText(text.substring(0, textPosition), currentRoom.getSize().getWidth() / 2, currentRoom.getSize().getHeight() / 2);

        frames++;

        if (frames % 10 == 0) {
            if (textPosition < text.length()) {
                DoodleApplication.getInstance().getSoundManager().playSound("res\\type.wav", 0.2);
                textPosition += 1;
            }
        }

        if (frames % 2 == 0) {
            if (frames > 90) {
                if (subtextPosition < subtext.length()) {
                    DoodleApplication.getInstance().getSoundManager().playSound("res\\type2.wav", 0.2);
                    subtextPosition += 1;
                }
            }
        }

        if (frames > 90) {
            gc.setFont(new Font(48));
            gc.setFill(Color.YELLOW);
            gc.fillText(subtext.substring(0, subtextPosition), currentRoom.getSize().getWidth() / 2, currentRoom.getSize().getHeight() / 2 + 64);
        }

        if (subtextPosition == subtext.length()) {
            doneFrames++;
        }

        if (doneFrames > 60) {
            setAlpha(getAlpha() - 0.02);
        }

        if (getAlpha() <= 0) {
            DoodleApplication.getInstance().getSoundManager().playMusic("res\\music.mp3", 0.5);
            destroy();
        }
    }
}
