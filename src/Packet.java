import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Zishan Ahmad on 8/11/2015.
 */
public class Packet {
    private int version;
    private int headerLength;
    private int differentiatedServices;
    private int totalLength;
    private int identification;
    private int fragmentation;
//    private int offset;
    private int ttl;
    private int protocol;
    private int checksum;
    private InetAddress sip;
    private InetAddress dip;

    public Packet() {
        version = 4;
        headerLength = 5;
        differentiatedServices = 0;
        totalLength = 54;
        identification = 3;
        fragmentation = 5850;
        ttl = 20;
        protocol = 6;
        checksum = 0;
    }

    public void setSip(String ip) {
        try {
            sip = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void setDip(String ip) {
        try {
            dip = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public String getSip() {
        return sip.getHostAddress();
    }

    public InetAddress getDipInInet() {
        return dip;
    }

    public String getDip() {
        return dip.getHostAddress();
    }

    private String getPaddedString(int property, int size) {
        String ver = Integer.toBinaryString(property);
        if(ver.length() < size) {
            while(ver.length() < size) {
                ver = "0" + ver;
            }
        }
        return ver;
    }

    private String getIpInBinary(InetAddress ip) {
        String temp = ip.getHostAddress();
        String[] ind = temp.split("\\.");
        String s = "";
        for(String t: ind) {
            s += Integer.toBinaryString(Integer.parseInt(t));
        }
        return s;
    }

    private String getPaddedIpString(String ip) {
        String temp = ip;
        if(temp.length() < 32) {
            while(temp.length() < 32) {
                temp = "0" + temp;
            }
        }
        return temp;
    }

    public String getPaddedDip() {
        return getPaddedIpString(getIpInBinary(dip));
    }

    private String getLastFour(String ip) {
        return ip.substring(0,4);
    }

    public int getPacketClass() {
        String lastFour = getLastFour(getIpInBinary(dip));
        int num =  Integer.parseInt(lastFour, 2);
        if(num >= 0 && num <= 7) {
            return 0;
        } else if(num > 7 && num <= 11) {
            return 1;
        } else if(num > 11 && num <= 13) {
            return 2;
        } else {
            return -1;
        }
    }

    public String getPacket() {
        String byteString = "";
        byteString += getPaddedString(version, 4);
        byteString += " " + getPaddedString(headerLength, 4);
        byteString += " " + getPaddedString(differentiatedServices, 8);
        byteString += " " + getPaddedString(totalLength, 16);
        byteString += " " + getPaddedString(identification, 16);
        byteString += " " + getPaddedString(fragmentation, 16);
        byteString += " " + getPaddedString(ttl, 8);
        byteString += " " + getPaddedString(protocol, 8);
        byteString += " " + getPaddedString(checksum, 16);
        byteString += " " + getPaddedIpString(getIpInBinary(sip));
        byteString += " " + getPaddedIpString(getIpInBinary(dip));
        return byteString;
    }
}
