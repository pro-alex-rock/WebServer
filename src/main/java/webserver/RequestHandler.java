package webserver;

import webserver.model.Request;

import java.io.InputStream;
import java.io.OutputStream;


public class RequestHandler implements AbstractRequestHandler {
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final RequestParser parser;
    private final String staticResourcePath;

    public RequestHandler(InputStream inputStream, OutputStream outputStream, String staticResourcePath) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.staticResourcePath = staticResourcePath;
        parser = new RequestParser();
    }

    @Override
    public void handle() {
        Request request = parser.parse(inputStream);
        ResourceReader resourceReader = new ResourceReader(staticResourcePath);
        ResponseWriter responseWriter = new ResponseWriter();
        try {
            String content = resourceReader.readContent(request.getUri()); //content of file, for example internal data of index.html or style.css
            responseWriter.writeSuccessResponse(outputStream, content);
        } catch (Exception e) {
            responseWriter.writeNotFoundResponse(outputStream);
            throw new RuntimeException("Unable to send content", e);
        }
    }
}
