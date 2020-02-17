package bg.sofia.uni.fmi.mjt.food.analyzer.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static bg.sofia.uni.fmi.mjt.food.analyzer.Constants.*;

public class FoodAnalyzerClient {

	private PrintWriter writer;
	

	public static void main(String[] args) {
		new FoodAnalyzerClient().start();
	}

	public void start() {
		try (Scanner scanner = new Scanner(System.in)) {
			connect(HOST,PORT);
			while (true) {
				String input = scanner.nextLine();
				String[] tokens = input.split(" ");
				String command = tokens[0];
				
				if (command.equals(COMMAND_FOR_DISCONNECT)) {
					writer.println(input);
					break;
				} else if (command.equals(COMMAND_FOR_FOOD_DETAILS)
						|| command.equals(COMMAND_FOR_FOOD_REPORT)
						|| command.equals(COMMAND_FOR_FOOD_DETAILS_BY_BARCODE)) {
					if (tokens.length < 2) {
						System.out.println("Not enough arguments for command " + command);
					} else {
						writer.println(input);
					}
					
				} else {
					System.out.println("Invalid command!");
				}

			}
		}
	}

	private void connect(String host, int port) {
		Socket socket = null;
		// This socket is closed in ClientRunnable when user is disconnected
		
		try {
			socket = new Socket(host, port);
			writer = new PrintWriter(socket.getOutputStream(), true);

			System.out.printf("connected to server running on localhost:%d%n", PORT);

			ClientRunnable clientRunnable = new ClientRunnable(socket);
			new Thread(clientRunnable).start();
		} catch (IOException e) {
			System.out.println("=> cannot connect to server on localhost:" 
					+ port + ", make sure that the server is started");
		} 
	}
	
}
