import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.animation.AnimationTimer;
import net.digaly.doodle.*;
import net.digaly.doodle.rendering.NodeBasedRenderer;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by Tom Dobbelaere on 18/10/2016.
 */
public class GameClient extends DoodleApplication
{
    public static void main(String[] args) throws URISyntaxException
    {
        settings.setTitle("Doodle Client");
        NodeBasedRenderer renderer = new NodeBasedRenderer();
        settings.setRenderer(renderer);

        launch(args);
    }

    @Override
    public void onApplicationReady()
    {
        Room myRoom = new Room(4096, 4096);
        setCurrentRoom(myRoom);

        Entity background = new BackgroundEntity(new Sprite());
        background.setSprite(new Sprite("doodle\\background-tileable.png"));
        background.getSprite().setOffset(new Point(0, 0));
        myRoom.addEntity(background);

        final Socket socket;
        try
        {
            socket = IO.socket("http://127.0.0.1:9999/");
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
            return;
        }


        socket.connect();

        socket.on("connect", new Emitter.Listener()
        {
            @Override
            public void call(Object... objects)
            {
                System.out.println("[event:connect] Client connected!");

                PlayerEntity me = new PlayerEntity(0, 0);
                getCurrentRoom().addEntity(me);

                new AnimationTimer()
                {
                    @Override
                    public void handle(long now)
                    {
                        System.out.println(now - System.currentTimeMillis());

                        JSONObject object = new JSONObject();
                        try
                        {
                            object.put("angle", me.getAngle());
                            object.put("x", me.getPosition().x);
                            object.put("y", me.getPosition().y);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                        socket.emit("update-position", object.toString());
                    }
                }.start();
            }
        });

        socket.on("digaly-event", new Emitter.Listener()
        {
            @Override
            public void call(Object... objects)
            {
                String ayy = (String) objects[0];

                System.out.println("[event:digaly-event] Received data: " + ayy);
            }
        });

        //socket.emit("update-position", "ayy");
    }
}
