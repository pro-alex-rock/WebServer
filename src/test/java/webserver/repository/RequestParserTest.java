package webserver.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import webserver.service.RequestParser;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oleksandr Haleta
 * 2021
 */
class RequestParserTest {

    InputStream inputStream;
    RequestParser requestParser = new RequestParser();

    @BeforeEach
    void setUp() {
        inputStream = Mockito.mock(InputStream.class);
    }

    @Test
    void shouldThrowIOExceptionCauseInputStreamNullParse() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            requestParser.parse(null);
        });
        assertEquals("The inputStream cannot be null in parse method.", exception.getMessage());
    }
}