package webserver;

import webserver.model.Request;

import java.io.InputStream;
import java.io.OutputStream;

/*public class RequestHandler {
    private BufferedReader reader;
    private BufferedWriter writer;

    public RequestHandler(BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void handle() {
    }
}*/

public class RequestHandler {
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final RequestParser parser;
    private String staticResourcePath = "src/main/resources/webapp";

    public RequestHandler(InputStream inputStream, OutputStream outputStream, String staticResourcePath) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.staticResourcePath = staticResourcePath;
        parser = new RequestParser();
    }

    public void handle() {
        Request request = parser.parse(inputStream);
        ResourceReader resourceReader = new ResourceReader(staticResourcePath);
        ResponseWriter responseWriter = new ResponseWriter();
        try {
            String content = resourceReader.readContent(request.getUri()); //content of file, for example internal data of index.html or style.css
            responseWriter.writeSuccessResponse(outputStream, content);
        } catch (Exception e) {
            responseWriter.writeNotFoundResponse(outputStream);
        }

    }
}
