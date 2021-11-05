package webserver.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Oleksandr Haleta
 * 2021
 */
class HttpResponseWriterTest {
    ResponseWriter writer = new HttpResponseWriter();
    OutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = Mockito.mock(OutputStream.class);
    }

    @Test
    void shouldThrowNPExceptionCauseOutputStreamNullWriteSuccessResponse() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            writer.writeSuccessResponse(null, "content");
        });
        assertTrue(exception.getMessage().contains("The outputStream cannot be null in 200method."));
    }

    @Test
    void shouldThrowNPExceptionCauseContentNullWriteSuccessResponse() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            writer.writeSuccessResponse(outputStream, null);
        });
        assertTrue(exception.getMessage().contains("The content cannot be null."));
    }

    @Test
    void shouldThrowNPExceptionCauseOutputStreamNullWriteNotFoundResponse() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            writer.writeNotFoundResponse(null);
        });
        assertTrue(exception.getMessage().contains("The outputStream cannot be null in 404method."));
    }
}