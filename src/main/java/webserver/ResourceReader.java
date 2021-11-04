package webserver;

import java.io.*;

public class ResourceReader {
    private final String staticResourcePath;

    public ResourceReader(String staticResourcePath) {
        this.staticResourcePath = staticResourcePath;
    }

    public String readContent(String uri) {
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
