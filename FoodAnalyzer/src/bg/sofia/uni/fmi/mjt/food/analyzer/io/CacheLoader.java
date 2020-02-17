package bg.sofia.uni.fmi.mjt.food.analyzer.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import bg.sofia.uni.fmi.mjt.food.analyzer.dto.details.Product;
import bg.sofia.uni.fmi.mjt.food.analyzer.dto.details.ProductDetails;
import bg.sofia.uni.fmi.mjt.food.analyzer.dto.details.ProductList;

import static bg.sofia.uni.fmi.mjt.food.analyzer.Constants.*;

public final class CacheLoader {
	
	private static final String KEY_VALUE_DELIMETER = "#";
	
	private static final String PRODUCT_FIELDS_DELIMETER = "/";
	
	private static final String NAME_OF_FIELD_DELIMETER = ":";
	
	private CacheLoader() {
		// Utility class. Static methods only
	}
	
	public static void loadCache(Map<String, ProductDetails> cache) {
		List<String> result = new ArrayList<>();
		try (Stream<String> lines = Files.lines(Paths.get(PATH_TO_PRODUCT_CACHE))) {
			result = lines.collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Could not read " + PATH_TO_PRODUCT_CACHE);
		}

		List<Product> list = new ArrayList<>();
		result.forEach(s -> {
			String[] tokens = s.split(KEY_VALUE_DELIMETER);
			String product = tokens[1];
			String foodName = tokens[0];
			String[] productParams = product.split(PRODUCT_FIELDS_DELIMETER);
			String name = productParams[0].split("Name: ")[1].trim();
			String group = productParams[1].split(NAME_OF_FIELD_DELIMETER)[1].trim();
			String ndbno = productParams[2].split(NAME_OF_FIELD_DELIMETER)[1].trim();
			String manu = productParams[3].split(NAME_OF_FIELD_DELIMETER)[1].trim();
			Product p = new Product(foodName, group, name, ndbno, manu);
			list.add(p);
		});

		Map<String, List<Product>> map = list.stream().collect(Collectors.groupingBy(Product::getType));

		for (Map.Entry<String, List<Product>> entry : map.entrySet()) {
			ProductDetails details = new ProductDetails(new ProductList(entry.getKey(), entry.getValue()));
			cache.put(entry.getKey(), details);
		}
	}
	 
}
