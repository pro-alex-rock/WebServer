package webserver.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ResourceReaderTest {
    ResourceReader resourceReader = new ResourceReader("src/test/java/webserver/repository");

    @Test
    public void shouldCompareContentFileAndTrueInReadContent() throws IOException {
        String expected = "Test content";
        String actual = resourceReader.readContent("test.txt");
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowNPExceptionCauseUriNullInReadContent() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            resourceReader.readContent(null);
        });
        assertEquals("The uri cannot be null in readContent method.", exception.getMessage());
    }
}