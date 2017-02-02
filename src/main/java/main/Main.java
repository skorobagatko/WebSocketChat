package main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import servlet.WebSocketChatServlet;

import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) {
        try {
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");

            ResourceHandler resource_handler = new ResourceHandler();
            resource_handler.setResourceBase("src/main/webapp");
            resource_handler.setDirectoriesListed(true);
            resource_handler.setWelcomeFiles(new String[]{"index.html"});

            WebAppContext webAppContext = new WebAppContext();
            webAppContext.setContextPath("/");
            webAppContext.setResourceBase("src/main/webapp");

            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{resource_handler, context, webAppContext});

            InetSocketAddress address = new InetSocketAddress("localhost", 8080);
            Server server = new Server(address);
            server.setHandler(handlers);
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
