package servlet;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import service.ChatWebSocketHolder;
import socket.ChatWebSocket;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "ChatServlet", urlPatterns = "/chat")
public class WebSocketChatServlet extends WebSocketServlet {

    private static final long LOGOUT_TIME = 10 * 60 * 1000;

    private ChatWebSocketHolder connections;

    public WebSocketChatServlet() {
        connections = new ChatWebSocketHolder();
    }

    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((req, resp) -> new ChatWebSocket(connections));
    }
}
