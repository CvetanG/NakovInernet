package app;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSender {
	public static final String MULTICAST_GROUP_ADDRESS = "224.0.0.1";
	public static final int TARGET_PORT = 2004;

	public static void main(String[] args) throws Exception {
		InetAddress multicastGroupAddr = InetAddress.getByName(MULTICAST_GROUP_ADDRESS);
		MulticastSocket multicastSocket = new MulticastSocket();
		while (true) {
			String message = "Hello " + new java.util.Date();
			DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), multicastGroupAddr,
					TARGET_PORT);
			multicastSocket.send(packet);
			System.out.println("Sent UDP packet to " + MULTICAST_GROUP_ADDRESS + ":" + TARGET_PORT);
			Thread.sleep(1000);
		}
	}
}
