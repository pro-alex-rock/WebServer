package webserver.io;

import java.io.*;
//TODO write image reader

public class ResourceReader implements DataReader {
    private final String staticResourcePath;

    public ResourceReader(String staticResourcePath) {
        this.staticResourcePath = staticResourcePath;
    }

    @Override
    public String readContent(String uri) throws IOException {
        if (uri == null) {
            throw new NullPointerException("The uri cannot be null in readContent method.");
        }
        StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(new File(staticResourcePath, uri)));
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        return builder.toString();
    }
}
