package udpserver;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class UDPDateClient {
	public static final String DATE_SERVER = "localhost";
	public static final int DATE_PORT = 12345;
	public static final String DATE_REQUEST = "GET DATE";
	public static final int TIMEOUT_IN_SECONDS = 5;

	public static void main(String[] args) throws Exception {
		// Send request to the UDP Date Server
		DatagramSocket datagramSocket = new DatagramSocket();
		String request = DATE_REQUEST;
		byte[] requestBuf = request.getBytes();
		DatagramPacket packetOut = new DatagramPacket(requestBuf, requestBuf.length, InetAddress.getByName(DATE_SERVER),
				DATE_PORT);
		datagramSocket.send(packetOut);
		System.out.println("Sent date request to the server.");
		// Receive the server response
		byte[] responseBuf = new byte[256];
		DatagramPacket packetIn = new DatagramPacket(responseBuf, responseBuf.length);
		datagramSocket.setSoTimeout(TIMEOUT_IN_SECONDS * 1000);
		try {
			datagramSocket.receive(packetIn);
			String response = new String(responseBuf, 0, packetIn.getLength());
			System.out.println("Server response: " + response);
		} catch (SocketTimeoutException ste) {
			System.err.println("Timeout! No response received" + " in " + TIMEOUT_IN_SECONDS + " seconds.");
		}
		datagramSocket.close();
	}
}
