import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by Zishan Ahmad on 8/11/2015.
 */
public class RoutingTable {
    private ArrayList<InetAddress> destination;
    private ArrayList<InetAddress> nextHop;
    private ArrayList<Integer> itf;
    private String name;

    public RoutingTable(String n) {
        destination = new ArrayList<InetAddress>();
        nextHop = new ArrayList<InetAddress>();
        itf = new ArrayList<Integer>();
        name = n;
    }

    public void addDestination(String ip) {
        try {
            destination.add(InetAddress.getByName(ip));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public String getDestinationAddress(int index) {
        return destination.get(index).getHostAddress();
    }

    public String getDestinationName(int index) {
        return destination.get(index).getHostName();
    }

    public void addNextHop(String ip) {
        try {
            nextHop.add(InetAddress.getByName(ip));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public String getNextHopAddress(int index) {
        return nextHop.get(index).getHostAddress();
    }

    public String getNextHopName(int index) {
        return nextHop.get(index).getHostName();
    }

    public void addInterface(int i) {
        itf.add(i);
    }

    public int getInterface(int index) {
        return itf.get(index);
    }

    public int findDestinationIP(String ip) {
        for(int i=0; i<destination.size(); i++) {
            if(getDestinationAddress(i).equals(ip)) {
                return i;
            }
        }
        return -1;
    }

    public void printRoutingTable() {
        System.out.println("Routing Table for router: " + name);
        System.out.println("-------------------------------------------------");
        System.out.println("|   Destination   |     Next Hop    | Interface |");
        System.out.print("-------------------------------------------------");
        System.out.println("");
        for (int i=0; i<destination.size(); i++) {
            System.out.print("| " + getDestinationAddress(i) + " | ");
            System.out.print(getNextHopAddress(i) + " |     ");
            System.out.println(getInterface(i) + "     | ");
        }
        System.out.println("-------------------------------------------------");
    }
}
