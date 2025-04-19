package service;

public class RepairOrder {
    private final Client client;
    private final String description;
    private RepairType repairType;

    public RepairOrder(Client client, String description) {
        this.client = client;
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public String getDescription() {
        return description;
    }

    public RepairType getRepairType() {
        return repairType;
    }

    public void setRepairType(RepairType repairType) {
        this.repairType = repairType;
    }

    @Override
    public String toString() {
        return description + " for client: " + client.getName() +
                (repairType != null ? " [" + repairType + "]" : "");
    }
}