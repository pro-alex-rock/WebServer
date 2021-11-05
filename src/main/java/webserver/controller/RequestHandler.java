package webserver.controller;

import webserver.model.Request;
import webserver.repository.DataReader;
import webserver.service.Parser;
import webserver.service.RequestParser;
import webserver.repository.ResourceReader;
import webserver.service.HttpResponseWriter;
import webserver.service.ResponseWriter;

import java.io.InputStream;
import java.io.OutputStream;


public class RequestHandler implements Handler {
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final Parser parser;
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
        DataReader resourceReader = new ResourceReader(staticResourcePath);
        ResponseWriter responseWriter = new HttpResponseWriter();
        try {
            String content = resourceReader.readContent(request.getUri());
            responseWriter.writeSuccessResponse(outputStream, content);
        } catch (Exception e) {
            responseWriter.writeNotFoundResponse(outputStream);
            throw new RuntimeException("Unable to send content", e);
        }
    }
}
