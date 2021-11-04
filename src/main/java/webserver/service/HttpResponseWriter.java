package webserver.service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HttpResponseWriter implements ResponseWriter {

    @Override
    public void writeSuccessResponse(OutputStream outputStream, String content) {
        try {
            outputStream.write("HTTP/1.1 200 OK\n".getBytes(StandardCharsets.UTF_8));
            outputStream.write("\n".getBytes());
            outputStream.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void writeNotFoundResponse(OutputStream outputStream) {
        try {
            outputStream.write("HTTP/1.1 404 Not Found\n".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Unable to send 404 message", e);
        }
    }
}
