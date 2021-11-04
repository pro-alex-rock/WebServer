package webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private String staticResourcePath = "src/main/resources/webapp";

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
            try(Socket socket = serverSocket.accept(); // TODO every socket open in new thread
                InputStream inputStream = new BufferedInputStream(socket.getInputStream());
                OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream())) {
                print(socket); //tempo method
                RequestHandler handler = new RequestHandler(inputStream, outputStream, staticResourcePath);
                handler.handle();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void print(Socket socket) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String line = "";
            while (!(line = bufferedReader.readLine()).isEmpty()) {
                System.out.println(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
