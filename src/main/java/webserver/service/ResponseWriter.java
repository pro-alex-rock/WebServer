package webserver.service;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Oleksandr Haleta
 * 2021
 */
public interface ResponseWriter {
    void writeSuccessResponse(OutputStream outputStream, String content);

    void writeNotFoundResponse(OutputStream outputStream) throws IOException;
}
