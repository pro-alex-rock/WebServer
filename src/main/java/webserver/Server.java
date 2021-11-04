package webserver;

import webserver.controller.Handler;
import webserver.controller.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private String staticResourcePath;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getStaticResourcePath() {
        return staticResourcePath;
    }

    public void setStaticResourcePath(String staticResourcePath) {
        this.staticResourcePath = staticResourcePath;
    }

    public void start() {
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     InputStream inputStream = socket.getInputStream();
                     OutputStream outputStream = socket.getOutputStream()) {
                    Handler handler = new RequestHandler(inputStream, outputStream, staticResourcePath);
                    handler.handle();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.setPort(3030);
        server.setStaticResourcePath("src/main/resources/webapp");
        server.start();
    }
}
