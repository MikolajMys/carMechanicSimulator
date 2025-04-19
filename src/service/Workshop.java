package service;

import java.util.List;

public class Workshop {
    private final List<Workstation> workstations;
    private Office office;

    public Workshop(List<Workstation> workstations) {
        this.workstations = workstations;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public boolean assignOrder(RepairOrder order) {
        for (Workstation workstation : workstations) {
            if (workstation.isAvailable()) {
                workstation.acceptOrder(order);
                return true;
            }
        }
        return false;
    }

    public Office getOffice() {
        return office;
    }

    public List<Workstation> getWorkstations() {
        return workstations;
    }
}