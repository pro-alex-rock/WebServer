package webserver.service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HttpResponseWriter implements ResponseWriter {

    @Override
    public void writeSuccessResponse(OutputStream outputStream, String content) {
        if (outputStream == null) {
            throw new NullPointerException("The outputStream cannot be null in 200method.");
        }
        if (content == null) {
            throw  new NullPointerException("The content cannot be null.");
        }
        try {
            outputStream.write("HTTP/1.1 200 OK\n".getBytes(StandardCharsets.UTF_8));
            outputStream.write("\n".getBytes());
            outputStream.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Unable to send content", e);
        }

    }

    @Override
    public void writeNotFoundResponse(OutputStream outputStream) {
        if (outputStream == null) {
            throw new NullPointerException("The outputStream cannot be null in 404method.");
        }
        try {
            outputStream.write("HTTP/1.1 404 Not Found\n".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Unable to send 404 message", e);
        }
    }
}
