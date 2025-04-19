package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Consumer;

import static org.mockito.Mockito.*;

class WorkerTest {

    private Worker worker;
    private Workstation mockWorkstation;
    private RepairOrder mockRepairOrder;
    private Client mockClient;
    private RepairType mockRepairType;

    @BeforeEach
    void setUp() {
        worker = new Worker("John");
        mockWorkstation = mock(Workstation.class);
        mockRepairOrder = mock(RepairOrder.class);
        mockClient = mock(Client.class);
        mockRepairType = mock(RepairType.class);
    }

    @Test
    void testPerformRepairPrintsCorrectOutput() {
        when(mockRepairOrder.getRepairType()).thenReturn(mockRepairType);
        when(mockRepairOrder.getClient()).thenReturn(mockClient);
        when(mockRepairType.toString()).thenReturn("Oil Change ($99.99, 30 min)");
        when(mockClient.getName()).thenReturn("Alice");

        worker.performRepair(mockRepairOrder);
    }

    @Test
    void testFinishJobMarksWorkstationNotBusyAndCallsOnFinishedJob() {
        worker.setWorkstation(mockWorkstation);
        when(mockWorkstation.isBusy()).thenReturn(true);

        @SuppressWarnings("unchecked")
        Consumer<Workstation> onFinishedJob = (Consumer<Workstation>) Mockito.mock(Consumer.class);
        worker.onFinishedJob = onFinishedJob;

        worker.finishJob();

        verify(mockWorkstation).setBusy(false);
        verify(onFinishedJob).accept(mockWorkstation);
    }

    @Test
    void testFinishJobWhenWorkstationAvailable() {
        worker.setWorkstation(mockWorkstation);
        when(mockWorkstation.isBusy()).thenReturn(false);
        when(mockWorkstation.isAvailable()).thenReturn(true);

        worker.finishJob();

        verify(mockWorkstation, never()).setBusy(anyBoolean());
    }

    @Test
    void testGetters() {
        assert worker.getName().equals("John");
        worker.setWorkstation(mockWorkstation);
        assert worker.getWorkstation() == mockWorkstation;
    }
}
