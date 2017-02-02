package service;

import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import socket.ChatWebSocket;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatWebSocketHolder {

    private Set<ChatWebSocket> connections;

    public ChatWebSocketHolder() {
        connections = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void add(ChatWebSocket socket) {
        connections.add(socket);
    }

    public void remove(ChatWebSocket socket) {
        connections.remove(socket);
    }

    public void send(String message) {
        for (ChatWebSocket socket : connections) {
            socket.sendMessage(message);
        }
    }
}
