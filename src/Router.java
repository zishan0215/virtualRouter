
/**
 * Created by Zishan Ahmad on 8/11/2015.
 */
public class Router {
    private String name;
    private RoutingTable routingTableA;
    private RoutingTable routingTableB;
    private RoutingTable routingTableC;
    private int[] classValues = {8, 16, 24};

    public Router(String n) {
        routingTableA = new RoutingTable(n);
        routingTableB = new RoutingTable(n);
        routingTableC = new RoutingTable(n);
        name = n;
    }

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public RoutingTable getRoutingTableA() {
        return routingTableA;
    }

    public RoutingTable getRoutingTableB() {
        return routingTableB;
    }

    public RoutingTable getRoutingTableC() {
        return routingTableC;
    }

    public void receivePacket(Packet packet) {
        int packetClass = packet.getPacketClass();
        if (packetClass == -1) {
            System.out.println("Packet Discarded!");
        } else {
            String[] temp = packet.getDip().split("\\.");
            String netIp = "";
            if (packetClass == 0) {
                // Class A
                netIp += temp[0];
                netIp += ".0.0.0";
                int index = routingTableA.findDestinationIP(netIp);
                if (index == -1) {
                    System.out.println("Network IP not found. Discard or route to default IP.");
                } else {
                    System.out.print("Route packet to: " + routingTableA.getNextHopAddress(index));
                    System.out.println(" through interface: " + routingTableA.getInterface(index));
                }
            } else if (packetClass == 1) {
                // Class B
                netIp += temp[0] + "." + temp[1];
                netIp += ".0.0";
                int index = routingTableB.findDestinationIP(netIp);
                if (index == -1) {
                    System.out.println("Network IP not found. Discard or route to default IP.");
                } else {
                    System.out.print("Route packet to: " + routingTableB.getNextHopAddress(index));
                    System.out.println(" through interface: " + routingTableB.getInterface(index));
                }
            } else {
                // Class C
                netIp += temp[0] + "." + temp[1] + "." + temp[2];
                netIp += ".0";
                int index = routingTableC.findDestinationIP(netIp);
                if (index == -1) {
                    System.out.println("Network IP not found. Discard or route to default IP.");
                } else {
                    System.out.print("Route packet to: " + routingTableC.getNextHopAddress(index));
                    System.out.println(" through interface: " + routingTableC.getInterface(index));
                }
            }
        }
    }
}
