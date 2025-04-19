package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client("John Doe");
    }

    @Test
    void testClientName() {
        assertEquals("John Doe", client.getName());
    }

    @Test
    void testClientNameNotNull() {
        assertNotNull(client.getName());
    }
}
