package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkstationTest {

    private Worker mockWorker;
    private RepairOrder mockOrder;
    private Workshop mockWorkshop;
    private Workstation workstation;

    @BeforeEach
    void setUp() {
        mockWorker = mock(Worker.class);
        mockOrder = mock(RepairOrder.class);
        mockWorkshop = mock(Workshop.class);
        workstation = new Workstation(mockWorker);
    }

    @Test
    void testWorkstationIsAvailableByDefault() {
        assertTrue(workstation.isAvailable());
        assertFalse(workstation.isBusy());
    }

    @Test
    void testSetAndGetWorkshop() {
        workstation.setWorkshop(mockWorkshop);
        assertEquals(mockWorkshop, workstation.getWorkshop());
    }

    @Test
    void testAcceptOrderMarksAsBusyAndCallsWorker() {
        workstation.acceptOrder(mockOrder);

        assertTrue(workstation.isBusy());
        assertEquals(mockOrder, workstation.getRepairOrder());

        verify(mockWorker).performRepair(mockOrder);
    }

    @Test
    void testSetBusy() {
        workstation.setBusy(true);
        assertTrue(workstation.isBusy());

        workstation.setBusy(false);
        assertFalse(workstation.isBusy());
    }
}
