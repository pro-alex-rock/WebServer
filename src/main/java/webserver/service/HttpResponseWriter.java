package webserver.service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HttpResponseWriter implements ResponseWriter {

    @Override
    public void writeSuccessResponse(OutputStream outputStream, String content) {
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
    public void writeNotFoundResponse(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new NullPointerException("The outputStream cannot be null.");
        }
            outputStream.write("HTTP/1.1 404 Not Found\n".getBytes(StandardCharsets.UTF_8));
    }
}
