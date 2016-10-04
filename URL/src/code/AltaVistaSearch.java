package code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class AltaVistaSearch {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://www.altavista.com/search");
		// Send request
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		PrintWriter out = new PrintWriter(connection.getOutputStream());
		String query = URLEncoder.encode("Svetlin Nakov", "UTF-8");
		out.println("q=" + query);
		out.close();
		// Retrieve response
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = in.readLine()) != null)
			System.out.println(line);
		in.close();
	}
}
