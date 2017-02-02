package socket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import service.ChatWebSocketHolder;

import java.io.IOException;

@WebSocket
public class ChatWebSocket {

    private ChatWebSocketHolder connections;
    private Session session;

    public ChatWebSocket(ChatWebSocketHolder connections) {
        this.connections = connections;
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        this.session = session;
        connections.add(this);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        connections.remove(this);
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
        connections.send(message);
    }

    public void sendMessage(String message) {
        try {
            session.getRemote().sendString(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
