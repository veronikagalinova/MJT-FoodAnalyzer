package bg.sofia.uni.fmi.mjt.food.analyzer;

public final class Constants {
	
	public static final int PORT = 8080;
	
	public static final int MAX_EXECUTOR_THREADS = 20;
	
	public static final String PATH_TO_PRODUCT_CACHE = "resources/products-cache.txt";
	
	public static final String HOST = "localhost";
	
	public static final String COMMAND_FOR_DISCONNECT = "disconnect";
	
	public static final String COMMAND_FOR_FOOD_DETAILS = "get-food";
	
	public static final String COMMAND_FOR_FOOD_REPORT = "get-food-report";
	
	public static final String COMMAND_FOR_FOOD_DETAILS_BY_BARCODE = "get-food-by-barcode";
	
	private Constants() {
		// Utility class
	}
}
