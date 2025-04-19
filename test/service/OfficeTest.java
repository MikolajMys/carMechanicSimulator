package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OfficeTest {

    private Office office;
    private Workshop mockWorkshop;
    private RepairOrder mockRepairOrder;
    private Workstation mockWorkstation;

    @BeforeEach
    void setUp() {
        mockWorkshop = mock(Workshop.class);
        office = new Office(mockWorkshop);
        mockRepairOrder = mock(RepairOrder.class);
        mockWorkstation = mock(Workstation.class);
    }

    @Test
    void testReceiveOrderWhenWorkstationAvailable() {
        when(mockRepairOrder.getDescription()).thenReturn("Oil change");
        when(mockWorkshop.assignOrder(mockRepairOrder)).thenReturn(true);

        office.receiveOrder(mockRepairOrder);

        verify(mockWorkshop).assignOrder(mockRepairOrder);
    }

    @Test
    void testReceiveOrderWhenNoWorkstationAvailable() {
        when(mockRepairOrder.getDescription()).thenReturn("Brake replacement");
        when(mockWorkshop.assignOrder(mockRepairOrder)).thenReturn(false);

        office.receiveOrder(mockRepairOrder);

        verify(mockWorkshop).assignOrder(mockRepairOrder);
        verify(mockRepairOrder).setRepairType(any(RepairType.class));
    }

    @Test
    void testReceiveOrderWithUnknownRepairType() {
        when(mockRepairOrder.getDescription()).thenReturn("Unknown repair");
        when(mockWorkshop.assignOrder(mockRepairOrder)).thenReturn(false);

        office.receiveOrder(mockRepairOrder);

        verify(mockRepairOrder).setRepairType(any(RepairType.class));
    }

    @Test
    void testTryAssignWaitingOrderWhenQueueNotEmpty() {
        when(mockWorkstation.isAvailable()).thenReturn(true);

        office.receiveOrder(mockRepairOrder);

        office.tryAssignWaitingOrder(mockWorkstation);

        verify(mockWorkstation).acceptOrder(mockRepairOrder);
    }

    @Test
    void testTryAssignWaitingOrderWhenQueueEmpty() {
        office.tryAssignWaitingOrder(mockWorkstation);

        verify(mockWorkstation, never()).acceptOrder(any(RepairOrder.class));
    }

    @Test
    void testRepairCatalogInitialization() {
        RepairOrder mockOrder = mock(RepairOrder.class);

        when(mockOrder.getDescription()).thenReturn("Oil change");
        office.receiveOrder(mockOrder);

        verify(mockOrder).setRepairType(any(RepairType.class));
    }

    @Test
    void testReceiveOrderSetsRepairType() {
        when(mockRepairOrder.getDescription()).thenReturn("Flat tire");

        office.receiveOrder(mockRepairOrder);

        verify(mockRepairOrder).setRepairType(any(RepairType.class));
    }
}
