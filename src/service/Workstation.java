package service;

public class Workstation {
    private final Worker worker;
    private boolean busy = false;
    private Workshop workshop;
    private RepairOrder currentOrder;

    public Workstation(Worker worker) {
        this.worker = worker;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public boolean isAvailable() {
        return !busy;
    }

    public boolean isBusy() {
        return busy;
    }

    public void acceptOrder(RepairOrder order) {
        this.currentOrder = order;
        busy = true;
        worker.performRepair(order);
    }

    public RepairOrder getRepairOrder() {
        return currentOrder;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
