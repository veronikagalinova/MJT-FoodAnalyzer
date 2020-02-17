package bg.sofia.uni.fmi.mjt.food.analyzer.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bg.sofia.uni.fmi.mjt.food.analyzer.dto.details.Product;
import bg.sofia.uni.fmi.mjt.food.analyzer.dto.details.ProductDetails;
import bg.sofia.uni.fmi.mjt.food.analyzer.dto.report.Report;
import bg.sofia.uni.fmi.mjt.food.analyzer.http.HttpRequestsManager;
import bg.sofia.uni.fmi.mjt.food.analyzer.upc.decoder.UPCReader;

import static bg.sofia.uni.fmi.mjt.food.analyzer.Constants.*;

public class ClientRequestHandler implements Runnable {

	private Socket socket;

	public ClientRequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

			while (true) {
				String commandInput = reader.readLine();

				if (commandInput != null) {
					String[] tokens = commandInput.split("\\s+");

					String command = tokens[0];
					
					if (command.equals(COMMAND_FOR_FOOD_DETAILS)) {
						String product = tokens[1];
						String details = retrieveProductDetails(product);
						writer.println(details);
					
					} else if (command.equals(COMMAND_FOR_FOOD_REPORT)) {
						String ndbno = tokens[1];
						String report  = getFoodReport(ndbno);
						writer.println(report);
					
					} else if (command.equals(COMMAND_FOR_FOOD_DETAILS_BY_BARCODE)) {
						String productInfo = getProductWithUPC(commandInput);
						writer.println(productInfo);
					
					} else if (command.equals(COMMAND_FOR_DISCONNECT)) {
						writer.println("disconnected");
						break;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				System.out.println("Exception occured while closing client socket");
			}
		}

	}
	
	String getFoodReport(String ndbno) {
		Report report = HttpRequestsManager.getFoodReport(ndbno);
		StringBuilder reportFormatter = new StringBuilder();
		report.getFoods().forEach(p -> reportFormatter.append(p.getFood().toString()).append(System.lineSeparator()));
		return reportFormatter.toString();
	}

	String getProductWithUPC(String commandInput) {
		// can contain only --upc or both --upc and --img -> use only --upc
		// PASS FIRST UPC
		String upc = null;
		if (commandInput.contains("--upc")) {
			upc = commandInput.split("\\s+")[1].split("=")[1];
		} else {
			String pathToImg = commandInput.split("\\s+")[1].split("=")[1];
			File barcodeFile = new File(pathToImg);
			try {
				upc = UPCReader.decodeQRCode(barcodeFile);
			} catch (IOException e) {
				System.out.println("Could not decode barcode from file");
			}
		}
		Product result = FoodAnalyzerServer.getFoodWithBarcode(upc);
		return result == null ? "No results available!" : result.toString();
	}

	 String retrieveProductDetails(String product) {
		 ProductDetails details = null;
			if (FoodAnalyzerServer.isProductInCache(product)) {
				System.out.println("product is in cache");
				details = FoodAnalyzerServer.getProductWithName(product);
			} else {
				System.out.println("product is not in cache");
				details = HttpRequestsManager.getFoodDetails(product);
				FoodAnalyzerServer.addNewProduct(product, details);
			}

			return details.printDetails();
	}
}
