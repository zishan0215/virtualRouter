
/**
 * Created by Zishan Ahmad on 8/11/2015.
 */
public class Main {
    public static void main(String[] args) {
        Router router = new Router("A");
        router.getRoutingTableA().addDestination("169.254.131.248");
        router.getRoutingTableA().addNextHop("145.254.106.234");
        router.getRoutingTableA().addInterface(2);
        router.getRoutingTableA().addDestination("137.254.131.248");
        router.getRoutingTableA().addNextHop("165.254.106.234");
        router.getRoutingTableA().addInterface(3);
        router.getRoutingTableA().addDestination("119.254.131.248");
        router.getRoutingTableA().addNextHop("125.254.106.234");
        router.getRoutingTableA().addInterface(1);
        router.getRoutingTableA().printRoutingTable();

        router.getRoutingTableB().addDestination("137.254.0.0");
        router.getRoutingTableB().addNextHop("145.254.106.234");
        router.getRoutingTableB().addInterface(2);
        router.getRoutingTableB().printRoutingTable();

        Packet packet = new Packet();
        packet.setDip("137.254.131.248");
        packet.setSip("169.254.131.248");

        System.out.println("Packet source is " + packet.getSip());
        System.out.println("Packet destination is " + packet.getDip());
        System.out.println("Packet: " + packet.getPacket());

        router.receivePacket(packet);
    }
}
