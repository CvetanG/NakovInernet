package app;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastListener {
	public static final String MULTICAST_GROUP_ADDRESS = "224.0.0.1";
	public static final int LISTENING_PORT = 2004;
	public static final int MAX_PACKET_SIZE = 1024;

	public static void main(String[] args) throws IOException {
		MulticastSocket multicastSocket = new MulticastSocket(LISTENING_PORT);
		InetAddress multicastGroupAddr = InetAddress.getByName(MULTICAST_GROUP_ADDRESS);
		multicastSocket.joinGroup(multicastGroupAddr);
		System.out.println("Joined to multicast group " + MULTICAST_GROUP_ADDRESS + ".");
		byte[] receiveBuf = new byte[MAX_PACKET_SIZE];
		DatagramPacket packet = new DatagramPacket(receiveBuf, receiveBuf.length);
		System.out.println("Listening for packets...");
		while (true) {
			multicastSocket.receive(packet);
			String packetAsString = new String(packet.getData(), 0, packet.getLength());
			System.out.println("Received packet from " + packet.getAddress().getHostAddress() + ":" + packet.getPort()
					+ " - " + packetAsString);
		}
	}
}
