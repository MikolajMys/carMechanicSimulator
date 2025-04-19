package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkshopTest {

    private Workshop workshop;
    private Workstation availableStation;
    private Workstation busyStation;
    private RepairOrder mockOrder;

    @BeforeEach
    void setUp() {
        availableStation = mock(Workstation.class);
        busyStation = mock(Workstation.class);
        mockOrder = mock(RepairOrder.class);
    }

    @Test
    void shouldAssignOrderToFirstAvailableWorkstation() {
        when(availableStation.isAvailable()).thenReturn(true);

        List<Workstation> stations = List.of(availableStation);
        workshop = new Workshop(stations);

        boolean result = workshop.assignOrder(mockOrder);

        assertTrue(result);
        verify(availableStation).acceptOrder(mockOrder);
    }

    @Test
    void shouldNotAssignOrderIfNoWorkstationIsAvailable() {
        when(busyStation.isAvailable()).thenReturn(false);

        List<Workstation> stations = List.of(busyStation);
        workshop = new Workshop(stations);

        boolean result = workshop.assignOrder(mockOrder);

        assertFalse(result);
        verify(busyStation, never()).acceptOrder(any());
    }

    @Test
    void shouldAssignToFirstAvailableEvenIfOtherIsBusy() {
        when(availableStation.isAvailable()).thenReturn(true);
        when(busyStation.isAvailable()).thenReturn(false);

        List<Workstation> stations = Arrays.asList(busyStation, availableStation);
        workshop = new Workshop(stations);

        boolean result = workshop.assignOrder(mockOrder);

        assertTrue(result);
        verify(availableStation).acceptOrder(mockOrder);
        verify(busyStation, never()).acceptOrder(any());
    }

    @Test
    void shouldReturnCorrectOfficeReference() {
        workshop = new Workshop(List.of());
        Office office = mock(Office.class);
        workshop.setOffice(office);

        assertEquals(office, workshop.getOffice());
    }

    @Test
    void shouldReturnWorkstationsList() {
        List<Workstation> stations = List.of(availableStation, busyStation);
        workshop = new Workshop(stations);

        assertEquals(stations, workshop.getWorkstations());
    }
}
