package webserver.repository;

import java.io.*;

public class ResourceReader implements DataReader {
    private final String staticResourcePath;

    public ResourceReader(String staticResourcePath) {
        this.staticResourcePath = staticResourcePath;
    }

    @Override
    public String readContent(String uri) {
        if (uri == null) {
            throw new NullPointerException("The uri cannot be null in readContent method.");
        }
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(staticResourcePath, uri)));
            String line = null;
            while ((line = reader.readLine()) != null) { //while (!(line = reader.readLine()).isEmpty()) generates an Exception
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
