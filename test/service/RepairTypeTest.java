package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepairTypeTest {

    @Test
    void testGetName() {
        RepairType repairType = new RepairType("Oil Change", 99.99, 30);
        assertEquals("Oil Change", repairType.getName());
    }

    @Test
    void testGetPrice() {
        RepairType repairType = new RepairType("Oil Change", 99.99, 30);
        assertEquals(99.99, repairType.getPrice());
    }

    @Test
    void testGetEstimatedMinutes() {
        RepairType repairType = new RepairType("Oil Change", 99.99, 30);
        assertEquals(30, repairType.getEstimatedMinutes());
    }

    @Test
    void testToString() {
        RepairType repairType = new RepairType("Oil Change", 99.99, 30);
        String expected = "Oil Change ($99.99, 30 min)";
        assertEquals(expected, repairType.toString());
    }
}
