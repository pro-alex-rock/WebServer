package webserver.repository;

import webserver.model.Request;

import java.io.InputStream;

/**
 * @author Oleksandr Haleta
 * 2021
 */
public interface Parser {
    Request parse(InputStream inputStream);
}
