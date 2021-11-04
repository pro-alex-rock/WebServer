package webserver;

import webserver.model.HttpMethod;
import webserver.model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/*public class RequestParser {

    public Request parse(BufferedReader reader) {

    }

    private void injectUriAndMethod(String requestLine, Request request) {

    }

    private void injectHeaders(BufferedReader reader, Request request) {

    }
}*/

public class RequestParser {

    public Request parse(InputStream inputStream) {
        Request request = new Request();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String uri = reader.readLine();
            injectUriAndMethod(uri, request);
            injectHeaders(reader, request);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    private void injectUriAndMethod(String requestLine, Request request) {
        String[] head = requestLine.split("\\s");
        request.setMethod(HttpMethod.valueOf(head[0]));
        request.setUri(head[1]);
    }

    private void injectHeaders(BufferedReader reader, Request request) {
        Map<String, String> headers = new HashMap<>();
        try {
            //BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while (!(line = reader.readLine()).isEmpty()) {
                String[] headerLine = line.split(":\\s");
                headers.put(headerLine[0], headerLine[1]);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        request.setHeaders(headers);
    }
}
