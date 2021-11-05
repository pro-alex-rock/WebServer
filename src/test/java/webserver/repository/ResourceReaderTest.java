package webserver.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ResourceReaderTest {
    ResourceReader resourceReader = new ResourceReader("src/test/java/webserver/repository");

    @BeforeEach
    void setUp() {
    }

    @Test
    public void shouldCompareContentFileAndTrueInReadContent() {
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