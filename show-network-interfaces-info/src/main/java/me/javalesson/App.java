package me.javalesson;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

import static java.lang.System.out;

/**
 * call as:
 * $App
 * will show network interfaces informaion similar to "ip addr"
 */
public class App {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface oneInterface = interfaces.nextElement();
            out.printf("%s:%n", oneInterface.getName());
            List<InterfaceAddress> addresses = oneInterface.getInterfaceAddresses();
            for (InterfaceAddress address : addresses) {
                out.println(address);
            }
            out.println();
        }
    }
}
