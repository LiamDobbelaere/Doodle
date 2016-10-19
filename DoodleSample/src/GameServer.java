import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import net.digaly.doodle.*;
import net.digaly.doodle.rendering.NodeBasedRenderer;
import org.json.JSONObject;

import java.util.Scanner;

/**
 * Created by Tom Dobbelaere on 18/10/2016.
 */
public class GameServer extends DoodleApplication
{
    public static void main(String[] args) throws InterruptedException
    {
        settings.setTitle("Doodle Server");
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

        PlayerEntity proxy = new PlayerEntity(0, 0);
        myRoom.addEntity(proxy);

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9999);

        final SocketIOServer server = new SocketIOServer(config);
        server.addConnectListener(new ConnectListener()
        {
            @Override
            public void onConnect(SocketIOClient socketIOClient)
            {
                System.out.println("[event:connect] " + socketIOClient.getRemoteAddress());
            }
        });

        server.addEventListener("digaly-event", String.class, new DataListener<String>()
        {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception
            {
                System.out.println(s);
            }
        });

        server.addEventListener("update-position", String.class, new DataListener<String>()
        {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception
            {
                JSONObject in = new JSONObject(s);

                proxy.setAngle(in.getInt("angle"));
                proxy.setPosition(new Point(in.getDouble("x"), in.getDouble("y")));
            }
        });

        server.start();
    }
}
