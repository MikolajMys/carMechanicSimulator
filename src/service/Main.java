package service;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Worker anna = new Worker("Anna");
        Worker tom = new Worker("Tom");

        Workstation ws1 = new Workstation(anna);
        Workstation ws2 = new Workstation(tom);

        tom.setWorkstation(ws1);
        anna.setWorkstation(ws2);

        List<Workstation> workstations = new ArrayList<>();
        workstations.add(ws1);
        workstations.add(ws2);

        Workshop workshop = new Workshop(workstations);
        for (Workstation ws : workstations) {
            ws.setWorkshop(workshop);
        }

        Office office = new Office(workshop);

        Client client1 = new Client("Jan");
        Client client2 = new Client("Maria");
        Client client3 = new Client("Zofia");
        Client client4 = new Client("Adam");

        RepairOrder order1 = new RepairOrder(client1, "Oil change");
        RepairOrder order2 = new RepairOrder(client2, "Brake replacement");
        RepairOrder order3 = new RepairOrder(client3, "I DONT KNOW");
        RepairOrder order4 = new RepairOrder(client4, "Engine noise");

        office.receiveOrder(order1);
        office.receiveOrder(order2);
        office.receiveOrder(order3);
        office.receiveOrder(order4);

        anna.finishJob();

        office.tryAssignWaitingOrder(ws1);

        tom.finishJob();

        anna.finishJob();
    }
}
