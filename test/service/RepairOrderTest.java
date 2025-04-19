package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RepairOrderTest {

    private Client mockClient;
    private RepairType mockRepairType;
    private RepairOrder repairOrder;

    @BeforeEach
    void setUp() {
        mockClient = mock(Client.class);
        mockRepairType = mock(RepairType.class);
        repairOrder = new RepairOrder(mockClient, "Engine repair");
    }

    @Test
    void testGetClient() {
        when(mockClient.getName()).thenReturn("Alice");
        assertEquals(mockClient, repairOrder.getClient());
    }

    @Test
    void testGetDescription() {
        assertEquals("Engine repair", repairOrder.getDescription());
    }

    @Test
    void testGetRepairTypeInitiallyNull() {
        assertNull(repairOrder.getRepairType());
    }

    @Test
    void testSetRepairType() {
        when(mockRepairType.toString()).thenReturn("Oil Change ($99.99, 30 min)");
        repairOrder.setRepairType(mockRepairType);
        assertEquals(mockRepairType, repairOrder.getRepairType());
    }

    @Test
    void testToStringWithRepairType() {
        when(mockClient.getName()).thenReturn("Alice");
        when(mockRepairType.toString()).thenReturn("Oil Change ($99.99, 30 min)");
        repairOrder.setRepairType(mockRepairType);

        String expected = "Engine repair for client: Alice [Oil Change ($99.99, 30 min)]";
        assertEquals(expected, repairOrder.toString());
    }

    @Test
    void testToStringWithoutRepairType() {
        when(mockClient.getName()).thenReturn("Alice");

        String expected = "Engine repair for client: Alice";
        assertEquals(expected, repairOrder.toString());
    }
}
