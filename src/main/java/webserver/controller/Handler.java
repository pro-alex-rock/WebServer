package webserver.controller;

import java.io.IOException;

public interface Handler {
    void handle() throws IOException;
}
