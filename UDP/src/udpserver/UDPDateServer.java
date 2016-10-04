package udpserver;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UDPDateServer {
	public static final int LISTENING_UDP_PORT = 12345;
	public static final String DATE_REQUEST = "GET DATE";
	public static final int RECEIVE_BUFFER_SIZE = 256;

	public static void main(String[] args) throws Exception {
		// Create UDP socket
		DatagramSocket datagramSocket = new DatagramSocket(LISTENING_UDP_PORT);
		System.out.println("UDP Date Server is listening " + "on port " + LISTENING_UDP_PORT);
		while (true) {
			// Receive UDP client request
			byte[] receiveBuf = new byte[RECEIVE_BUFFER_SIZE];
			DatagramPacket packetIn = new DatagramPacket(receiveBuf, receiveBuf.length);
			datagramSocket.receive(packetIn);
			String request = new String(receiveBuf, 0, packetIn.getLength());
			// Send response to the client
			if (request.equalsIgnoreCase(DATE_REQUEST)) {
				String response = new Date().toString();
				byte[] responseBuf = response.getBytes();
				InetAddress senderIP = packetIn.getAddress();
				int senderPort = packetIn.getPort();
				DatagramPacket packetOut = new DatagramPacket(responseBuf, responseBuf.length, senderIP, senderPort);
				datagramSocket.send(packetOut);
			}
		}
	}
}
