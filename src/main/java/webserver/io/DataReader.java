package webserver.io;

import java.io.IOException;

/**
 * @author Oleksandr Haleta
 * 2021
 */
public interface DataReader {
    String readContent(String uri) throws IOException;
}
