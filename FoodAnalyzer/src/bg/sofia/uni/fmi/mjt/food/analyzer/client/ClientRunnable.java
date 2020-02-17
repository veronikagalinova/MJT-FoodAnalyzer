package bg.sofia.uni.fmi.mjt.food.analyzer.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientRunnable implements Runnable {

	private Socket socket;

	public ClientRunnable(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			while (true) {
				String serverResponse = reader.readLine();
				if ("disconnected".equals(serverResponse)) {
					closeConnection();
					return;
				}
				System.out.println(serverResponse);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage() + " exception in ClientRunnable");
		}
	}
	
	private void closeConnection() {
		System.out.println("disconnected from server");
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("Error occured while closing client socket!");
		}
	}

}
