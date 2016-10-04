package chatclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class NakovChatClient {
	public static final String SERVER_HOSTNAME = "localhost";
	public static String KEEP_ALIVE_MESSAGE = "!keep-alive";
	public static final int SERVER_PORT = 2002;
	private static BufferedReader mSocketReader;
	private static PrintWriter mSocketWriter;

	public static void main(String[] args) {
		// Connect to the chat server
		try {
			Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
			mSocketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			mSocketWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			System.out.println("Connected to server " + SERVER_HOSTNAME + ":" + SERVER_PORT);
		} catch (IOException ioe) {
			System.err.println("Can not connect to " + SERVER_HOSTNAME + ":" + SERVER_PORT);
			ioe.printStackTrace();
			System.exit(-1);
		}
		// Start socket --> console transmitter thread
		PrintWriter consoleWriter = new PrintWriter(System.out);
		TextDataTransmitter socketToConsoleTransmitter = new TextDataTransmitter(mSocketReader, consoleWriter);
		socketToConsoleTransmitter.setDaemon(false);
		socketToConsoleTransmitter.start();
		// Start console --> socket transmitter thread
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		TextDataTransmitter consoleToSocketTransmitter = new TextDataTransmitter(consoleReader, mSocketWriter);
		consoleToSocketTransmitter.setDaemon(false);
		consoleToSocketTransmitter.start();
	}
}

/**
 * Transmits text data from the given reader to given writer and runs as a
 * separete thread.
 */
class TextDataTransmitter extends Thread {
	private BufferedReader mReader;
	private PrintWriter mWriter;

	public TextDataTransmitter(BufferedReader aReader, PrintWriter aWriter) {
		mReader = aReader;
		mWriter = aWriter;
	}

	/**
	 * Until interrupted reads a text line from the reader and sends it to the
	 * writer.
	 */
	public void run() {
		try {
			while (!isInterrupted()) {
				String data = mReader.readLine();
				if (!data.equals(NakovChatClient.KEEP_ALIVE_MESSAGE)) {
					mWriter.println(data);
					mWriter.flush();
				}
			}
		} catch (IOException ioe) {
			System.err.println("Lost connection to server.");
			System.exit(-1);
		}
	}
}
