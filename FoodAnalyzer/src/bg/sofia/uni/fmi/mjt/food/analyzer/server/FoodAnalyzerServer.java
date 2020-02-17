package bg.sofia.uni.fmi.mjt.food.analyzer.server;

import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bg.sofia.uni.fmi.mjt.food.analyzer.dto.details.Product;
import bg.sofia.uni.fmi.mjt.food.analyzer.dto.details.ProductDetails;
import bg.sofia.uni.fmi.mjt.food.analyzer.io.CacheLoader;

import static bg.sofia.uni.fmi.mjt.food.analyzer.Constants.*;

public class FoodAnalyzerServer {
	
	private static ExecutorService executor = Executors.newFixedThreadPool(MAX_EXECUTOR_THREADS);
	
	private static Map<String,ProductDetails> productsCache = new ConcurrentHashMap<>();
	
	
	public static void main(String[] args) throws IOException {
		FoodAnalyzerServer server = new FoodAnalyzerServer();
		CacheLoader.loadCache(productsCache);
		server.start();
	}
	
	public void start() throws IOException {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			
			System.out.printf("server is running on localhost:%d%n", PORT);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("A client connected to server " + clientSocket.getInetAddress());

				ClientRequestHandler clientConnectionHandler = new ClientRequestHandler(clientSocket);
				executor.execute(clientConnectionHandler);
			}
		} 
	}
	
	public static void addNewProduct(String productName, ProductDetails productDetails) {
		if (productDetails.printDetails().equals(ProductDetails.NO_RESULT)) {
			return;
		}
		
		try (FileWriter cacheWriter = new FileWriter(PATH_TO_PRODUCT_CACHE,true)) {
			synchronized (cacheWriter) {
				productsCache.putIfAbsent(productName, productDetails);
				cacheWriter.write(productDetails.printDetails());
				System.out.println("successfully added " + productName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isProductInCache(String productName) {
		return productsCache.containsKey(productName);
	}
	
	public static ProductDetails getProductWithName(String productName) {
		return productsCache.get(productName);
	} 
	
	public static Product getFoodWithBarcode(String barcode) {
		Optional<ProductDetails> productDetailsWithBarcode = productsCache.entrySet().stream()
				.map(Map.Entry::getValue)
				.filter(details -> details.printDetails().contains(barcode)).findFirst();

		if (productDetailsWithBarcode.isPresent()) {
			List<Product> productList = productDetailsWithBarcode.get().getProductList().getItem();
			
			for (Product p : productList) {
				if (p.getUPC().equals(barcode)) {
					return p;
				}
			}
			
		}
		return null;
	}
	
}
