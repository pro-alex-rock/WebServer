package webserver.io;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import webserver.service.RequestParser;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Oleksandr Haleta
 * 2021
 */
class RequestParserTest {

    InputStream inputStream = Mockito.mock(InputStream.class);
    RequestParser requestParser = new RequestParser();

    @Test
    void shouldThrowIOExceptionCauseInputStreamNullParse() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            requestParser.parse(null);
        });
        assertEquals("The inputStream cannot be null in parse method.", exception.getMessage());
    }
}