package service;

import java.util.function.Consumer;

public class Worker {
    private final String name;
    private Workstation workstation;
    public Consumer<Workstation> onFinishedJob;

    public Worker(String name) {
        this.name = name;
    }

    public void setWorkstation(Workstation workstation) {
        this.workstation = workstation;
    }

    public void performRepair(RepairOrder order) {
        System.out.println(name + " is repairing: " + order.getRepairType() +
                " for client: " + order.getClient().getName());
    }

    public void finishJob() {
        System.out.println("test");
        if (workstation != null && workstation.isBusy()) {
            workstation.setBusy(false);
            System.out.println(name + " has finished the repair.");

            if (onFinishedJob != null) {
                onFinishedJob.accept(workstation);
            }
        } else if (workstation != null && workstation.isAvailable()) {
            System.out.println(name + " is already chillin.");
        }
    }

    public String getName() {
        return name;
    }

    public Workstation getWorkstation() {
        return workstation;
    }
}