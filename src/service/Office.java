package service;

import java.util.*;

public class Office {
    private final Workshop workshop;
    private final Map<String, RepairType> repairCatalog;
    private final Queue<RepairOrder> waitingOrders = new LinkedList<>();

    public Office(Workshop workshop) {
        this.workshop = workshop;
        this.workshop.setOffice(this);

        this.repairCatalog = new HashMap<>();

        repairCatalog.put("Oil change", new RepairType("Oil change", 100.0, 30));
        repairCatalog.put("Brake replacement", new RepairType("Brake replacement", 250.0, 90));
        repairCatalog.put("Flat tire", new RepairType("Flat tire", 50.0, 20));
        repairCatalog.put("Engine noise", new RepairType("Engine noise", 300.0, 120));
    }

    public void receiveOrder(RepairOrder order) {
        RepairType type = repairCatalog.getOrDefault(
                order.getDescription(),
                new RepairType("Other", 0.0, -1)
        );
        order.setRepairType(type);
        boolean assigned = workshop.assignOrder(order);

        if (!assigned) {
            System.out.println("No available workstations. Order is added to waiting list.");
            waitingOrders.offer(order);
        }
    }

    public void tryAssignWaitingOrder(Workstation workstation) {
        if (!waitingOrders.isEmpty()) {
            RepairOrder nextOrder = waitingOrders.poll();
            System.out.println("Assigning waiting order to freed workstation.");
            workstation.acceptOrder(nextOrder);
        }
    }
}